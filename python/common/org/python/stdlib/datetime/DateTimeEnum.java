package org.python.stdlib.datetime;

/**
 * Enum class for datetime.date
 * Last edited: 01/10/2020
 */
public enum DateTimeEnum {

    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    YR_VAL_ERR("year %d is out of range"),
    MON_VAL_ERR("month must be in 1..12"),
    DAY_VAL_ERR("day is out of range for month"),
    SYNTAX_ERR("positional argument follows keyword argument"),
    MAX_ARG_ERR("function takes at most 3 arguments (%d given)"),
    TYPE_ERR("integer argument expected, got "),
    DAY_MISS_ERR("function missing required argument 'day' (pos 3)"),
    MON_MISS_ERR("function missing required argument 'month' (pos 2)"),
    YR_MISS_ERR("function missing required argument 'year' (pos 1)"),
    STR_TYPE_ERR("an integer is required (got type str)"),
    NONE_TYPE_ERR("an integer is required (got type NoneType)"),
    ERROR("error"),
    NO_ERROR("no error"),
    MIN_DATE_STR("0001-01-01"),
    MAX_DATE_STR("9999-12-31"),
    MON("MONDAY"),
    TUE("TUESDAY"),
    WED("WEDNESDAY"),
    THU("THURSDAY"),
    FRI("FRIDAY"),
    SAT("SATURDAY"),
    SUN("SUNDAY"),
    REPLACE_YR_ERR("argument for replace() given by name ('year') and position (1)"),
    REPLACE_MON_ERR("argument for replace() given by name ('month') and position (2)"),
    INVALID_ISOFORMAT_STR("Invalid isoformat string: '%s'"),
    INVALID_ISOFORMAT_TYPE("fromisoformat: argument must be str"),
    DATE_STR_REGEX("\\d{4}-\\d{2}-\\d{2}");

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
