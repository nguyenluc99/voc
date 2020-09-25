package test.python.stdlib.datetime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.python.Object;
import org.python.exceptions.Exception;
import org.python.exceptions.SyntaxError;
import org.python.exceptions.ValueError;
import org.python.exceptions.TypeError;
import org.python.stdlib.datetime.Date;
import org.python.stdlib.datetime.DateTimeEnum;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test org.python.stdlib.datetime.Date class
 * @author Adam Ross,
 * Last edited: 25/09/2020
 */
@DisplayName("Junit test: org.python.stdlib.datetime.Date")
public class DateTest {

    private Date newDate;
    private org.python.Object[] args;
    private Map<java.lang.String, org.python.Object> kwargs;

    private final int maxYear = 9999;
    private final int maxMonth = 12;
    private final int maxDay = 31;
    private final int minDate = 1;

    private final int yearTooLarge = 10000;
    private final int monthTooLarge = 13;
    private final int dayTooLarge = 32;

    /**
     * Convert primitive int to org.python.types.Int type
     * @param arg primitive int type argument
     * @return int converted to org.python.types.Int type
     */
    private org.python.types.Int intToObj(int arg) {
        return org.python.types.Int.getInt(arg);
    }

    /**
     * Convert primitive double to org.python.types.Float type
     * @param arg primitive double type argument
     * @return double converted to org.python.types.Float type
     */
    private org.python.types.Float doubleToObj(double arg) {
        return new org.python.types.Float(arg);
    }

    /**
     * Convert String to org.python.types.Str type
     * @param arg String type argument
     * @return String converted to org.python.types.Str type
     */
    private org.python.types.Str strToObj(String arg) {
        return new org.python.types.Str(arg);
    }

    /**
     * Groups assertions so that all are executed and all failures are reported
     * @param year expected value for Date.year argument being asserted
     * @param month expected value for Date.year argument being asserted
     * @param day expected value for Date.year argument being asserted
     */
    private void assertAllDate(int year, int month, int day) {
        assertAll(
            () -> assertEquals(this.newDate.year, this.intToObj(year)),
            () -> assertEquals(this.newDate.month, this.intToObj(month)),
            () -> assertEquals(this.newDate.day, this.intToObj(day)));
    }

