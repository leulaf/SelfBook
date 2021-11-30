import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    
    private int startTimeHours;
    private int startTimeMinutes;
    private int endTimeHours;
    private int endTimeMinutes; 
    private boolean startMorning;
    private boolean endMorning;

    public Time() {
        // initialize the attributes we need 
        this.startTimeHours = 0;
        this.startTimeMinutes = 0;
        this.startTimeHours = 0;
        this.endTimeMinutes = 0;
        this.startMorning = false;
        this.endMorning = false;
    }

    public void setStartTime(int hours, int minutes, boolean startMorning) {
        this.startTimeHours = hours;
        this.startTimeMinutes = minutes;
        this.startMorning = startMorning;
    }

    public void setEndTime(int hours, int minutes, boolean startMorning) {
        this.endTimeHours = hours;
        this.endTimeMinutes = minutes;
        this.endMorning = startMorning;
    }

    public int getStartTimeHours() {
        return this.startTimeHours;
    }

    public int getEndTimeHours(){
        return this.endTimeHours;
    }

    public int getStartTimeMinutes(){
        return this.startTimeMinutes;
    }

    public int getEndTimeMinutes(){
        return this.endTimeMinutes;
    }

    public boolean getStartMorning() {
        return this.startMorning;
    }

    public boolean getEndMorning() {
        return this.endMorning;
    }

    boolean simpleOverlap(Date start1, Date end1, Date start2, Date end2){
        // a simple test to check if the times for two movies overlap
        return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime(); 
    }

    public boolean checkOverlap(Time otherTime) {
        // checks if there is an overlap between this object's time 
        // and the object passed 
        // if this is true, then they overlap
        // if this is false, then they are valid

        // as long as the date and month are the same, we can set constraints 
        // we need to parse this into a 24 hour format first 
        String thisStartTime24 = "";
        if (this.getStartMorning())
            thisStartTime24 += this.getStartTimeHours();
        else 
            thisStartTime24 += this.getStartTimeHours() + 12;

        int startTimeMins = this.getStartTimeMinutes();
        if (startTimeMins < 10)
            thisStartTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            thisStartTime24 += ":" + startTimeMins + ":00";
        
        thisStartTime24 = "01/01/1970 " + thisStartTime24;

        String thisEndTime24 = "";
        if (this.getEndMorning())
            thisEndTime24 += this.getEndTimeHours();
        else 
            thisEndTime24 += this.getEndTimeHours() + 12;

        startTimeMins = this.getEndTimeMinutes();
        if (startTimeMins < 10)
            thisEndTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            thisEndTime24 += ":" + startTimeMins + ":00";
        
        thisEndTime24 = "01/01/1970 " + thisEndTime24;        

        String otherStartTime24 = "";
        if (otherTime.getStartMorning())
            otherStartTime24 += otherTime.getStartTimeHours();
        else 
            otherStartTime24 += otherTime.getStartTimeHours() + 12;

        startTimeMins = otherTime.getStartTimeMinutes();
        if (startTimeMins < 10)
            otherStartTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            otherStartTime24 += ":" + startTimeMins + ":00";
        
        otherStartTime24 = "01/01/1970 " + otherStartTime24;

        String otherEndTime24 = "";
        if (this.getEndMorning()) 
            otherEndTime24 += otherTime.getEndTimeHours();
        else 
            otherEndTime24 += otherTime.getEndTimeHours() + 12;

        startTimeMins = otherTime.getEndTimeMinutes();
        if (startTimeMins < 10)
            otherEndTime24 += ":" + "0" + startTimeMins + ":00";
        else 
            otherEndTime24 += ":" + startTimeMins + ":00";
        
        otherEndTime24 = "01/01/1970 " + otherEndTime24;

        // now let's make a datetime object from it 
        Date otherStartEpoch;
        Date otherEndEpoch;
        Date thisStartEpoch;
        Date thisEndEpoch;
        try {
            thisStartEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(thisStartTime24);
            thisEndEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(thisEndTime24);        
            otherStartEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(otherStartTime24);
            otherEndEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(otherEndTime24);
            return this.simpleOverlap(thisStartEpoch, thisEndEpoch, otherStartEpoch, otherEndEpoch);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private boolean validateTimeGiven() {
        // checks if the times given are valid and if the time slot is valid 

        // step 1 check the start time and end time  to make sure it's less than 12
        int startHours = this.getStartTimeHours();
        int endHours = this.getEndTimeHours();

        if (startHours > 12 || startHours < 1 || endHours > 12 || endHours < 1) {
            System.out.println("Hours cannot be less than 1 or more than 12. Please try again.");
            return false;
        }
        int startMinutes = this.getStartTimeMinutes();
        int endMinutes = this.getEndTimeMinutes();
        if (startMinutes > 59 || startMinutes < 0 || endMinutes > 59 || endMinutes < 0) {
            System.out.println("Minutes cannot be less than 0 or more than 59. Please try again.");
            return false;
        }

        // step 2 check that the time slot is valid (i.e. there's no 12pm - 10am)
        boolean startMorning = this.getStartMorning();
        boolean endMorning = this.getEndMorning();
        if (startMorning == endMorning) {
            // the time stamps are in the same zone, so now we just need to check that end is greater than start
            if (endHours > startHours) {
                return true;
            } else if (endHours == startHours) {
                return (endMinutes > startMinutes);
            } else {
                // we need to make sure that we adjust the start time and end time. 
                if (!startMorning && startHours != 12)
                    startHours += 12;
                if (!endMorning && endHours != 12)
                    endHours += 12;

                if (endHours > startHours) 
                    return true;
                
                return false;
            }
        } 
        return true;        
    }

    public boolean parseTime(String timeGiven) { 
        // parse the timeGiven and make sure it's valid 
        // adjust each attribute to the timeGiven 
        // return true if success, false if there's a conflict or an invalid entry 
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

        if (timeGiven.charAt(5) == 'p')
            timeZoneMorning = false;
        
        this.setStartTime(hours, minutes, timeZoneMorning);

        hours = Character.getNumericValue(timeGiven.charAt(8)) * 10 + Character.getNumericValue(timeGiven.charAt(9));
        minutes  = Character.getNumericValue(timeGiven.charAt(11)) * 10 + Character.getNumericValue(timeGiven.charAt(12));

        if (timeGiven.charAt(13) == 'p')
            timeZoneMorning = false;
        else 
            timeZoneMorning = true;
        
        this.setEndTime(hours, minutes, timeZoneMorning);

        // now we need to make sure that the time given was valid 
        return this.validateTimeGiven();
    }

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

