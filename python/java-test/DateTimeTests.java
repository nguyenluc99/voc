import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;

import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;
import org.python.types.Str;
import org.python.exceptions.ValueError;
import org.python.exceptions.SyntaxError;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class DateTimeTests {

    /**
     * Test creation of the DateTime object
     */
    @Test
    public void test_creation() {
        Int[] args = {Int.getInt(1), Int.getInt(2), Int.getInt(3)};
        DateTime date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("0001-02-03 00:00:00"));

        args = Arrays.copyOf(args, args.length-1);
        HashMap<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        kwargs.put("day", Int.getInt(3));

        date = new DateTime(args, kwargs);
        assertEquals(date.__str__(), new Str("0001-02-03 00:00:00"));

        args = Arrays.copyOf(args, args.length-1);
        kwargs.put("month", Int.getInt(2));

        date = new DateTime(args, kwargs);
        assertEquals(date.__str__(), new Str("0001-02-03 00:00:00"));

        args = Arrays.copyOf(args, args.length-1);
        kwargs.put("year", Int.getInt(1));

        date = new DateTime(args, kwargs);
        assertEquals(date.__str__(), new Str("0001-02-03 00:00:00"));

        args = Arrays.copyOf(args, args.length+3);
        args[0] = Int.getInt(11);
        args[1] = Int.getInt(12);
        args[2] = Int.getInt(13);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("0011-12-13 00:00:00"));

        args[0] = Int.getInt(111);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("0111-12-13 00:00:00"));

        args[0] = Int.getInt(1111);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("1111-12-13 00:00:00"));

        args = Arrays.copyOf(args, args.length+3);
        args[3] = Int.getInt(0);
        args[4] = Int.getInt(0);
        args[5] = Int.getInt(0);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("1111-12-13 00:00:00"));

        args = Arrays.copyOf(args, args.length+1);
        args[6] = Int.getInt(0);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("1111-12-13 00:00:00"));

        args = Arrays.copyOf(args, args.length-1);
        args[0] = Int.getInt(9999);
        args[1] = Int.getInt(12);
        args[2] = Int.getInt(31);
        args[3] = Int.getInt(23);
        args[4] = Int.getInt(59);
        args[5] = Int.getInt(59);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("9999-12-31 23:59:59"));

        args = Arrays.copyOf(args, args.length+1);
        args[6] = Int.getInt(999999);
        date = new DateTime(args, Collections.emptyMap());
        assertEquals(date.__str__(), new Str("9999-12-31 23:59:59.999999"));

        args[0] = Int.getInt(99999);
        Int[] finalArgs = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs, Collections.emptyMap()));

        args[0] = Int.getInt(9999);
        args[1] = Int.getInt(13);
        Int[] finalArgs1 = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs1, Collections.emptyMap()));

        args[1] = Int.getInt(12);
        args[2] = Int.getInt(32);
        Int[] finalArgs2 = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs2, Collections.emptyMap()));

        args[2] = Int.getInt(31);
        args[3] = Int.getInt(25);
        Int[] finalArgs3 = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs3, Collections.emptyMap()));

        args[3] = Int.getInt(24);
        args[4] = Int.getInt(60);
        Int[] finalArgs4 = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs4, Collections.emptyMap()));

        args[4] = Int.getInt(59);
        args[5] = Int.getInt(60);
        Int[] finalArgs5 = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs5, Collections.emptyMap()));

        args[5] = Int.getInt(59);
        args[6] = Int.getInt(9999999);
        Int[] finalArgs6 = args;
        assertThrows(ValueError.class, () -> new DateTime(finalArgs6, Collections.emptyMap()));
    }

    /**
     * Test Comparison equal
     */
    @Test
    public void test___eq__() {
        Int[] args = {Int.getInt(1), Int.getInt(1), Int.getInt(1),Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1)};
        DateTime date1 = new DateTime(args,Collections.emptyMap());
        DateTime date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__((org.python.Object) date2), org.python.types.Bool.TRUE);

        args[0] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

        args[0] = Int.getInt(1);
        args[1] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

        args[1] = Int.getInt(1);
        args[2] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

        args[2] = Int.getInt(1);
        args[3] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

        args[3] = Int.getInt(1);
        args[4] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

        args[4] = Int.getInt(1);
        args[5] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

        args[5] = Int.getInt(1);
        args[6] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__eq__(date2), org.python.types.Bool.FALSE);

    }

    /**
     * Test Comparison greater than
     */
    @Test
    public void test___gt__() {
        Int[] args = {Int.getInt(1), Int.getInt(1), Int.getInt(1),Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1)};
        DateTime date1 = new DateTime(args,Collections.emptyMap());
        DateTime date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__((org.python.Object) date2), org.python.types.Bool.FALSE);

        args[0] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);

        args[0] = Int.getInt(1);
        args[1] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);

        args[1] = Int.getInt(1);
        args[2] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);

        args[2] = Int.getInt(1);
        args[3] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);

        args[3] = Int.getInt(1);
        args[4] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);

        args[4] = Int.getInt(1);
        args[5] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);

        args[5] = Int.getInt(1);
        args[6] = Int.getInt(2);
        date1 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__gt__(date2), org.python.types.Bool.TRUE);
    }

    /**
     * Test Comparison greater or equal
     */
    @Test
    public void test___ge__() {
        Int[] args = {Int.getInt(1), Int.getInt(1), Int.getInt(1),Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1)};
        DateTime date1 = new DateTime(args,Collections.emptyMap());
        DateTime date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__((org.python.Object) date2), org.python.types.Bool.TRUE);

        args[0] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);

        args[0] = Int.getInt(1);
        args[1] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);

        args[1] = Int.getInt(1);
        args[2] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);

        args[2] = Int.getInt(1);
        args[3] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);

        args[3] = Int.getInt(1);
        args[4] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);

        args[4] = Int.getInt(1);
        args[5] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);

        args[5] = Int.getInt(1);
        args[6] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__ge__(date2), org.python.types.Bool.FALSE);
    }

    /**
     * Test comparison less than
     */
    @Test
    public void test___lt__() {
        Int[] args = {Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1)};
        DateTime date1 = new DateTime(args, Collections.emptyMap());
        DateTime date2 = new DateTime(args, Collections.emptyMap());
        assertEquals(date1.__lt__((org.python.Object) date2), org.python.types.Bool.FALSE);

        args[0] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__lt__(date2), org.python.types.Bool.TRUE);
    }

    /**
     * Test comparison less or equal
     */
    @Test
    public void test___le__() {
        Int[] args = {Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1), Int.getInt(1)};
        DateTime date1 = new DateTime(args, Collections.emptyMap());
        DateTime date2 = new DateTime(args, Collections.emptyMap());
        assertEquals(date1.__le__((org.python.Object) date2), org.python.types.Bool.TRUE);

        args[0] = Int.getInt(2);
        date2 = new DateTime(args,Collections.emptyMap());
        assertEquals(date1.__le__(date2), org.python.types.Bool.TRUE);
    }

    @Test
    public void test_today() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
        LocalDateTime now = LocalDateTime.now(); 


        org.python.Object python_date_full = DateTime.today();
        String java_date_full = (String) python_date_full.__str__().toJava();
        String java_date_reduced = java_date_full.substring(0,java_date_full.length()-4);
        assertEquals(new Str(dtf.format(now)),new Str(java_date_reduced) );
    }

    @Test
    public void test_date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now(); 


        DateTime python_date_full = (DateTime) DateTime.today();
        org.python.Object python_date_yymmdd = python_date_full.date();
        String java_date_full = (String) python_date_yymmdd.__str__().toJava();
        assertEquals(new Str(dtf.format(now)), new Str(java_date_full));
    }

    @Test
    public void test_weekday() {

        DateTime python_date_full = (DateTime) DateTime.today();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int[] convertToPython = { 6, 0, 1, 2, 3, 4, 5 };
        long week_day = convertToPython[day-1]; 
        assertEquals(python_date_full.weekday().toJava(), week_day);
    }

    @Test 
    public void test_isoformat() {
        String separator = "T";
        org.python.types.Str python_separator = (org.python.types.Str) new Str(separator);
        
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss.SS");
        LocalDateTime now = LocalDateTime.now(); 


        DateTime python_date_full = (DateTime) DateTime.today();
        org.python.Object python_iso = python_date_full.isoformat(python_separator);
        String java_time_full = (String) python_iso.__str__().toJava();
        String java_iso_reduced = java_time_full.substring(0,java_time_full.length()-4);
        assertEquals(new Str(dtf1.format(now) + separator + dtf2.format(now)), new Str(java_iso_reduced));
    }

    /**
     * Tests creation DateTime objects from iso format strings.
     */
    @Test
    public void test_fromisoformat() {

        Str isoString = new Str("9999-12-31");
        DateTime fromIso = (DateTime) DateTime.fromisoformat(isoString);
        assertEquals(fromIso.__str__(), new Str("9999-12-31 00:00:00"));

        isoString = new Str("2011-11-04T00:05:23");
        fromIso = (DateTime) DateTime.fromisoformat(isoString);
        assertEquals(fromIso.__str__(), new Str("2011-11-04 00:05:23"));

        isoString = new Str("2011-11-04 00:05:23.283");
        fromIso = (DateTime) DateTime.fromisoformat(isoString);
        assertEquals(fromIso.__str__(), new Str("2011-11-04 00:05:23.283000"));

        isoString = new Str("2011-11-04 24:59:59.999999");
        fromIso = (DateTime) DateTime.fromisoformat(isoString);
        assertEquals(fromIso.__str__(), new Str("2011-11-04 24:59:59.999999"));

        isoString = new Str("0001-01-01 00:00:00.000000");
        fromIso = (DateTime) DateTime.fromisoformat(isoString);
        assertEquals(fromIso.__str__(), new Str("0001-01-01 00:00:00"));
    }
}


