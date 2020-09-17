package python;
import java.lang.Math;

@org.python.Module(
    __doc__ =
        "This is a math module for python.\n" +
            "\n" +
            "Functions:\n" +
            "\n" +
            "floor() -- Return the greatest integer less than or equal to a real number value input"
)
public class math extends org.python.types.Module {
    public math() {
        super();
    }

    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("math");
    @org.python.Attribute
    public static org.python.Object __package__ = new org.python.types.Str("");

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

    @org.python.Method(
        __doc__ = "floor(pyFloorArg: number) -> integer\n" +
            "\n" +
            "Return the greatest integer less than or equal to a real number value input\n",
        args = {"pyFloorArg"}
    )
    public static org.python.types.Int floor(org.python.Object pyFloorArg) {
        double javaFloorArg;

        switch(pyFloorArg.typeName()) {
            case "float":
                javaFloorArg = ((org.python.types.Float) pyFloorArg.__float__()).value;

                if (javaFloorArg == Double.POSITIVE_INFINITY || javaFloorArg == Double.NEGATIVE_INFINITY) {
                    throw new org.python.exceptions.OverflowError("Cannot convert float infinity to integer");
                }

                if (javaFloorArg != javaFloorArg) {
                    throw new org.python.exceptions.ValueError("Cannot convert float NaN to integer");
                }
                break;
            case "int":
                javaFloorArg = ((org.python.types.Int) pyFloorArg.__int__()).value;
                break;
            case "bool":
                if (((org.python.types.Bool) pyFloorArg.__bool__()).value) {
                    javaFloorArg = 1.0;
                } else {
                    javaFloorArg = 0.0;
                }
                break;
            default:
                throw new org.python.exceptions.TypeError("A float is required, not: " + pyFloorArg.typeName());
        }
        return org.python.types.Int.getInt((int)Math.floor(javaFloorArg));
    }
}
