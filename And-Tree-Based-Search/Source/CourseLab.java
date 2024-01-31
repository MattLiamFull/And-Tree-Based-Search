package Source;

import java.util.ArrayList;

public class CourseLab {

    private String type;
    private String name;

    //hard constraint non-compatible list
    private ArrayList<CourseLab> nonCompatibles = new ArrayList<>();
    private ArrayList<Slot> unwantedSlots = new ArrayList<>();


    //list of courselabs that WANT to be assigned at the same time as this one
    private ArrayList<CourseLab> pairList = new ArrayList<>();

    //list of courselabs with same course but different section
    private ArrayList<CourseLab> sectionList = new ArrayList<>();

    private Slot partAssign;

    //an array list of "tuples"-> array lists of two elements. {(slot, point), (slot2, points2), ...}
    private ArrayList<ArrayList<Object>> preferenceList = new ArrayList<>();

    private int globalOrderingindex;

    public CourseLab(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public ArrayList<CourseLab> getNonCompatibles() {
        return nonCompatibles;
    }

    public void addNonCompatibles(CourseLab courseLab) {
        this.nonCompatibles.add(courseLab);
    }

    public ArrayList<Slot> getUnwantedSlots() {
        return unwantedSlots;
    }

    public void addUnwantedSlots(Slot unwantedSlot) {
        this.unwantedSlots.add(unwantedSlot);
    }

    public ArrayList<CourseLab> getPairList() {
        return pairList;
    }

    public void addToPairList(CourseLab courseLab) {
        this.pairList.add(courseLab);
    }

    public ArrayList<CourseLab> getSectionList() {
        return sectionList;
    }

    public void addToSectionList(CourseLab courseLab) {
        this.sectionList.add(courseLab);
    }

    public ArrayList<ArrayList<Object>> getPreferenceList() {
        return preferenceList;
    }

    public void addToPreferenceList(ArrayList<Object> preferencePair) {
        this.preferenceList.add(preferencePair);
    }

    public void setPartAssign(Slot partAssign){
        this.partAssign = partAssign;
    }

    public Slot getPartAssign(){
        return this.partAssign;
    }

    public int getGlobalOrderingIndex(){
        return this.globalOrderingindex;
    }

    // to set each courselab index for easy lookup
    public void setGlobalOrderingIndex(int index){
        this.globalOrderingindex = index;
    }

    /**
     * Function to return the human-readable needed information from a courselab
     * @return The name of this courselab.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
