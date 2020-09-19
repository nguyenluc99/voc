from unittest import expectedFailure

from ..utils import TranspileTestCase


class DateTests(TranspileTestCase):

    def test_creation(self):
        self.assertCodeExecution("""
            from datetime import date
            print(date(14, 10, day=11))
            print(date(14, 10, 11))
            print(date(14, month=10, day=11))            
            print(date(year=14, month=10, day=11))
            print(date(1,1,1))
       
        """)

    def test_year_too_large(self):
        self.assertCodeExecution("""
        from datetime import date
        try:
            date(14444, 10, 11)
        except ValueError as err:
            print(err)
        
        """)

    def test_month_too_large(self):
        self.assertCodeExecution("""
        from datetime import date
        try:
            date(14, 122, 11)
        except ValueError as err:
            print(err)
        
        """)

    def test_one_arg_no_month(self):
        self.assertCodeExecution("""
            from datetime import date
            try:
                date(year=14)
            except TypeError as err:
                print(err)
            
            """)

    def test_one_arg_no_month(self):
        self.assertCodeExecution("""
            from datetime import date
            try:
                date(year=14)
            except TypeError as err:
                print(err)
            
            """)

    def test_one_arg_year_float(self):
        self.assertCodeExecution("""
            from datetime import date
            try:
                date(year=14.0)
            except TypeError as err:
                print(err)
            """)

    def test_one_arg_w_month(self):
        self.assertCodeExecution("""
            from datetime import date
            try:
                date(month=14.0)
            except TypeError as err:
                print(err)
            """)

    def test_one_arg_w_day(self):
        self.assertCodeExecution("""
        from datetime import date
        try:
            date(day=71)
        except TypeError as err:
            print(err)        
        """)

    def test_no_arg(self):
        self.assertCodeExecution("""
            from datetime import date
            try:
                date()
            except TypeError as err:
                print(err)
            """)

    def test_ctime(self):
        self.assertCodeExecution("""
            from datetime import date
            for d in range(1,13):
                x = date(1993,12,1)
                print(x.ctime())
            """)

    def test_one_arg_w_day(self):
        self.assertCodeExecution("""
            from datetime import date
            try:
                date(day=71)
            except TypeError as err:
                print(err)        
            """)

        
class DateTimeTests(TranspileTestCase):

    def test_creation(self):
       self.assertCodeExecution("""
           from datetime import datetime
           print(datetime(1,2,3))
           print(datetime(1,2,day=3))
           print(datetime(1,month=2,day=3))
           print(datetime(year=1,month=2,day=3))

           print(datetime(11,12,13))
           print(datetime(111,12,13))
           print(datetime(1111,12,13))
           
           print(datetime(1,1,1,0,0,0))
           print(datetime(1,1,1,0,0,0,0))
           print(datetime(9999,12,31,23,59,59))
           print(datetime(9999,12,31,23,59,59,999999))
           
           print(datetime(1,2,3,4,5,6,7))
           print(datetime(1,2,3,4,5,6,1117))
           print(datetime(1,2,3,4,5,6,111117))
           """)
    
    def test_date(self):
        self.assertCodeExecution("""
            from datetime import datetime
            print(datetime(1993, 5, 17).date())
            print(datetime(13, 2, 20).date())
            print(datetime(1700, 6, 3).date())
            print(datetime(400, 1, 28).date())
            print(datetime(2007, 3, 17).date())
            print(datetime(1843, 12, 1).date())
            print(datetime(4000, 11, 10).date())
        """)

    def test_class_attributes(self):
        self.assertCodeExecution("""
            from datetime import datetime
            print(datetime(1993,5,17).min)
            print(datetime(1993,5,17).max)
            print (datetime(1993,5,17).year)
            print (datetime(1993,5,17).month)
            print (datetime(1993,5,17,20,30,12,34).hour)
            print (datetime(1993,5,17,20,30,12,34).minute)
            print (datetime(1993,5,17,20,30,12,34).second)
            print (datetime(1993,5,17,20,30,12,34).microsecond)
        """)
    
    def test_year_too_large(self):
        self.assertCodeExecution("""
        from datetime import datetime
        try:
            datetime(19999, 10, 11)
        except ValueError as err:
            print(err)
        
        """)    

    def test_year_too_small(self):
        self.assertCodeExecution("""
        from datetime import datetime
        try:
            datetime(0, 10, 11)
        except ValueError as err:
            print(err)
        
        """)  

    def test_month_too_large(self):
        self.assertCodeExecution("""
        from datetime import datetime
        try:
            datetime(14, 41, 11)
        except ValueError as err:
            print(err)
        
        """)  
        
    def test_month_too_small(self):
            self.assertCodeExecution("""
        from datetime import datetime
        try:
            datetime(0, 0, 11)
        except ValueError as err:
            print(err)
        
        """)  

            
class TimeDeltaTests(TranspileTestCase):

    def test_creation(self):
        self.assertCodeExecution("""
            from datetime import timedelta
            print(timedelta(14, 10, 1, weeks=11))
            print(timedelta(91, 10, 1))
            print(timedelta(14, 10, 1,  weeks=11, hours = 1, milliseconds=10))""")

    def test_class_attributes(self):
        self.assertCodeExecution("""
            from datetime import timedelta
            print(timedelta(14, 10, 1, weeks=11).days)
            print(timedelta(14, 10, 1, weeks=11).min)
            print(timedelta(14, 10, 1, weeks=11).max)
            print(timedelta(14, 10, 1, weeks=11).resolution)
            print(timedelta(91, 10, 1).seconds)
            print(timedelta(14, 10, 1,  weeks=11, hours = 1, milliseconds=10).microseconds)
        """)

    def test_total_seconds(self):
        self.assertCodeExecution("""
            from datetime import timedelta
            print(timedelta(14, 10, 1, weeks=11).total_seconds())
        """)
    
    def test_addition(self):
        self.assertCodeExecution("""
            from datetime import timedelta
            d = timedelta(14, 10, 1)
            print(d)
            t = timedelta(17, 2, 100)
            print(t)
            print(t+d)
            
        """)

    def test_positive(self):
        self.assertCodeExecution("""
            from datetime import timedelta
            d = timedelta(14, 10, 1)
            print(d)
            t = +d
            print(t)
        """)

    def test_overflow(self):
        self.assertCodeExecution("""
            from datetime import timedelta
            print(timedelta(1, 86401, 1))
            print(timedelta(1, 6401,1000000))
            
        """)
