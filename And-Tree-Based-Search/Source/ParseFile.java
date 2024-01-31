package Source;

import java.util.ArrayList;
import java.util.Arrays;

import Source.Exceptions.CourseLabDoesNotExistException;
import Source.Exceptions.InputException;
import Source.Exceptions.PartAssignException;
import Source.Exceptions.SlotDoesNotExistException;


public class ParseFile {

    private String filePath;
    private ReadFile reader;
    private Parser parser;


    private TimeSlotStringMap timeSlotStringMap;

    //the arraylist containing all courses and labs
    private ArrayList<CourseLab> courseLabList = new ArrayList<>();

    //the arraylist containing all slots
    private ArrayList<Slot> slotList = new ArrayList<>();



    /**
     * Constructor to create a Code.ParseFile object to work with the input file.
     * Only creates a Code.ParseFile object if the filePath provided exists and is a .txt file.
     * Otherwise, will exit the program.
     *
     * @param filePath The file to parse.
     * @param timeSlotStringMap TODO: fill in
     */
    public ParseFile(String  filePath, TimeSlotStringMap timeSlotStringMap) {
        this.timeSlotStringMap = timeSlotStringMap;
        this.filePath = filePath;
        this.reader = new ReadFile(this.filePath);
        String fileContent = this.reader.read();
        this.parser = new Parser(fileContent);


    }

    public void ProcessFile() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        //parse the file here and create courselab and slots, editing them
        // Slots first?

        ArrayList<CourseLab> courses = this.parser.getCourses();
        ArrayList<CourseLab> labs = this.parser.getLabs();

        this.parser.getCourseSlots(timeSlotStringMap);
        this.parser.getLabSlots(timeSlotStringMap);

        ArrayList<Slot> courseSlots = timeSlotStringMap.getCourseSlots();
        ArrayList<Slot> labSlots = timeSlotStringMap.getLabSlots();

        if(courses.size() == 0) throw new CourseLabDoesNotExistException("No courses input");

        if(courseSlots.size() == 0) throw new SlotDoesNotExistException("No course slots input");

        if(labSlots.size() == 0 && labs.size() == 0) throw new SlotDoesNotExistException("No lab slots input");

        this.courseLabList.addAll(courses);
        this.courseLabList.addAll(labs);

        this.slotList.addAll(courseSlots);
        this.slotList.addAll(labSlots);

