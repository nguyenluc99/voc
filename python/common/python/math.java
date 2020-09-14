package python;
import java.lang.Math;
@org.python.Module(
        __doc__ =
            "This is a math module for python" 
)
public class math extends org.python.types.Module {
    public math() {
        //super();
    }

    @org.python.Method(
            __doc__ = "Returns a new subclass of tuple with named fields.\n" +
                "\n",
            args = {"x"}
    )
    public static double ceil(org.python.Object x) {
        double d =((org.python.types.Float) x.__float__()).value;
        return Math.ceil(d);//Math.ceil(d);
        //throw new org.python.exceptions.NotImplementedError("Ceil not implemented yet.");
    }
    
}
