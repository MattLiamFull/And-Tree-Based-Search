package Source;

import java.util.ArrayList;
import java.util.regex.*;

public class Validator {

    //the arraylist containing all courses and labs
    private ArrayList<CourseLab> courseLabList;

    //the arraylist containing all slots
    private ArrayList<Slot> slotList;
    public Validator(ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList) {
        this.courseLabList = courseLabList;
        this.slotList = slotList;
    }

    public void setCourseLabListIndices(ArrayList<CourseLab> courseLabList){
        // Set the indices for each courselab to its location in the courselabList
        for (int i = 0; i < courseLabList.size(); i++){
            CourseLab courseLab = courseLabList.get(i);
            courseLab.setGlobalOrderingIndex(i);
        }
    }

    public void setCPSC813913Constraints(ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList){
        for (int i = 0; i < courseLabList.size(); i++){
            CourseLab courseLab = courseLabList.get(i);
            // Check if CPSC313/CPSC413 course is in list for CPSC813/CPSC913 constraint setup
            if ((courseLab.getName().contains("CPSC 313") || courseLab.getName().contains("CPSC 413"))){
                // Set the slots that conflict with CPSC813/CPSC913 into CPSC313/CPSC413 unwanted slots
                for (Slot slot : slotList){
                    if (slot.getDay().equals("TU") && ((slot.getStartTime() == 1700 && slot.getType().equals("LEC")) || slot.getStartTime() == 1800 || slot.getStartTime() == 1830)){
                        if (!courseLab.getUnwantedSlots().contains(slot)){
                            courseLab.addUnwantedSlots(slot);
                        }
                        
                        // Do the same for all of its non compatibles
                        ArrayList<CourseLab> nonCompatible813913 = courseLab.getNonCompatibles();
                        for (CourseLab nc813913CourseLab : nonCompatible813913){
                            if (!nc813913CourseLab.getUnwantedSlots().contains(slot)){
                                nc813913CourseLab.addUnwantedSlots(slot);
                            }
                        }
                    }
                }
            }
        }
    }

    public void set500LevelConstraints(ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList){
        ArrayList<CourseLab> courses500 = new ArrayList<>();

        // Regex to check for 500 level courses
        String regex = "\\b5[0-9]{2}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        for (int i = 0; i < courseLabList.size(); i++){
            CourseLab courseLab = courseLabList.get(i);
            if (courseLab.getType().equals("LEC")){
                String courseLabName = courseLab.getName();
                matcher = pattern.matcher(courseLabName);
                if (matcher.find()){
                    courses500.add(courseLab);
                }
            }
            
        }
        // Loop through the 500 level courses and add all other's to each non compatible list
        for (CourseLab courseLab : courses500){
            for (CourseLab otherCourseLab : courses500){
                if (!courseLab.equals(otherCourseLab)){
                    courseLab.addNonCompatibles(otherCourseLab);
                }
            }
        }
    }

    public void setTU11AMConstraint(ArrayList<CourseLab> courseLabList, ArrayList<Slot> slotList){
        Slot tu11AMSlot;
        for (Slot slot : slotList){
            if (slot.getDay().equals("TU") && (slot.getStartTime() == 1100) && slot.getType().equals("LEC")){
                tu11AMSlot = slot; 
                for (CourseLab courseLab : courseLabList){
                    if(courseLab.getType().equals("LEC")){
                        courseLab.addUnwantedSlots(tu11AMSlot);
                    }
                }
                return;
            }
        }
    }

    public void setCoursesAndLabsConstraint(ArrayList<CourseLab> courseLabList){
        
        for (CourseLab course : courseLabList){
            // Loop until course is found
            if (course.getType().equals("LEC")){
                // Get the name of the course
                String courseName = course.getName();
                // Then, loop until lab is found
                for (CourseLab lab : courseLabList){
                    if (!lab.getType().equals("LEC")){
                        // Get the name of the lab
                        String labName = lab.getName();
                        // Check if lab matches course for case when LEC is in lab name
                        if (labName.contains("LEC")){
                            if (courseName.equals(labName.substring(0,15))){
                                course.addNonCompatibles(lab);
                                lab.addNonCompatibles(course);
                            }
                        } else {
                            // Check if lab matches course for case when no LEC in lab name (only one section)
                            if (courseName.substring(0,8).equals(labName.substring(0,8))){
                                course.addNonCompatibles(lab);
                                lab.addNonCompatibles(course);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setSectionList(ArrayList<CourseLab> courseLabList){
        
        for (CourseLab courseLab : courseLabList){
            if (courseLab.getType().equals("LEC")){
                String courseNumber = courseLab.getName().substring(0,8);
                for (CourseLab othCourseLab: courseLabList){
                    if (othCourseLab.getType().equals("LEC") && !courseLab.equals(othCourseLab)){
                        String otherCourseNumber = othCourseLab.getName().substring(0,8);
                        if (courseNumber.equals(otherCourseNumber)){
                            courseLab.addToSectionList(othCourseLab);
                        }
                    }
                }
            }
        }
    }

}
