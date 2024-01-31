package Source;
import Source.Exceptions.InputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Backups
// private String courseRegex = "(CPSC|SENG)\\s+\\d{3}\\s+LEC\\s+\\d{2}\\s*";
// private String labRegex = "(CPSC|SENG)\\s+\\d{3}\\s+(?:(?:LEC\\s+\\d{2}\\s+)?(LAB|TUT)\\s+\\d{2}|LAB\\s+\\d{2})\\s*";

public class Parser {
    private String courseRegex = "[A-Z]{4}\\s+\\d{3}\\s+LEC\\s+\\d{2}\\s*";
    private String labRegex = "[A-Z]{4}\\s+\\d{3}\\s+(?:(?:LEC\\s+\\d{2}\\s+)?(LAB|TUT)\\s+\\d{2}|LAB\\s+\\d{2})\\s*";
    private String slotRegex = "(MO|TU|FR)\\s*,\\s*((?:0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9])\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*";
    private String partSlotRegex = "(MO|TU|FR)\\s*,\\s*((?:0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9])\\s*";


    public String string;
    /*
    * Constructor that takes in string input from a parsed file
    * The majority of this class is just getting input strings from each input type and cleaning them
    * @param String string data from input file   
    */
    public Parser(String string){

        this.string = string;
    } 
    /*
    * gets a field from the input string 
    * @param regex a reguar expression in the form of "Start:(.*?)End:" for getting a field at the end of file, use "Start:(.*$)"
    * Ex. "Name:(.*?)Course slots:" would return the name 
    * @return returns all content between start and end
    */
    public String getField(String regex){
        String field = "";
        Pattern fieldPattern = Pattern.compile(regex,Pattern.DOTALL);
        Matcher fieldMatcher = fieldPattern.matcher(this.string);

        if (fieldMatcher.find()){
            field = fieldMatcher.group(1);
            field = field.strip();
            return field;
        }
        return field;
    }

    /*
    * @return String the name parameter in the input file 
    * I dont think this needs to be used anywhere, its not in the output  
    * 
    */
    public String getName(){
        return getField("Name:(.*?)Course slots:");
    }


    /*
    * sets course min and max for lecture slots from the input file
    */    
    public void getCourseSlots(TimeSlotStringMap ts) throws InputException{
        String courseSlotsString = getField("Course slots:(.*?)Lab slots:");
        if(courseSlotsString.equals("")){
            return;
        }
        String[] slotsStrings = courseSlotsString.split("\n");
        for (String slot:slotsStrings){
            if(!validateSlotString(slot)) throw new InputException("Invalid input for course slot: " + slot);
            String[] rawParams = slot.split(",");
            String[] params = Arrays.stream(rawParams)
                                    .map(String::strip)
                                    .toArray(String[]::new);
            

            int max = Integer.parseInt(params[2]);
            int min = Integer.parseInt(params[3]);
            String key = params[0] + params[1];
            ts.setCourseMinMax(key, min, max);
        }
    }

    /*
    * sets course min and max for lecture slots from the input file
    *  
    */
    public void getLabSlots(TimeSlotStringMap ts) throws InputException{
        String labSlotsString = getField("Lab slots:(.*?)Courses:");
        if(labSlotsString.equals("")){
            return;
        }
        String[] slotsStrings = labSlotsString.split("\n");
        for (String slot:slotsStrings){
            if(!validateSlotString(slot)) throw new InputException("Invalid input for lab slot: " + slot);
            String[] rawParams = slot.split(",");
            String[] params = Arrays.stream(rawParams)
                                    .map(String::strip)
                                    .toArray(String[]::new);
            int max = Integer.parseInt(params[2]);
            int min = Integer.parseInt(params[3]);
            String key = params[0] + params[1];
            ts.setLabMinMax(key, min, max);
        }
    }

    /*
    * @return ArrayList of course labs from the input file
    */    
    public ArrayList<CourseLab> getCourses() throws InputException{
        String coursesString = getField("Courses:(.*?)Labs:");
        if(coursesString.equals("")){
            ArrayList<CourseLab> empty = new ArrayList<>();
            return empty;
        }

        String[] courseStrings = coursesString.split("\n");

        String[] spacedCourseStrings = Arrays.stream(courseStrings)
            .map(s -> s.replaceAll("\\s+", " ")) // Removing extra spaces 
            .toArray(String[]::new);

        String[] cleanedCourseStrings = Arrays.stream(spacedCourseStrings)
                .map(String::strip)
                .toArray(String[]::new);

        ArrayList<CourseLab> courseObjectList = new ArrayList<>(); 

        for(String cleanedCourseString : cleanedCourseStrings){
            if(!validateCourseString(cleanedCourseString)) throw new InputException("Invalid input for course: " + cleanedCourseString);
            CourseLab courseObject;
            courseObject = new CourseLab("LEC", cleanedCourseString);
            courseObjectList.add(courseObject);
        }
        
        return courseObjectList;
    }

