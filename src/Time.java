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

    private boolean checkOverlap(Time otherTime) {
        // checks if there is an overlap between this object's time 
        // and the object passed 

        // as long as the date and month are the same, we can set constraints 
        // Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();



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
                return false;
            }
        } 
        return true;        
    }
    
    public boolean parseTime(String timeGiven) { 
        // parse the timeGiven and make sure it's valid 
        // adjust each attribute to the timeGiven 
        // return true if success, false if there's a conflict or an invalid entry 
        timeGiven.toLowerCase();
        timeGiven.replace(" ", "");
        Pattern time = Pattern.compile("[0-9][0-9][:][0-9][0-9][ap][m][-][0-9][0-9][:][0-9][0-9][ap][m]");
        Matcher match = time.matcher(timeGiven);
        if (!match.matches()) {
            System.out.println("The time format given is invalid. Please try again.");
            return false;
        }
        // find and set the start time and end time 
        int hours;
        int minutes;
        boolean timeZoneMorning = true;

        hours = timeGiven.charAt(0) * 10 + timeGiven.charAt(1);
        minutes  = timeGiven.charAt(3) * 10 + timeGiven.charAt(4);
        if (timeGiven.charAt(5) == 'p')
            timeZoneMorning = false;
        
        this.setStartTime(hours, minutes, timeZoneMorning);

        hours = timeGiven.charAt(7) * 10 + timeGiven.charAt(8);
        minutes  = timeGiven.charAt(10) * 10 + timeGiven.charAt(11);
        if (timeGiven.charAt(12) == 'p')
            timeZoneMorning = false;
        else 
            timeZoneMorning = true;
        
        this.setEndTime(hours, minutes, timeZoneMorning);

        // now we need to make sure that the time given was valid 
        this.validateTimeGiven();
        return true;
    }

    public String toString() {
        String s = "";
        int startTimeMins = this.getStartTimeMinutes();
        if (startTimeMins < 10)
            s += this.getStartTimeHours() + ":" + "0" + startTimeMins;
        else 
            s += this.getStartTimeHours() + ":" + startTimeMins;

        if (this.getStartMorning())
            s += "am";
        else 
            s += "pm";
        
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

