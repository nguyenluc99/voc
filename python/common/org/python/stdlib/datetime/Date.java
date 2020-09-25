package org.python.stdlib.datetime;

import org.python.Object;
import java.util.Collections;
import java.util.Map;
import java.time.LocalDate;

/**
 * Java class for Python's datetime.date class
 * Assumes syntax order of input is correct. That is, args are always before kwargs
 * Last edited: 25/09/2020
 */
public class Date extends org.python.types.Object {

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
     * Class constructor for datetime.date.
     * Takes exactly 3 inputs, whether arg, kwarg or both.
     * Assumes syntax order of input is correct. That is, args are always before kwargs.
     * @param args Object list containing between 0 and 3 org.python.types.Int objects.
     * @param kwargs HashMap containing between 0 and 3 org.python.types.Int objects.
     */
    @org.python.Method(__doc__ = "")
    public Date(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        super();
        this.kwargs = kwargs;
        this.args = args;

        int totalArguments = this.args.length + this.kwargs.size();

        switch(totalArguments) {
            case 3: // length of kwargs and args combined is equal to 3
                this.setDate();
                break;
            case 2:
                this.twoArguments();
                break;
            case 1:
                this.oneArgument();
                break;
            case 0:
                break;
            default: // length of kwargs and args combined is greater than 3
                throw new org.python.exceptions.TypeError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(),
                    totalArguments));
        }
    }

    /**
     * Constructs a new Date(year, month, day)
     */
    private void setDate() {
        this.validateArgTypes();

        // Checks if there are kwargs that the key "day" exists, otherwise throws TypeError
        if (this.kwargs.size() > 0 && !this.kwargs.containsKey(DateTimeEnum.DAY.toString())) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.DAY_MISS_ERR.toString());
        }

        // Checks if more than 1 kwarg that the key "month" exists, otherwise throws TypeError
        if (this.kwargs.size() > 1 && !this.kwargs.containsKey(DateTimeEnum.MONTH.toString())) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.MONTH.toString());
        }
        this.validateKwargTypes();

        // Set year attribute
        if (this.kwargs.containsKey(DateTimeEnum.YEAR.toString())) {
            this.year = this.kwargs.get(DateTimeEnum.YEAR.toString());
        } else {
            this.year = this.args[0];
        }

        // Set month attribute
        if (this.kwargs.containsKey(DateTimeEnum.MONTH.toString())) {
            this.month = this.kwargs.get(DateTimeEnum.MONTH.toString());
        } else {
            this.month = this.args[1];
        }

        // Set day attribute
        if (this.kwargs.containsKey(DateTimeEnum.DAY.toString())) {
            this.day = this.kwargs.get(DateTimeEnum.DAY.toString());
        } else {
            this.day = this.args[2];
        }

        // Check year attribute value is within the appropriate range
        if (this.minDate > ((org.python.types.Int) this.year).value ||
            ((org.python.types.Int) this.year).value > this.maxYear) {
            throw new org.python.exceptions.ValueError(String.format(DateTimeEnum.YR_VAL_ERR.toString(),
                ((org.python.types.Int) this.year).value));
        }

        // Check month attribute value is within the appropriate range
        if (this.minDate > ((org.python.types.Int) this.month).value ||
            ((org.python.types.Int) this.month).value > this.maxMonth) {
            throw new org.python.exceptions.ValueError(DateTimeEnum.MON_VAL_ERR.toString());
        }

        // Check day attribute value is within the appropriate range
        if (this.minDate > ((org.python.types.Int) this.day).value ||
            ((org.python.types.Int) this.day).value > this.maxDay) {
            throw new org.python.exceptions.ValueError(DateTimeEnum.DAY_VAL_ERR.toString());
        }
    }

    /**
     * Method for when only two inputs are included in total between the args and kwargs parameters
     * Validates input and checks kwarg keys to return a Python TypeError with the correct message
     */
    private void twoArguments() {
        this.validateArgTypes();

        if (args.length == 2) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.DAY_MISS_ERR.toString());
        }

        if (args.length == 1) {
            if (this.kwargs.containsKey(DateTimeEnum.YEAR.toString()) ||
                this.kwargs.containsKey(DateTimeEnum.DAY.toString())) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.MON_MISS_ERR.toString());
            }
        } else {
            if (this.kwargs.containsKey(DateTimeEnum.MONTH.toString()) &&
                this.kwargs.containsKey(DateTimeEnum.DAY.toString())) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.YR_MISS_ERR.toString());
            }

            if (this.kwargs.containsKey(DateTimeEnum.YEAR.toString()) &&
                this.kwargs.containsKey(DateTimeEnum.DAY.toString())) {
                this.kwargs.put(DateTimeEnum.DAY.toString(), org.python.types.Int.getInt(this.maxDay));
                this.validateKwargTypes();
                throw new org.python.exceptions.TypeError(DateTimeEnum.MON_MISS_ERR.toString());
            }
        }
        this.validateKwargTypes();
        throw new org.python.exceptions.TypeError(DateTimeEnum.DAY_MISS_ERR.toString());
    }

    /**
     * Method for when only one input is included in each of the args and kwargs parameters
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
     * Validates arg input values are of type Integer, otherwise throws exception
     */
    private void validateArgTypes() {
        for (Object arg : this.args) {
            if (arg == null) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.NONE_TYPE_ERR.toString());
            }

            if (arg instanceof org.python.types.Str) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.STR_TYPE_ERR.toString());
            }

            if (!(arg instanceof org.python.types.Int)) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() + arg.typeName());
            }
        }
    }

    /**
     * Validates kwarg input values are of type Integer, otherwise throws exception
     */
    private void validateKwargTypes() {
        for (Map.Entry<String, Object> kwarg : this.kwargs.entrySet()) {
            if (kwarg.getValue() == null) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.NONE_TYPE_ERR.toString());
            }

            if (kwarg.getValue() instanceof org.python.types.Str) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.STR_TYPE_ERR.toString());
            }

            if (!(kwarg.getValue() instanceof org.python.types.Int)) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() +
                    kwarg.getValue().typeName());
            }
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
     * Creates a new Date() instance for the current local date
     * @return current local date
     */
    @org.python.Method(__doc__ = "")
    public static Date today() {
        LocalDate today = LocalDate.now();
        org.python.Object[] args = { org.python.types.Int.getInt(today.getYear()),
            org.python.types.Int.getInt(today.getMonthValue()), org.python.types.Int.getInt(today.getDayOfMonth()) };
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
