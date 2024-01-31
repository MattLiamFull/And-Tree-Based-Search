package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Source.CourseLab;
import Source.Assign;
import Source.Constr;
import Source.Slot;
import Source.Validator;
public class ValidatorTests {

    private ArrayList<CourseLab> courseLabList = new ArrayList<>();
    private ArrayList<Slot> slotList = new ArrayList<>();
    private Validator validator;

    @Test
    public void testSetCourseLabIndices() {
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

        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        courseLabList.add(courseLab1);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        validator = new Validator(courseLabList, slotList);

        validator.setCourseLabListIndices(courseLabList);
        assertEquals(2, courseLab1.getGlobalOrderingIndex());
        assertEquals(0, courseLab2.getGlobalOrderingIndex());
        assertEquals(1, courseLab3.getGlobalOrderingIndex());
    }

    @Test
    public void testSetTU11AMConstraint() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        validator = new Validator(courseLabList, slotList);

        validator.setTU11AMConstraint(courseLabList, slotList);

        assertEquals(1, courseLab1.getUnwantedSlots().size());
        assertEquals(0, courseLab2.getUnwantedSlots().size());
        assertEquals(0, courseLab3.getUnwantedSlots().size());

        assertEquals(slot4, courseLab1.getUnwantedSlots().get(0));
    }

    @Test
    public void testSetCoursesAndLabsConstraint() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab courseLab5 = new CourseLab("LEC", "CPSC 471 LEC 01");
        CourseLab courseLab6 = new CourseLab("LAB", "CPSC 471 LAB 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        courseLabList.add(courseLab4);
        courseLabList.add(courseLab5);
        courseLabList.add(courseLab6);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        validator = new Validator(courseLabList, slotList);

        validator.setCoursesAndLabsConstraint(courseLabList);

        assertEquals(2, courseLab1.getNonCompatibles().size());
        assertEquals(1, courseLab2.getNonCompatibles().size());
        assertEquals(1, courseLab3.getNonCompatibles().size());
        assertEquals(0, courseLab4.getNonCompatibles().size());
        assertEquals(1, courseLab5.getNonCompatibles().size());
        assertEquals(1, courseLab6.getNonCompatibles().size());

        assertEquals(courseLab2, courseLab1.getNonCompatibles().get(0));
        assertEquals(courseLab3, courseLab1.getNonCompatibles().get(1));
        assertEquals(courseLab1, courseLab2.getNonCompatibles().get(0));
        assertEquals(courseLab1, courseLab3.getNonCompatibles().get(0));
        assertEquals(courseLab6, courseLab5.getNonCompatibles().get(0));
        assertEquals(courseLab5, courseLab6.getNonCompatibles().get(0));
    }

    @Test
    public void testSet500LevelConstraint() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 433 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 501 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 501 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LEC", "CPSC 501 LEC 02");
        CourseLab courseLab7 = new CourseLab("LEC", "CPSC 502 LEC 01");
        CourseLab courseLab8 = new CourseLab("LAB", "CPSC 502 LAB 01");
        CourseLab courseLab9 = new CourseLab("LEC", "CPSC 533 LEC 01");
        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        courseLabList.add(courseLab4);
        courseLabList.add(courseLab5);
        courseLabList.add(courseLab6);
        courseLabList.add(courseLab7);
        courseLabList.add(courseLab8);
        courseLabList.add(courseLab9);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        validator = new Validator(courseLabList, slotList);

        validator.set500LevelConstraints(courseLabList, slotList);

        assertEquals(0, courseLab1.getNonCompatibles().size());
        assertEquals(0, courseLab2.getNonCompatibles().size());
        assertEquals(0, courseLab3.getNonCompatibles().size());
        assertEquals(3, courseLab4.getNonCompatibles().size());
        assertEquals(0, courseLab5.getNonCompatibles().size());
        assertEquals(3, courseLab6.getNonCompatibles().size());
        assertEquals(3, courseLab7.getNonCompatibles().size());
        assertEquals(0, courseLab8.getNonCompatibles().size());
        assertEquals(3, courseLab9.getNonCompatibles().size());

        assertEquals(courseLab6, courseLab4.getNonCompatibles().get(0));
        assertEquals(courseLab7, courseLab4.getNonCompatibles().get(1));
        assertEquals(courseLab9, courseLab4.getNonCompatibles().get(2));

        assertEquals(courseLab4, courseLab6.getNonCompatibles().get(0));
        assertEquals(courseLab7, courseLab6.getNonCompatibles().get(1));
        assertEquals(courseLab9, courseLab6.getNonCompatibles().get(2));

        assertEquals(courseLab4, courseLab7.getNonCompatibles().get(0));
        assertEquals(courseLab6, courseLab7.getNonCompatibles().get(1));
        assertEquals(courseLab9, courseLab7.getNonCompatibles().get(2));

        assertEquals(courseLab4, courseLab9.getNonCompatibles().get(0));
        assertEquals(courseLab6, courseLab9.getNonCompatibles().get(1));
        assertEquals(courseLab7, courseLab9.getNonCompatibles().get(2));
    }

    @Test
    public void testSetCPSC813913Constraint() {
        CourseLab courseLab1 = new CourseLab("LEC", "CPSC 313 LEC 01");
        CourseLab courseLab2 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 01");
        CourseLab courseLab3 = new CourseLab("LAB", "CPSC 313 LEC 01 LAB 02");
        CourseLab courseLab4 = new CourseLab("LEC", "CPSC 413 LEC 01");
        CourseLab courseLab5 = new CourseLab("LAB", "CPSC 413 LEC 01 LAB 01");
        CourseLab courseLab6 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab courseLab7 = new CourseLab("LEC", "CPSC 501 LEC 01");

        int[] slot1Times = {900, 1000};
        Slot slot1 = new Slot("LEC", 2, 0, "MO", slot1Times);
        int[] slot2Times = {1100, 1200};
        Slot slot2 = new Slot("LAB", 2, 0, "MO", slot2Times);
        int[] slot3Times = {1300, 1400};
        Slot slot3 = new Slot("LAB", 2, 0, "MO", slot3Times);
        int[] slot4Times = {1100, 1230};
        Slot slot4 = new Slot("LEC", 2, 0, "TU", slot4Times);
        int[] slot5Times = {1700, 1830};
        Slot slot5 = new Slot("LEC", 2, 0, "TU", slot5Times);
        int[] slot6Times = {1700, 1800};
        Slot slot6 = new Slot("LAB", 2, 0, "TU", slot6Times);
        int[] slot7Times = {1800, 1930};
        Slot slot7 = new Slot("LAB", 2, 0, "TU", slot7Times);

        courseLabList.add(courseLab1);
        courseLabList.add(courseLab2);
        courseLabList.add(courseLab3);
        courseLabList.add(courseLab4);
        courseLabList.add(courseLab5);
        courseLabList.add(courseLab6);
        courseLabList.add(courseLab7);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);
        slotList.add(slot6);
        slotList.add(slot7);

        validator = new Validator(courseLabList, slotList);

        validator.setCoursesAndLabsConstraint(courseLabList);
        courseLab5.addNonCompatibles(courseLab7);
        validator.setCPSC813913Constraints(courseLabList, slotList);

        assertEquals(3, courseLab1.getUnwantedSlots().size());
        assertEquals(3, courseLab2.getUnwantedSlots().size());
        assertEquals(3, courseLab3.getUnwantedSlots().size());
        assertEquals(3, courseLab4.getUnwantedSlots().size());
        assertEquals(3, courseLab5.getUnwantedSlots().size());
        assertEquals(0, courseLab6.getUnwantedSlots().size());
        assertEquals(3, courseLab7.getUnwantedSlots().size());

        assertEquals(slot5, courseLab1.getUnwantedSlots().get(0));
        assertEquals(slot6, courseLab1.getUnwantedSlots().get(1));
        assertEquals(slot7, courseLab1.getUnwantedSlots().get(2));

        assertEquals(slot5, courseLab2.getUnwantedSlots().get(0));
        assertEquals(slot6, courseLab2.getUnwantedSlots().get(1));
        assertEquals(slot7, courseLab2.getUnwantedSlots().get(2));

        assertEquals(slot5, courseLab3.getUnwantedSlots().get(0));
        assertEquals(slot6, courseLab3.getUnwantedSlots().get(1));
        assertEquals(slot7, courseLab3.getUnwantedSlots().get(2));

        assertEquals(slot5, courseLab4.getUnwantedSlots().get(0));
        assertEquals(slot6, courseLab4.getUnwantedSlots().get(1));
        assertEquals(slot7, courseLab4.getUnwantedSlots().get(2));

        assertEquals(slot5, courseLab5.getUnwantedSlots().get(0));
        assertEquals(slot6, courseLab5.getUnwantedSlots().get(1));
        assertEquals(slot7, courseLab5.getUnwantedSlots().get(2));

        assertEquals(slot5, courseLab7.getUnwantedSlots().get(0));
        assertEquals(slot6, courseLab7.getUnwantedSlots().get(1));
        assertEquals(slot7, courseLab7.getUnwantedSlots().get(2));
    }
}
