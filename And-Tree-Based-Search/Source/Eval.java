package Source;

import java.security.Key;
import java.util.*;

public class Eval {

    private int penCourseMin;
    private int penLabMin;
    private int penNotPaired;
    private int penSection;
    private int wMinFilled;
    private int wPref;
    private int wPair;
    private int wSecDiff;

    //the arraylist containing all courses and labs
    private ArrayList<CourseLab> courseLabList;
    private ArrayList<Slot> slotList;

    private Assign globalOrdering;

    private HashMap<Slot, HashSet<CourseLab>> courseLabInSlot = new HashMap<>();

    private boolean debug = true;
    private int penBadCourses = 0;
    private int penBadLabs = 0;

    public Eval(int penCourseMin, int penLabMin, int penNotPaired, int penSection, int wMinFilled, int wPref, int wPair, int wSecDiff, ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList, Assign globalOrdering) {
        this.penCourseMin = penCourseMin;
        this.penLabMin = penLabMin;
        this.penNotPaired = penNotPaired;
        this.penSection = penSection;
        this.wMinFilled = wMinFilled;
        this.wPref = wPref;
        this.wPair = wPair;
        this.wSecDiff = wSecDiff;
        this.courseLabList = courseLabList;
        this.slotList = slotList;
        this.globalOrdering = globalOrdering;
    }

    /**
     * Function to use the soft constraints to evaluate an assign.
     *
     * @param assignToEvaluate The assign merged with the global ordering to evaluate.
     * @return The eval value for the assign passed in.
     */
    public int EvaluateAssign(Assign assignToEvaluate) {

        //breaking up the merged assign into a hashmap of course : list of slots
        courseLabInSlot = assignToEvaluate.CreateHashMapFromAssign(globalOrdering);

        return EvalMinFilled(assignToEvaluate) * wMinFilled
                + EvalPref() * wPref
                + EvalPair(assignToEvaluate) * wPair
                + EvalSecDiff() * wSecDiff;
    }

    public int EvaluateAssignDebug(Assign assignToEvaluate) {

        //breaking up the merged assign into a hashmap of course : list of slots
        courseLabInSlot = assignToEvaluate.CreateHashMapFromAssign(globalOrdering);

        if(debug) {
            penBadCourses = 0;
            penBadLabs = 0;
            System.out.println("Eval breakdown");
            System.out.println("MinFilled: " + (EvalMinFilled(assignToEvaluate) * wMinFilled));
            if (debug){
                System.out.println("Courses below min: " + (penBadCourses * wMinFilled));
                System.out.println("Labs below min: " + (penBadLabs * wMinFilled));
            }
            penBadCourses = 0;
            penBadLabs = 0;
            System.out.println("Preferences: " + (EvalPref() * wPref));
            System.out.println("Not paired: " + (EvalPair(assignToEvaluate) * wPair));
            System.out.println("Sections in same slot: " + (EvalSecDiff() * wSecDiff));
        }

        return EvalMinFilled(assignToEvaluate) * wMinFilled
                + EvalPref() * wPref
                + EvalPair(assignToEvaluate) * wPair
                + EvalSecDiff() * wSecDiff;
    }

    /**
     * Function to determine the number of slots that do not satisfy the minimum filled soft constraint.
     * Each slot has a courseLabMin that will be used in this function.
     * Only does calculations on full assigns.
     *
     * @param assignToEvaluate The assign to evaluate.
     * @return The number of slots that do not satisfy the soft constraint or 0 if not a full assign.
     */
    public int EvalMinFilled(Assign assignToEvaluate) {

        int penBadSlots = 0;

        //if the last element in an assign isnt empty
        if (assignToEvaluate.getAssign().get(assignToEvaluate.getAssign().size()-1) != "!") {

            //go through each slot
            for (int i = 0; i < slotList.size(); i++) {

                //if the slots min requirements are > the number of courselabs assigned to it

                int numAssigned = assignToEvaluate.getSlotAssignCounts()[i];
                if (slotList.get(i).getCourseLabMin() > numAssigned) {

                    //if the slot is a lecture slot
                    if (slotList.get(i).getType().equals("LEC")) {

                        penBadSlots += penCourseMin * (slotList.get(i).getCourseLabMin() - numAssigned);
                        penBadCourses += penCourseMin * (slotList.get(i).getCourseLabMin() - numAssigned) ;
                    }else {

                        penBadSlots += penLabMin * (slotList.get(i).getCourseLabMin() - numAssigned);
                        penBadLabs += penLabMin * (slotList.get(i).getCourseLabMin() - numAssigned);

                    }
                }
            }

        }

        return penBadSlots;
    }

