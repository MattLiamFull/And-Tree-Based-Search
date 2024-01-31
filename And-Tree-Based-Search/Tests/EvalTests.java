package Tests;

import Source.Assign;
import Source.CourseLab;
import Source.Eval;
import Source.Slot;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class EvalTests {

    private ArrayList<CourseLab> courseLabList = new ArrayList<>();
    private ArrayList<Slot> slotList = new ArrayList<>();
    private Assign globalOrdering = new Assign();
    private Assign testAssign;
    private Eval evalInstance;
    private HashMap<Slot, HashSet<CourseLab>> hashMap;

    /**
     * Function to change the weights and penalties of eval.
     */
    public void ChangeEvalWeights(int penCourseMin, int penLabMin, int penNotPaired, int penSection, int wMinFilled, int wPref, int wPair, int wSecDiff) {
        evalInstance = new Eval(penCourseMin, penLabMin, penNotPaired, penSection, wMinFilled, wPref, wPair, wSecDiff, courseLabList, slotList, globalOrdering);
    }

    /*
    START PERFECT INPUT TESTS
     */

    /**
     * Function to set up perfect test variables.
     */
    public void SetUpPerfectTests() {

        /*
        CREATE COURSELABS AND SLOTS
        CREATE COURSELAB LIST AND SLOTLIST
        CREATE GLOBAL ORDERING
        CREATE TEST ASSIGN
        CREATE EVAL INSTANCE
        CALL HASHMAP CREATE FUNCTION
         */

        CourseLab course1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab course2 = new CourseLab("LEC", "CPSC 433 LEC 02");
        CourseLab course3 = new CourseLab("LAB", "CPSC 435 LEC 02 LAB 01");
        CourseLab course4 = new CourseLab("LEC", "CPSC 436 LEC 03");

        Slot slot1 = new Slot("LEC", 100, 2, "MO", new int[]{900, 1000});
        Slot slot2 = new Slot("LEC", 100, 1, "MO", new int[]{1100, 1200});
        Slot slot3 = new Slot("LAB", 100, 1, "MO", new int[]{1300, 1400});

        course1.addToPairList(course4);

        course1.addToPreferenceList(new ArrayList<>(Arrays.asList(slot1, 0)));

        courseLabList = new ArrayList<>();
        courseLabList.add(course1);
        courseLabList.add(course2);
        courseLabList.add(course3);
        courseLabList.add(course4);

        slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        //creating the global ordering
        globalOrdering = new Assign();
        for (int i = 0; i < courseLabList.size(); i++) {

            globalOrdering.AddElementToAssign(courseLabList.get(i));
            courseLabList.get(i).setGlobalOrderingIndex(i);
        }

        //creating the test assign and filling it with all slots
        testAssign = new Assign(globalOrdering.getSize(), slotList);
        testAssign.ChangeElementOfAssign(slotList.get(0), 0);
        testAssign.ChangeElementOfAssign(slotList.get(1), 1);
        testAssign.ChangeElementOfAssign(slotList.get(2), 2);
        testAssign.ChangeElementOfAssign(slotList.get(0), 3);

        evalInstance = new Eval(
                1, 1, 1, 1,
                1, 1, 1, 1,
                courseLabList, slotList, globalOrdering
        );

        //creating the hashMap to use in the test
        evalInstance.setCourseLabInSlot(testAssign.CreateHashMapFromAssign(globalOrdering));
        hashMap = evalInstance.getCourseLabInSlot();

    }

    /**
     * Function to set up perfect test without pairs and preference variables.
     */
    public void SetUpBlankParamsTests() {

        /*
        CREATE COURSELABS AND SLOTS
        CREATE COURSELAB LIST AND SLOTLIST
        CREATE GLOBAL ORDERING
        CREATE TEST ASSIGN
        CREATE EVAL INSTANCE
        CALL HASHMAP CREATE FUNCTION
         */

        CourseLab course1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab course2 = new CourseLab("LEC", "CPSC 434 LEC 02");
        CourseLab course3 = new CourseLab("LAB", "CPSC 435 LEC 02 LAB 01");
        CourseLab course4 = new CourseLab("LEC", "CPSC 436 LEC 03");

        Slot slot1 = new Slot("LEC", 100, 2, "MO", new int[]{900, 1000});
        Slot slot2 = new Slot("LEC", 100, 1, "MO", new int[]{1100, 1200});
        Slot slot3 = new Slot("LAB", 100, 1, "MO", new int[]{1300, 1400});

        courseLabList = new ArrayList<>();
        courseLabList.add(course1);
        courseLabList.add(course2);
        courseLabList.add(course3);
        courseLabList.add(course4);

        slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        //creating the global ordering
        globalOrdering = new Assign();
        for (int i = 0; i < courseLabList.size(); i++) {

            globalOrdering.AddElementToAssign(courseLabList.get(i));
            courseLabList.get(i).setGlobalOrderingIndex(i);
        }

        //creating the test assign and filling it with all slots
        testAssign = new Assign(globalOrdering.getSize(), slotList);
        testAssign.ChangeElementOfAssign(slotList.get(0), 0);
        testAssign.ChangeElementOfAssign(slotList.get(1), 1);
        testAssign.ChangeElementOfAssign(slotList.get(2), 2);
        testAssign.ChangeElementOfAssign(slotList.get(0), 3);

        evalInstance = new Eval(
                1, 1, 1, 1,
                1, 1, 1, 1,
                courseLabList, slotList, globalOrdering
        );

        //creating the hashMap to use in the test
        evalInstance.setCourseLabInSlot(testAssign.CreateHashMapFromAssign(globalOrdering));
        hashMap = evalInstance.getCourseLabInSlot();

    }

    /**
     * Function to set up a part assign.
     */
    public void SetUpPartAssignTests() {

        /*
        CREATE COURSELABS AND SLOTS
        CREATE COURSELAB LIST AND SLOTLIST
        CREATE GLOBAL ORDERING
        CREATE TEST ASSIGN
        CREATE EVAL INSTANCE
        CALL HASHMAP CREATE FUNCTION
         */

        CourseLab course1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab course2 = new CourseLab("LEC", "CPSC 434 LEC 02");
        CourseLab course3 = new CourseLab("LAB", "CPSC 435 LEC 02 LAB 01");
        CourseLab course4 = new CourseLab("LEC", "CPSC 436 LEC 03");

        Slot slot1 = new Slot("LEC", 100, 2, "MO", new int[]{900, 1000});
        Slot slot2 = new Slot("LEC", 100, 1, "MO", new int[]{1100, 1200});
        Slot slot3 = new Slot("LAB", 100, 1, "MO", new int[]{1300, 1400});

        course1.addToPairList(course4);

        courseLabList = new ArrayList<>();
        courseLabList.add(course1);
        courseLabList.add(course2);
        courseLabList.add(course3);
        courseLabList.add(course4);

        slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        //creating the global ordering
        globalOrdering = new Assign();
        for (int i = 0; i < courseLabList.size(); i++) {

            globalOrdering.AddElementToAssign(courseLabList.get(i));
            courseLabList.get(i).setGlobalOrderingIndex(i);
        }

        //creating the test assign and filling it with all slots
        testAssign = new Assign(globalOrdering.getSize(), slotList);
        testAssign.ChangeElementOfAssign(slotList.get(0), 0);
        testAssign.ChangeElementOfAssign(slotList.get(1), 1);
        testAssign.ChangeElementOfAssign(slotList.get(2), 2);
        //course4 not assigned

        System.out.println(testAssign.getAssign());

        evalInstance = new Eval(
                1, 1, 1, 1,
                1, 1, 1, 1,
                courseLabList, slotList, globalOrdering
        );

        //creating the hashMap to use in the test
        evalInstance.setCourseLabInSlot(testAssign.CreateHashMapFromAssign(globalOrdering));
        hashMap = evalInstance.getCourseLabInSlot();

    }

    /**
     * Function to set up single errors.
     */
    public void SetUpSingleErrorTests() {

        /*
        CREATE COURSELABS AND SLOTS
        CREATE COURSELAB LIST AND SLOTLIST
        CREATE GLOBAL ORDERING
        CREATE TEST ASSIGN
        CREATE EVAL INSTANCE
        CALL HASHMAP CREATE FUNCTION
         */

        CourseLab course1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab course2 = new CourseLab("LEC", "CPSC 434 LEC 02");
        CourseLab course3 = new CourseLab("LAB", "CPSC 435 LEC 02 LAB 01");
        CourseLab course4 = new CourseLab("LEC", "CPSC 434 LEC 03");

        Slot slot1 = new Slot("LEC", 100, 2, "MO", new int[]{900, 1000});
        Slot slot2 = new Slot("LEC", 100, 1, "MO", new int[]{1100, 1200});
        Slot slot3 = new Slot("LAB", 100, 1, "MO", new int[]{1300, 1400});

        course1.addToPairList(course4);

        course1.addToPreferenceList(new ArrayList<>(Arrays.asList(slot1, 2)));
        course1.addToPreferenceList(new ArrayList<>(Arrays.asList(slot2, 1)));

        courseLabList = new ArrayList<>();
        courseLabList.add(course1);
        courseLabList.add(course2);
        courseLabList.add(course3);
        courseLabList.add(course4);

        slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        //creating the global ordering
        globalOrdering = new Assign();
        for (int i = 0; i < courseLabList.size(); i++) {

            globalOrdering.AddElementToAssign(courseLabList.get(i));
            courseLabList.get(i).setGlobalOrderingIndex(i);
        }

        //creating the test assign and filling it with all slots
        testAssign = new Assign(globalOrdering.getSize(), slotList);
        testAssign.ChangeElementOfAssign(slotList.get(0), 0);
        testAssign.ChangeElementOfAssign(slotList.get(1), 1);
        testAssign.ChangeElementOfAssign(slotList.get(2), 2);
        testAssign.ChangeElementOfAssign(slotList.get(1), 3);

        evalInstance = new Eval(
                1, 1, 1, 1,
                1, 1, 1, 1,
                courseLabList, slotList, globalOrdering
        );

        //creating the hashMap to use in the test
        evalInstance.setCourseLabInSlot(testAssign.CreateHashMapFromAssign(globalOrdering));
        hashMap = evalInstance.getCourseLabInSlot();

    }

    /**
     * Function to set up multiple errors.
     */
    public void SetUpMultipleErrorTests() {

        /*
        CREATE COURSELABS AND SLOTS
        CREATE COURSELAB LIST AND SLOTLIST
        CREATE GLOBAL ORDERING
        CREATE TEST ASSIGN
        CREATE EVAL INSTANCE
        CALL HASHMAP CREATE FUNCTION
         */

        CourseLab course1 = new CourseLab("LEC", "CPSC 433 LEC 01");
        CourseLab course2 = new CourseLab("LEC", "CPSC 434 LEC 02");
        CourseLab course3 = new CourseLab("LAB", "CPSC 435 LEC 02 LAB 01");
        CourseLab course4 = new CourseLab("LEC", "CPSC 436 LEC 01");
        CourseLab course5 = new CourseLab("LEC", "CPSC 436 LEC 02");
        CourseLab course6 = new CourseLab("LEC", "CPSC 436 LEC 03");
        CourseLab course7 = new CourseLab("LEC", "CPSC 436 LEC 04");
        CourseLab course8 = new CourseLab("LAB", "CPSC 436 LEC 04 LAB 01");


        Slot slot1 = new Slot("LEC", 100, 2, "MO", new int[]{900, 1000});
        Slot slot2 = new Slot("LEC", 100, 2, "MO", new int[]{1100, 1200});
        Slot slot3 = new Slot("LAB", 100, 1, "MO", new int[]{1300, 1400});
        Slot slot4 = new Slot("LEC", 100, 1, "MO", new int[]{1400, 1500});
        Slot slot5 = new Slot("LEC", 100, 1, "MO", new int[]{1500, 1600});

        course1.addToPairList(course2);
        course1.addToPairList(course5);

        course1.addToPreferenceList(new ArrayList<>(Arrays.asList(slot1, 1)));
        course2.addToPreferenceList(new ArrayList<>(Arrays.asList(slot2, 1)));

        courseLabList = new ArrayList<>();
        courseLabList.add(course1);
        courseLabList.add(course2);
        courseLabList.add(course3);
        courseLabList.add(course4);
        courseLabList.add(course5);
        courseLabList.add(course6);
        courseLabList.add(course7);
        courseLabList.add(course8);

        slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);
        slotList.add(slot5);

        //creating the global ordering
        globalOrdering = new Assign();
        for (int i = 0; i < courseLabList.size(); i++) {

            globalOrdering.AddElementToAssign(courseLabList.get(i));
            courseLabList.get(i).setGlobalOrderingIndex(i);
        }

        //creating the test assign and filling it with all slots
        testAssign = new Assign(globalOrdering.getSize(), slotList);
        testAssign.ChangeElementOfAssign(slotList.get(0), 0);
        testAssign.ChangeElementOfAssign(slotList.get(1), 1);
        testAssign.ChangeElementOfAssign(slotList.get(2), 2);
        testAssign.ChangeElementOfAssign(slotList.get(3), 3);
        testAssign.ChangeElementOfAssign(slotList.get(3), 4);
        testAssign.ChangeElementOfAssign(slotList.get(3), 5);
//        testAssign.ChangeElementOfAssign(slotList.get(3), 6);
        testAssign.ChangeElementOfAssign(slotList.get(3), 7);

        evalInstance = new Eval(
                1, 1, 1, 1,
                1, 1, 1, 1,
                courseLabList, slotList, globalOrdering
        );

        //creating the hashMap to use in the test
        evalInstance.setCourseLabInSlot(testAssign.CreateHashMapFromAssign(globalOrdering));
        hashMap = evalInstance.getCourseLabInSlot();

    }

    /**
     * Test for the create hash map function to make sure it creates the correct hash map for a given input.
     */
    @Test
    public void CreateHashMapTest() {

        SetUpPerfectTests();

        /*
        START TESTS
         */

        //make sure no slot duplicated
        assertTrue(hashMap.keySet().size() <= slotList.size());

        //make sure all slots present
        for (Slot slot : hashMap.keySet()) {

            assertTrue(slotList.contains(slot));
        }

        //making sure each courselab is in the correct slot
        //with no duplicates appearing in the same or other slots
        for (CourseLab courseLab : courseLabList) {

            int count = 0;

            //go through all slots
            for (Slot slot : hashMap.keySet()) {

                //for each list of courselabs
                for (CourseLab courseLabInner : hashMap.get(slot)) {

                    if (courseLab == courseLabInner) {
                        count++;
                    }
                }
            }

            assertEquals(1, count);
        }

    }


    /**
    * Test for the eval min filled.
    * When all slots are good.
    */
    @Test
    public void PerfectEvalMinFilledTest() {

        SetUpPerfectTests();

        assertEquals(0, evalInstance.EvalMinFilled(testAssign));
    }

    /**
     * Test for eval pair.
     * When all pairs are satisfied.
     */
    @Test
    public void PerfectEvalPairTest() {

        SetUpPerfectTests();

        assertEquals(0, evalInstance.EvalPair(testAssign));
    }

    /**
     * Test for eval pref.
     * When the values in the preference list are 0.
     */
    @Test
    public void PerfectEvalPrefZeroTest() {

        SetUpPerfectTests();

        assertEquals(0, evalInstance.EvalPref());
    }

    /**
     * Test when all sections of one course exist in different slots.
     */
    @Test
    public void PerfectEvalSecDiffTest() {

        SetUpPerfectTests();

        assertEquals(0, evalInstance.EvalSecDiff());
    }


    /**
     * Test if the eval is correctly returned as 0 given assign with no issues.
     */
    @Test
    public void PerfectEvalZero() {

        SetUpPerfectTests();

        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(1, 1, 1, 1, 0, 0, 0, 0);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(0, 0, 0, 0, 1, 1, 1, 1);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(-1, -1, -1, -1, -1, -1, -1, -1);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));
    }

    /*
    END PERFECT INPUT SECTION
    START SHIT DOESNT APPLY TESTS
     */

    /**
     * Test for eval pref.
     * When there is no preference list.
     */
    @Test
    public void EvalPrefTestNotApplicableEmptyList() {

        SetUpBlankParamsTests();

        assertEquals(0, evalInstance.EvalPref());
    }

    /**
     * Test for eval pair.
     * When there is no pair list.
     */
    @Test
    public void EvalPairTestNotApplicable() {

        SetUpBlankParamsTests();

        assertEquals(0, evalInstance.EvalPair(testAssign));
    }

    /*
    END SHIT DOESNT APPLY TESTS
    START PARTASSIGN TESTS
     */

    /**
     * Test for min filled.
     * When the assign is a part assign.
     */
    @Test
    public void EvalMinFilledTestNotApplicablePartAssign() {

        SetUpPartAssignTests();

        assertEquals(0, evalInstance.EvalMinFilled(testAssign));
    }

    /**
     * Test for pair.
     * When only one course in a pair has been assigned.
     */
    @Test
    public void EvalPairTestNotApplicablePartAssign() {

        SetUpPartAssignTests();

        assertEquals(0, evalInstance.EvalPair(testAssign));
    }

    /*
    END PARTASSIGN TESTS
    START SINGLE ERROR TESTS
     */

    /**
     * Test for eval min filled.
     * When there is one slot with its min not filled.
     */
    @Test
    public void EvalMinFilledOneIssue() {

        SetUpSingleErrorTests();

        assertEquals(1, evalInstance.EvalMinFilled(testAssign));

    }

    /**
     * Test for eval pref.
     * When there is a course with a nonzero preference.
     */
    @Test
    public void EvalPrefNonZeroPref() {

        SetUpSingleErrorTests();

        assertEquals(1, evalInstance.EvalPref());

    }

    /**
     * Test for eval pair.
     * When two courses in a pair aren't put in the same slot.
     */
    @Test
    public void EvalPairBadPair() {

        SetUpSingleErrorTests();

        assertEquals(1, evalInstance.EvalPair(testAssign));
    }

    /**
     * Test for eval sec diff.
     * When two sections are put in the same slot.
     */
    @Test
    public void EvalSecDiffSectionsInSameSlot() {

        SetUpSingleErrorTests();

        assertEquals(1, evalInstance.EvalSecDiff());

    }

    /**
     * Test for eval.
     * Properly sums up all errors in eval.
     */
    @Test
    public void EvalSingleErrorSum() {

        SetUpSingleErrorTests();

        assertEquals(5, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(320, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(1, 1, 1, 1, 0, 0, 0, 0);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(0, 0, 0, 0, 1, 1, 1, 1);
        assertEquals(2, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(-1, -1, -1, -1, -1, -1, -1, -1);
        assertEquals(1, evalInstance.EvaluateAssign(testAssign));
    }

    /*
    END SINGLE ERROR TESTS
    START MULTIPLE ERROR TESTS
     */

    //TODO

    /**
     * Test for eval min filled.
     * Where two course mins are broken.
     */
    @Test
    public void EvalMinFilledMultipleError() {

        SetUpMultipleErrorTests();

        assertEquals(2, evalInstance.EvalMinFilled(testAssign));
    }

    /**
     * Test for eval pref.
     * Where multiple courses have preference.
     */
    @Test
    public void EvalPrefMultipleCourses() {

        SetUpMultipleErrorTests();

        assertEquals(2, evalInstance.EvalPref());

    }

    /**
     * Test for eval pair.
     * Multiple courses have pairs that are in diff slots.
     */
    @Test
    public void EvalPairMultipleBadPairs() {

        SetUpMultipleErrorTests();

        assertEquals(2, evalInstance.EvalPair(testAssign));

    }

    /**
     * Test for eval sec diff.
     * Where 3 sections of same course are in same slot.
     * ALSO CAN INDIRECTLY TEST FOR 4 EVAL by uncommenting line
     *
     */
    @Test
    public void EvalSecDiff3Sections() {

        SetUpMultipleErrorTests();

        assertEquals(3, evalInstance.EvalSecDiff());

    }

    /**
     * Test for eval sec diff
     * Where sections of a course aren't next to each other.
     */
    //TODO:

    /**
     * Test for eval
     */

    /**
     * Test for eval.
     * Properly sums up all errors in eval.
     */
    @Test
    public void EvalMultipleErrorSum() {

        SetUpMultipleErrorTests();

        fail();

        assertEquals(5, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(320, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(1, 1, 1, 1, 0, 0, 0, 0);
        assertEquals(0, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(0, 0, 0, 0, 1, 1, 1, 1);
        assertEquals(2, evalInstance.EvaluateAssign(testAssign));

        ChangeEvalWeights(-1, -1, -1, -1, -1, -1, -1, -1);
        assertEquals(1, evalInstance.EvaluateAssign(testAssign));
    }

}
