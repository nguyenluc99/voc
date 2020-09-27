package org.python.stdlib.datetime;

import org.python.Object;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

@org.python.Module(
    __doc__ =
        "This module provides various functions to represent a date(year, month, day) in an idealized calendar,\n" +
            "the current Gregorian calendar indefinitely extended in both directions. January 1 of year 1 is called\n" +
            "day number 1, January 2 of year 1 is called day number 2, and so on.\n" +
            "It is a java implementation of the Python datetime.date module\n" +
            "\n" +
            "Last edited: 27/09/2020\n" +
            "\n" +
            "Attributes:\n" +
            "\n" +
            "year -- (instance attribute) between MINYEAR and MAXYEAR inclusive\n" +
            "month -- (instance attribute) between 1 and 12 inclusive\n" +
            "day -- (instance attribute) between 1 and the number of days in the given month of the given year\n" +
            "max -- (class attribute) the earliest representable date, date(MINYEAR, 1, 1)\n" +
            "min -- (class attribute) the latest representable date, date(MAXYEAR, 12, 31)\n" +
            "\n" +
            "Variables:\n" +
            "\n" +
            "maxYear -- private final var for the maximum year: 9999\n" +
            "maxMonth -- private final var for the maximum month: 12\n" +
            "maxDay -- private final var for the maximum day: 31\n" +
            "minDate -- private final var for the minimum date for each of year, month and day: 1\n" +
            "weekdays -- private final var for mapping weekday strings returned from LocalDate to an int in [0, 6]\n" +
            "kwargs -- private final var for HashMap of keyword arguments passed to the class in the kwargs param\n" +
            "args -- private final var for array of argument names passed to the class in the args param\n" +
            "\n" +
            "Functions:\n" +
            "\n" +
            "__repr__() -- (instance method) return string representation of Date object, format: YYYY-MM-DD\n" +
            "today() -- (class method) return current local date\n" +
            "ctime() -- (instance method) return a string representing the date, format: Wed Dec  D HH:MM:SS YYYY\n" +
            "weekday() -- (instance method) integer in range [0, 6] representing Monday to Sunday, respectively"
)
public class Date extends org.python.types.Object {

    private final int maxYear = 9999;
    private final int maxMonth = 12;
    private final int maxDay = 31;
    private final int minDate = 1;

    private final Map<String, Integer> weekdays;

    private final Map<String, Object> kwargs;
    private final Object[] args;

    @org.python.Attribute
    public final org.python.types.Int year;

    @org.python.Attribute
    public final org.python.types.Int month;

    @org.python.Attribute
    public final org.python.types.Int day;

    @org.python.Attribute
    public static final Date min = __min__();

    @org.python.Attribute
    public static final Date max = __max__();

