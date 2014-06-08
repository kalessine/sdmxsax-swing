package sdmxsaxswing.visualisation.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimePeriodFormatException;
import org.jfree.data.time.Year;
import org.jfree.date.MonthConstants;
import org.jfree.date.SerialDate;

/**
 * Defines a quarter (in a given year). The range supported is Q1 1900 to Q4
 * 9999. This class is immutable, which is a requirement for all
 * {@link RegularTimePeriod} subclasses.
 */
public class Semester extends RegularTimePeriod implements Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 3810061714380888672L;
    /**
     * Constant for quarter 1.
     */
    public static final int FIRST_SEMESTER = 1;

    /**
     * Constant for quarter 4.
     */
    public static final int LAST_SEMESTER = 2;

    /**
     * The first month in each quarter.
     */
    public static final int[] FIRST_MONTH_IN_SEMESTER = {
        0, MonthConstants.JANUARY, MonthConstants.JULY
    };

    /**
     * The last month in each quarter.
     */
    public static final int[] LAST_MONTH_IN_SEMESTER = {
        0, MonthConstants.JUNE, MonthConstants.DECEMBER
    };

    /**
     * The year in which the quarter falls.
     */
    private short year;

    /**
     * The quarter (1-4).
     */
    private byte semester;

    /**
     * The first millisecond.
     */
    private long firstMillisecond;

    /**
     * The last millisecond.
     */
    private long lastMillisecond;

    /**
     * Constructs a new Quarter, based on the current system date/time.
     */
    public Semester() {
        this(new Date());
    }

    /**
     * Constructs a new quarter.
     *
     * @param year the year (1900 to 9999).
     * @param quarter the quarter (1 to 4).
     */
    public Semester(int semester, int year) {
        if ((semester < FIRST_SEMESTER) || (semester > LAST_SEMESTER)) {
            throw new IllegalArgumentException("Semester outside valid range.");
        }
        this.year = (short) year;
        this.semester = (byte) semester;
        peg(Calendar.getInstance());
    }

 /**
      * Constructs a new quarter.
      *
      * @param quarter  the quarter (1 to 4).
      * @param year  the year (1900 to 9999).
      */
     public Semester(int semester, Year year) {
         if ((semester < FIRST_SEMESTER) || (semester > LAST_SEMESTER)) {
             throw new IllegalArgumentException("Semester outside valid range.");
         }
         this.year = (short) year.getYear();
         this.semester = (byte) semester;
         peg(Calendar.getInstance());
     }
   /**
     * Constructs a new Quarter, based on a date/time and the default time zone.
     *
     * @param time the date/time.
     */
    public Semester(Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
    }

    /**
     * Constructs a Quarter, based on a date/time and time zone.
     *
     * @param time the date/time.
     * @param zone the zone (<code>null</code> not permitted).
     */
    public Semester(Date time, TimeZone zone) {
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
        int month = calendar.get(Calendar.MONTH) + 1;
        switch(SerialDate.monthCodeToQuarter(month)){
            case 1:this.semester=1;break;
            case 2:this.semester=1;break;
            case 3:this.semester=2;break;
            case 4:this.semester=2;break;
        }
        this.year = (short) calendar.get(Calendar.YEAR);
        peg(calendar);
    }

    /**
     * Returns the quarter.
     *
     * @return The quarter.
     */
    public int getSemester() {
        return this.semester;
    }

    /**
     * Returns the year.
     *
     * @return The year.
     */
    public Year getYear() {
        return new Year(this.year);
    }

    /**
     * Returns the year.
     *
     * @return The year.
     *
     * @since 1.0.3
     */
    public int getYearValue() {
        return this.year;
    }

    /**
     * Returns the first millisecond of the quarter. This will be determined
     * relative to the time zone specified in the constructor, or in the
     * calendar instance passed in the most recent call to the
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the quarter.
     *
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the quarter. This will be determined
     * relative to the time zone specified in the constructor, or in the
     * calendar instance passed in the most recent call to the
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the quarter.
     *
     * @see #getFirstMillisecond()
     */
    public long getLastMillisecond() {
        return this.lastMillisecond;
    }

    /**
     * Recalculates the start date/time and end date/time for this time period
     * relative to the supplied calendar (which incorporates a time zone).
     *
     * @param calendar the calendar (<code>null</code> not permitted).
     *
     * @since 1.0.3
     */
    public void peg(Calendar calendar) {
        this.firstMillisecond = getFirstMillisecond(calendar);
        this.lastMillisecond = getLastMillisecond(calendar);
    }

    /**
     * Returns the quarter preceding this one.
     *
     * @return The quarter preceding this one (or <code>null</code> if this is
     * Q1 1900).
     */
    public RegularTimePeriod previous() {
        Semester result;
        if (this.semester > FIRST_SEMESTER) {
            result = new Semester(this.semester - 1, this.year);
        } else {
            if (this.year > 1900) {
                result = new Semester(LAST_SEMESTER, this.year - 1);
            } else {
                result = null;
            }
        }
        return result;
    }

    /**
     * Returns the quarter following this one.
     *
     * @return The quarter following this one (or null if this is Q4 9999).
     */
    public RegularTimePeriod next() {
        Semester result;
        if (this.semester < LAST_SEMESTER) {
            result = new Semester(this.semester + 1, this.year);
        } else {
            if (this.year < 9999) {
                result = new Semester(FIRST_SEMESTER, this.year + 1);
            } else {
                result = null;
            }
        }
        return result;
    }

    /**
     * Returns a serial index number for the quarter.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.year * 4L + this.semester;
    }

    /**
     * Tests the equality of this Quarter object to an arbitrary object. Returns
     * <code>true</code> if the target is a Quarter instance representing the
     * same quarter as this object. In all other cases, returns
     * <code>false</code>.
     *
     * @param obj the object (<code>null</code> permitted).
     *
     * @return <code>true</code> if quarter and year of this and the object are
     * the same.
     */
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof Semester) {
                Semester target = (Semester) obj;
                return (this.semester == target.getSemester()
                        && (this.year == target.getYearValue()));
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Returns a hash code for this object instance. The approach described by
     * Joshua Bloch in "Effective Java" has been used here:
     * <p>
     * <code>http://developer.java.sun.com/developer/Books/effectivejava
     * /Chapter3.pdf</code>
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.semester;
        result = 37 * result + this.year;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Quarter object relative
     * to the specified object:
     *
     * negative == before, zero == same, positive == after.
     *
     * @param o1 the object to compare
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;

         // CASE 1 : Comparing to another Quarter object
        // --------------------------------------------
        if (o1 instanceof Semester) {
            Semester q = (Semester) o1;
            result = this.year - q.getYearValue();
            if (result == 0) {
                result = this.semester - q.getSemester();
            }
        } // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else if (o1 instanceof RegularTimePeriod) {
            // more difficult case - evaluate later...
            result = 0;
        } // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
            // consider time periods to be ordered after general objects
            result = 1;
        }

        return result;

    }

    /**
     * Returns a string representing the quarter (e.g. "Q1/2002").
     *
     * @return A string representing the quarter.
     */
    public String toString() {
        return "S" + this.semester + "/" + this.year;
    }

    /**
     * Returns the first millisecond in the Quarter, evaluated using the
     * supplied calendar (which determines the time zone).
     *
     * @param calendar the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond in the Quarter.
     *
     * @throws NullPointerException if <code>calendar</code> is
     * <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
        int month = Semester.FIRST_MONTH_IN_SEMESTER[this.semester];
        calendar.set(this.year, month - 1, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
         // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the Quarter, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the Quarter.
     *
     * @throws NullPointerException if <code>calendar</code> is
     * <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
        int month = Semester.LAST_MONTH_IN_SEMESTER[this.semester];
        int eom = SerialDate.lastDayOfMonth(month, this.year);
        calendar.set(this.year, month - 1, eom, 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
         // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Parses the string argument as a quarter.
     * <P>
     * This method should accept the following formats: "YYYY-QN" and "QN-YYYY",
     * where the "-" can be a space, a forward-slash (/), comma or a dash (-).
     *
     * @param s A string representing the quarter.
     *
     * @return The quarter.
     */
    public static Semester parseSemester(String s) {

         // find the Q and the integer following it (remove both from the
        // string)...
        int i = s.indexOf("S");
        if (i == -1) {
            throw new TimePeriodFormatException("Missing S.");
        }

        if (i == s.length() - 1) {
            throw new TimePeriodFormatException("S found at end of string.");
        }

        String qstr = s.substring(i + 1, i + 2);
        int quarter = Integer.parseInt(qstr);
        String remaining = s.substring(0, i) + s.substring(i + 2, s.length());

        // replace any / , or - with a space
        remaining = remaining.replace('/', ' ');
        remaining = remaining.replace(',', ' ');
        remaining = remaining.replace('-', ' ');

        // parse the string...
        Year year = Year.parseYear(remaining.trim());
        Semester result = new Semester(quarter, year);
        return result;

    }

}
