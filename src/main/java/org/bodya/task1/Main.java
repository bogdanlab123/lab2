package org.bodya.task1;


public class Main {
    public static void main(String[] args) {
        DoubleLinkedDeque<Integer> doubleLinkedDeque = new DoubleLinkedDeque<>();
        doubleLinkedDeque.insertFront(4); // [4]
        doubleLinkedDeque.insertRear(5); // [4,5]
        doubleLinkedDeque.insertFront(3); // [3,4,5]


        for (Integer integer : doubleLinkedDeque) {
            System.out.println(integer);
        }
    }
}
