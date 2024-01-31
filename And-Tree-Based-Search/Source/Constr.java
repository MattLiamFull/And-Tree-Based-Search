package Source;

import java.util.ArrayList;

public class Constr {

    private boolean debug = false;
    //the arraylist containing all courses and labs
    private ArrayList<CourseLab> courseLabList;

    //the arraylist containing all slots
    private ArrayList<Slot> slotList;
    public Constr(ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList) {
        this.courseLabList = courseLabList;
        this.slotList = slotList;
    }

    /**
     * Check all hard constraints
     * @return true if hard constraints are all satisfied, false otherwise
     */
    public boolean checkHardConstraints(Assign assignment) {

        ArrayList<Object> assign = assignment.getAssign();

        if (debug) {
            System.out.println("Assign to check constr with, num unassigned is "+ assignment.getDepth());
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

        int[] slotAssignCounts = assignment.getSlotAssignCounts();
        // Check that no more than coursemax slots for all slots
        // Check that no more than labmax slots for all slots
        for (int i =0; i<slotAssignCounts.length; i++){
            Slot slot = slotList.get(i);
            if(slotAssignCounts[i] > slot.getCourseLabMax()){
                if(debug) System.out.println("Failed constr - over course/labmax");
                return false;
            }
        }
        // Check assign course is not same slot as assign related labs (including overlapping)
        // Check not-compatible list
        // Check CPSC5xx courses in different slots

        // For all of these, we are just using the non-compatible list attached to each courselab and making sure it is valid
        // The non-compatible list is built to ensure these types are all considered not compatible
        

       
        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);
                // Courselab list is same order as global ordering
                CourseLab courseLab = courseLabList.get(i);

                // First, check that the slot it is in is valid for course or lab type
                if ((slot.getType().equals("LEC") && !courseLab.getType().equals("LEC")) || 
                slot.getType().equals("LAB") && !courseLab.getType().equals("LAB")){
                    // If a lab is attempted to be put in a course slot, or vice versa, return false
                    // First, check that the course is not CPSC 813 or CPSC 913
                    if (!courseLab.getType().equals("QUIZ")){
                        if(debug) System.out.println("Failed constr - wrong slot type");
                        return false;
                    }
                    
                }
                ArrayList<CourseLab> nonCompatibles = courseLab.getNonCompatibles();
                for (int j = 0; j < nonCompatibles.size(); j++){
                    // If any of the non compatible slots are the same slot as our courselab, constr returns false
                    int nonCompatibleIndex = nonCompatibles.get(j).getGlobalOrderingIndex();
                    if (assign.get(nonCompatibleIndex) == "!"){
                        continue;
                    } else {
                        Slot nonCompatibleSlot = (Slot) assign.get(nonCompatibleIndex);
                        if (slot.equals(nonCompatibleSlot)){
                            if(debug) System.out.println("Failed constr - same slot as incompatible");
                            return false;
                        }
                        // Below checks if the slots are overlapping
                        int slotStartTime = slot.getStartTime();
                        int slotEndTime = slot.getEndTime();
                        String slotDay = slot.getDay();
                        int nonCompatibleSlotStartTime = nonCompatibleSlot.getStartTime();
                        int nonCompatibleSlotEndTime = nonCompatibleSlot.getEndTime();
                        String nonCompatibleSlotDay = nonCompatibleSlot.getDay();
                        // Check if course and lab are exact same time and day (MO only)
                        if (slotStartTime == nonCompatibleSlotStartTime && slotEndTime == nonCompatibleSlotEndTime
                        && slotDay.equals(nonCompatibleSlotDay)){
                            if(debug) System.out.println("Failed constr - same course and lab");
                            if(debug) System.out.println("Slot info: "+slotDay+", "+slotStartTime+"-"+slotEndTime);
                            if(debug) System.out.println("Non compat slot info: "+nonCompatibleSlotDay+", "+nonCompatibleSlotStartTime+"-"+nonCompatibleSlotEndTime);
                            return false;
                        }
                        // Check if start time falls between start and end times of other slot
                        // Also checking if on same day, or special case of MO and FR overlaps
                        if (slotStartTime > nonCompatibleSlotStartTime && slotStartTime < nonCompatibleSlotEndTime
                        && (slotDay.equals(nonCompatibleSlotDay) || (slotDay.equals("MO") && nonCompatibleSlotDay.equals("FR") && slot.getType().equals("LEC"))
                        || (slotDay.equals("FR") && nonCompatibleSlotDay.equals("MO") && nonCompatibleSlot.getType().equals("LEC")))){
                            if(debug) System.out.println("Failed constr - overlapping non compatible start time");
                            if(debug) System.out.println("Slot info: "+slotDay+", "+slotStartTime+"-"+slotEndTime);
                            if(debug) System.out.println("Non compat slot info: "+nonCompatibleSlotDay+", "+nonCompatibleSlotStartTime+"-"+nonCompatibleSlotEndTime);
                            return false;
                        }
                        // Check if end time falls between start and end times of other slot
                        // Also checking if on same day, or special case of MO and FR overlaps
                        if (slotEndTime > nonCompatibleSlotStartTime && slotEndTime <nonCompatibleSlotEndTime
                        && (slotDay.equals(nonCompatibleSlotDay) || (slotDay.equals("MO") && nonCompatibleSlotDay.equals("FR") && slot.getType().equals("LEC"))
                        || (slotDay.equals("FR") && nonCompatibleSlotDay.equals("MO") && nonCompatibleSlot.getType().equals("LEC")))){
                            if(debug) System.out.println("Failed constr - overlapping non compatible end time");
                            if(debug) System.out.println("Slot info: "+slotDay+", "+slotStartTime+"-"+slotEndTime);
                            if(debug) System.out.println("Non compat slot info: "+nonCompatibleSlotDay+", "+nonCompatibleSlotStartTime+"-"+nonCompatibleSlotEndTime);
                            return false;
                        }
                    }
                   
                }
            }
        }
        // Check partial assign list

        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);

                CourseLab courseLab = courseLabList.get(i);
                Slot partAssignSlot = courseLab.getPartAssign();
                if (partAssignSlot != null && !slot.equals(partAssignSlot)){
                    // If wrong slot, return false
                    if(debug) System.out.println("Not in partial assign slot");
                    return false;
                }
            }
        }
        // Check unwanted list (this will cover CPSC813 and CPSC913 constraints)
        // Check CPSC813 special constraints
        // Check CPSC913 special constraints
        // Check no courses (labs ok) at TU11:00

        // For these, all unwanted slots are added to the unwanted list on parsing
        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);
                CourseLab courseLab = courseLabList.get(i);
                ArrayList<Slot> unwantedSlots = courseLab.getUnwantedSlots();
                for (int j = 0; j < unwantedSlots.size(); j++){
                    // If any of the non comptabiles slots is the same slot as our courselab, constr returns false
                    Slot unwantedSlot = unwantedSlots.get(j);
                    if (slot.equals(unwantedSlot)){
                        if(debug) System.out.println("In an unwanted slot");
                        return false;
                    }
                }
            }
        }
        // Check LEC9x courses in evening slots
        for (int i = 0; i < assign.size(); i++){
            if (assign.get(i) == "!"){
                continue;
            } else {
                Slot slot = (Slot) assign.get(i);
                CourseLab courseLab = courseLabList.get(i);
                // Check if the course is an evening course
                if (courseLab.getType().equals("LEC") && courseLab.getName().contains("LEC 9")){
                    // Check if the slot start time is earlier than 6pm, if so, not an evening slot so return false
                    int startTime = slot.getStartTime();
                    if (startTime < 1800){
                        if(debug) System.out.println("Evening course in morning slot");
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
