package org.python.stdlib.datetime;

import org.python.Object;
import java.util.Collections;
import java.util.Map;

public class Date extends org.python.types.Object {
    /**
     * Java class for Python's datetime.date class
     * Last edited: 24/09/2020
     */

    private final int maxYear = 9999;
    private final int maxMonth = 12;
    private final int maxDay = 31;
    private final int minDate = 1;

    private Map<String, Object> kwargs;
    private Object[] args;

    @org.python.Attribute
    public org.python.Object year = __year__();

    @org.python.Attribute
    public org.python.Object month = __month__();

    @org.python.Attribute
    public org.python.Object day = __day__();

    @org.python.Attribute
    public static final org.python.Object min = __min__();

    @org.python.Attribute
    public static final org.python.Object max = __max__();

    /**
     * Class constructor
     * @param args Object list containing anywhere between 0 and 3 org.python.types.Int objects.
     * @param kwargs HashMap containing anywhere between 0 and 3 org.python.types.Int objects.
     */
    @org.python.Method(__doc__ = "")
    public Date(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        super();
        this.kwargs = kwargs;
        this.args = args;

        switch(this.args.length + this.kwargs.size()) {
            case 3: // length of kwargs and args combined is equal to 3
                setDate();
                break;
            case 2:
                twoArguments();
                break;
            case 1:
                oneArgument();
                break;
            default: // length of kwargs and args combined is greater than 3
                throw new org.python.exceptions.TypeError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(),
                    this.args.length + this.kwargs.size()));
        }
    }

    /**
     * Constructs a new Date(year, month, day)
     */
    private void setDate() {
        // Checks that positional arguments don't follow keyword arguments
        if (this.args.length < 3 && this.kwargs.get(DateTimeEnum.DAY.toString()) == null ||
            this.args.length < 2 && this.kwargs.get(DateTimeEnum.MONTH.toString()) == null) {
            throw new org.python.exceptions.SyntaxError(DateTimeEnum.SYNTAX_ERR.toString());
        }

        // Set year attribute
        if (this.kwargs.get(DateTimeEnum.YEAR.toString()) != null) {
            this.year = this.kwargs.get(DateTimeEnum.YEAR.toString());
        } else {
            this.year = this.args[0];
        }

        // Set month attribute
        if (this.kwargs.get(DateTimeEnum.MONTH.toString()) != null) {
            this.month = this.kwargs.get(DateTimeEnum.MONTH.toString());
        } else {
            this.month = this.args[1];
        }

        // Set day attribute
        if (this.kwargs.get(DateTimeEnum.DAY.toString()) != null) {
            this.day = this.kwargs.get(DateTimeEnum.DAY.toString());
        } else {
            this.day = this.args[2];
        }

        // Check each attribute value is of type: org.python.types.Int
        if (!(this.year instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() +
                this.year.typeName());
        } else if (!(this.month instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() +
                this.month.typeName());
        } else if (!(this.day instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() +
                this.day.typeName());
        }

        // Check each attribute value is within the appropriate range
        if (this.minDate > ((org.python.types.Int) this.year).value ||
            ((org.python.types.Int) this.year).value > this.maxYear) {
            throw new org.python.exceptions.ValueError(String.format(DateTimeEnum.YR_VAL_ERR.toString(),
                ((org.python.types.Int) this.year).value));
        } else if (this.minDate > ((org.python.types.Int) this.month).value ||
            ((org.python.types.Int) this.month).value > this.maxMonth) {
            throw new org.python.exceptions.ValueError(DateTimeEnum.MON_VAL_ERR.toString());
        } else if (this.minDate > ((org.python.types.Int) this.day).value ||
            ((org.python.types.Int) this.day).value > this.maxDay) {
            throw new org.python.exceptions.ValueError(DateTimeEnum.DAY_VAL_ERR.toString());
        }
    }

    /**
     * Class for when only two inputs is included in each of the args and kwargs parameters
     * Not tested - same as initial code, but moved from class constructor method conditional
     */
    private void twoArguments() {
        if (args.length == 2) {
            this.year = args[0];
            this.month = args[1];
        } else {
            if (kwargs.get("year") != null) {
                this.year = kwargs.get("year");
            }

            if (args.length > 0) {
                this.year = args[0];
            }

            if (kwargs.get("month") != null) {
                this.month = kwargs.get("month");
            }

            if (kwargs.get("day") != null) {
                this.day = kwargs.get("day");
            }
        }

        String y = this.year + "";
        String m = this.month + "";
        String d = this.day + "";

        if (!y.equals("null") && !(this.year instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError("intege argument expected, got " + this.year.typeName());
        }

        if (kwargs.get("year") != null && args.length > 0) {
            throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
        }

        if (!(this.month instanceof org.python.types.Int) && !m.equals("null")) {
            throw new org.python.exceptions.TypeError("integer argument expected, got " + this.month.typeName());
        }

        if (y.equals("null")) {

            throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
        }

        if (m.equals("null")) {

            throw new org.python.exceptions.TypeError("function missing required argument 'month' (pos 2)");
        }

        if (d.equals("null")) {
            throw new org.python.exceptions.TypeError("function missing required argument 'day' (pos 3)");
        }
    }

    /**
     * Class for when only one input is included in each of the args and kwargs parameters
     * Not tested - same as initial code, but moved from class constructor method conditional
     */
    private void oneArgument() {
        if (kwargs.get("year") != null) {
            this.year = kwargs.get("year");
        } else if (args.length > 0) {
            this.year = args[0];
        }

        if (kwargs.get("month") != null) {
            this.month = kwargs.get("month");
        }

        if (kwargs.get("day") != null) {
            this.day = kwargs.get("day");
        }

        String y = this.year + "";
        String m = this.month + "";
        String d = this.day + "";

        if (!(this.year instanceof org.python.types.Int) && !y.equals("null")) {
            throw new org.python.exceptions.TypeError("integer argument expected, got " + this.year.typeName());
        }
        if (!y.equals("null")) {
            throw new org.python.exceptions.TypeError("function missing required argument 'month' (pos 2)");
        }
        if (!m.equals("null") || !d.equals("null")) {
            throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
        }
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public org.python.types.Str __repr__() {
        String year = this.year + "";
        while (year.length() < 4)
            year = "0" + year;

        String month = this.month + "";
        while (month.length() < 2)
            month = "0" + month;

        String day = this.day + "";
        while (day.length() < 2)
            day = "0" + day;
        return new org.python.types.Str(year + "-" + month + "-" + day);
    }

    /**
     * Not tested
     * @return
     */
    public static org.python.Object constant_4() {
        return org.python.types.Int.getInt(4);
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public org.python.types.Str __year__() {
        return new org.python.types.Str(this.year + "");
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public org.python.types.Str __month__() {
        return new org.python.types.Str(this.month + "");
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public org.python.types.Str __day__() {
        return new org.python.types.Str(this.day + "");
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    private static org.python.Object __max__() {
        org.python.types.Int day = org.python.types.Int.getInt(31);
        org.python.types.Int month = org.python.types.Int.getInt(12);
        org.python.types.Int year = org.python.types.Int.getInt(9999);
        org.python.Object[] args = { year, month, day };
        return new Date(args, Collections.emptyMap());
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    private static org.python.Object __min__() {
        org.python.types.Int day = org.python.types.Int.getInt(1);
        org.python.types.Int month = org.python.types.Int.getInt(1);
        org.python.types.Int year = org.python.types.Int.getInt(1);
        org.python.Object[] args = { year, month, day };
        return new Date(args, Collections.emptyMap());
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public static org.python.Object today() {
        java.time.LocalDateTime today = java.time.LocalDateTime.now();
        int y = today.getYear();
        int m = today.getMonthValue();
        int d = today.getDayOfMonth();
        org.python.Object[] args = { org.python.types.Int.getInt(y), org.python.types.Int.getInt(m), org.python.types.Int.getInt(d) };
        return new Date(args, Collections.emptyMap());
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public org.python.Object ctime() {
        String[] monthList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        double monthNum = ((org.python.types.Int) this.month).value;
        String monthStr = monthList[(int) monthNum - 1];

        String[] weekdayList = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        double weekdayNum = ((org.python.types.Int) weekday()).value;
        String weekdayStr = weekdayList[(int) weekdayNum];
        return new org.python.types.Str(weekdayStr + " " + monthStr + "  " + this.day + " 00:00:00 " + this.year);
    }

    /**
     * Not tested
     * @return
     */
    @org.python.Method(__doc__ = "")
    public org.python.Object weekday() {
        double y = ((org.python.types.Int) this.year).value;
        double m = ((org.python.types.Int) this.month).value;
        double d = ((org.python.types.Int) this.day).value;

        java.util.Date myCalendar = new java.util.GregorianCalendar((int) y, (int) m - 1, (int) d).getTime();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(myCalendar);
        int day = c.get(java.util.Calendar.DAY_OF_WEEK);
        int[] convertToPython = { 6, 0, 1, 2, 3, 4, 5 };
        return org.python.types.Int.getInt(convertToPython[day - 1]);
    }
}
