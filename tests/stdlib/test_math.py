from unittest import expectedFailure

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):
    """
    Testing of Python3 math module methods with TranspileTestCase:
        * math.floor
        * math.ceil
        * math.fabs
    """

    #######################################################
    # math.floor
    @expectedFailure
    def test_math_floor_none_argument(self):
        self.assertCodeExecution("""
            from math import floor
            test_arg = None
            print(floor(test_arg))
            """)

    @expectedFailure
    def test_math_floor_str_argument(self):
        self.assertCodeExecution("""
            from math import floor
            test_arg = "str"
            print(floor(test_arg))
            """)

    @expectedFailure
    def test_math_floor_nan_argument(self):
        self.assertCodeExecution("""
                from math import floor
                test_arg = float("nan")
                print(floor(test_arg))
                """)

    @expectedFailure
    def test_math_floor_inf_argument(self):
        self.assertCodeExecution("""
            from math import floor
            test_arg = float("Infinity")
            print(floor(test_arg))

            test_arg = float("-Infinity")
            print(floor(test_arg))
            """)

    def test_math_floor_int_argument(self):
        self.assertCodeExecution("""
            from math import floor
            test_arg = 2
            print(floor(test_arg))

            test_arg = -2
            print(floor(test_arg))
            """)

    def test_math_floor_float_argument(self):
        self.assertCodeExecution("""
            from math import floor
            test_arg = 16.66
            print(floor(test_arg))

            test_arg = -16.66
            print(floor(test_arg))
            """)

    def test_math_floor_bool_argument(self):
        self.assertCodeExecution("""
            from math import floor
            test_arg = True
            print(floor(test_arg))

            test_arg = False
            print(floor(test_arg))
            """)

    #######################################################
    # math.ceil
    def test_ceil(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(1.3))

            print(math.ceil(-1.2))

            print(math.ceil(0))

            print(math.ceil(3))

            """)

    #######################################################
    # math.fabs
    def test_fabs(self):
        self.assertCodeExecution("""
            from math import fabs
            print(fabs(-2))
            print(fabs(3))
            print(fabs(-1.119))
            print(fabs(1.122))
            print(fabs(True))
            print(fabs(False))
            """)

    @expectedFailure
    def test_fabs_with_complex(self):
        self.assertCodeExecution("""
            from math import fabs
            print(fabs(3j))
        """)
    #######################################################
