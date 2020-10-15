package test.python.stdlib.datetime;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ListWorkload {

    public static void main(String[] args) {
        //ListWorkload.workload1();
        //ListWorkload.workload2();
        ListWorkload.__setitem__Workload();
        ListWorkload.countWorkload();

        Integer sizeOfList = Integer.valueOf(1000);
        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Random rd = new Random();
        for (int i = 0; i < sizeOfList.intValue(); i++) {
            Integer temp = rd.nextInt();
            arrayList1.add(temp);
            arrayList2.add(temp);
            arrayList3.add(temp);
        }
        // ascending order
        WorkloadTest.testBubbleSort(arrayList1);
        WorkloadTest.testComparator(arrayList2);
        WorkloadTest.testCollection(arrayList3);

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
<<<<<<< HEAD

=======
>>>>>>> 0a69b691fd17bad80f0f015fdce597700915d1d4

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

<<<<<<< HEAD
=======
    static class MyCompare implements Comparator<Integer> {
        @Override
        public int compare(Integer c1, Integer c2) {
            if (c1 == c2) return 0;
            if (c1 > c2) {
                return 1;
            }
            return -1;
        }
    }

    public static void testBubbleSort(ArrayList<Integer> arr) {
        Integer len = arr.size();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(i).intValue() < arr.get(j).intValue()) {
                    Integer temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
        }
    }

    public static void testComparator(ArrayList<Integer> arr) {
        arr.sort(new WorkloadTest.MyCompare());
    }

    public static void testCollection(ArrayList<Integer> arr) {
        Collections.sort(arr, new WorkloadTest.MyCompare());
    }
>>>>>>> 0a69b691fd17bad80f0f015fdce597700915d1d4
}
