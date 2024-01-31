package Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeSlotStringMap {

    // Maps that look like this: MO8:00 = [800,900] where 800 is starting at 8:00 and 900 is ending at 9:00
    // You just need to make the key when parsing and then we can look up the end value
    Map<String, Slot> lecturesTimeSlots = new HashMap<>();
    Map<String, Slot> labsTimeSlots = new HashMap<>();

    public void addTimeSlot(Map<String, Slot> schedule, String weekday, String timeString, int startTime, int endTime, String type) {
        String key = weekday + timeString;
        int[] startEndTime = {startTime, endTime};
        Slot value = new Slot(type, 0, 0, weekday, startEndTime);
        schedule.put(key, value);
    }

    public void setCourseMinMax(String key, int min, int max){
        Slot toSet = lecturesTimeSlots.get(key);
        if( toSet != null){
            toSet.setCourseLabMinMax(min, max);
            lecturesTimeSlots.put(key, toSet);
        }else{
            System.out.println(key);
            // TODO: Should probably throw an excption here
        }
    }

    public void setLabMinMax(String key, int min, int max){
        Slot toSet = labsTimeSlots.get(key);
        if(toSet != null){
            toSet.setCourseLabMinMax(min, max);
            labsTimeSlots.put(key, toSet);
        }else{
            System.out.println(key);
            // TODO: Should probably throw an excption here
        }
    }

    public ArrayList<Slot> getCourseSlots(){
        ArrayList<Slot> courseSlots = new ArrayList<>();
        for(Slot courseSlot : lecturesTimeSlots.values()){
            if(courseSlot.getCourseLabMax() > 0){
                courseSlots.add(courseSlot);
            }
        }
        return courseSlots;
    }

    public ArrayList<Slot> getLabSlots(){
        ArrayList<Slot> labSlots = new ArrayList<>();
        for(Slot labSlot : labsTimeSlots.values()){
            if(labSlot.getCourseLabMax() > 0){
                labSlots.add(labSlot);
            }
        }
        return labSlots;
    }
    
    public Slot getSlotFromKey(String key, String type){
        if(type == "LEC"){
            return lecturesTimeSlots.get(key);
        }else if(type == "LAB"){
            return labsTimeSlots.get(key);
        }else{
            return null;
        }
    }


    public TimeSlotStringMap() {
        // Map for lectures
        addTimeSlot(lecturesTimeSlots, "MO", "8:00", 800, 900, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "9:00", 900, 1000, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "10:00", 1000, 1100, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "11:00", 1100, 1200, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "12:00", 1200, 1300, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "13:00", 1300, 1400, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "14:00", 1400, 1500, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "15:00", 1500, 1600, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "16:00", 1600, 1700, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "17:00", 1700, 1800, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "18:00", 1800, 1900, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "19:00", 1900, 2000, "LEC");
        addTimeSlot(lecturesTimeSlots, "MO", "20:00", 2000, 2100, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "8:00", 800, 930, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "9:30", 930, 1100, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "11:00", 1100, 1230, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "12:30", 1230, 1400, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "14:00", 1400, 1530, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "15:30", 1530, 1700, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "17:00", 1700, 1830, "LEC");
        addTimeSlot(lecturesTimeSlots, "TU", "18:30", 1830, 2000, "LEC");

        // Map for labs
        addTimeSlot(labsTimeSlots, "MO", "8:00", 800, 900, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "9:00", 900, 1000, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "10:00", 1000, 1100, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "11:00", 1100, 1200, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "12:00", 1200, 1300, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "13:00", 1300, 1400, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "14:00", 1400, 1500, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "15:00", 1500, 1600, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "16:00", 1600, 1700, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "17:00", 1700, 1800, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "18:00", 1800, 1900, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "19:00", 1900, 2000, "LAB");
        addTimeSlot(labsTimeSlots, "MO", "20:00", 2000, 2100, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "8:00", 800, 900, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "9:00", 900, 1000, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "10:00", 1000, 1100, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "11:00", 1100, 1200, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "12:00", 1200, 1300, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "13:00", 1300, 1400, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "14:00", 1400, 1500, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "15:00", 1500, 1600, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "16:00", 1600, 1700, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "17:00", 1700, 1800, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "18:00", 1800, 1900, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "19:00", 1900, 2000, "LAB");
        addTimeSlot(labsTimeSlots, "TU", "20:00", 2000, 2100, "LAB");
        addTimeSlot(labsTimeSlots, "FR", "8:00", 800, 1000, "LAB");
        addTimeSlot(labsTimeSlots, "FR", "10:00", 1000, 1200, "LAB");
        addTimeSlot(labsTimeSlots, "FR", "12:00", 1200, 1400, "LAB");
        addTimeSlot(labsTimeSlots, "FR", "14:00", 1400, 1600, "LAB");
        addTimeSlot(labsTimeSlots, "FR", "16:00", 1600, 1800, "LAB");
        addTimeSlot(labsTimeSlots, "FR", "18:00", 1800, 2000, "LAB");
    }
}