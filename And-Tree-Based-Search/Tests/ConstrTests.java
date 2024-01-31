package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Source.CourseLab;
import Source.Assign;
import Source.Constr;
import Source.Slot;
public class ConstrTests {

    private ArrayList<CourseLab> courseLabList = new ArrayList<>();
    private ArrayList<Slot> slotList = new ArrayList<>();
    private Assign testAssign;
    private Constr constr;

    @Test
    public void invalidPartialConstrCoursemax() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 1, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void validPartialConstrCoursemaxAtMax() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCoursemaxBelowMax() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 3, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void invalidPartialConstrLabmax() {
        CourseLab courseLab1 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 471 LEC 01 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LAB", 1, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void validPartialConstrLabmaxAtMax() {
        CourseLab courseLab1 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 471 LEC 01 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LAB", 2, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrLabmaxBelowMax() {
        CourseLab courseLab1 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 471 LEC 01 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LAB", 3, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrOnlyCourses() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCourseWithLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCourseWithLabsDiffDaysSimilarTimes() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {900, 1000};
        Slot slot3 = new Slot("LAB", 2, 0, "TU", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrMultipleCourseWithLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab4.addNonCompatibles(courseLab6);
        courseLab5.addNonCompatibles(courseLab4);
        courseLab6.addNonCompatibles(courseLab4);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        courseLabList.add(courseLab6);
        courseLab6.setGlobalOrderingIndex(5);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(6, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot1, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 4);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrNonCompatibleCoursesAndCourses() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LEC", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrNonCompatibleCoursesAndLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab1.addNonCompatibles(courseLab4);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab4.addNonCompatibles(courseLab6);
        courseLab4.addNonCompatibles(courseLab1);
        courseLab5.addNonCompatibles(courseLab4);
        courseLab6.addNonCompatibles(courseLab4);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 3, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        courseLabList.add(courseLab6);
        courseLab6.setGlobalOrderingIndex(5);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(6, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 4);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrNonCompatibleLabsAndLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab2.addNonCompatibles(courseLab3);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab2);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab4.addNonCompatibles(courseLab6);
        courseLab5.addNonCompatibles(courseLab4);
        courseLab6.addNonCompatibles(courseLab4);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 3, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        courseLabList.add(courseLab6);
        courseLab6.setGlobalOrderingIndex(5);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(6, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 4);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCoursePartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.setPartAssign(slot1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab2.setPartAssign(slot2);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCourseAndLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.setPartAssign(slot1);
        courseLab2.setPartAssign(slot2);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCourseUnwantedSlots() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrLabUnwantedSlots() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab2.addUnwantedSlots(slot3);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCourseAndLabUnwantedSlots() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot3);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrEveningCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 91");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1900};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrNoTU11AMCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCPSC813Course() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validPartialConstrCPSC913Course() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void invalidPartialConstrCourseAndLabSameTime() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {900, 1000};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCourseAndLabOverlap() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {800, 1000};
        Slot slot2 = new Slot("LAB", 2, 0, "FR", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCourseAndCourseNonCompatible() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 433 LEC 02 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab1.addNonCompatibles(courseLab4);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab5.addNonCompatibles(courseLab4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(5, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot1, 3);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCourseAndLabNonCompatible() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 433 LEC 02 LAB 01");
        int[] slot1Times = {930, 1100};
        Slot slot1 = new Slot("LEC", 2, 0, "TU", slot1Times);
        int[] slot2Times = {1000, 1100};
        Slot slot2 = new Slot("LAB", 2, 0, "TU", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab2.addNonCompatibles(courseLab4);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab2);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab5.addNonCompatibles(courseLab4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(5, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(3);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrLabAndLabNonCompatible() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 433 LEC 02 LAB 01");
        int[] slot1Times = {930, 1100};
        Slot slot1 = new Slot("LEC", 2, 0, "TU", slot1Times);
        int[] slot2Times = {1000, 1100};
        Slot slot2 = new Slot("LAB", 2, 0, "TU", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab2.addNonCompatibles(courseLab3);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab2);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab5.addNonCompatibles(courseLab4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(5, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(3);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCoursePartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.setPartAssign(slot1);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab2.setPartAssign(slot3);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCourseAndLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.setPartAssign(slot1);
        courseLab2.setPartAssign(slot3);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCourseUnwantedSlot() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrLabUnwantedSlot() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab2.addUnwantedSlots(slot2);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCourseAndLabUnwantedSlot() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot2);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrEveningCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 91");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1900};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrNoTU11AMCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLab1.addUnwantedSlots(slot4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCPSC813CourseDirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCPSC813CourseIndirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot5, 1);
        testAssign.incrSlotCountAtIndex(4);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCPSC913CourseDirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidPartialConstrCPSC913CourseIndirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot5, 1);
        testAssign.incrSlotCountAtIndex(4);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCoursemax() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 2);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void validFullConstrCoursemaxAtMax() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 3, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 2);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCoursemaxBelowMax() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 4, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 2);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void invalidFullConstrLabmax() {
        CourseLab courseLab1 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 471 LEC 01 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LAB", 2, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 2);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void validFullConstrLabmaxAtMax() {
        CourseLab courseLab1 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 471 LEC 01 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LAB", 3, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 2);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrLabmaxBelowMax() {
        CourseLab courseLab1 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 471 LEC 01 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LAB", 4, 0, "MO", slot1Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 1);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot1, 2);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrOnlyCourses() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(1, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCourseWithLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCourseWithLabsDiffDaysSimilarTimes() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {900, 1000};
        Slot slot3 = new Slot("LAB", 2, 0, "TU", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrMultipleCourseWithLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab4.addNonCompatibles(courseLab6);
        courseLab5.addNonCompatibles(courseLab4);
        courseLab6.addNonCompatibles(courseLab4);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        courseLabList.add(courseLab6);
        courseLab6.setGlobalOrderingIndex(5);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(6, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot1, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 4);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot2, 5);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrNonCompatibleCoursesAndCourses() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab3 = new CourseLab("LEC", "CPSC 471 LEC 01");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LEC", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrNonCompatibleCoursesAndLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab1.addNonCompatibles(courseLab4);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab4.addNonCompatibles(courseLab6);
        courseLab4.addNonCompatibles(courseLab1);
        courseLab5.addNonCompatibles(courseLab4);
        courseLab6.addNonCompatibles(courseLab4);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 3, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        courseLabList.add(courseLab6);
        courseLab6.setGlobalOrderingIndex(5);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(6, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 4);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot2, 5);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrNonCompatibleLabsAndLabs() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 457 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 457 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab2.addNonCompatibles(courseLab3);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab2);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab4.addNonCompatibles(courseLab6);
        courseLab5.addNonCompatibles(courseLab4);
        courseLab6.addNonCompatibles(courseLab4);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 3, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 3, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 3, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        courseLabList.add(courseLab6);
        courseLab6.setGlobalOrderingIndex(5);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(6, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 4);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot2, 5);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCoursePartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.setPartAssign(slot1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab2.setPartAssign(slot2);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCourseAndLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.setPartAssign(slot1);
        courseLab2.setPartAssign(slot2);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCourseUnwantedSlots() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrLabUnwantedSlots() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab2.addUnwantedSlots(slot3);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCourseAndLabUnwantedSlots() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1500, 1600};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot3);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrEveningCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 91");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1900};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrNoTU11AMCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCPSC813Course() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void validFullConstrCPSC913Course() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertTrue(result);

    }

    @Test
    public void invalidFullConstrCourseAndLabSameTime() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {900, 1000};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCourseAndLabOverlap() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {800, 1000};
        Slot slot2 = new Slot("LAB", 2, 0, "FR", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCourseAndCourseNonCompatible() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 433 LEC 02 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab1.addNonCompatibles(courseLab4);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab5.addNonCompatibles(courseLab4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        testAssign = new Assign(5, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot1, 3);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 4);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCourseAndLabNonCompatible() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 433 LEC 02 LAB 01");
        int[] slot1Times = {930, 1100};
        Slot slot1 = new Slot("LEC", 2, 0, "TU", slot1Times);
        int[] slot2Times = {1000, 1100};
        Slot slot2 = new Slot("LAB", 2, 0, "TU", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab2.addNonCompatibles(courseLab4);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab4.addNonCompatibles(courseLab2);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab5.addNonCompatibles(courseLab4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(5, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 4);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrLabAndLabNonCompatible() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 433 LEC 02 LAB 01");
        int[] slot1Times = {930, 1100};
        Slot slot1 = new Slot("LEC", 2, 0, "TU", slot1Times);
        int[] slot2Times = {1000, 1100};
        Slot slot2 = new Slot("LAB", 2, 0, "TU", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab2.addNonCompatibles(courseLab3);
        courseLab3.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab2);
        courseLab4.addNonCompatibles(courseLab5);
        courseLab5.addNonCompatibles(courseLab4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        courseLabList.add(courseLab4);
        courseLab4.setGlobalOrderingIndex(3);
        courseLabList.add(courseLab5);
        courseLab5.setGlobalOrderingIndex(4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(5, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot3, 2);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot4, 3);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 4);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCoursePartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.setPartAssign(slot1);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab2.setPartAssign(slot3);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCourseAndLabPartAssign() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.setPartAssign(slot1);
        courseLab2.setPartAssign(slot3);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCourseUnwantedSlot() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrLabUnwantedSlot() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab2.addUnwantedSlots(slot2);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCourseAndLabUnwantedSlot() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1300, 1400};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot2);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrEveningCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 91");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1900};
        Slot slot4 = new Slot("LEC", 2, 0, "MO", slot4Times);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrNoTU11AMCourse() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLab1.addUnwantedSlots(slot4);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 2);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCPSC813CourseDirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCPSC813CourseIndirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot2, 1);
        testAssign.incrSlotCountAtIndex(1);
        testAssign.ChangeElementOfAssign(slot5, 2);
        testAssign.incrSlotCountAtIndex(4);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCPSC913CourseDirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot4, 0);
        testAssign.incrSlotCountAtIndex(3);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }

    @Test
    public void invalidFullConstrCPSC913CourseIndirect() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1800, 1930};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1800, 1900};
        Slot slot5 = new Slot("LAB", 2, 0, "TU", slot5Times);

        courseLab1.addNonCompatibles(courseLab2);
        courseLab1.addNonCompatibles(courseLab3);
        courseLab2.addNonCompatibles(courseLab1);
        courseLab3.addNonCompatibles(courseLab1);

        courseLab1.addUnwantedSlots(slot4);
        courseLab2.addUnwantedSlots(slot5);
        courseLab3.addUnwantedSlots(slot5);

        courseLabList.add(courseLab1);
        courseLab1.setGlobalOrderingIndex(0);
        courseLabList.add(courseLab2);
        courseLab2.setGlobalOrderingIndex(1);
        courseLabList.add(courseLab3);
        courseLab3.setGlobalOrderingIndex(2);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        testAssign = new Assign(3, slotList);
        testAssign.ChangeElementOfAssign(slot1, 0);
        testAssign.incrSlotCountAtIndex(0);
        testAssign.ChangeElementOfAssign(slot3, 1);
        testAssign.incrSlotCountAtIndex(2);
        testAssign.ChangeElementOfAssign(slot5, 2);
        testAssign.incrSlotCountAtIndex(4);
        constr = new Constr(courseLabList, slotList);

        boolean result = constr.checkHardConstraints(testAssign);
        assertFalse(result);

    }
}
