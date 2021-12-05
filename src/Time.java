import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    
    private int startTimeHours;
    private int startTimeMinutes;
    private int endTimeHours;
    private int endTimeMinutes; 
    private boolean startMorning;
    private boolean endMorning;

    //creates a time. used to set the start and end time of a movie
    public Time() {
        // initialize the attributes we need 
        this.startTimeHours = 0;
        this.startTimeMinutes = 0;
        this.startTimeHours = 0;
        this.endTimeMinutes = 0;
        this.startMorning = false;
        this.endMorning = false;
    }
    //mutator method to set the start time of  a movie
    public void setStartTime(int hours, int minutes, boolean startMorning) {
        this.startTimeHours = hours;
        this.startTimeMinutes = minutes;
        this.startMorning = startMorning;
    }
    //mutator method to set the end time of  a movie
    public void setEndTime(int hours, int minutes, boolean startMorning) {
        this.endTimeHours = hours;
        this.endTimeMinutes = minutes;
        this.endMorning = startMorning;
    }
    //accessor method that returns the first part of the start time ex: time = 12:30, returns 12
    public int getStartTimeHours() {
        return this.startTimeHours;
    }
    //accessor method that returns the first part of the end time ex: time = 12:30, returns 12
    public int getEndTimeHours(){
        return this.endTimeHours;
    }
    //accessor method that returns the second part of the start time ex: time = 12:30, returns 30
    public int getStartTimeMinutes(){
        return this.startTimeMinutes;
    }
    //accessor method that returns the second part of the end time ex: time = 12:30, returns 30
    public int getEndTimeMinutes(){
        return this.endTimeMinutes;
    }
    //return true if movie starts at AM time, false if PM time
    public boolean getStartMorning() {
        return this.startMorning;
    }
    //return true if movie ends at AM time, false if PM time
    public boolean getEndMorning() {
        return this.endMorning;
    }

    //takes in two start and end times and returns true if they overlap
    boolean simpleOverlap(String start1, String end1, String start2, String end2) {
        // parse the times into minutes
        int s1 = Integer.parseInt(start1.split(":")[0])*60 + Integer.parseInt(start1.split(":")[1]);
        int e1 = Integer.parseInt(end1.split(":")[0])*60 + Integer.parseInt(end1.split(":")[1]);
        int s2 = Integer.parseInt(start2.split(":")[0])*60 + Integer.parseInt(start2.split(":")[1]);
        int e2 = Integer.parseInt(end2.split(":")[0])*60 + Integer.parseInt(end2.split(":")[1]);

        // overlap check!
        return (s2 >= s1 && s2 <= e1) || (e2 >= s1 && e2 <= e1) || (s1 >= s2 && s1 <= e2) || (s2 >= s1 && s2 <= e1);
    }

    //parses this time object and the time object passed to a 24 hr format then checks if they overlap and returns true if there is an overlap
    public boolean checkOverlap(Time otherTime) {
        // as long as the date and month are the same, we can set constraints 
        // we need to parse this into a 24 hour format first 
        String thisStartTime24 = "";
        if (this.getStartMorning())
            //special case for 12 because it needs to be 00 in 24 hr format
            if (this.getStartTimeHours() == 12)
                thisStartTime24 += "00";
            else 
                thisStartTime24 += this.getStartTimeHours();
        else 
            if (this.getStartTimeHours() != 12)
                thisStartTime24 += this.getStartTimeHours() + 12;
            else 
                thisStartTime24 += this.getStartTimeHours();

        int startTimeMins = this.getStartTimeMinutes();
        //special case when mins < 10, we need a 0 at the beginning
        if (startTimeMins < 10)
            thisStartTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            thisStartTime24 += ":" + startTimeMins + ":00";
        
        //repeating the process for end times
        String thisEndTime24 = "";
        if (this.getEndMorning())
            if (this.getEndTimeHours() == 12)
                thisEndTime24 += "00";
            else 
                thisEndTime24 += this.getEndTimeHours();
        else 
            if (this.getEndTimeHours() != 12)
                thisEndTime24 += this.getEndTimeHours() + 12;
            else 
                thisEndTime24 += this.getEndTimeHours();


        startTimeMins = this.getEndTimeMinutes();
        if (startTimeMins < 10)
            thisEndTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            thisEndTime24 += ":" + startTimeMins + ":00";
        
        //getting the 24 hr format time for the time object passed
        String otherStartTime24 = "";
        if (otherTime.getStartMorning())
            if (otherTime.getStartTimeHours() == 12)
                otherStartTime24 += "00";
            else 
                otherStartTime24 += otherTime.getStartTimeHours();
        else 
            if (otherTime.getStartTimeHours() != 12)
                otherStartTime24 += otherTime.getStartTimeHours() + 12;
            else 
                otherStartTime24 += otherTime.getStartTimeHours();

        startTimeMins = otherTime.getStartTimeMinutes();
        if (startTimeMins < 10)
            otherStartTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            otherStartTime24 += ":" + startTimeMins + ":00";

        //repeating the process for end times
        String otherEndTime24 = "";
        if (otherTime.getEndMorning()) 
            if (otherTime.getEndTimeHours() == 12)
                otherEndTime24 += "00";
            else
                otherEndTime24 += otherTime.getEndTimeHours();
        else 
            if (otherTime.getEndTimeHours() != 12)
                otherEndTime24 += otherTime.getEndTimeHours() + 12;
            else 
                otherEndTime24 += otherTime.getEndTimeHours();

        startTimeMins = otherTime.getEndTimeMinutes();
        if (startTimeMins < 10)
            otherEndTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            otherEndTime24 += ":" + startTimeMins + ":00";
        //now that we have evrything in 24 hr format, pass the start and end times to simpleoverlap to check if they overlap
        return this.simpleOverlap(thisStartTime24, thisEndTime24, otherStartTime24, otherEndTime24);

    }
    // checks if the times given are valid, if the time slot is valid 
    private boolean validateTimeGiven() {
        // check the start time hours and end time hours to make sure it's between 1-12
        int startHours = this.getStartTimeHours();
        int endHours = this.getEndTimeHours();

        if (startHours > 12 || startHours < 1 || endHours > 12 || endHours < 1) {
            System.out.println("Hours cannot be less than 1 or more than 12. Please try again.");
            return false;
        }

        // check the start time minutes and end time minutes to make sure it's between 0-59
        int startMinutes = this.getStartTimeMinutes();
        int endMinutes = this.getEndTimeMinutes();
        if (startMinutes > 59 || startMinutes < 0 || endMinutes > 59 || endMinutes < 0) {
            System.out.println("Minutes cannot be less than 0 or more than 59. Please try again.");
            return false;
        }
        
        return true;        
    }
    // parse the timeGiven and make sure it's valid 
    // return true if success, false if there's a conflict or an invalid entry 
    public boolean parseTime(String timeGiven) { 
        // adjust each attribute to the timeGiven 
        timeGiven = timeGiven.toLowerCase();
        timeGiven = timeGiven.replace(" ", "");
        Pattern time = Pattern.compile("[0-9][0-9]:[0-9][0-9][ap][m][-][0-9][0-9]:[0-9][0-9][ap][m]");
        Matcher match = time.matcher(timeGiven);
        if (!match.matches()) {
            System.out.println("The time format given is invalid. Please try again.");
            return false;
        }
        // find and set the start time and end time 
        int hours;
        int minutes;
        boolean timeZoneMorning = true;

        hours = Character.getNumericValue(timeGiven.charAt(0)) * 10 + Character.getNumericValue(timeGiven.charAt(1));
        minutes  = Character.getNumericValue(timeGiven.charAt(3)) * 10 + Character.getNumericValue(timeGiven.charAt(4));
        //since timeGiven is parsed, can easily check the time zone (AM/PM)
        if (timeGiven.charAt(5) == 'p')
            timeZoneMorning = false;
        //set start time
        this.setStartTime(hours, minutes, timeZoneMorning);

        hours = Character.getNumericValue(timeGiven.charAt(8)) * 10 + Character.getNumericValue(timeGiven.charAt(9));
        minutes  = Character.getNumericValue(timeGiven.charAt(11)) * 10 + Character.getNumericValue(timeGiven.charAt(12));

        if (timeGiven.charAt(13) == 'p')
            timeZoneMorning = false;
        else 
            timeZoneMorning = true;
        //set end time
        this.setEndTime(hours, minutes, timeZoneMorning);

        // now we need to make sure that the time given was valid 
        return this.validateTimeGiven();
    }
    //used to print an easy to read time
    public String toString() {
        String s = "";
        int startTimeMins = this.getStartTimeMinutes();
        if (startTimeMins < 10)
            s += this.getStartTimeHours() + ":" + "0" + startTimeMins;
        else 
            s += this.getStartTimeHours() + ":" + startTimeMins;

        if (this.getStartMorning())
            s += "am-";
        else 
            s += "pm-";
        
        int endTimeMins = this.getEndTimeMinutes();
        if (endTimeMins < 10)
            s += this.getEndTimeHours() + ":" + "0" + endTimeMins;
        else 
            s += this.getEndTimeHours() + ":" + endTimeMins;

        if (this.getEndMorning())
            s += "am";
        else 
            s += "pm";
        
        return s;
    }
}

