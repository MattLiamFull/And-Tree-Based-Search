package Source;

public class Slot {

    private String type;
    private int courseLabMax;
    private int courseLabMin;
    private String day;
    private int startTime;
    private int endTime;

    /**
     * Constructor that takes in all strings and converts to the correct type.
     * Using all string as input to make the parser simpler.
     *
     * @param courseLabMax The max number of courses or labs allowed in this slot.
     * @param courseLabMin The min number of courses of labs allowed in this slot.
     * @param day The days this course is on.   TODO: figure out if will say monday, wednesday, friday or logic it out. will need to be changed to arraylist if no logic
     * @param startEndTimes An integer array of two elements where (start time, end time).
     */
    public Slot(String type, Integer courseLabMax, Integer courseLabMin, String day, int[] startEndTimes) {
        this.type = type;
        this.courseLabMax = courseLabMax;
        this.courseLabMin = courseLabMin;
        this.day = day;
        this.startTime = startEndTimes[0];
        this.endTime = startEndTimes[1];
    }

    public void setCourseLabMinMax(int min, int max){
        this.courseLabMin = min;
        this.courseLabMax = max;
    }

    public String getType() {
        return type;
    }

    public int getCourseLabMax() {
        return courseLabMax;
    }

    public int getCourseLabMin() {
        return courseLabMin;
    }

    public String getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    /**
     * Function to return the human-readable necessary information about the current slot.
     *
     * @return The day and start time "day, start time".
     */
    @Override
    public String toString() {

        //converting the start time back to human-readable form
        String startTimeReadable = String.valueOf(this.startTime);
        if(startTimeReadable.length() == 4){
            startTimeReadable = startTimeReadable.substring(0, 2) + ":" + startTimeReadable.substring(2);
        }else if(startTimeReadable.length() == 3){
            startTimeReadable = startTimeReadable.substring(0, 1) + ":" + startTimeReadable.substring(1);
        }
        

        return this.day + ", " + startTimeReadable;
    }
}