    /**
     * Function to determine the preference weighting of the slots the courses are assigned to.
     * If they are assigned to a slot that is preferred of course.
     *
     * @return The total weight.
     */
    public int EvalPref() {

        int totalWeight = 0;

        /*
        go through all courselabs
            find the slot it is in
        go through the preference list
            check each slot against the slot the courselab is in
            if there is a match, add the value, break
         */

        //goes through each courselab possible
        for (CourseLab courseLab : courseLabList) {

            //for each possible slot
            KEYLOOP:
            for (Slot key : courseLabInSlot.keySet()) {

                //if the courselab we are looking for exists in the slots list
                if (courseLabInSlot.get(key).contains(courseLab)) {

                    //goes through the preference list
                    for (ArrayList<Object> pair : courseLab.getPreferenceList()) {

                        //if there is a match, only add the pref for the slot its in
                        if (pair.get(0) != key) {

                            totalWeight += (int) pair.get(1);
                            break KEYLOOP;
                        }
                    }
                }
            }
        }

        return totalWeight;
    }

    /**
     * Function to determine the number of pairs of courses that are not assigned together but prefer to be.
     * Only gives penalty if both courses in a pair have been assigned.
     *
     * @return The number of courses that want to be assigned together but are not.
     */
    public int EvalPair(Assign assignToEvaluate) {

        int penBadPairs = 0;

        /*
        EACH PAIR WILL NOT UPDATE BOTH COURSELABS PAIR LISTS, ONLY ONE (easy math)
         */

        //for each possible slot
        for (Slot key : courseLabInSlot.keySet()) {

            //converting the courselab list for a given slot to an arraylist to iterate through
            ArrayList<CourseLab> courseLabList = new ArrayList<>(courseLabInSlot.get(key));

            //go through all the courselabs in one a slot
            for (int i = 0; i < courseLabList.size(); i++) {

                //go through entire pair list for a courselab
                for (int j = 0; j < courseLabList.get(i).getPairList().size(); j++) {

                    //the slot the pair course lab is in
                        //the assign value at the index of the global ordering that the pair courselab is in
                            //have the courselab -> courseLabList[i].getPairList().get(j)
                    Object pairCourseLabSlot = assignToEvaluate.getAssign().get(courseLabList.get(i).getPairList().get(j).getGlobalOrderingIndex());

                    //if the pair has NOT been assigned, dont do shit
                    if (pairCourseLabSlot == "!") {

                        break;

                        //if the slot that the pair courselab is assigned to is not the same as the other courselab, add penalty
                    }else if (pairCourseLabSlot != key) {

                        penBadPairs += penNotPaired;
                    }
                }

            }

        }

        return penBadPairs;
    }

    /**
     * Function to count how many courses have different sections scheduled at the same time.
     * Only adds penalty for LEC courselabs.
     *
     * @return The number of courses that have multiple sections overlap.
     */
    public int EvalSecDiff() {

        /*
        go through each slot, and determine if there are courses that are sections of each other
        better to just check the strings
         */

        //create an arraylist of all the course codes for a slot

        int numBadSections = 0;

        //go through each slot
        for (Slot slot : courseLabInSlot.keySet()) {

            //creating an arraylist to store the course codes
            ArrayList<String> courseCodeList = new ArrayList<>();

            //goes through the courselabs in a slot
            for (CourseLab courseLab : courseLabInSlot.get(slot)) {

                //only add courses
                if (!courseLab.getType().equals("LAB")) {

                    //add the coursenumber to the arraylist
                    courseCodeList.add(courseLab.getName().substring(5, 8));
                }
            }

            //sorting it alphabetically
            Collections.sort(courseCodeList);

            String storedElement = "";
            int counter = 0;
            //go throygh the code list and determine how many duplicates there are
            for (int i = 0; i < courseCodeList.size(); i++) {

                if (courseCodeList.get(i).equals(storedElement)) {

                    counter++;
                }else {

                    if (counter > 1) {
                        counter++;
                    }

                    numBadSections += counter;
                    counter = 0;
                    storedElement = courseCodeList.get(i);
                }
            }

            if (counter > 1) {
                counter++;
            }
            numBadSections += counter;
        }

        return numBadSections * penSection;
    }

    /**
     * FIXME: THIS IS A GETTER USED FOR TESTING PURPOSES ONLY (USED IN ONE TEST)
     */
    public HashMap<Slot, HashSet<CourseLab>> getCourseLabInSlot() {
        return courseLabInSlot;
    }

    /**
     * FIXME: THIS IS A SETTER USED FOR TESTING PURPOSES ONLY
     */
    public void setCourseLabInSlot(HashMap<Slot, HashSet<CourseLab>> courseLabInSlot) {
        this.courseLabInSlot = courseLabInSlot;
    }
}
