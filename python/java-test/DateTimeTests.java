import java.io.*;
import java.util.Collections;
import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;
import org.python.types.Str;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DateTimeTests {
    @Test
    public void test_construction() {
        Int[] args = {Int.getInt(1), Int.getInt(2), Int.getInt(3)};
        System.out.println(Collections.emptyMap());


        DateTime date = new DateTime(args, Collections.emptyMap());
        System.out.println(date.__str__());
        assertEquals(date.__str__(), new Str("0001-02-03 00:00:00"));
    }
}
