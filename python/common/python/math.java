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
    public static org.python.Object ceil(org.python.Object x) {
        double d =((org.python.types.Float) x.__float__()).value;
        int c = (int)Math.ceil(d);
        return  org.python.types.Int.getInt(c);     //Math.ceil(d);
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

    @org.python.Method(
        __doc__ = "Returns the absolute value of a floating point number\n" +
            "\n",
        args = {"pyFabsArg"}
    )
    public static org.python.types.Float fabs(org.python.Object pyFabsArg)  {
        double javaFabsValue;

        switch(pyFabsArg.typeName()) {
            case "float":
                javaFabsValue = ((org.python.types.Float) pyFabsArg.__float__()).value;
                if (javaFabsValue == Double.POSITIVE_INFINITY || javaFabsValue == Double.NEGATIVE_INFINITY) {
                    throw new org.python.exceptions.OverflowError("Cannot convert float infinity to float absolute value");
                }
                if (javaFabsValue < 0.0){
                    javaFabsValue = -javaFabsValue;
                }
                break;
            case "int":
                javaFabsValue = ((org.python.types.Int) pyFabsArg.__int__()).value;
                if (javaFabsValue < 0)
                    javaFabsValue = javaFabsValue * -1;
                break;
            case "bool":
                if (((org.python.types.Bool) pyFabsArg.__bool__()).value == Boolean.TRUE) {
                    javaFabsValue = 1.0;
                } else {
                    javaFabsValue = 0.0;
                }
                break;
            case "complex":
                throw new org.python.exceptions.TypeError("can't convert complex to float");
            default:
                throw new org.python.exceptions.TypeError("must be real number, not " + pyFabsArg.typeName());
        }
        return new org.python.types.Float(javaFabsValue);
    }

}
