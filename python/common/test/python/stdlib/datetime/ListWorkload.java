package test.python.stdlib.datetime;


import java.util.Random;

public class ListWorkload {

    public static void main(String[] args) {
        //ListWorkload.workload1();
        //ListWorkload.workload2();
        ListWorkload.__setitem__Workload();
        ListWorkload.countWorkload();
    }

    // Remove at beginning (good for linked-list, bad for array-list)
    public static void workload1() {
        org.python.types.List list = new org.python.types.List();
        for (int i = 0; i < 100000; i++) {
            list.append(org.python.types.Int.getInt(i));
        }
        while (list.__len__().value > 0) {
            list.__delitem__(org.python.types.Int.getInt(0));
        }
    }

    // Remove at random index (good for array-list, bad for linked-list
    public static void workload2() {
        org.python.types.List list = new org.python.types.List();
        for (int i = 0; i < 100000; i++) {
            list.append(org.python.types.Int.getInt(i));
        }

        Random rand = new Random();
        while (list.__len__().value > 0) {
            long index = (long)rand.nextInt((int)list.__len__().value);
            list.__delitem__(org.python.types.Int.getInt(index));
        }
    }

    public static void __setitem__Workload() {
        org.python.types.List list = new org.python.types.List();


        for (int i = 0; i < 10000; i++) {
            list.append(org.python.types.Int.getInt(i));
        }

        for (int i = 0; i < 10000; i++) {
            list.__setitem__(org.python.types.Int.getInt(i), org.python.types.Int.getInt(1337));
        }
    }
    public static void countWorkload() {
        org.python.types.List list = new org.python.types.List();

        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            list.append(org.python.types.Int.getInt(rand.nextInt(10) ));
        }

        for (int i = 0; i < 1000; i++) {
            list.count(org.python.types.Int.getInt(i%10));
        }
    }
}
