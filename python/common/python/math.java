package python;
import java.lang.Math;

@org.python.Module(
    __doc__ =
        "This is a math module for python"
)
public class math extends org.python.types.Module {
    public math() {
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

    @org.python.Method(
        __doc__ = "floor(pyFloorArg: number) -> integer\n" +
            "\n" +
            "Returns the greatest interger less than or equal to a real number value input\n",
        args = {"pyFloorArg"}
    )
    public static org.python.Object floor(org.python.Object pyFloorArg) {
        double javaFloorArg;

        if (pyFloorArg instanceof org.python.types.Float) {
            javaFloorArg = ((org.python.types.Float) pyFloorArg.__float__()).value;

            if (javaFloorArg == Double.POSITIVE_INFINITY) {
                throw new org.python.exceptions.OverflowError("Python cannot convert float infinity to integer");
            }

            if (javaFloorArg != javaFloorArg) {
                throw new org.python.exceptions.ValueError("Python cannot convert float NaN to integer");
            }
        } else {
            throw new org.python.exceptions.TypeError("Floor not fully implemented yet; doesn't accept type " +
                pyFloorArg.typeName() + ")");
        }
        return org.python.types.Int.getInt((int)Math.floor(javaFloorArg));
    }
}
