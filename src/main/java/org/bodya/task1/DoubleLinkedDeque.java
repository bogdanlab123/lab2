package org.bodya.task1;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoubleLinkedDeque<T> implements Iterable<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    DoubleLinkedDeque() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Метод для перевірки чи черга пуста.
     */
    public boolean isEmpty() {
        return (front == null);
    }

    /**
     * Метод який повертає розмір черги.
     */
    public int size() {
        return size;
    }

    /**
     * Метод для вставки елементу на початок черги.
     */
    public void insertFront(T data) {
        Node<T> newNode = new Node<>(data);

        // Якщо черга пуста
        if (front == null)
            rear = front = newNode;
            // Вставти вузол в кінець черги
        else {
            newNode.setNext(front);
            front.setPrev(newNode);
            front = newNode;
        }
        // Збільшує кількість елементів на 1
        size++;
    }

    /**
     * Метод для вставки елементу в кінець черги.
     *
     * @param data - дані для вставки.
     */
    void insertRear(T data) {
        Node<T> newNode = new Node<>(data);

        // Якщо черга пуста
        if (rear == null)
            front = rear = newNode;

            // Вставляє вузол в кінець черги
        else {
            newNode.setPrev(rear);
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    /**
     * Метод для видалення елементу з початку черги.
     */
    void deleteFront() {
        // Якщо черга пуста, тоді вивести повідомлення
        if (isEmpty()) {
            System.out.println("Черга пуста");
        }
        // Видаляє вузол з початку черги і змінює посилання.
        else {
            Node<T> temp = front;
            front = front.getNext();

            // Якщо присутній тільки один елемент
            if (front == null)
                rear = null;
            else
                front.setPrev(null);

            // Зменшує кількість елементів на 1
            size--;
        }
    }

    /**
     * Метод для видалення елементу з кінця черги.
     */
    void deleteRear() {
        // Якщо черга пуста, тоді вивести повідомлення
        if (isEmpty()) {
            System.out.println("Черга пуста");
        }
        // Видаляє вузол з кінця черги і змінює посилання.
        else {
            Node temp = rear;
            rear = rear.getPrev();

            // Якщо присутній тільки один елемент
            if (rear == null)
                front = null;
            else
                rear.setNext(null);

            // Зменшити кількість елементів на 1
            size--;
        }
    }

    /**
     * Метод для отримання елементу з початку черги.
     */
    T getFront() {
        // Якщо черга пуста, повернути null
        if (isEmpty())
            return null;
        return front.getData();
    }

    /**
     * Метод для отримання елементу з кінця черги.
     */
    T getRear() {

        // Якщо черга пуста, повернути null.
        if (isEmpty())
            return null;
        return rear.getData();
    }

    /**
     * Метод для видалення всіх елементів черги.
     */
    void erase() {
        rear = null;
        while (front != null) {
            Node temp = front;
            front = front.getNext();
        }
        size = 0;
    }

    /**
     * Метод для повернення ітератора на чергу.
     * Потрібен для використання в foreach
     *
     * @return - клас ітератор для обходу черги.
     */
    public Iterator<T> iterator() {
        return new DoubleLinkedDequeIterator<>(this);
    }

    /**
     * Внутрішній клас ітератор черги.
     */
    class DoubleLinkedDequeIterator<T> implements Iterator<T> {
        Node<T> current;

        public DoubleLinkedDequeIterator(DoubleLinkedDeque<T> deque) {
            current = deque.front;
        }

        /**
         * Перевірка чи наступний елемент не null. Якщо null,
         * значить кінець черги.
         *
         * @return - істина, якщо є наступний елемент.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Метод для отримання наступного елементу черги.
         *
         * @return - дані вузла.
         */
        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }

        /**
         * Видалення не підтримується.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}