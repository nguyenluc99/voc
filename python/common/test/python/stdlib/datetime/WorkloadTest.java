package test.python.stdlib.datetime;

import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;

public class WorkloadTest {
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
        arr.sort(new MyCompare());
    }

    public static void testCollection(ArrayList<Integer> arr) {
        Collections.sort(arr, new MyCompare());
    }

    public static void main(String[] args) {
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
}


