package Source;

import java.util.*;

public class Assign {

    //can be either the "!" or "?" strings or a Code.Slot
    private ArrayList<Object> assign = new ArrayList<>();

    // Array counting how many asignments to each slot
    // This will be in same order as slotList, but quick to check
    // and kept with each assignment
    private int[] slotAssignCounts;

    //the arraylist containing all slots
    private ArrayList<Slot> slotList;

    // sol entry false is ?, true is yes
    private boolean solEntry = false;

    // arraylist of children assigns which represent the Div function branches
    private ArrayList<Assign> assignBranches = new ArrayList<>();

    // Eval score for the current assign
    private int evalScore;

    // Depth of the current assign (used to prioritize depth first)
    private int depth;

    /**
     * Empty constructor.
     */
    public Assign() {
    }

    /**
     * Creates and initializes an assign to contain "!" with number equal to the size of the global ordering followed by one "?".
     * Each "!" represents an element in the global ordering.
     * The "?" represents the sol entry.
     *
     * FIXME: initialize with global ordering? due to merge
     *
     * @param globalOrderingSize The size of the global ordering.
     * @param slotList The complete list of slots.
     */
    public Assign(int globalOrderingSize, ArrayList<Slot> slotList) {

        this.assign = new ArrayList<>(globalOrderingSize);

        for (int i = 0; i < globalOrderingSize; i++) {

            this.assign.add("!");
        }

        this.slotList = slotList;
        this.slotAssignCounts = new int[slotList.size()];
        this.depth = globalOrderingSize;
    }

    /**
     * Function to break a merged assign apart into a hashmap of the form:
     * Code.Slot : Vector<CourseLab>
     * This will work for partassigns too as there is a check for courselabs with no slot assigned to it.
     *
     * @param globalOrdering The global ordering.
     */
    public HashMap<Slot, HashSet<CourseLab>> CreateHashMapFromAssign(Assign globalOrdering) {

        //if the slot for a courselab is "!"
        //do nothing
        //else
        //add the slot to the hashmap
        //add the courselab to the course list at the slot in the hashmap

        HashMap<Slot, HashSet<CourseLab>> courseLabInSlot = new HashMap<>();

        //go through entire assign
        for (int i = 0; i < getSize(); i++) {

            //if the slot isnt empty
            if (assign.get(i) != "!") {

                //if the slot is already in the dict
                if (courseLabInSlot.containsKey(assign.get(i))) {

                    //add the courselab that it matched to the slot's hashset
                    courseLabInSlot.get(assign.get(i)).add((CourseLab) globalOrdering.getAssign().get(i));
                }else {

                    //create a new key, and add the courselab to the slot's hashset
                    courseLabInSlot.put((Slot) assign.get(i), new HashSet<>());
                    courseLabInSlot.get(assign.get(i)).add((CourseLab) globalOrdering.getAssign().get(i));
                }
            }
        }

        return courseLabInSlot;
    }

    /**
     * Prints an assign after converting to a human-readable form by merging it with the global ordering to create a list of CourseLabs and a Slots.
     *
     * @param globalOrdering The global ordering with which to merge.
     */
    public void PrintAssign(Assign globalOrdering) {

        //print in form:
            /*
            courselab     : slot
            EG:
                CPSC 433 LEC 01             : MO, 10:00
                CPSC 433 LEC 01 TUT 01      : TU, 10:00
             */

        HashMap<Slot, HashSet<CourseLab>> courseLabInSlot = CreateHashMapFromAssign(globalOrdering);

        ArrayList<String> alphabeticalOrderArray = new ArrayList<>();

        boolean cpsc813Flag = false;
        boolean cpsc913Flag = false;

        //go through all slots
        for (Slot key : courseLabInSlot.keySet()) {

            //go through all the courselabs in the slot
            for (CourseLab courseLab : courseLabInSlot.get(key)) {

                // // Adding this to check if cpsc813/cpsc913 need to be added in our output
                // if (!cpsc813Flag){
                //     if (courseLab.getName().contains("CPSC 313")){
                //         cpsc813Flag = true;
                //         alphabeticalOrderArray.add("CPSC 813 LEC 01\t\t: TU, 18:00");
                //     }
                // }
                // if (!cpsc913Flag){
                //     if (courseLab.getName().contains("CPSC 413")){
                //         cpsc913Flag = true;
                //         alphabeticalOrderArray.add("CPSC 913 LEC 01\t\t: TU, 18:00");
                //     }
                // }
                
                String formattedString = String.format("%-30s: %s", courseLab, key);
                //add the courselab slot properly formatted to an array to sort
                // alphabeticalOrderArray.add(courseLab + "\t\t: " + key);
                alphabeticalOrderArray.add(formattedString);
            }
        }

        //sort alphabetically then print
        Collections.sort(alphabeticalOrderArray);
        for (String courseLabSlot : alphabeticalOrderArray) {
            System.out.println(courseLabSlot);
        }
    }

    /**
     * Function to create a deep copy for the current assign object.
     *
     * @return A new assign that is identical in values.
     */
    public Assign DeepCopy() {

        Assign deepCopy = new Assign();

        for (Object slot : this.assign) {

            deepCopy.AddElementToAssign(slot);
        }

        deepCopy.slotAssignCounts = Arrays.copyOf(this.getSlotAssignCounts(), this.getSlotAssignCounts().length);

        return deepCopy;
    }

    /**
     * Adds an element to the assign.
     *
     * @param elementToAdd The object to add to the assign.
     */
    public void AddElementToAssign(Object elementToAdd) {

        assign.add(elementToAdd);
        // When assigning a slot, need to get the slot ID number
        // which should be related to its index
        // Then increment number assigned for use with Code.Constr
    }

    /**
     * Modifies the element of an assign given an index and what to replace it with.
     *
     * @param slotToInsert The slot to insert instead.
     * @param index The position of the element to change.
     */
    public void ChangeElementOfAssign(Slot slotToInsert, int index) {
        assign.set(index, slotToInsert);
    }

    /**
     * Gets the current assign.
     *
     * @return The current assign
     */
    public ArrayList<Object> getAssign() {
        return assign;
    }

    /**
     * Gets value at an index of the Arraylist
     *
     * @return A slot or a !
     */
    public Object getElementOfAssign(int index) {
        return assign.get(index);
    }

    public boolean isSolEntry() {
        return solEntry;
    }

    /**
     * Modified setter to change the sol entry.
     */
    public void setSolEntryTrue() {
        this.solEntry = true;
    }

    /**
     * Gets the size of the current assign.
     *
     * @return The size of the current assign.
     */
    public int getSize() {
        return this.assign.size();
    }

    // Funciton to get the total assigned per slot for Code.Constr
    public int[] getSlotAssignCounts(){
        return slotAssignCounts;
    }

    // Function to increment num of courses/labs assigned to a given slot
    public void incrSlotCountAtIndex(int index){
        slotAssignCounts[index] += 1;
    }

    // Function to get the child branches
    public ArrayList<Assign> getAssignBranches(){
        return assignBranches;
    }

    // Function to set the child branches from the div function
    public void setAssignBranches(ArrayList<Assign> div){
        assignBranches = div;
    }

    // Function to get eval score
    public int getEvalScore(){
        return evalScore;
    }

    // Function to set eval score
    public void setEvalScore(int score){
        evalScore = score;
    }

    // Function to get depth
    public int getDepth(){
        return depth;
    }

    // Function to set depth
    public void setDepth(int numUnassigned){
        depth = numUnassigned;
    }

}
