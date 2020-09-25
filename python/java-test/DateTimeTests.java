import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;

import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;
import org.python.types.Str;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DateTimeTests {
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
    }
}


