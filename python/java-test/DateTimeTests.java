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
        
        Int[] args = {Int.getInt(1), Int.getInt(2), Int.getInt(3)};
        System.out.println(Collections.emptyMap());
        DateTime date = new DateTime(args, Collections.emptyMap());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime now = LocalDateTime.now(); 
        System.out.println(date.__str__());
        //Str date_str = new Str(date.today());
        org.python.Object date2 = date.today();
        String date3 = (String) date2.__str__().toJava();
        assertEquals(date3, new Str(dtf.format(now)));
    }
}