        setNotCompatibles();
        setUnwanted();
        setPreferences();
        setPairs();
        setPartAssign(); 

    }

    public ArrayList<CourseLab> getCourseLabList() {
        return courseLabList;
    }

    public ArrayList<Slot> getSlotList() {
        return slotList;
    }

    private void setNotCompatibles() throws InputException, CourseLabDoesNotExistException{
        ArrayList<ArrayList<CourseLab>> notCompatibles = this.parser.getNotCompatible();
        if(notCompatibles.size() == 0){
            return;
        }
        for(ArrayList<CourseLab> notCompat : notCompatibles){
            if(notCompat.size() == 2){
                CourseLab a = notCompat.get(0);
                CourseLab b = notCompat.get(1);
                for(CourseLab cl : courseLabList){
                    if(cl.getName().equals(a.getName())){
                        cl.addNonCompatibles(getCourseLabByName(b.getName()));
                    }else if(cl.getName().equals(b.getName())){
                        cl.addNonCompatibles(getCourseLabByName(a.getName()));
                    }
                }
            }
        }
    }

    private void setUnwanted() throws InputException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ArrayList<String[]> unwanteds = this.parser.getUnwanted();
        if(unwanteds.size() == 0){
            return;
        }

        for(String[] params : unwanteds){
            if(params.length == 3){
                String courseName = params[0];
                String key = params[1] + params[2];
                for(CourseLab cl : courseLabList){
                    CourseLab test = getCourseLabByName(courseName);
                    if(cl.getName().equals(courseName)){
                        Slot toAdd = timeSlotStringMap.getSlotFromKey(key, cl.getType());
                        if(!checkSlotExists(toAdd)) throw new SlotDoesNotExistException("Slot does not exist: " + toAdd);
                        cl.addUnwantedSlots(toAdd);
                    }
                }
            }           
        }
    }

    private void setPreferences() throws InputException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ArrayList<String[]> prefStrings = this.parser.getPreferences();
            
        if(prefStrings.size() == 0){
            return;
        }
        for(String[] pref : prefStrings){
            if(pref.length == 4){
                try{
                    String key = pref[0] + pref[1];
                    String courseLabName = pref[2];
                    int weight = Integer.parseInt(pref[3]);
                    CourseLab cl = getCourseLabByName(courseLabName);
                    ArrayList<Object> prefObj = new ArrayList<>();
                    if(cl.getName().contains("LAB") || cl.getName().contains("TUT")) {
                        Slot toAdd = timeSlotStringMap.getSlotFromKey(key, "LAB");
                        if(!checkSlotExists(toAdd)) throw new SlotDoesNotExistException("Slot in prefs does not exist: " + toAdd);
                        prefObj.add(toAdd);
                        prefObj.add(weight);
                        cl.addToPreferenceList(prefObj);
                    }else if(cl.getName().contains("LEC")){
                        Slot toAdd = timeSlotStringMap.getSlotFromKey(key, "LEC");
                        if(!checkSlotExists(toAdd)) throw new SlotDoesNotExistException("Slot in prefs does not exist: " + toAdd);
                        prefObj.add(toAdd);
                        prefObj.add(weight);
                        cl.addToPreferenceList(prefObj);
                    }
                }catch(CourseLabDoesNotExistException e){
                    System.out.println("Invalid preference, ignored");
                    continue;
                }catch(SlotDoesNotExistException e){
                    System.out.println("Invalid preference, ignored");
                    continue;
                }

            }
        }

    }


    private void setPairs() throws InputException, CourseLabDoesNotExistException{
        ArrayList<String[]> pairs = this.parser.getPair();
        if(pairs.size() == 0){
            return;
        }
        for(String[] pair: pairs){
            if(pair.length == 2){
                String a = pair[0];
                String b = pair[1];
                CourseLab test = getCourseLabByName(a); // just testing the courselab here
                CourseLab test1 = getCourseLabByName(b); // just testing the courselab here
                for(CourseLab cl : courseLabList){
                    if(cl.getName().equals(a)){
                        cl.addToPairList(getCourseLabByName(b));
                    }//else if(cl.getName().equals(b)){ // Not adding the "symmetric pair here"
                        //cl.addToPairList(getCourseLabByName(a));
                    //}
                }
            } 
        }

    }


    private void setPartAssign() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        String[] partAssignStrings = this.parser.getPartialAssignments();
        if(partAssignStrings.length == 0){
            return;
        }
        for(String partAssignString : partAssignStrings){ // for each part assign, we need to find the course and then set its part assign.
                                                          // if there is already a part assign for a course (input error) throw error
            String[] courseLabSlot = partAssignString.split(",");
            String[] cleanedCourseLabSlot = Arrays.stream(courseLabSlot)
                                                .map(String::strip)
                                                .toArray(String[]::new);

            String courseLabName = cleanedCourseLabSlot[0];

            String key = cleanedCourseLabSlot[1] + cleanedCourseLabSlot[2];

            CourseLab partAssignCl = getCourseLabByName(courseLabName);
            for(CourseLab cl : courseLabList){
                // get the courseLab
                if(cl != null){
                    if(cl.getName().equals(partAssignCl.getName())){
                        // Checking if Lab or Tut
                        if(cl.getName().contains("LAB") || cl.getName().contains("TUT")) {
                            // set lab slot as part assign
                            Slot toAdd = timeSlotStringMap.getSlotFromKey(key, "LAB");
                            if(!checkSlotExists(toAdd)) throw new SlotDoesNotExistException("Slot in partial assignment does not exist: " + toAdd);
                            cl.setPartAssign(toAdd);
                        }else if(cl.getName().contains("LEC")){
                            // set course slot as part assign
                            Slot toAdd = timeSlotStringMap.getSlotFromKey(key, "LEC");
                            if(!checkSlotExists(toAdd)) throw new SlotDoesNotExistException("Slot in partial assignment does not exist: " + toAdd);
                            cl.setPartAssign(toAdd);
                        }
                    }
                }else{
                    throw new PartAssignException();
                }
            }
        }
    }

    private CourseLab getCourseLabByName(String name) throws CourseLabDoesNotExistException{
        for(CourseLab cl : courseLabList){
            if(cl.getName().equals(name)) return cl;
        }
        throw new CourseLabDoesNotExistException("CourseLab does not exist: " + name);
    } // TODO Throw exception 

    /*
     * Checks that a courselab is a valid courselab that is defined in the Courses or Labs Parameters
     * 
     */

    private boolean checkSlotExists(Slot check){
        for(Slot sl : slotList){
            if(slotEquals(sl, check)) return true;
        }
        return false;
    }

    public boolean slotEquals(Slot slot1, Slot slot2){
        if(slot1 == null || slot2 == null){
            return false;
        }
        if(slot1.getCourseLabMax() != slot2.getCourseLabMax()){
            return false;
        }
        if(slot1.getCourseLabMin() != slot2.getCourseLabMin()){
            return false;
        }
        if(!slot1.getDay().equals(slot2.getDay())){
            return false;
        }        
        if(slot1.getEndTime() != slot2.getEndTime()){
            return false;
        }      
        if(slot1.getStartTime() != slot2.getStartTime()){
            return false;
        }
        if(!slot1.getType().equals(slot2.getType())){
            return false;
        }
        return true;
    }
}
