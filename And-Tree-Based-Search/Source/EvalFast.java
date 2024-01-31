package Source;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class EvalFast {

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


    private boolean debug = true;

    public EvalFast(int penCourseMin, int penLabMin, int penNotPaired, int penSection, int wMinFilled, int wPref, int wPair, int wSecDiff, ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList, Assign globalOrdering) {
        this.penCourseMin = penCourseMin;
        this.penLabMin = penLabMin;
        this.penNotPaired = penNotPaired;
        this.penSection = penSection;
        this.wMinFilled = wMinFilled;
        this.wPref = wPref;
        this.wPair = wPair;
        this.wSecDiff = wSecDiff;
        this.courseLabList = courseLabList;
        
    }

    /**
     * Function to use the soft constraints to evaluate an assign.
     *
     * @param assignment The assign merged with the global ordering to evaluate.
     * @return The eval value for the assign passed in.
     */
    public int EvaluateAssign(Assign assignment) {

        return EvalMinFilled(assignment) * wMinFilled
                + EvalPref(assignment) * wPref
                + EvalPair(assignment) * wPair
                + EvalSecDiff(assignment) * wSecDiff;
    }

    /**
     * Function to determine the number of slots that do not satisfy the minimum filled soft constraint.
     * Each slot has a courseLabMin that will be used in this function.
     * Only does calculations on full assigns.
     *
     * @param assignment The assign to evaluate.
     * @return The number of slots that do not satisfy the soft constraint or 0 if not a full assign.
     */
    public int EvalMinFilled(Assign assignment) {

        int penBadSlots = 0;

        // Run only if full assign
        if (assignment.getDepth() == 0) {

            int[] slotAssignCounts = assignment.getSlotAssignCounts();
            // Check how many below minimums and add the penalty
            for (int i =0; i<slotAssignCounts.length; i++){
                Slot slot = slotList.get(i);
                int numBelowMin =  slot.getCourseLabMin() - slotAssignCounts[i];
                if(numBelowMin > 0){
                    if (slot.getType().equals("LEC")){
                        penBadSlots += (numBelowMin * penCourseMin);
                    } else {
                        penBadSlots += (numBelowMin * penLabMin);
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
    public int EvalPref(Assign assignment) {

        int totalWeight = 0;

        ArrayList<Object> assign = assignment.getAssign();
        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);

                CourseLab courseLab = courseLabList.get(i);
                ArrayList<ArrayList<Object>> preferenceList = courseLab.getPreferenceList();
                for (ArrayList<Object> preference : preferenceList){
                    Slot preferredSlot = (Slot) preference.get(0);
                    int preferenceAmount = (int) preference.get(1);
                    if (!slot.equals(preferredSlot)){
                        totalWeight += preferenceAmount;
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
    public int EvalPair(Assign assignment) {

        int penBadPairs = 0;

        ArrayList<Object> assign = assignment.getAssign();
        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);

                CourseLab courseLab = courseLabList.get(i);
                ArrayList<CourseLab> pairList = courseLab.getPairList();
                for (CourseLab pairedCourseLab : pairList){
                    int pairedIndex = pairedCourseLab.getGlobalOrderingIndex();
                    if (assign.get(pairedIndex) == "!"){
                        continue;
                    } else {
                        Slot pairedSlot = (Slot) assign.get(pairedIndex);
                        if (!slot.equals(pairedSlot)){
                            penBadPairs += penNotPaired;
                        }
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
    public int EvalSecDiff(Assign assignment) {

        int numBadSections = 0;

        ArrayList<Object> assign = assignment.getAssign();
        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);

                CourseLab courseLab = courseLabList.get(i);
                ArrayList<CourseLab> sectionList = courseLab.getSectionList();
                for (CourseLab sectionCourse : sectionList){
                    int sectionIndex = sectionCourse.getGlobalOrderingIndex();
                    if (assign.get(sectionIndex) == "!"){
                        continue;
                    } else {
                        Slot sectionSlot = (Slot) assign.get(sectionIndex);
                        if (!slot.equals(sectionSlot)){
                            numBadSections ++;
                        }
                    }
                }
            }
        }
        // At the end, divide by two since adding the sections to both courses
        numBadSections = numBadSections/2;



        return numBadSections * penSection;
    }
}