    /*
    * @return ArrayList of course labs from the input file
    */
    public ArrayList<CourseLab> getLabs() throws InputException{
        String labsString = getField("Labs:(.*?)Not compatible:");
        if(labsString.equals("")){
            ArrayList<CourseLab> empty = new ArrayList<>();
            return empty;
        }
        String[] labsStrings = labsString.split("\n");
        String[] spacedLabStrings = Arrays.stream(labsStrings)
                .map(s -> s.replaceAll("\\s+", " ")) // Removing extra spaces 
                .toArray(String[]::new);

        String[] cleanedLabStrings = Arrays.stream(spacedLabStrings)
                .map(String::strip)
                .toArray(String[]::new);

        ArrayList<CourseLab> courseLabObjectList = new ArrayList<>();    
        
        for(String cleanedLabString:cleanedLabStrings){
            if(!validateLabString(cleanedLabString)) throw new InputException("Invalid input for lab: " + cleanedLabString);
            CourseLab courseLabObject;
            courseLabObject = new CourseLab("LAB", cleanedLabString);
            courseLabObjectList.add(courseLabObject);

        }
        return parseCourseLabStringList(labsStrings);
    }
    
    /*
    * @return sets of courselabs that are not compatible 
    */
    public ArrayList<ArrayList<CourseLab>> getNotCompatible() throws InputException{

        String notCompatiblesString = getField("Not compatible:(.*?)Unwanted:");
        String[] notCompatibleStrings = notCompatiblesString.split("\n");

        for(String notCompat : notCompatibleStrings){
            if(!validateNcPair(notCompat) && !notCompat.equals("")) throw new InputException("Invalid input for not compatible: " + notCompat);
        }

        ArrayList<ArrayList<CourseLab>> pairs = new ArrayList<>();
        for(int i = 0; i < notCompatibleStrings.length; i++){
            String[] notCompatiblePair = notCompatibleStrings[i].split(",");
            ArrayList <CourseLab> notCompatibleObjects = parseCourseLabStringList(notCompatiblePair);
            pairs.add(notCompatibleObjects);
        }

        return pairs;
    }
    // @return a string array in the form courselab, day, time
    // Returns cleaned unwanted strings
    public ArrayList<String[]> getUnwanted() throws InputException{
        String unwantedString = getField("Unwanted:(.*?)Preferences:");
        String[] unwantedStrings = unwantedString.split("\n");
        String[] spacedUnwantedStrings = Arrays.stream(unwantedStrings) // Removing extra spaces 
                                                .map(s -> s.replaceAll("\\s+", " "))
                                                .toArray(String[]::new);
        // 
        String[] cleanedUnwantedStrings = Arrays.stream(spacedUnwantedStrings)
                                                .map(String::strip)
                                                .toArray(String[]::new);
        
        for(String unwanted : cleanedUnwantedStrings){
            if(!validateUnwantedPartial(unwanted) && !unwanted.equals("")) throw new InputException("Invalid input for unwanted: " + unwanted);
        }

        ArrayList<String[]> unwanteds = new ArrayList<>();
        for(String unwantedPair : cleanedUnwantedStrings){
            String[] splitUnwanted = unwantedPair.split(",");
            splitUnwanted = Arrays.stream(splitUnwanted)
                                        .map(String::strip)
                                        .toArray(String[]::new);

            unwanteds.add(splitUnwanted);
        }
        
        return unwanteds;
    }

    public ArrayList<String[]> getPreferences() throws InputException{
        String rawPrefsString = getField("Preferences:(.*?)Pair:");
        String[] rawPrefsStrings = rawPrefsString.split("\n");
        String[] spacedPrefStrings = Arrays.stream(rawPrefsStrings) // Removing extra spaces 
                                        .map(s -> s.replaceAll("\\s+", " "))
                                        .toArray(String[]::new);

        String[] cleanedPrefStrings = Arrays.stream(spacedPrefStrings)
                                                .map(String::strip)
                                                .toArray(String[]::new);

        for(String pref : cleanedPrefStrings){
            if(!validatePrefs(pref) && !pref.equals("")) throw new InputException("Invalid input for preferences: " + pref);
        }
        
        ArrayList<String[]> prefs = new ArrayList<>();

        for(String prefString : cleanedPrefStrings){
            // ["DAY", "TIME", "Code.CourseLab", "Weight"]
            String[] splitPrefs = prefString.split(",");
            String[] cleanSplitPrefs = Arrays.stream(splitPrefs)
                                                .map(String::strip)
                                                .toArray(String[]::new);
            
            prefs.add(cleanSplitPrefs);
        }

        return prefs;
    }

