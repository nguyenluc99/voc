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
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test org.python.stdlib.datetime.Date class
 * @author Adam Ross, Meriton Bytyqi
 * Last edited: 27/09/2020
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
     * Convert boolean to org.python.types.Bool type
     * @param arg boolean type argument
     * @return boolean converted to org.python.types.Bool type
     */
    private org.python.types.Bool boolToObj(boolean arg) {
        return org.python.types.Bool.getBool(arg);
    }

    /**
     * Groups assertions so that all are executed and all failures are reported
     * @param year  expected value for Date.year argument being asserted
     * @param month expected value for Date.year argument being asserted
     * @param day   expected value for Date.year argument being asserted
     */
    private void assertAllDate(int year, int month, int day) {
        assertAll(
            () -> assertEquals(this.newDate.year, this.intToObj(year)),
            () -> assertEquals(this.newDate.month, this.intToObj(month)),
            () -> assertEquals(this.newDate.day, this.intToObj(day)));
    }

    /**
     * Asserts exception errors are returned and are of the correct type with correct message
     * @param expectedMsg     expected message for the expected exception error
     * @param expectedErrType expected type for the expected exception error
     */
    private void assertError(String expectedMsg, Class<? extends Throwable> expectedErrType) {
        Exception exception = (Exception) assertThrows(expectedErrType, () ->
            new Date(this.args, this.kwargs));
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Asserts exception errors for invalid Date.replace() are returned and of the correct type with correct message
     * @param expectedMsg     expected message for the expected exception error
     * @param expectedErrType expected type for the expected exception error
     */
    private void assertReplaceError(String expectedMsg, Class<? extends Throwable> expectedErrType) {
        Exception exception = (Exception) assertThrows(expectedErrType, () ->
            this.newDate.replace(this.args, this.kwargs));
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
     * Test Date() creation with inputs: 12, year=9999, day=31
     */
    @Test
    @DisplayName("Test Date() creation with inputs: 12, year=9999, day=31")
    public void testDateCreationWithYearAndDayKwargsAfterArg() {
        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with inputs: 12, 31, year=9999
     */
    @Test
    @DisplayName("Test Date() creation with inputs: 12, 31, year=9999")
    public void testDateCreationWithYearKwargAfterTwoArgs() {
        this.args = new Object[]{this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() creation with inputs: 9999, 31, month=12
     */
    @Test
    @DisplayName("Test Date() creation with inputs: 9999, 31, month=12")
    public void testDateCreationWithMonthKwargAfterTwoArgs() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);
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

        // with bool values (which are accepted in Python datetime.date)
        this.args = new Object[]{this.boolToObj(true), this.intToObj(this.maxMonth)};
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.boolToObj(false), this.intToObj(this.maxMonth)};
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxYear), this.boolToObj(true)};
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxYear), this.boolToObj(false)};
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

        // with bool values (which are accepted in Python datetime.date)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(true));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(false));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(false));
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

        // with bool values (which are accepted in Python datetime.date)
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(true));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(false));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(false));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
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

        // with bool values (which are accepted in Python datetime.date)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(true));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(false));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(false));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
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

        // with bool values (which are accepted in Python datetime.date)
        this.args = new Object[]{this.boolToObj(true)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.boolToObj(false)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(false));
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

        // with bool values (which are accepted in Python datetime.date)
        this.args = new Object[]{this.boolToObj(true)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.boolToObj(false)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.DAY_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(false));
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

        // with bool values (which are accepted in Python datetime.date)
        this.args = new Object[]{this.boolToObj(true)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.boolToObj(false)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(false));
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

    /**
     * Test datetime.date.today() class method
     */
    @Test
    @DisplayName("Test datetime.date.today() class method")
    public void testDateTodayClassMethod() {
        LocalDate curLocalDate = LocalDate.now();
        this.newDate = Date.today();
        this.assertAllDate(curLocalDate.getYear(), curLocalDate.getMonthValue(), curLocalDate.getDayOfMonth());
    }

    /**
     * Test Date() creation with 3 arg and kwarg inputs including boolean type: true
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg and kwarg inputs including boolean type: true")
    public void testDateCreationWithThreeArgsAndKwargsIncludingBooleanTypeTrue() {
        this.args = new Object[]{this.boolToObj(true)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(1, this.maxMonth, this.maxDay);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(true));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, 1, this.maxDay);
    }

    /**
     * Test Date() creation with 3 arg and kwarg inputs including boolean type: false - expect ValueError
     */
    @Test
    @DisplayName("Test Date() creation with 3 arg and kwarg inputs including boolean type: false - expect ValueError")
    public void testDateCreationWithThreeArgsAndKwargsIncludingBooleanTypeFalse() {
        this.args = new Object[]{this.boolToObj(false)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), 0), ValueError.class);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(false));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date().__repr__() instance method
     */
    @Test
    @DisplayName("Test Date().__repr__() instance method")
    public void testDateReprInstanceMethod() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.__repr__(), this.strToObj(DateTimeEnum.MIN_DATE_STR.toString()));

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.__repr__(), this.strToObj(DateTimeEnum.MAX_DATE_STR.toString()));

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.__repr__(), this.strToObj(DateTimeEnum.MAX_DATE_STR.toString()));

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.__repr__(), this.strToObj(DateTimeEnum.MIN_DATE_STR.toString()));
    }

    /**
     * Test Date().weekday() instance method for 10 random dates between 0001-01-01 to 9999-12-31
     */
    @Test
    @DisplayName("Test Date().weekday() instance method for 10 random dates between 0001-01-01 to 9999-12-31")
    public void testDateWeekDayInstanceMethodPostMidSixteenthCentury() {
        // year=9999, month=12, day=31
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(4), this.newDate.weekday());

        // year=2020, month=9, day=26
        this.args = new Object[]{this.intToObj(2020), this.intToObj(9), this.intToObj(26)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(5), this.newDate.weekday());

        // year=1595, month=3, day=15
        this.args = new Object[]{this.intToObj(1595), this.intToObj(3), this.intToObj(15)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(2), this.newDate.weekday());

        // year=1415, month=10, day=25
        this.args = new Object[]{this.intToObj(1415), this.intToObj(10), this.intToObj(25)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(2), this.newDate.weekday());

        // year=1, month=1, day=1
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(0), this.newDate.weekday());

        // year=1066, month=10, day=14
        this.args = new Object[]{this.intToObj(1066), this.intToObj(10), this.intToObj(14)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(6), this.newDate.weekday());

        // year=1776, month=7, day=4
        this.args = new Object[]{this.intToObj(1766), this.intToObj(7), this.intToObj(4)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(4), this.newDate.weekday());

        // year=1945, month=8, day=15
        this.args = new Object[]{this.intToObj(1945), this.intToObj(8), this.intToObj(15)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(2), this.newDate.weekday());

        // year=1929, month=10, day=29
        this.args = new Object[]{this.intToObj(1929), this.intToObj(10), this.intToObj(29)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(1), this.newDate.weekday());

        // year=793, month=6, day=8
        this.args = new Object[]{this.intToObj(793), this.intToObj(6), this.intToObj(8)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.intToObj(1), this.newDate.weekday());
    }

    /**
     * Test Date() with one arguments
     */
    @Test
    @DisplayName("Test Date() with one argument")
    public void testDateWithOneArguments() {
        // Test: Date(1)
        this.args = new Object[]{this.intToObj(this.minDate)};
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // Test: Date(1.2)
        this.args = new Object[]{this.doubleToObj(1.2)};
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(1.2).typeName(),
            TypeError.class);

        // Test: Date(null)
        this.args = new Object[]{null};
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        // Test: Date(true)
        this.args = new Object[]{boolToObj(true)};
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // Test: Date(false)
        this.args = new Object[]{boolToObj(false)};
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // Test: Date("str")
        this.args = new Object[]{strToObj("str")};
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        // Test: Date(year=1)
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // Test: Date(year=1.2)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(1.2));
        this.assertError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(1.2).typeName(),
            TypeError.class);

        // Test: Date(year="str")
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.strToObj("str"));
        this.assertError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);

        // Test: Date(year=null)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.assertError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);

        // Test: Date(year=true)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);

        // Test: Date(year=false)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.boolToObj(false));
        this.assertError(DateTimeEnum.MON_MISS_ERR.toString(), TypeError.class);
        this.kwargs.clear();

        // Test: Date(month=1)
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(month=1.2)
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(1.2));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(month="str")
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.strToObj("str"));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(month=null)
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(month=true)
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(month=false)
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.boolToObj(false));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);
        this.kwargs.clear();

        // Test: Date(day=1)
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(day=1.2)
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(1.2));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(day="str")
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.strToObj("str"));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(day=null)
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(day=true)
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(true));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);

        // Test: Date(day=false)
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.boolToObj(false));
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date() with zero arguments
     */
    @Test
    @DisplayName("Test Date() with zero arguments")
    public void testDateWithZeroArguments() {
        this.args = new Object[]{};
        this.assertError(DateTimeEnum.YR_MISS_ERR.toString(), TypeError.class);
    }
  
    /**
     * Test Date.max class attribute
     */
    @Test
    @DisplayName("Test Date.max class attribute")
    public void testDateMaxClassAttribute() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
        this.newDate = Date.max;
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date.min class attribute
     */
    @Test
    @DisplayName("Test Date.min class attribute")
    public void testDateMinClassAttribute() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
        this.newDate = Date.min;
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
    }

    /**
     * Test Date.year instance attribute
     */
    @Test
    @DisplayName("Test Date.year instance attribute")
    public void testDateYearInstanceAttribute() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(intToObj(this.maxYear), this.newDate.year);
    }

    /**
     * Test Date.month instance attribute
     */
    @Test
    @DisplayName("Test Date.month instance attribute")
    public void testDateMonthInstanceAttribute() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(intToObj(this.maxMonth), this.newDate.month);
    }

    /**
     * Test Date.day instance attribute
     */
    @Test
    @DisplayName("Test Date.day instance attribute")
    public void testDateDayInstanceAttribute() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(intToObj(this.maxDay), this.newDate.day);
    }

    /**
     * Test Date() creation max day range for February during a leap year
     */
    @Test
    @DisplayName("Test Date() creation max day range for February during a leap year")
    public void testDateCreationMaxDayRangeFebruaryLeapYear() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 2, 29);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2028));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2028, 2, 29);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2028));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2012));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2012, 2, 29);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2012));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(1996));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(1996, 2, 29);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(1996));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation max day range for February not during a leap year
     */
    @Test
    @DisplayName("Test Date() creation max day range for February not during a leap year")
    public void testDateCreationMaxDayRangeFebruaryNotLeapYear() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2019));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(28));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2019, 2, 28);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2019));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2021));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(28));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2021, 2, 28);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2021));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2018));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(28));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2018, 2, 28);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2018));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2017));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(28));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2017, 2, 28);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2017));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(2));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(29));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation max day range for 30 day months
     */
    @Test
    @DisplayName("Test Date() creation max day range for 30 day months")
    public void testDateCreationMaxDayRangeThirtyDayMonths() {
        this.args = new Object[]{};

        // April
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(4));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 4, 30);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(4));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // June
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(6));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 6, 30);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(6));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // September
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(9));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 9, 30);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(9));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // November
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(11));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(30));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 11, 30);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(11));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() creation max day range for 30 day months
     */
    @Test
    @DisplayName("Test Date() creation max day range for 31 day months")
    public void testDateCreationMaxDayRangeThirtyOneDayMonths() {
        this.args = new Object[]{};

        // January
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(1));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 1, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(1));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // March
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(3));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 3, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(3));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // May
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(5));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 5, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(5));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // July
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(7));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 7, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(7));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // August
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(8));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 8, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(8));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // October
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(10));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 10, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(10));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);

        // December
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(12));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(31));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(2020, 12, 31);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(2020));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(12));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(32));
        this.assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date().replace() with no inputs in total between args and kwargs
     */
    @Test
    @DisplayName("Test Date().replace() with no inputs in total between args and kwargs")
    public void testDateReplaceNoInputs() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{};
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.args = new Object[]{};
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.kwargs.clear();
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.kwargs.clear();
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
    }

    /**
     * Test Date().replace() with three valid inputs in total between args and kwargs
     */
    @Test
    @DisplayName("Test Date().replace() with three valid inputs in total between args and kwargs")
    public void testDateReplaceThreeValidInputs() {
        // Date(1, 1, 1).replace(9999, 12, 31)
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(year=1, month=1, day=1)
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(year=9999, month=12, day-31).replace(1, 1, 1)
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, 12, day=31)
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, day=31).replace(1, month=1, day=1)
        this.args = new Object[]{this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, month=1, day=1).replace(year=9999, month=12, day=31)
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(year=9999, month=12, day=31).replace(1, 1, day=1)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
    }

    /**
     * Test Date().replace() with more than three inputs in total between args and kwargs
     */
    @Test
    @DisplayName("Test Date().replace() with more than three inputs in total between args and kwargs")
    public void testDateReplaceMoreThreeInputs() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date().replace() with one valid arg input
     */
    @Test
    @DisplayName("Test Date().replace() with one valid arg input")
    public void testDateReplaceOneValidArgInput() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.args = new Object[]{this.intToObj(this.minDate)};
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date().replace() with two valid arg inputs
     */
    @Test
    @DisplayName("Test Date().replace() with two valid arg inputs")
    public void testDateReplaceTwoValidArgInputs() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth)};
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.minDate);
    }

    /**
     * Test Date().replace() with one invalid arg input
     */
    @Test
    @DisplayName("Test Date().replace() with one invalid arg input")
    public void testDateReplaceOneInvalidArgInput() {
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(null)
        this.args = new Object[]{null};
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace("str")
        this.args = new Object[]{strToObj(DateTimeEnum.ERROR.toString())};
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(2.0)
        this.args = new Object[]{doubleToObj(2.0)};
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(2.0).typeName(),
            TypeError.class);
    }

    /**
     * Test Date().replace() with two invalid arg inputs
     */
    @Test
    @DisplayName("Test Date().replace() with two invalid arg inputs")
    public void testDateReplaceTwoInvalidArgInputs() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace(null, "str")
        this.args = new Object[]{null, strToObj(DateTimeEnum.ERROR.toString())};
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace(null, 2.0)
        this.args = new Object[]{null, doubleToObj(2.0)};
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace("str", null)
        this.args = new Object[]{strToObj(DateTimeEnum.ERROR.toString()), null};
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace("str", 2.0)
        this.args = new Object[]{strToObj(DateTimeEnum.ERROR.toString()), doubleToObj(2.0)};
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace(2.0, null)
        this.args = new Object[]{doubleToObj(2.0), null};
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(2.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace(2.0, "str")
        this.args = new Object[]{doubleToObj(2.0), strToObj(DateTimeEnum.ERROR.toString())};
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(2.0).typeName(),
            TypeError.class);
    }

    /**
     * Test Date().replace() with one valid kwarg input
     */
    @Test
    @DisplayName("Test Date().replace() with one valid kwarg input")
    public void testDateReplaceOneValidKwargInput() {
        // Date(9999, 12, 31).replace(day=1)
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.minDate));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.minDate);

        // Date(9999, 12, 31).replace(month=1)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.minDate));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.minDate, this.maxDay);

        // Date(9999, 12, 31).replace(year=1)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.minDate));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date().replace() with two valid kwarg inputs
     */
    @Test
    @DisplayName("Test Date().replace() with two valid kwarg inputs")
    public void testDateReplaceTwoValidKwargInputs() {
        // Date(1, 1, 1).replace(month=12, day=31)
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.maxMonth, this.maxDay);

        // Date(1, 1, 1).replace(month=12, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.minDate);

        // Date(1, 1, 1).replace(day=31, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.minDate, this.maxDay);
    }

    /**
     * Test Date().replace() with one invalid kwarg input
     */
    @Test
    @DisplayName("Test Date().replace() with one invalid kwarg input")
    public void testDateReplaceOneInvalidKwargInput() {
        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(day=null)
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(day="str")
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.DAY.toString(), strToObj(DateTimeEnum.ERROR.toString()));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(day=2.0)
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.DAY.toString(), doubleToObj(2.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(2.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(month=null)
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(month="str")
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.MONTH.toString(), strToObj(DateTimeEnum.ERROR.toString()));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(month=2.0)
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.MONTH.toString(), doubleToObj(2.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(2.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(year=null)
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(year="str")
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.YEAR.toString(), strToObj(DateTimeEnum.ERROR.toString()));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Date(9999, 12, 31).replace(year=2.0)
        this.kwargs.clear();
        this.kwargs.put(DateTimeEnum.YEAR.toString(), doubleToObj(2.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(2.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date().replace() with one arg and one kwarg valid inputs
     */
    @Test
    @DisplayName("Test Date().replace() with one arg and one kwarg valid inputs")
    public void testDateReplaceOneArgAndOneKwargValidInputs() {
        // Date(1, 1, 1).replace(9999, day=31)
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.minDate, this.maxDay);

        // Date(1, 1, 1).replace(9999, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.newDate = this.newDate.replace(this.args, this.kwargs);
        this.assertAllDate(this.maxYear, this.maxMonth, this.minDate);

        // Date(1, 1, 1).replace(12, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date().replace() with one arg and one kwarg invalid inputs
     */
    @Test
    @DisplayName("Test Date().replace() with one arg and one kwarg invalid inputs")
    public void testDateReplaceOneArgAndOneKwargInvalidInputs() {
        // Date(1, 1, 1).replace(null, day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{null};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace("str", day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{strToObj(DateTimeEnum.ERROR.toString())};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999.0, day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{doubleToObj(9999.0)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(9999.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, day=null)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), null);
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, day="str")
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), strToObj(DateTimeEnum.ERROR.toString()));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, day=31.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), doubleToObj(31.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(31.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(null, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{null};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace("str", month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{strToObj(DateTimeEnum.ERROR.toString())};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999.0, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{doubleToObj(9999.0)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(9999.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, month=null)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), null);
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, month="str")
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), strToObj(DateTimeEnum.ERROR.toString()));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, month=12.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), doubleToObj(12.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(12.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(null, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{null};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(DateTimeEnum.NONE_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace("str", year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{strToObj(DateTimeEnum.ERROR.toString())};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(DateTimeEnum.STR_TYPE_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12.0, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{doubleToObj(12.0)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(12.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, year=null)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), null);
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, year="str")
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), strToObj(DateTimeEnum.ERROR.toString()));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, year=9999.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), doubleToObj(9999.0));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date().replace() with one arg and two kwarg invalid inputs
     */
    @Test
    @DisplayName("Test Date().replace() with one arg and two kwarg invalid inputs")
    public void testDateReplaceOneArgAndTwoKwargInvalidInputs() {
        // Date(1, 1, 1).replace(9999.0, month=12, day=31)
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.doubleToObj(9999.0)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(9999.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, month=12.0, day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(12.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(12.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, month=12, day=31.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(31.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(31.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12.0, year=9999, day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.doubleToObj(12.0)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(12.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, year=9999.0, day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(9999.0));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, year=9999, day=31.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(31.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(31.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, year=9999, day=31)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(31.0, year=9999, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.doubleToObj(31.0)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(31.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(31, year=9999.0, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(9999.0));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(31, year=9999, month=12.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(12.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(12.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(31, year=9999, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
    }

    /**
     * Test Date().replace() with two args and one kwarg invalid inputs
     */
    @Test
    @DisplayName("Test Date().replace() with two arg and one kwarg invalid inputs")
    public void testDateReplaceTwoArgAndOneKwargInvalidInputs() {
        // Date(1, 1, 1).replace(9999.0, 12, day=31)
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.doubleToObj(9999.0), this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.intToObj(this.maxDay));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(9999.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, 12, day=31.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), this.doubleToObj(31.0));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(31.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999.0, 31, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.doubleToObj(9999.0), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.intToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(9999.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, 31, month=12.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(12.0));
        this.assertReplaceError(DateTimeEnum.REPLACE_MON_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(9999, 31, month=12)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxYear), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), this.doubleToObj(this.maxMonth));
        this.assertReplaceError(DateTimeEnum.REPLACE_MON_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12.0, 31, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.doubleToObj(12.0), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(DateTimeEnum.TYPE_ERR.toString() + doubleToObj(12.0).typeName(),
            TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, 31, year=9999.0)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.doubleToObj(9999.0));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        // Date(1, 1, 1).replace(12, 31, year=9999)
        this.kwargs.clear();
        this.args = new Object[]{this.intToObj(this.minDate), this.intToObj(this.minDate), this.intToObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);

        this.args = new Object[]{this.intToObj(this.maxMonth), this.intToObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), this.intToObj(this.maxYear));
        this.assertReplaceError(DateTimeEnum.REPLACE_YR_ERR.toString(), TypeError.class);
        this.assertAllDate(this.minDate, this.minDate, this.minDate);
    }

    /*
     * Test Ctime() function
     */
    @Test
    @DisplayName("Test Ctime() function")
    public void testCtime() {
        this.args = new Object[]{intToObj(2020), intToObj(9), intToObj(26)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.ctime(), new org.python.types.Str("Sat Sep 26 00:00:00 2020"));
        // year < 10, day < 10
        this.args = new Object[]{intToObj(1), intToObj(1), intToObj(1)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.ctime(), new org.python.types.Str("Mon Jan  1 00:00:00 0001"));
        // year < 100
        this.args = new Object[]{intToObj(20), intToObj(12), intToObj(31)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.ctime(), new org.python.types.Str("Thu Dec 31 00:00:00 0020"));
        // year < 1000
        this.args = new Object[]{intToObj(300), intToObj(2), intToObj(28)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.ctime(), new org.python.types.Str("Wed Feb 28 00:00:00 0300"));
        this.args = new Object[]{intToObj(this.maxYear), intToObj(this.maxMonth), intToObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertEquals(this.newDate.ctime(), new org.python.types.Str("Fri Dec 31 00:00:00 9999"));
    }
}