    /**
     * Asserts exception errors are returned and are of the correct type with correct message
     * @param expectedMsg expected message for the expected exception error
     * @param expectedErrType expected type for the expected exception error
     */
    private void assertError(String expectedMsg, Class<? extends Throwable> expectedErrType) {
        Exception exception = (Exception) assertThrows(expectedErrType, () ->
            new Date(this.args, this.kwargs));
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Initializer before each test
     */
    @BeforeEach
    public void init() {
        this.kwargs = new HashMap<>();
    }

    /**
     * Test Date() creation with all three inputs, which are only args
     * Tests the maximum and the minimum possible dates
     */
    @Test
    @DisplayName("Test Date() creation with all three inputs, only args")
    public void testDateCreationWithThreeInputsOnlyArgs() {
        // Test create Date() instance: 9999, 12, 31
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Test create Date() instance: 1, 1, 1
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
    }


    /**
     * Test Date() creation with all three inputs, which are only kwargs
     * Tests the maximum and the minimum possible dates
     */
    @Test
    @DisplayName("Test Date() creation with all three inputs, only kwargs")
    public void testDateCreationWithThreeInputsOnlyKwargs() {
        this.args = new Object[]{};

        // Test create Date() instance: year=9999, month=12, day=31
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Test create Date() instance: year=1, month=1, day=1
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
    }

    /**
     * Test Date() creation with inputs: 9999, 12, day=31
     */
    @Test
    @DisplayName("Test Date() creation with inputs: 9999, 12, day=31")
    public void testDateCreationWithDayKwargAndTwoArgs() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(args, kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date() creation with inputs: 9999, month=12, day=31
     */
    @Test
    @DisplayName("Test Date() creation with inputs: 9999, month=12, day=31")
    public void testDateCreationWithMonthAndDayKwargsAndOneArg() {
        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date() creation with inputs: 9999, month=12, 31 - expect SyntaxError
     */
    @Test
    @DisplayName("Test Date() creation with inputs: 9999, month=12, 31 - expect SyntaxError")
    @Disabled
    public void testDateCreationWithMonthKwargAndTwoArgs() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() creation with inputs: year=9999, 12, 31 - expect SyntaxError
     */
    @Test
    @DisplayName("Test Date() creation with inputs: year=9999, 12, 31 - expect SyntaxError")
    @Disabled
    public void testDateCreationWithYearKwargAndTwoArgs() {
        this.args = new Object[]{this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() creation with inputs: year=9999, 12, day=31 - expect SyntaxError
     */
    @Test
    @DisplayName("Test Date() creation with inputs: year=9999, 12, day=31 - expect SyntaxError")
    @Disabled
    public void testDateCreationWithYearAndDayKwargsAndArg() {
        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() creation with inputs: year=9999, month=12, 31 - expect SyntaxError
     */
    @Test
    @DisplayName("Test Date() creation with inputs: year=9999, month=12, 31 - expect SyntaxError")
    @Disabled
    public void testDateCreationWithYearAndMonthKwargsAndArg() {
        this.args = new Object[]{this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() creation with too large year arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with arg year too large - expect ValueError")
    public void testDateCreationWithYearArgTooLarge() {
        this.args = new Object[]{this.intToObj(this.yearTooLarge), this.intToObj(this.maxMonth),
            this.intToObj(this.maxDay)};
        this.assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), this.yearTooLarge), ValueError.class);
    }

    /**
     * Test Date() creation with too small year arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with arg year too small - expect ValueError")
    public void testDateCreationWithYearArgTooSmall() {
        this.args = new Object[]{this.intToObj(this.minDate - 1), this.intToObj(this.maxMonth),
            this.intToObj(this.maxDay)};
        this.assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), this.minDate - 1), ValueError.class);
    }

    /**
     * Test Date() creation with too large year kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with kwarg year too large - expect ValueError")
    public void testDateCreationWithYearKwargTooLarge() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.yearTooLarge));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), this.yearTooLarge), ValueError.class);
    }

    /**
     * Test Date() creation with too small year kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with kwarg year too small - expect ValueError")
    public void testDateCreationWithYearKwargTooSmall() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate - 1));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), this.minDate - 1), ValueError.class);
    }

    /**
     * Test Date() creation with too large month arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with arg month too large - expect ValueError")
    public void testDateCreationMonthArgTooLarge() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.monthTooLarge),
            this.intToObj(this.maxDay)};
        this.assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too small month arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with arg month too small - expect ValueError")
    public void testDateCreationMonthArgTooSmall() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.minDate - 1),
            this.intToObj(this.maxDay)};
        this.assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too large month kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with kwarg month too large - expect ValueError")
    public void testDateCreationWithMonthKwargTooLarge() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.monthTooLarge));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too small month kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with kwarg month too small - expect ValueError")
    public void testDateCreationWithMonthKwargTooSmall() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate - 1));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too large day arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with arg day too large - expect ValueError")
    public void testDateCreationWithDayArgTooLarge() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth),
            this.intToObj(this.dayTooLarge)};
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too small day arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with arg day too small - expect ValueError")
    public void testDateCreationWithDayArgTooSmall() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth),
            this.intToObj(this.minDate - 1)};
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too large day kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with kwarg day too large - expect ValueError")
    public void testDateCreationWithDayKwargTooLarge() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.dayTooLarge));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation with too large day kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with kwarg day too small - expect ValueError")
    public void testDateCreationWithDayKwargTooSmall() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate - 1));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() instance creation with too many (four) arg inputs - expect TypeError
     */
    @Test
    @DisplayName("Test Date() instance with too many (four) arg inputs - expect TypeError")
    public void testDateCreationWithFourArgsTooMany() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay),
            this.intToObj(this.maxDay)};
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);

        // with wrong value types
        this.args = new Object[]{this.doubleToObj(1.0), this.strToObj(DateTimeEnum.NO_ERROR.toString()), null,
            this.doubleToObj(1.0)};
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() creation with too many inputs (3 kwargs and 1 arg) - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with too many inputs (3 kwargs and 1 arg) - expect TypeError")
    public void testDateCreationWithThreeKwargsAndOneArgTooMany() {
        this.args = new Object[]{intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);

        // with wrong value types
        this.args = new Object[]{strToObj(DateTimeEnum.NO_ERROR.toString())};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(1.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() creation with too many inputs (2 kwargs and 2 args) - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with too many inputs (2 kwargs and 2 args) - expect TypeError")
    public void testDateCreationWithTwoKwargsAndTwoArgsTooMany() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);

        // with wrong value types
        this.args = new Object[]{this.strToObj(DateTimeEnum.NO_ERROR.toString()), null};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(1.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() creation with too many inputs (2 kwargs and 2 args) - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with too many inputs (2 kwargs and 2 args) - expect TypeError")
    public void testDateCreationWithOneKwargAndThreeArgsTooMany() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);

        // with wrong value types
        this.args = new Object[]{null, this.strToObj(DateTimeEnum.NO_ERROR.toString()), this.doubleToObj(1.0)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(1.0));
        this.assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() creation with two inputs, which are only args - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with two inputs, only args - expect TypeError")
    public void testDateCreationWithTwoInputsOnlyArgs() {
        // Test create Date() instance: 12, 31
        this.args = new Object[]{this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        // Test create Date() instance: 1, 1
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.args = new Object[]{null, this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{null, this.strToObj(DateTimeEnum.NO_ERROR.toString())};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{null, this.doubleToObj(1.0)};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.doubleToObj(0.0), this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.doubleToObj(0.0), this.strToObj(DateTimeEnum.NO_ERROR.toString())};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.doubleToObj(0.0), null};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString()), this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString()), null};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString()), this.doubleToObj(1.0)};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with two kwarg inputs; year and month - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with two kwarg inputs; year and month - expect TypeError")
    public void testDateCreationWithTwoInputsOnlyKwargsYearAndMonth() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(0.0));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(0.0));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with two kwarg inputs; month and day - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with two kwarg inputs; month and day - expect TypeError")
    public void testDateCreationWithTwoInputsOnlyKwargsMonthAndDay() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(1.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(1.0));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with two kwarg inputs; year and day - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with two kwarg inputs; year and day - expect TypeError")
    public void testDateCreationWithTwoInputsOnlyKwargsYearAndDay() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(1.0));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(0.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with one arg and one kwarg year input - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with one arg and one kwarg year input - expect TypeError")
    public void testDateCreationWithTwoInputsOneArgAndKwargYear() {
        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.args = new Object[]{null};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{null};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.doubleToObj(0.0)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(1.0));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.doubleToObj(0.0)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(0.0));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString())};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString())};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with one arg and one kwarg month input - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with one arg and one kwarg month input - expect TypeError")
    public void testDateCreationWithTwoInputsOneArgAndKwargMonth() {
        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(0.0));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with one arg and one kwarg day input - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with one arg and one kwarg day input - expect TypeError")
    public void testDateCreationWithTwoInputsOneArgAndKwargDay() {
        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // with wrong value types
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(1.0));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj(DateTimeEnum.NO_ERROR.toString()));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 arg inputs including String type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg inputs including String type - expect TypeError")
    public void testDateCreationWithThreeArgsIncludingStrType() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate),
            this.strToObj(DateTimeEnum.ERROR.toString())};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString()), this.intToObj(this.minDate),
            this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate), this.strToObj(DateTimeEnum.ERROR.toString()),
            this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 kwarg inputs including String type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 kwarg inputs including String type - expect TypeError")
    public void testDateCreationWithThreeKwargsIncludingStrType() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 arg and kwarg inputs including String type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg and kwarg inputs including String type - expect TypeError")
    public void testDateCreationWithThreeArgsAndKwargsIncludingStrType() {
        this.args = new Object[]{this.strToObj(DateTimeEnum.ERROR.toString())};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.strToObj(DateTimeEnum.ERROR.toString()));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 arg inputs including null type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg inputs including null type - expect TypeError")
    public void testDateCreationWithThreeArgsIncludingNullType() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), null};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{null, this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate), null, this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 kwarg inputs including null type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 kwarg inputs including null type - expect TypeError")
    public void testDateCreationWithThreeKwargsIncludingNullType() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 arg and kwarg inputs including null type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg and kwarg inputs including null type - expect TypeError")
    public void testDateCreationWithThreeArgsAndKwargsIncludingNullType() {
        this.args = new Object[]{null};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with 3 arg inputs including double type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg inputs including double type - expect TypeError")
    public void testDateCreationWithThreeArgsIncludingDoubleType() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.doubleToObj(0.0)};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.doubleToObj(0.0), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate), this.doubleToObj(0.0), this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);
    }

    /**
     * Test Date() creation with 3 kwarg inputs including double type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 kwarg inputs including double type - expect TypeError")
    public void testDateCreationWithThreeKwargsIncludingDoubleType() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(0.0));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(0.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(0.0));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);
    }

    /**
     * Test Date() creation with 3 arg and kwarg inputs including double type - expect TypeError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg and kwarg inputs including double type - expect TypeError")
    public void testDateCreationWithThreeArgsAndKwargsIncludingDoubleType() {
        this.args = new Object[]{this.doubleToObj(0.0)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(0.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(0.0).typeName(),
            TypeError.class);
    }
}
