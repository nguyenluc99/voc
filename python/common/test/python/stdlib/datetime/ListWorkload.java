package test.python.stdlib.datetime;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ListWorkload {

    public static void main(String[] args) {
        //ListWorkload.workload1();
        //ListWorkload.workload2();
        ListWorkload.__setitem__Workload();
        ListWorkload.countWorkload();

        ListWorkload.workload1Luc();
        ListWorkload.workload2Luc();
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

    public static void addElementToArrayList(Integer sizeOfList, ArrayList<Integer> arrayList, Random rd) {
        for (int i = 0; i < sizeOfList; i++) {
            arrayList.add(rd.nextInt(Integer.MAX_VALUE) % (i + 1), i);
        }
    }

    public static void addElementToLinkedList(Integer sizeOfList, LinkedList<Integer> linkedList, Random rd) {
        for (int i = 0; i < sizeOfList; i++) {
            linkedList.add(rd.nextInt(Integer.MAX_VALUE) % (i + 1), i);
        }
    }

    public static void removeSubListArrayList(Integer iteration, Integer sizeOfList, ArrayList arrayList, Random rd) {
        int first, last;

        for (int i = 0; i < iteration; i++) {
            first = rd.nextInt(Integer.MAX_VALUE) % (sizeOfList + 1);
            last = rd.nextInt(Integer.MAX_VALUE) % (sizeOfList + 1);
            if (first < last) {
                arrayList.subList(first, last).clear();
                sizeOfList = sizeOfList + first - last;
            } else {
                arrayList.subList(last, first).clear();
                sizeOfList = sizeOfList + last - first;
            }
        }
    }

    public static void removeSubListLinkedList(Integer iteration, Integer sizeOfList, LinkedList linkedList, Random rd) {
        int first, last;

        for (int i = 0; i < iteration; i++) {
            first = rd.nextInt(Integer.MAX_VALUE) % (sizeOfList + 1);
            last = rd.nextInt(Integer.MAX_VALUE) % (sizeOfList + 1);
            if (first < last) {
                linkedList.subList(first, last).clear();
                sizeOfList = sizeOfList + first - last;
            } else {
                linkedList.subList(last, first).clear();
                sizeOfList = sizeOfList + last - first;
            }
        }
    }

    public static void workload1Luc() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        Random rd = new Random();

        int sizeOfList = 3000;

        ListWorkload.addElementToLinkedList(sizeOfList, linkedList, rd);
        ListWorkload.addElementToArrayList(sizeOfList, arrayList, rd);
    }

    public static void workload2Luc() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        Random rd = new Random();
        int sizeOfList = 100000;
        int iteration = 5000;

        for (int i = 0; i < sizeOfList; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        ListWorkload.removeSubListArrayList(iteration, sizeOfList, arrayList, rd);
        ListWorkload.removeSubListLinkedList(iteration, sizeOfList, linkedList, rd);
    }

}
