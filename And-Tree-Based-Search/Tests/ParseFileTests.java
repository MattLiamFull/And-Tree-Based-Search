package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import Source.CourseLab;
import Source.ParseFile;
import Source.Slot;
import Source.TimeSlotStringMap;
import Source.Exceptions.CourseLabDoesNotExistException;
import Source.Exceptions.SlotDoesNotExistException;
import Source.Exceptions.InputException;
import Source.Exceptions.PartAssignException;


public class ParseFileTests {
    TimeSlotStringMap tsMap;
    int[] time1 = {800,900};
    int[] time2 = {900, 1000};

    @Before
    public void setUp(){
        this.tsMap = new TimeSlotStringMap();
    }

    @Test
    public void testInputNoFail()throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\jorgConstrains\\input.txt", this.tsMap);
        parse.ProcessFile();
    }

    @Test
    public void testPartialCourseHasLabSlot(){
        ParseFile parse = new ParseFile("Tests/InputFiles/jorgConstrains/partialSlotIsLabSlot.txt", this.tsMap);
        assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());
    }

    @Test
    public void testPartialLabHasCourseSlot(){
        ParseFile parse = new ParseFile("Tests\\InputFiles\\jorgConstrains\\partialSlotIsCourseSlot.txt", this.tsMap);
        assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());
    }


    @Test
    public void test() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(4, cl.size());
    }

    @Test
    public void testCourseLabs() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(4, cl.size());
        assertEquals("CPSC 433 LEC 01", cl.get(0).getName());
        assertEquals("CPSC 457 LEC 01", cl.get(1).getName());
        assertEquals( "CPSC 433 LEC 01 LAB 01", cl.get(2).getName());
        assertEquals("CPSC 457 LEC 01 LAB 01", cl.get(3).getName());
    }

    @Test
    public void testSlots() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        Slot slot1 = new Slot("LEC", 3, 2, "MO", time1);
        Slot slot2 = new Slot("LEC", 3, 2, "MO", time2);
        Slot slot3 = new Slot("LAB", 3, 2, "MO", time1);
        Slot slot4 = new Slot("LAB", 3, 2, "MO", time2);
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<Slot> sl = parse.getSlotList();
        assertEquals(4, sl.size());
        assertTrue(slotEquals(sl.get(0), slot2));
        assertTrue(slotEquals(sl.get(1), slot1));
        assertTrue(slotEquals(sl.get(2), slot4));
        assertTrue(slotEquals(sl.get(3), slot3));
    }

    @Test
    public void testNotCompatible() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(cl.get(0).getNonCompatibles().get(0).getName(), "CPSC 457 LEC 01");
        assertEquals(cl.get(1).getNonCompatibles().get(0).getName(), "CPSC 433 LEC 01");
    }

    @Test 
    public void testUnwanted() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        Slot slot1 = new Slot("LEC", 3, 2, "MO", time1);
        Slot slot2 = new Slot("LEC", 3, 2, "MO", time2);
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertTrue(slotEquals(slot2, cl.get(0).getUnwantedSlots().get(0)));
        assertTrue(slotEquals(slot1, cl.get(1).getUnwantedSlots().get(0)));
    }

    @Test
    public void testPrefs() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        Slot slot1 = new Slot("LEC", 3, 2, "MO", time1);
        //Slot slot2 = new Slot("LEC", 3, 2, "MO", time2);
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        Object prefSlotObject = cl.get(0).getPreferenceList().get(0).get(0);
        Object prefWeightObject = cl.get(0).getPreferenceList().get(0).get(1);
        Slot prefSlot = (Slot) prefSlotObject;
        int prefWeight = (int) prefWeightObject;
        assertTrue(slotEquals(slot1, prefSlot));
        assertEquals(4, prefWeight);
    }

    @Test 
    public void testPair() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");  
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        CourseLab cl1 = cl.get(2);
        CourseLab cl2 = cl.get(1);
        assertTrue(courseLabEquals(courseLab2, cl2.getPairList().get(0)));
        //assertTrue(courseLabEquals(courseLab1, cl1.getPairList().get(0)));
    }

    @Test
    public void testPartialAssignment() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests/InputFiles/testInput.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();

        CourseLab courseLab1 = cl.get(3);
        Slot slot1 = new Slot("LAB", 3, 2, "MO", time2);
        assertTrue(slotEquals(slot1, courseLab1.getPartAssign()));
    }

    @Test
    public void testEmptyAll() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\emptyInput.txt", this.tsMap);
        assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());

        ArrayList<CourseLab> cl = parse.getCourseLabList();
        ArrayList<Slot> slots = parse.getSlotList();
    }

    @Test 
    public void testNotCompatibleEmpty() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\notCompatibleEmpty.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(0, cl.get(0).getNonCompatibles().size());
    }

    @Test 
    public void testunwantedEmpty() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\unwantedEmpty.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(0, cl.get(0).getUnwantedSlots().size());
    }

    @Test 
    public void testCourseEmpty(){
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\courseEmpty.txt", this.tsMap);
        assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());
    }

    @Test 
    public void testCourseSlotsEmpty(){
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\courseSlotEmpty.txt", this.tsMap);
        assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());
    }

    @Test 
    public void testLabSlotEmpty(){ // Where there are labs but no lab slots 
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\labSlotEmpty.txt", this.tsMap);
        assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());  
    }



    @Test
    public void testPrefsEmpty() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\prefsEmpty.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(0, cl.get(0).getPreferenceList().size());
    }

    @Test 
    public void testPairEmpty() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\pairEmpty.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(0, cl.get(0).getPairList().size());
    }

    @Test
    public void testPartialAssignmentsEmpty() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\emptyInputs\\partialAssignmentEmpty.txt", this.tsMap);
        parse.ProcessFile();
        ArrayList<CourseLab> cl = parse.getCourseLabList();
        assertEquals(null, cl.get(0).getPartAssign());        
    }

    @Test 
    public void testgarbageCourseSlot() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbageCourseSlot.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());
    }

    @Test 
    public void testgarbageLabSlot() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbageLabSlot.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());
    }

    @Test 
    public void testgarbageCourse() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbageCourse.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testgarbageLab() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbageLab.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testgarbageNotCompat() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbageNotCompat.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testgarbageUnwanted() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbageUnwanted.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testgarbagePrefs() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbagePrefs.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testgarbagePair() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbagePair.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testgarbagePartial() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\garbageInputs\\garbagePartial.txt", this.tsMap);
        assertThrows(InputException.class,()-> parse.ProcessFile());

    }

    @Test 
    public void testDeptInstance1() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
         ParseFile parse = new ParseFile("Tests\\InputFiles\\departmentInstances\\deptinst1.txt", this.tsMap);
         parse.ProcessFile();
    }

    @Test 
    public void testDeptInstance2() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
         ParseFile parse = new ParseFile("Tests\\InputFiles\\departmentInstances\\deptinst2.txt", this.tsMap);
         parse.ProcessFile();
    }

    @Test 
    public void testNonExistentCourseNotCompat() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\notCompatibleCourseDNE.txt", this.tsMap);
        assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());  
    }

    @Test 
    public void testNonExistentCourseUnwanted() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\unwantedCourseDNE.txt", this.tsMap);
        assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());  
    }

    // Commented out because this constrint should just be ignored if it is incorrect
    // @Test 
    // public void testNonExistentCoursePrefs() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
    //     ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\prefsCourseDNE.txt", this.tsMap);
    //     assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());  
    // }

    @Test 
    public void testNonExistentCoursePair() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\pairCourseDNE.txt", this.tsMap);
        assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());  
    }

    @Test 
    public void testNonExistentCoursePartial() throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\partialCourseDNE.txt", this.tsMap);
        assertThrows(CourseLabDoesNotExistException.class,()-> parse.ProcessFile());  
    }

    @Test 
    public void testNonExistentSlotUnwanted()throws InputException, PartAssignException, CourseLabDoesNotExistException, SlotDoesNotExistException{
        ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\unwantedSlotDNE .txt", this.tsMap);
        assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());  
    }

    // Commented out because this constrint should just be ignored if it is incorrect
    // @Test 
    // public void testNonExistentSlotPrefs(){
    //     ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\prefSlotDNE .txt", this.tsMap);
    //     assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());
    // }

    @Test
    public void testNonExistentSlotPartial(){
        ParseFile parse = new ParseFile("Tests\\InputFiles\\doesNotExist\\partialSlotDNE .txt", this.tsMap);
        assertThrows(SlotDoesNotExistException.class,()-> parse.ProcessFile());
    }


    public boolean courseLabEquals(CourseLab cl1, CourseLab cl2){
        if(cl1 == null || cl2 == null){
            return false;
        }
        if(!cl1.getName().equals(cl2.getName())){
            return false;
        }
        if(!cl1.getType().equals(cl2.getType())){
            return false;
        }
        return true;
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