    /*
    * @return - arraylist of [courselabstring, courselabstring]
    * 
    */
    public ArrayList<String[]> getPair() throws InputException{
        String pairsString = getField("Pair:(.*?)Partial assignments:");
        String[] pairStrings = pairsString.split("\n");

        for(String pairStr : pairStrings){
            if(!validateNcPair(pairStr) && !pairStr.equals("")) throw new InputException("Invalid input for not compatible: " + pairStr);
        }

        String[] spacedPairStrings = Arrays.stream(pairStrings) // Removing extra spaces 
                                .map(s -> s.replaceAll("\\s+", " ")) 
                                .toArray(String[]::new);
        
        ArrayList<String[]> pairs = new ArrayList<>();
        for(String pairString:spacedPairStrings){
            String[] splitPairString = pairString.split(",");
            splitPairString = Arrays.stream(splitPairString)
                            .map(String::strip)
                            .toArray(String[]::new);

            pairs.add(splitPairString);
        }
        return pairs;
    }

    /*
    * This method will just return cleaned String[] for partial assignments logic will be done in parse file 
    * 
    */
    public String[] getPartialAssignments() throws InputException{
        String partialAssignmentString = getField("Partial assignments:(.*$)");
        
        if(partialAssignmentString.equals("")){
            String[] empty = {};
            return empty;
        }
        
        String[] partialAssignmentStrings = partialAssignmentString.split("\n");
        String[] spacedPartialAssignmentStrings = Arrays.stream(partialAssignmentStrings)
                                                        .map(s -> s.replaceAll("\\s+", " ")) // Removing extra spaces 
                                                        .toArray(String[]::new);
                                                        
        String[] cleanedPartialAssignmentStrings = Arrays.stream(spacedPartialAssignmentStrings)
                                                            .map(String::strip)
                                                            .toArray(String[]::new);
        
        for(String partial : cleanedPartialAssignmentStrings){
            if(!validateUnwantedPartial(partial) && !partial.equals("")) throw new InputException("Invalid input for unwanted: " + partial);
        }

        return cleanedPartialAssignmentStrings;
    }

    /*
    * Parses a string in the form [courselabstring, courselabstring] into arraylist of the actual abjects
    * 
    * This probably should be in the Code.ParseFile class but I did this early on
    */    
    private ArrayList<CourseLab> parseCourseLabStringList(String[] courseLabStrings){
        String[] spacedCourseLabStrings = Arrays.stream(courseLabStrings)
                .map(s -> s.replaceAll("\\s+", " ")) // Removing extra spaces 
                .toArray(String[]::new);

        String[] cleanedCourseLabStrings = Arrays.stream(spacedCourseLabStrings)
                .map(String::strip)
                .toArray(String[]::new);

        ArrayList<CourseLab> courseLabObjectList = new ArrayList<>();    
        
        for(String cleanedCourseLabString:cleanedCourseLabStrings){
            CourseLab courseLabObject;
            courseLabObject = new CourseLab("LAB", cleanedCourseLabString);
            courseLabObjectList.add(courseLabObject);

        }
        return courseLabObjectList;
    }



    public boolean validateCourseString(String courseLine){
        Pattern fieldPattern = Pattern.compile("^"+courseRegex+"$",Pattern.DOTALL);
        Matcher fieldMatcher = fieldPattern.matcher(courseLine);
        return fieldMatcher.find();  
    }


    private boolean validateLabString(String courseLabLine){
        Pattern fieldPattern = Pattern.compile("^"+ labRegex +"$",Pattern.DOTALL);
        //[A-Z]+\\s\\d+\\s(?:LEC\\s\\d+|(?:LEC\\s\\d+\\s)?LAB\\s\\d+|(?:TUT|LAB)\\s\\d+)?$
        Matcher fieldMatcher = fieldPattern.matcher(courseLabLine);
        return fieldMatcher.find();
    }

    private boolean validateSlotString(String slotLine){
        Pattern fieldPattern = Pattern.compile("^"+ slotRegex +"$",Pattern.DOTALL);
        Matcher fieldMatcher = fieldPattern.matcher(slotLine);
        return fieldMatcher.find();
    }

    private boolean validateNcPair(String ncPairLine){
        Pattern fieldPattern = Pattern.compile("^"+"(?:"+courseRegex+"|"+ labRegex+")"+"\\s*,\\s*"+"(?:"+courseRegex+"|"+ labRegex+")"+"$",Pattern.DOTALL);
        Matcher fieldMatcher = fieldPattern.matcher(ncPairLine);
        return fieldMatcher.find();
    }

    private boolean validateUnwantedPartial(String unwantedPartialLine){
        Pattern fieldPattern = Pattern.compile("^"+"(?:"+courseRegex+"|"+ labRegex+")"+"\\s*,\\s*"+ partSlotRegex +"$",Pattern.DOTALL);
        Matcher fieldMatcher = fieldPattern.matcher(unwantedPartialLine);
        return fieldMatcher.find();
    }

    private boolean validatePrefs(String prefsLine){
        Pattern fieldPattern = Pattern.compile("^"+ partSlotRegex + "\\s*,\\s*" +"(?:"+ courseRegex +"|"+ labRegex +")"+ "\\s*,\\s*" + "\\d+" +"$",Pattern.DOTALL);
        Matcher fieldMatcher = fieldPattern.matcher(prefsLine);
        return fieldMatcher.find();
    }

}
