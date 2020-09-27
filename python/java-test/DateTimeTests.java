import java.io.*;
import java.util.Collections;
import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;
import org.python.types.Str;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M");
        LocalDateTime now = LocalDateTime.now(); 

        
        DateTime python_date_full = (DateTime) DateTime.today();
        //org.python.Object python_date_mm = python_date_full.weekday();
        //String java_date_full = (String) python_date_mm.__str__().toJava();
        //org.python.Object week = (org.python.types.Object) python_date_full.weekday();
        long number = 32;
        assertEquals(python_date_full.weekday().toJava(), number);
    }
    //______________

}
