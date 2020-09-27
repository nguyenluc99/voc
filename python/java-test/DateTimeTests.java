import java.io.*;
import java.util.Collections;
import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;
import org.python.types.Str;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class DateTimeTests {
    @Test
    public void test_construction() {
        Int[] args = {Int.getInt(1), Int.getInt(2), Int.getInt(3)};
        System.out.println(Collections.emptyMap());


        DateTime date = new DateTime(args, Collections.emptyMap());
        System.out.println(date.__str__());
        assertEquals(date.__str__(), new Str("0001-02-03 00:00:00"));
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
    //Maybe these should be removed
    @Test
    public void test___year__() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now(); 

        
        DateTime python_date_full = (DateTime) DateTime.today();
        org.python.Object python_date_yy = python_date_full.__year__();
        String java_date_full = (String) python_date_yy.__str__().toJava();
        assertEquals(new Str(dtf.format(now)), new Str(java_date_full));
    }


    //______________
    @Test
    public void test_weekday() {
        //Test today
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

}
