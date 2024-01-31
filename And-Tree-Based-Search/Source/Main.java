package Source;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;



import Source.Exceptions.CourseLabDoesNotExistException;
import Source.Exceptions.InputException;
import Source.Exceptions.PartAssignException;
import Source.Exceptions.SlotDoesNotExistException;


public class Main {

    private static final int NUMBER_OF_COMMAND_LINE_ARGS = 1 + 4 + 4;

    //the environment variable to be used throughout the search
    private static Env environment;

    private static TimeSlotStringMap timeSlotStringMap = new TimeSlotStringMap();
    private static Assign globalOrdering;

    //eval penalties given via command line arguments
    private static int penCourseMin;
    private static int penLabMin;
    private static int penNotPaired;
    private static int penSection;

    //eval weights to be given via command line arguments
    private static int wMinFilled;
    private static int wPref;
    private static int wPair;
    private static int wSecDiff;

    // Classes used by program to run the search process
    private static ParseFile fileParser;
    private static Eval eval;
    private static Constr constr;
    private static Validator validator;
    private static PriorityQueue<Assign> minHeap;

    // Turn on debug flag to print stuff to help debug
    private static boolean debug = false;
    // Second debug flag to track number of full solutions found
    private static boolean debug2 = true;
    // Toggle use of branch and bound here
    private static boolean branchAndBoundEnabled = false;
    // Counts how many valid solutions we found
    private static int numSolutionsFound = 0;
    // Toggle if partial eval being used
    private static boolean partialEvalEnabled = true;
    // Toggle if full eval being used
    private static boolean fullEvalEnabled = true;
    // Debug eval
    private static boolean debugFullEval = true;
    private static boolean debugPartialEval = false;
    // Toggle for cpsc813913 change
    private static boolean cpsc813913Flag = true;

    // Use Eval fast
    //private static boolean useEvalFast = false;
    // private static EvalFast evalFast;

