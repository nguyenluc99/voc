package org.python.stdlib.datetime;

public enum DateTimeEnum {
    /**
     * Enum class for datetime.date
     * Date: 23/09/2020
     */

    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    YR_VAL_ERR("year %d is out of range"),
    MON_VAL_ERR("month must be in 1..12"),
    DAY_VAL_ERR("day is out of range for month"),
    SYNTAX_ERR("positional argument follows keyword argument"),
    MAX_ARG_ERR("function takes at most 3 arguments (%d given)");

    private final String dateTimeEnum;

    /**
     * Initializes dateTimeEnum with string
     * @param dateTimeEnum string being set to dateTimeEnum
     */
    DateTimeEnum(final String dateTimeEnum) {
        this.dateTimeEnum = dateTimeEnum;
    }

    /**
     * Get dateTimeEnum as a string
     * @return dateTimeENum as a string
     */
    @Override
    public String toString() {
        return dateTimeEnum;
    }
}
