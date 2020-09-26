package org.python.stdlib.datetime;

import org.python.Object;
import java.util.Collections;
import java.util.Map;
import java.time.LocalDate;

/**
 * Java class for Python's datetime.date class
 * Assumes syntax order of input is correct. That is, args are always before kwargs
 * Last edited: 26/09/2020
 */
public class Date extends org.python.types.Object {

    private final int maxYear = 9999;
    private final int maxMonth = 12;
    private final int maxDay = 31;
    private final int minDate = 1;

    private final Map<String, Object> kwargs;
    private final Object[] args;

    @org.python.Attribute
    public org.python.Object year = __year__();

    @org.python.Attribute
    public org.python.Object month = __month__();

    @org.python.Attribute
    public org.python.Object day = __day__();

    @org.python.Attribute
    public static final Date min = __min__();

    @org.python.Attribute
    public static final Date max = __max__();

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
                throw new org.python.exceptions.TypeError(DateTimeEnum.YR_MISS_ERR.toString());
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
            throw new org.python.exceptions.TypeError(DateTimeEnum.MON_MISS_ERR.toString());
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
        int maxDayRange;

        // Get the maximum day range for the given month and year
        if (((org.python.types.Int) this.month).value == 2) {
            if (((org.python.types.Int) this.year).value % 4 == 0) {
                maxDayRange = 29;
            } else {
                maxDayRange = 28;
            }
        } else if (((org.python.types.Int) this.month).value == 4 || ((org.python.types.Int) this.month).value == 6 ||
            ((org.python.types.Int) this.month).value == 9 || ((org.python.types.Int) this.month).value == 11) {
            maxDayRange = 30;
        } else {
            maxDayRange = this.maxDay;
        }

        // Check day attribute value is within the appropriate range
        if (this.minDate > ((org.python.types.Int) this.day).value ||
            ((org.python.types.Int) this.day).value > maxDayRange) {
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
     * Method for when only one input is included in total between each of the args and kwargs parameters
     * Validates input and checks kwarg keys to return a Python TypeError with the correct message
     */
    private void oneArgument() {
        this.validateArgTypes();

        if (kwargs.containsKey(DateTimeEnum.DAY.toString()) || kwargs.containsKey(DateTimeEnum.MONTH.toString())) {
            throw new org.python.exceptions.TypeError(DateTimeEnum.YR_MISS_ERR.toString());
        }
        this.validateKwargTypes();
        throw new org.python.exceptions.TypeError(DateTimeEnum.MON_MISS_ERR.toString());
    }

    /**
     * Validates arg input values are of type Integer, otherwise throws exception
     */
    private void validateArgTypes() {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.NONE_TYPE_ERR.toString());
            }

            if (args[i] instanceof org.python.types.Str) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.STR_TYPE_ERR.toString());
            }

            if (args[i] instanceof org.python.types.Bool) {
                if (((org.python.types.Bool) args[i].__bool__()).value) {
                    args[i] = org.python.types.Int.getInt(1);
                } else {
                    args[i] = org.python.types.Int.getInt(0);
                }
            }

            if (!(args[i] instanceof org.python.types.Int)) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() + args[i].typeName());
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

            if (kwarg.getValue() instanceof org.python.types.Bool) {
                if (((org.python.types.Bool) kwarg.getValue().__bool__()).value) {
                    kwarg.setValue(org.python.types.Int.getInt(1));
                } else {
                    kwarg.setValue(org.python.types.Int.getInt(0));
                }
            }

            if (!(kwarg.getValue() instanceof org.python.types.Int)) {
                throw new org.python.exceptions.TypeError(DateTimeEnum.TYPE_ERR.toString() +
                    kwarg.getValue().typeName());
            }
        }
    }

    /**
     * Method for returning the Date object representation
     * @return string representation of Date object: YYYY-MM-DD
     */
    @org.python.Method(__doc__ = "")
    public org.python.types.Str __repr__() {
        String year = ((org.python.types.Str) this.year.__str__()).value;

        while (year.length() < 4)
            year = "0".concat(year);
        String month = ((org.python.types.Str) this.month.__str__()).value;

        if (month.length() < 2)
            month = "0".concat(month);
        String day = ((org.python.types.Str) this.day.__str__()).value;

        if (day.length() < 2)
            day = "0".concat(day);
        return new org.python.types.Str(String.format("%s-%s-%s", year, month, day));
    }

    /**
     * Getter for year instance attribute - between MINYEAR and MAXYEAR inclusive
     * @return year attribute
     */
    @org.python.Method(__doc__ = "")
        private org.python.Object __year__() { return this.year; }

    /**
     * Getter for month instance attribute - between 1 and 12 inclusive.
     * @return month attribute
     */
    @org.python.Method(__doc__ = "")
        private org.python.Object __month__() { return this.month; }

    /**
     * Getter for day instance attribute - between 1 and the number of days in the given month of the given year
     * @return day attribute
     */
    @org.python.Method(__doc__ = "")
        private org.python.Object __day__() { return this.day; }

    /**
     * The latest representable date
     * @return date(MAXYEAR, 12, 31)
     */
    @org.python.Method(__doc__ = "")
    private static Date __max__() {
        org.python.types.Int day = org.python.types.Int.getInt(31);
        org.python.types.Int month = org.python.types.Int.getInt(12);
        org.python.types.Int year = org.python.types.Int.getInt(9999);
        org.python.Object[] args = { year, month, day };
        return new Date(args, Collections.emptyMap());
    }

    /**
     * The earliest representable date
     * @return date(MINYEAR, 1, 1)
     */
    @org.python.Method(__doc__ = "")
    private static Date __min__() {
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
        return new org.python.types.Str(weekdayStr + " " + monthStr + " " + this.day + " 00:00:00 " + this.year);
    }

    /**
     * Return the day of the week as an integer, where Monday is 0 and Sunday is 6
     * @return integer in range [0, 6] representing Monday to Sunday, respectively
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
