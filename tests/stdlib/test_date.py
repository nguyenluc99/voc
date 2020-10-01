from ..utils import TranspileTestCase


class DateTest(TranspileTestCase):
    """
    TranspileTestCase testing for Date.java.

    __repr__() in Date.java is tested with date.__str__()
    This is because it is an inbuilt replacement
    Otherwise, it would return "datetime.date(year, month, day)"

    Date: 01/10/2020
    """

    def test_datetime_date(self):
        """
        Test for all implemented Date.java methods
        Commented out tests are failing beyond the scope of Date.java
        """
        self.assertCodeExecution("""
            from datetime import date
            print(date(2020, 9, 30))
            print(date(2020, 9, 30).ctime())
            print(date(2020, 9, 30).weekday())
            print(date(2020, 9, 30).year)
            print(date(2020, 9, 30).month)
            print(date(2020, 9, 30).day)
            print(date(2020, 9, 30).__str__())
            print(date(9999, 12, day=31))
            print(date(9999, 12, day=31).ctime())
            print(date(9999, 12, day=31).weekday())
            print(date(9999, 12, day=31).year)
            print(date(9999, 12, day=31).month)
            print(date(9999, 12, day=31).day)
            print(date(9999, 12, day=31).__str__())
            print(date(1999, month=12, day=31))
            print(date(1999, month=12, day=31).ctime())
            print(date(1999, month=12, day=31).weekday())
            print(date(1999, month=12, day=31).year)
            print(date(1999, month=12, day=31).month)
            print(date(1999, month=12, day=31).day)
            print(date(1999, month=12, day=31).__str__())
            print(date(True, True, True))
            print(date(True, True, True).ctime())
            print(date(True, True, True).weekday())
            print(date(True, True, True).year)
            print(date(True, True, True).month)
            print(date(True, True, True).day)
            print(date(True, True, True).__str__())
            print(date(year=2400, month=2, day=29))
            print(date(year=2400, month=2, day=29).ctime())
            print(date(year=2400, month=2, day=29).weekday())
            print(date(year=2400, month=2, day=29).year)
            print(date(year=2400, month=2, day=29).month)
            print(date(year=2400, month=2, day=29).day)
            print(date(year=2400, month=2, day=29).__str__())
            print(date.today())
            print(date.today().ctime())
            print(date.today().weekday())
            print(date.today().year)
            print(date.today().month)
            print(date.today().day)
            print(date.today().__str__())
            print(date.fromisoformat("2020-09-30"))
            print(date.fromisoformat("2020-09-30").ctime())
            print(date.fromisoformat("2020-09-30").weekday())
            print(date.fromisoformat("2020-09-30").year)
            print(date.fromisoformat("2020-09-30").month)
            print(date.fromisoformat("2020-09-30").day)
            print(date.fromisoformat("2020-09-30").__str__())
            d = date.today()
            print(d)
            d = d.replace(9, month=5)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(month=1)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            d = d.replace(year=31)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(day=12)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(8999, day=12)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(1700, 5, day=15)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(1066, month=7, day=28)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(year=1, month=2, day=3)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(year=4646, day=20)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(month=3, day=7)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace(month=3, year=9696)
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            d = d.replace()
            print(d.year)
            print(d.month)
            print(d.day)
            print(d.weekday())
            print(d.ctime())
            print(d.__str__())
            print(date.fromisoformat("2020-10-01"))
            print(date.fromisoformat(date(2020, 10, 1).__str__()))
            print(date.max == date.min)
            print(date.min == date.max)
            print(date.max == date.max)
            print(date.min == date.min)
            print(date.min != date.max)

            try:
                print(date(10000, month=12, day=31))
            except Exception as e:
                print(e)

            try:
                print(date(2020, 9, 31))
            except Exception as e:
                print(e)

            try:
                print(date(2020, 2, 30))
            except Exception as e:
                print(e)

            try:
                print(date(2019, 2, 29))
            except Exception as e:
                print(e)

            try:
                print(date(2400, 2, 30))
            except Exception as e:
                print(e)

            try:
                print(date(2100, 2, 30))
            except Exception as e:
                print(e)

            try:
                print(date(9999, 13, 31))
            except Exception as e:
                print(e)

            try:
                print(date(0, 12, 31))
            except Exception as e:
                print(e)

            try:
                print(date(9999, 0, 31))
            except Exception as e:
                print(e)

            try:
                print(date(9999, 12, 0))
            except Exception as e:
                print(e)

            try:
                print(date(1000, year=12, day=31))
            except Exception as e:
                print(e)

            try:
                print(date(10, 10, year=12))
            except Exception as e:
                print(e)

            try:
                print(date(1000, 10, month=12))
            except Exception as e:
                print(e)

            try:
                print(date())
            except Exception as e:
                print(e)

            try:
                print(date(1, 2, 3, 4))
            except Exception as e:
                print(e)

            # try:
            #     print(date(1, 1, None))
            # except Exception as e:
            #     print(e)

            try:
                print(date(9999, "str", 31))
            except Exception as e:
                print(e)

            try:
                print(date(2020.0, 9, 30))
            except Exception as e:
                print(e)

            try:
                print(date(1000, 10, False))
            except Exception as e:
                print(e)

            try:
                print(date(1, 1))
            except Exception as e:
                print(e)

            try:
                print(date(1, day=31))
            except Exception as e:
                print(e)

            try:
                print(date(1, month=12))
            except Exception as e:
                print(e)

            try:
                print(date(1, year=9999))
            except Exception as e:
                print(e)

            try:
                print(date(year=1, month=12))
            except Exception as e:
                print(e)

            try:
                print(date(year=9999, day=22))
            except Exception as e:
                print(e)

            try:
                print(date(year=99999, day=22))
            except Exception as e:
                print(e)

            try:
                print(date(year=1, month=12.0))
            except Exception as e:
                print(e)

            try:
                print(date(year=9999, day=None))
            except Exception as e:
                print(e)

            try:
                print(date(year=1, day="str"))
            except Exception as e:
                print(e)

            try:
                print(date(year=1.0, month=12))
            except Exception as e:
                print(e)

            # try:
            #     print(date(year=None, day=1))
            # except Exception as e:
            #     print(e)

            try:
                print(date(year="str", day=2))
            except Exception as e:
                print(e)

            try:
                print(date(1, 40))
            except Exception as e:
                print(e)

            try:
                print(date(True, 2))
            except Exception as e:
                print(e)

            try:
                print(date(True, day=4))
            except Exception as e:
                print(e)

            try:
                print(date(False, day=4))
            except Exception as e:
                print(e)

            # try:
            #     print(date(False, None))
            # except Exception as e:
            #     print(e)

            # try:
            #     print(date(None, False))
            # except Exception as e:
            #     print(e)

            try:
                print(date(9.0, year=1))
            except Exception as e:
                print(e)

            # try:
            #     print(date(None, year=1))
            # except Exception as e:
            #     print(e)

            try:
                print(date("str", year=1))
            except Exception as e:
                print(e)

            try:
                print(date("str", month=1))
            except Exception as e:
                print(e)

            try:
                print(date(1.0, month=1))
            except Exception as e:
                print(e)

            # try:
            #     print(date(None, month=1))
            # except Exception as e:
            #     print(e)

            try:
                print(date(0, 99999))
            except Exception as e:
                print(e)

            try:
                print(date(9, day=32))
            except Exception as e:
                print(e)

            try:
                print(date(month=13, year=99))
            except Exception as e:
                print(e)

            try:
                print(date(1))
            except Exception as e:
                print(e)

            try:
                print(date(day=1))
            except Exception as e:
                print(e)

            try:
                print(date(month=1))
            except Exception as e:
                print(e)

            try:
                print(date(year=1))
            except Exception as e:
                print(e)

            try:
                print(date(0))
            except Exception as e:
                print(e)

            try:
                print(date(day=0))
            except Exception as e:
                print(e)

            try:
                print(date(month=0))
            except Exception as e:
                print(e)

            try:
                print(date(year=0))
            except Exception as e:
                print(e)

            try:
                print(date(99999))
            except Exception as e:
                print(e)

            try:
                print(date(day=32))
            except Exception as e:
                print(e)

            try:
                print(date(month=13))
            except Exception as e:
                print(e)

            try:
                print(date(year=10000))
            except Exception as e:
                print(e)

            try:
                print(date(True))
            except Exception as e:
                print(e)

            try:
                print(date(day=False))
            except Exception as e:
                print(e)

            try:
                print(date(month=4.0))
            except Exception as e:
                print(e)

            # try:
            #     print(date(year=None))
            # except Exception as e:
            #     print(e)

            try:
                print(date("str"))
            except Exception as e:
                print(e)

            try:
                print(date(year="str"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("10000-10-01"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("1000-10-01 "))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("1000,10,01"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("1000, 10, 01"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("any string"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat(None))
            except Exception as e:
                print(e)

            # try:
            #     print(date.fromisoformat(2020, 10, 10))
            # except Exception as e:
            #     print(e)

            try:
                print(date.fromisoformat(2020.0))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat(True))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat(False))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("0000-00-00"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("0001-13-01"))
            except Exception as e:
                print(e)

            try:
                print(date.fromisoformat("0001-01-32"))
            except Exception as e:
                print(e)

            d = date.today()

            # try:
            #     print(date.replace(1, 2, 3, 4))
            # except Exception as e:
            #     print(e)
        """)