    @org.python.Method(
        __doc__ = "Date(args, kwargs)\n" +
            "\n" +
            "Class constructor for datetime.date.\n" +
            "Takes exactly 3 inputs, whether arg, kwarg or both.\n" +
            "Assumes syntax order of input is correct. That is, args are always before kwargs.\n",
        args = {"args"},
        kwargs = "kwargs"
    )
    public Date(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        super();
        this.kwargs = kwargs;
        this.args = args;

        int totalArguments = this.args.length + this.kwargs.size();

        switch(totalArguments) {
            case 3: // length of kwargs and args combined is equal to 3
                this.validateThreeInputs();
                break;
            case 2: // length of kwargs and args combined is equal to 2 - throws expected error
                this.validateTwoInputs();
                break;
            case 1: // length of kwargs and args combined is equal to 1 - throws expected error
                this.validateOneInput();
                break;
            case 0: // length of kwargs and args combined is equal to 0 - throws expected error
                throw new org.python.exceptions.TypeError(DateTimeEnum.YR_MISS_ERR.toString());
            default: // length of kwargs and args combined is greater than 3 - throws expected error
                throw new org.python.exceptions.TypeError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(),
                    totalArguments));
        }

        // Set year attribute
        if (this.kwargs.containsKey(DateTimeEnum.YEAR.toString())) {
            this.year = (org.python.types.Int) this.kwargs.get(DateTimeEnum.YEAR.toString());
        } else {
            this.year = (org.python.types.Int) this.args[0];
        }

        // Set month attribute
        if (this.kwargs.containsKey(DateTimeEnum.MONTH.toString())) {
            this.month = (org.python.types.Int) this.kwargs.get(DateTimeEnum.MONTH.toString());
        } else {
            this.month = (org.python.types.Int) this.args[1];
        }

        // Set day attribute
        if (this.kwargs.containsKey(DateTimeEnum.DAY.toString())) {
            this.day = (org.python.types.Int) this.kwargs.get(DateTimeEnum.DAY.toString());
        } else {
            this.day = (org.python.types.Int) this.args[2];
        }
        this.validateAttributeRanges();

        // Set mapping of weekday names Mon - Sun to integers in range [0, 6], respectively
        this.weekdays = new HashMap<>();
        this.weekdays.put(DateTimeEnum.MON.toString(), 0);
        this.weekdays.put(DateTimeEnum.TUE.toString(), 1);
        this.weekdays.put(DateTimeEnum.WED.toString(), 2);
        this.weekdays.put(DateTimeEnum.THU.toString(), 3);
        this.weekdays.put(DateTimeEnum.FRI.toString(), 4);
        this.weekdays.put(DateTimeEnum.SAT.toString(), 5);
        this.weekdays.put(DateTimeEnum.SUN.toString(), 6);
    }

    /**
     * Method for when three inputs are included in total between the args and kwargs parameters
     * Validates types and kwarg keys are correct, otherwise throws a Python Type error with the correct message
     */
    private void validateThreeInputs() {
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
    }

    /**
     * Method for when only two inputs are included in total between the args and kwargs parameters
     * Validates input and checks kwarg keys to throw a Python TypeError with the correct message
     */
    private void validateTwoInputs() {
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
    private void validateOneInput() {
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
     * Validates instance attributes year, month and day are set to the correct range values
     */
    private void validateAttributeRanges() {
        // Check year attribute value is within the appropriate range
        if (this.minDate > this.year.value || this.year.value > this.maxYear) {
            throw new org.python.exceptions.ValueError(String.format(DateTimeEnum.YR_VAL_ERR.toString(),
                this.year.value));
        }

        // Check month attribute value is within the appropriate range
        if (this.minDate > this.month.value || this.month.value > this.maxMonth) {
            throw new org.python.exceptions.ValueError(DateTimeEnum.MON_VAL_ERR.toString());
        }
        int maxDayRange;

        // Get the maximum day range for the given month and year
        if (this.month.value == 2) {
            if (this.year.value % 4 == 0) {
                maxDayRange = 29;
            } else {
                maxDayRange = 28;
            }
        } else if (this.month.value == 4 || this.month.value == 6 || this.month.value == 9 || this.month.value == 11) {
            maxDayRange = 30;
        } else {
            maxDayRange = this.maxDay;
        }

        // Check day attribute value is within the appropriate range
        if (this.minDate > this.day.value || this.day.value > maxDayRange) {
            throw new org.python.exceptions.ValueError(DateTimeEnum.DAY_VAL_ERR.toString());
        }
    }

    @org.python.Method(
        __doc__ = "__repr__() -> org.python.types.Str\n" +
            "\n" +
            "Method for returning the Date object representation as a string in format: YYYY-MM-DD."
    )
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
     * The latest representable date
     * @return date(MAXYEAR, 12, 31)
     */
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
    private static Date __min__() {
        org.python.types.Int day = org.python.types.Int.getInt(1);
        org.python.types.Int month = org.python.types.Int.getInt(1);
        org.python.types.Int year = org.python.types.Int.getInt(1);
        org.python.Object[] args = { year, month, day };
	return new Date(args, Collections.emptyMap());
    }

    @org.python.Method(
        __doc__ = "today() -> Date()\n" +
            "\n" +
            "Method for returning a new Date() instance for the current local date."
    )
    public static Date today() {
        LocalDate today = LocalDate.now();
        org.python.Object[] args = { org.python.types.Int.getInt(today.getYear()),
            org.python.types.Int.getInt(today.getMonthValue()), org.python.types.Int.getInt(today.getDayOfMonth()) };
        return new Date(args, Collections.emptyMap());
    }

    @org.python.Method(
        __doc__ = "ctime() -> org.python.types.Str\n" +
            "\n" +
            "Method for returning a string representing the date, format: Wed Dec  D HH:MM:SS YYYY"
    )
    public org.python.Object ctime() {
        String[] monthList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        double monthNum = this.month.value;
        String monthStr = monthList[(int) monthNum - 1];

        String[] weekdayList = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        String weekdayStr = weekdayList[(int) this.weekday().value];

        long dayNum = ((org.python.types.Int) this.day).value;
        String dayStr = new String(Long.toString(dayNum));
        if (dayNum < 10) dayStr = " " + dayStr;

        long yearNum = ((org.python.types.Int) this.year).value;
        String yearStr = new String(Long.toString(yearNum));
        if (yearNum < 10) {
            yearStr = "000" + yearStr;
        } else if (yearNum < 100) {
            yearStr = "00" + yearStr;
        } else if (yearNum < 1000) {
            yearStr = "0" + yearStr;
        }
        return new org.python.types.Str(weekdayStr + " " + monthStr + " " + dayStr + " 00:00:00 " + yearStr);
    }

    @org.python.Method(
        __doc__ = "weekday() -> org.python.types.Int.getInt\n" +
            "\n" +
            "Method for returning the day of the week as an integer, where Monday is 0 and Sunday is 6."
    )
    public org.python.types.Int weekday() {
        int weekdayInt = this.weekdays.get(LocalDate.parse(String.valueOf(this.__repr__())).getDayOfWeek().toString());
        return org.python.types.Int.getInt(weekdayInt);
    }
}