    /**
     * Code.Main function.
     *
     * Command line argument order:
     *  String inputFilePath
     *  int wMinFilled
     *  int wPref
     *  int wPair
     *  int wSecDiff
     *
     * @param args The command line args.
     */
    public static void main(String[] args) {

        //making sure the correct number of command line args was given
        if (args.length != NUMBER_OF_COMMAND_LINE_ARGS) {

            System.err.println("The wrong number of command line arguments was given. Expected: " + NUMBER_OF_COMMAND_LINE_ARGS + " Given: " + args.length);
            System.exit(0);
        }

        try {
            // eval penalties
            penCourseMin = Integer.parseInt(args[1]);
            penLabMin = Integer.parseInt(args[2]);
            penNotPaired = Integer.parseInt(args[3]);
            penSection = Integer.parseInt(args[4]);

            //eval weights:
            wMinFilled = Integer.parseInt(args[5]);
            wPref = Integer.parseInt(args[6]);
            wPair = Integer.parseInt(args[7]);
            wSecDiff = Integer.parseInt(args[8]);

        }catch (Exception exception) {

            //if the error is with user inputting non integers
            if (exception.getClass().getName().equals("NumberFormatException")) {
                System.err.println("Some of the values input for the weights of different parts of Code.Eval are not integers. Please try again");
            }else {
                System.err.println("Something went wrong trying to parse the weight values for Code.Eval. Please try again.");
            }

            System.exit(0);
        }

        //error checking the file path and making sure it opens
        File file;
        if (!((file = new File(args[0])).exists() && file.isFile() && args[0].endsWith(".txt"))) {

            System.err.println("The file path given is invalid. Try again.");
            System.exit(0);
        }

        if(debug) System.out.println("Valid command line args");

        //creates the Code.ParseFile object that will be used to read and parse the file
        fileParser = new ParseFile(args[0], timeSlotStringMap);
        try{
            fileParser.ProcessFile();
        } catch(InputException e){
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (CourseLabDoesNotExistException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (PartAssignException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (SlotDoesNotExistException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        if(debug) System.out.println("File parsed successfully");

        ArrayList<CourseLab> courseLabList = fileParser.getCourseLabList();
        ArrayList<Slot> slotList = fileParser.getSlotList();
        //initializing the environment
        environment = new Env();

        if (cpsc813913Flag){
            boolean cpsc813Flag = false;
            boolean cpsc913Flag = false;
            boolean cpsc313Flag = false;
            boolean cpsc413Flag = false;
            CourseLab cpsc813;
            CourseLab cpsc913;

            // Check if 313 and 413 exist
            for (CourseLab courseLab : courseLabList){
                if (courseLab.getName().contains("CPSC 313")){
                    cpsc313Flag = true;
                }
                if (courseLab.getName().contains("CPSC 413")){
                    cpsc413Flag = true;
                }
            }

            // Check if the slot exists
            for (Slot slot : slotList){
                if (slot.getDay().equals("TU") && (slot.getStartTime() == 1800)){

                    if (cpsc313Flag){
                        cpsc813 = new CourseLab("QUIZ", "CPSC 813 LEC 01");
                        cpsc813.setPartAssign(slot);
                        cpsc813Flag = true;
                        courseLabList.add(cpsc813);
                    }
                    
                    if (cpsc413Flag){
                        cpsc913 = new CourseLab("QUIZ", "CPSC 913 LEC 01");
                        cpsc913.setPartAssign(slot);
                        cpsc913Flag = true;
                        courseLabList.add(cpsc913);
                    }
                }
            }

            if (cpsc313Flag && !cpsc813Flag){
                System.out.println("Slot required for CPSC 813 has labmax of 0. Therefore no valid solution.");
                System.exit(0);
            }
            if (cpsc413Flag && !cpsc913Flag){
                System.out.println("Slot required for CPSC 913 has labmax of 0. Therefore no valid solution.");
                System.exit(0);
            }
        }

        //initializes the global ordering ArrayList
        globalOrdering = CreateGlobalOrdering();

        // Checking each courselab composition after parsing
        if (debug){
            System.out.println("Checking courselabs parsed in correctly for constr");
            for (CourseLab courseLab : courseLabList){
                System.out.println("CourseLab details:");
                System.out.println(courseLab.getName());
                System.out.println(courseLab.getType());
                ArrayList<CourseLab> nonCompatList = courseLab.getNonCompatibles();
                System.out.println("Non compatibles");
                for(CourseLab othCourseLab: nonCompatList){
                    System.out.println(othCourseLab.getName());
                }
                ArrayList<Slot> unwantedList = courseLab.getUnwantedSlots();
                System.out.println("Unwanted slots");
                for(Slot othSlot: unwantedList){
                    System.out.println(othSlot.getDay() + ", " + othSlot.getStartTime());
                }
                System.out.println();
            }
        }
       
        // create the validator to use
        validator = new Validator(courseLabList, slotList);
        // Set the indices for each courselab to its location in the courselabList
        validator.setCourseLabListIndices(courseLabList);
        // Set course and labs constraint
        validator.setCoursesAndLabsConstraint(courseLabList);
        // Set constraint for TU11AM lecture slot
        validator.setTU11AMConstraint(courseLabList, slotList);
        // Set constraints for CPSC813/913
        validator.setCPSC813913Constraints(courseLabList, slotList);
        // Set constraints for 500 level courses
        validator.set500LevelConstraints(courseLabList, slotList);
        // Set up section lists
        validator.setSectionList(courseLabList);

        // Checking each courselab composition after validation
        if (debug){
            System.out.println();
            System.out.println("Checking courselabs validated in correctly for constr");
            for (CourseLab courseLab : courseLabList){
                System.out.println("CourseLab details:");
                System.out.println(courseLab.getName());
                System.out.println(courseLab.getType());
                ArrayList<CourseLab> nonCompatList = courseLab.getNonCompatibles();
                System.out.println("Non compatibles");
                for(CourseLab othCourseLab: nonCompatList){
                    System.out.println(othCourseLab.getName());
                }
                ArrayList<Slot> unwantedList = courseLab.getUnwantedSlots();
                System.out.println("Unwanted slots");
                for(Slot othSlot: unwantedList){
                    System.out.println(othSlot.getDay() + ", " + othSlot.getStartTime());
                }
                System.out.println();
            }
        }

        // create the constr to use
        constr = new Constr(courseLabList, slotList);

        //creating the eval instance to be used
        eval = new Eval(penCourseMin, penLabMin, penNotPaired, penSection, wMinFilled, wPref, wPair, wSecDiff, fileParser.getCourseLabList(), slotList, globalOrdering);

        //creating the eval fast instance to be used
        // evalFast = new EvalFast(penCourseMin, penLabMin, penNotPaired, penSection, wMinFilled, wPref, wPair, wSecDiff, courseLabList, slotList, globalOrdering);

        // creating minheap instance to use to run the search process
        //the depth is the number of "!"
        minHeap = new PriorityQueue<>(Comparator.comparingInt(Assign::getDepth).thenComparingInt(Assign::getEvalScore));

        //TODO: main loop

        // Start with initial assign, which is all ! entries
        Assign assignment = new Assign(courseLabList.size(), slotList);
        // Add the initial assignment to the minHeap
        minHeap.add(assignment);

        if(debug) System.out.println("Starting main loop");
        // Loop through minHeap until it is empty.
        // Items will be added to the minheap only when they are
        // 1. are a valid constr
        // 2. have an eval score (saved via assign.setEvalScore)
        // 3. have a depth, which is number of ! in the assign (saved via assign.setDepth)
        // 4. have a sol entry of false (problem not solved)

        // Items are removed from the minheap only when they have been solved
        // Note that invalid constr assigns will be marked as solved and never
        // added to the minheap since they are not valid
        System.out.println();
        while (!minHeap.isEmpty()){
            if(debug) System.out.println("Minheap size at start of loop: " + minHeap.size());
            if(debug) System.out.println("Should go here after dealing with children");
            // Get the top of the heap and make children from it
            Assign currentAssign = minHeap.poll();
            ArrayList<Object> assign = currentAssign.getAssign();

            if(debug) System.out.println("Number unassigned in current assign");
            if(debug) System.out.println(currentAssign.getDepth());
            // Next, find first ! entry
 
        for (int i = 0; i < assign.size(); i++){
            if(debug)System.out.println("What is in assign");
            if(debug)System.out.println(courseLabList.get(i) + " " + assign.get(i));
            if (assign.get(i) == "!"){
                ArrayList<Assign> assignBranches = new ArrayList<>();
                // Loop through slots list and create child branch for each one
                for (int j = 0; j < slotList.size(); j++){
                    // Create a new child which has the same assign and slot counts as parent
                    Assign childAssign = currentAssign.DeepCopy();
                    Slot slotToAssign = slotList.get(j);
                    // Change the leftmost unassigned slot in the child
                    childAssign.ChangeElementOfAssign(slotToAssign, i);
                    // update the slot assign counts array
                    childAssign.incrSlotCountAtIndex(j);
                    assignBranches.add(childAssign);
                }
                // After all child branches have been made, run Constr on each of them
                for (int j = 0; j < assignBranches.size(); j++){
                    if(debug) System.out.println("Checking child branches");
                    Assign childAssign = assignBranches.get(j);
                    // Set depth in the assign,
                    // where depth is number of unassigned courselabs
                    childAssign.setDepth(assign.size()- (i + 1));
                    boolean result = constr.checkHardConstraints(childAssign);
                    // If false, hard constraints are violated, so set sol entry to yes
                    if (!result){
                        if(debug) System.out.println("Child did not pass constr");
                        childAssign.setSolEntryTrue();
                    } else {
                        // Get eval score of the child branch
                        int evalScore = 0;
                        if (partialEvalEnabled){
                            if (debugPartialEval){
                                evalScore = eval.EvaluateAssignDebug(childAssign);
                                if(debug2) {
                                    ArrayList<Object> childPrintList = childAssign.getAssign();
                                    System.out.println("Current solution is: ");
                                    System.out.println("Eval-value: "+ evalScore);
                                    for (int k = 0; k < childPrintList.size(); k++){
                                        String courseLabName = courseLabList.get(k).getName();
                                        if (childPrintList.get(k) == "!"){
                                            System.out.println(courseLabName + " !");
                                        } else {
                                            Slot childSlot = (Slot) childPrintList.get(k);
                                            System.out.println(courseLabName + " " + childSlot.getDay() +", " + childSlot.getStartTime() + "("+ childSlot.getType()+")");
                                        }
                                    }
                                    System.out.println();
                                }
                            } else {
                                evalScore = eval.EvaluateAssign(childAssign);
                            }
                        }

                        // If a current best solution has already been obtained, compare it to the eval scores
                        // of these branches. If these branches are worse, mark as yes and do not add to heap
                        boolean branchAndBoundFlag = false;
                        if (environment.getCurrentBestSolution() != null){
                            int currentBestEvalScore = environment.getEvalCurrentBestSolution();
                            if (evalScore > currentBestEvalScore){
                                branchAndBoundFlag = true;
                            }
                        }
                        // If child branch has lower eval score, keep it and add to the heap
                        if (!branchAndBoundFlag || !branchAndBoundEnabled){
                            // Set eval score 
                            childAssign.setEvalScore(evalScore);
                            // Add this child branch to the heap if it is not worse than current best solution
                            if(debug) System.out.println("Child passed and added to heap");
                            minHeap.add(childAssign);
                            if(debug) System.out.println("New heap size:" + minHeap.size());
                        } else {
                            // If child branch has worse eval score, set sol entry to yes
                            // Do not add to the minheap
                            childAssign.setSolEntryTrue();
                        }
                        
                    }
                }
                // After spawning child branches, set sol entry of parent to yes
                currentAssign.setSolEntryTrue();

                // Now that all child branches have been done, continue to the top of the while loop
                // This will go back to start of while loop, to get the next branch off the top of the heap

                if(debug) System.out.println("Finishing up with child branches");
                break;
            }
        }
        if (currentAssign.getDepth() != 0){
            continue;
        }
        if(debug) System.out.println("I am a full assignment");
            // If there are no ! left in the assign, it has been fully assigned. Confirm it passes
            // constr and check its eval score. Set as current best solution if none exist
            boolean resultFullAssign = constr.checkHardConstraints(currentAssign);
                    // If true, hard constraints pass.
                    if (resultFullAssign){
                        // Increment counter of number of valid solutions found
                        numSolutionsFound ++;
                        // Next, get eval score of the full assign
                        int evalScoreFullAssign = 0;
                        if (fullEvalEnabled){
                            if (debugFullEval){
                                evalScoreFullAssign = eval.EvaluateAssignDebug(currentAssign);
                            } else {
                                evalScoreFullAssign = eval.EvaluateAssign(currentAssign);
                            }
                        }
                        if(debug2)System.out.println("Eval score of full solution is: "+ evalScoreFullAssign);
                        currentAssign.setEvalScore(evalScoreFullAssign);
                        // Check if current best solution exists
                        if (environment.getCurrentBestSolution() != null){
                            // If so, compare to this full assign
                            int currentBestEvalScore = environment.getEvalCurrentBestSolution();
                            if (evalScoreFullAssign < currentBestEvalScore){
                                // If the eval score is the new best, update the environment
                                environment.setCurrentBestSolution(currentAssign, evalScoreFullAssign);
                                if(debug) System.out.println("New best assign");
                            }
                        } else {
                            // If no current best exists yet, set this full assign as the current best
                            environment.setCurrentBestSolution(currentAssign, evalScoreFullAssign);
                            if(debug) System.out.println("First complete assign!");
                        }
                    }
                    // After updating the current best, if it is the new current best, trim the tree
                    if (branchAndBoundEnabled){
                        if (environment.getCurrentBestSolution().equals(currentAssign)){
                            int bestEvalScore = currentAssign.getEvalScore();
                            // Create an empty heap, that will be updated to only contain values less than the best eval score
                            // Note that this is done this way to only iterate through the heap once O(N) time, rather than attempting to remove each individually
                            // which would be O(N2) time
                            PriorityQueue<Assign> newHeap = new PriorityQueue<>(Comparator.comparingInt(Assign::getDepth).thenComparingInt(Assign::getEvalScore));
                            // Loop through the min heap, and remove assigns that have greater or equal scores to the new best
                            if(debug) System.out.println("Attempting to reduce bad parts of minHeap");
                            while (!minHeap.isEmpty()){
                                Assign heapAssign = minHeap.poll();
                                int heapAssignEvalScore = heapAssign.getEvalScore();
                                if (heapAssignEvalScore <= bestEvalScore){
                                    // Transfer the assigns with equal or better eval scores to the new heap
                                    newHeap.add(heapAssign);
                                } 
                            }
                            // After finishing, set the minHeap to be the newHeap
                            minHeap = newHeap;
                            if(debug) System.out.println("Minheap has been reduced");
                        }
                    }
                    
                

            // After this check, mark this completed assign as yes, and remove from the heap
            currentAssign.setSolEntryTrue();
            if(debug2) {
                System.out.println("Current solution is: ");
                System.out.println("Eval-value: "+ currentAssign.getEvalScore());
                for (int i = 0; i < assign.size(); i++){
                    String courseLabName = courseLabList.get(i).getName();
                    if (assign.get(i) == "!"){
                        System.out.println(courseLabName + " !");
                    } else {
                        Slot slot = (Slot) assign.get(i);
                        System.out.println(courseLabName + " " + slot.getDay() +", " + slot.getStartTime() + "("+ slot.getType()+")");
                    }
                }
            }
            if(debug2 && debug) System.out.println("Full solutions found: "+ numSolutionsFound);
            if(debug) System.out.println("Items remaining in minheap: "+ minHeap.size());
            if (debug || debug2) System.out.println();
        }
        // END OF LOOP

        // After the loop, the goal condition has been reached, as all entries in the tree have been 
        // marked sol entry yes, and there are no further moves available
        
        // Check if a best solution was found, and if so print it out
        if(debug2) System.out.println("Number of valid solutions found: "+ numSolutionsFound);
        if (environment.getCurrentBestSolution() != null){
            DisplaySolution();
        } else {
            // If not, the problem could not be solved
            System.out.println("No valid assignment could be obtained for the provided input file");
        }
    }

    /**
     * Function to create the global ordering ArrayList.
     *
     * @return The Code.Assign that is the global ordering ArrayList.
     */
    public static Assign CreateGlobalOrdering() {

        Assign globalOrdering = new Assign();

        //get the courselab list
        ArrayList<CourseLab> courseLabList = fileParser.getCourseLabList();

        //go through the courselab list and create the global ordering from it
        for (int i = 0; i < courseLabList.size(); i++) {

            globalOrdering.AddElementToAssign(courseLabList.get(i));
            courseLabList.get(i).setGlobalOrderingIndex(i);
        }

        return globalOrdering;
    }

    /**
     * Function that will be used to display the output of the program.
     */
    public static void DisplaySolution() {

        System.out.println("Eval-value: " + environment.getEvalCurrentBestSolution());

        environment.getCurrentBestSolution().PrintAssign(globalOrdering);

    }
}
