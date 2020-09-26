package org.python.stdlib.datetime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.python.Object;
import org.python.exceptions.Exception;
import org.python.exceptions.SyntaxError;
import org.python.exceptions.ValueError;
import org.python.exceptions.TypeError;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Junit test: org.python.stdlib.datetime.Date")
public class DateTest {
    /**
     * Test org.python.stdlib.datetime.Date class
     * Authors: Adam Ross,
     * Date: 23/09/2020
     */

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
    private org.python.types.Int toObj(int arg) {
        return org.python.types.Int.getInt(arg);
    }

    /**
     * Groups assertions so that all are executed and all failures are reported
     * @param year expected value for Date.year argument being asserted
     * @param month expected value for Date.year argument being asserted
     * @param day expected value for Date.year argument being asserted
     */
    private void assertAllDate(int year, int month, int day) {
        assertAll(
            () -> assertEquals(this.newDate.year, toObj(year)),
            () -> assertEquals(this.newDate.month, toObj(month)),
            () -> assertEquals(this.newDate.day, toObj(day)));
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
     * Test Date() instance creation with all three inputs, which are only args
     * Tests the maximum and the minimum possible dates
     */
    @Test
    @DisplayName("Test Date() instance creation with all three inputs, only args")
    public void testCreationWithThreeInputsOnlyArgs() {
        // Test create Date() instance: 9999, 12, 31
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxMonth), toObj(this.maxDay)};
        this.newDate = new Date(this.args, this.kwargs);
        assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Test create Date() instance: 1, 1, 1
        this.args = new Object[]{toObj(this.minDate), toObj(this.minDate), toObj(this.minDate)};
        this.newDate = new Date(this.args, this.kwargs);
        assertAllDate(this.minDate, this.minDate, this.minDate);
    }


    /**
     * Test Date() instance creation with all three inputs, which are only kwargs
     * Tests the maximum and the minimum possible dates
     */
    @Test
    @DisplayName("Test Date() instance creation with all three inputs, only kwargs")
    public void testCreationWithThreeInputsOnlyKwargs() {
        this.args = new Object[]{};

        // Test create Date() instance: year=9999, month=12, day=31
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        assertAllDate(this.maxYear, this.maxMonth, this.maxDay);

        // Test create Date() instance: year=1, month=1, day=1
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.minDate));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.minDate));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.minDate));
        this.newDate = new Date(this.args, this.kwargs);
        assertAllDate(this.minDate, this.minDate, this.minDate);
    }

    /**
     * Test Date() instance creation with all three inputs;
     * one day kwarg and two args: 9999, 12, day=31
     */
    @Test
    @DisplayName("Test create Date() with inputs: 9999, 12, day=31")
    public void testCreationWithDayKwargAndTwoArgs() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        this.newDate = new Date(args, kwargs);
        assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date() instance creation with all three inputs;
     * month and day kwargs and one arg: 9999, month=12, day=31
     */
    @Test
    @DisplayName("Test create Date() with inputs: 9999, month=12, day=31")
    public void testCreationWithMonthAndDayKwargsAndOneArg() {
        this.args = new Object[]{toObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        this.newDate = new Date(this.args, this.kwargs);
        assertAllDate(this.maxYear, this.maxMonth, this.maxDay);
    }

    /**
     * Test Date() instance creation with all three inputs;
     * one month kwarg and two args: 9999, month=12, 31
     */
    @Test
    @DisplayName("Test create Date() with inputs: 9999, month=12, 31 - expect SyntaxError")
    public void testCreationWithMonthKwargAndTwoArgs() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() instance creation with all three inputs;
     * one year kwarg and two args: year=9999, 12, 31
     */
    @Test
    @DisplayName("Test create Date() with inputs: year=9999, 12, 31 - expect SyntaxError")
    public void testCreationWithYearKwargAndTwoArgs() {
        this.args = new Object[]{toObj(this.maxMonth), toObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() instance creation with all three inputs;
     * year and day kwargs and one arg: year=9999, 12, day=31
     */
    @Test
    @DisplayName("Test create Date() with inputs: year=9999, 12, day=31 - expect SyntaxError")
    public void testCreationWithYearAndDayKwargsAndArg() {
        this.args = new Object[]{toObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() instance creation with all three inputs;
     * year and month kwargs and one arg: year=9999, month=12, 31
     */
    @Test
    @DisplayName("Test create Date() with inputs: year=9999, month=12, 31 - expect SyntaxError")
    public void testCreationWithYearAndMonthKwargsAndArg() {
        this.args = new Object[]{toObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        assertError(DateTimeEnum.SYNTAX_ERR.toString(), SyntaxError.class);
    }

    /**
     * Test Date() instance creation with too large year arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() instance with arg year too large - expect ValueError")
    public void testYearArgTooLarge() {
        this.args = new Object[]{toObj(this.yearTooLarge), toObj(this.maxMonth), toObj(this.maxDay)};
        assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), this.yearTooLarge),
            ValueError.class);
    }

    /**
     * Test Date() instance creation with too large year kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() instance with kwarg year too large - expect ValueError")
    public void testYearKwargTooLarge() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.yearTooLarge));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        assertError(String.format(DateTimeEnum.YR_VAL_ERR.toString(), this.yearTooLarge),
            ValueError.class);
    }

    /**
     * Test Date() instance creation with too large month arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() instance with arg month too large - expect ValueError")
    public void testMonthArgTooLarge() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.monthTooLarge), toObj(this.maxDay)};
        assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() instance creation with too large month kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() instance with kwarg month too large - expect ValueError")
    public void testMonthKwargTooLarge() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.monthTooLarge));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        assertError(DateTimeEnum.MON_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() instance creation with too large day arg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() instance with arg day too large - expect ValueError")
    public void testDayArgTooLarge() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxMonth), toObj(this.dayTooLarge)};
        assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() instance creation with too large day kwarg - expect ValueError
     */
    @Test
    @DisplayName("Test Date() instance with kwarg day too large - expect ValueError")
    public void testDayKwargTooLarge() {
        this.args = new Object[]{};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.dayTooLarge));
        assertError(DateTimeEnum.DAY_VAL_ERR.toString(), ValueError.class);
    }

    /**
     * Test Date() instance creation with too many (four) arg inputs - expect TypeError
     */
    @Test
    @DisplayName("Test Date() instance with too many (four) arg inputs - expect TypeError")
    public void testFourArgsTooMany() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxMonth), toObj(this.maxDay),
            toObj(this.maxDay)};
        assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() instance creation with too many inputs (three kwargs and one arg) - expect TypeError
     */
    @Test
    @DisplayName("Test Date() instance with too many inputs (three kwargs and one arg) - expect TypeError")
    public void testThreeKwargsAndOneArgTooMany() {
        this.args = new Object[]{toObj(this.maxYear)};
        this.kwargs.put(DateTimeEnum.YEAR.toString(), toObj(this.maxYear));
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() instance creation with too many inputs (two kwargs and two args) - expect TypeError
     */
    @Test
    @DisplayName("Test Date() instance with too many inputs (two kwargs and two args) - expect TypeError")
    public void testTwoKwargsAndTwoArgsTooMany() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxMonth)};
        this.kwargs.put(DateTimeEnum.MONTH.toString(), toObj(this.maxMonth));
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }

    /**
     * Test Date() instance creation with too many inputs (two kwargs and two args) - expect TypeError
     */
    @Test
    @DisplayName("Test Date() instance with too many inputs (two kwargs and two args) - expect TypeError")
    public void testOneKwargAndThreeArgsTooMany() {
        this.args = new Object[]{toObj(this.maxYear), toObj(this.maxMonth), toObj(this.maxDay)};
        this.kwargs.put(DateTimeEnum.DAY.toString(), toObj(this.maxDay));
        assertError(String.format(DateTimeEnum.MAX_ARG_ERR.toString(), 4), TypeError.class);
    }
}
