package org.bodya.lab3.task4;

/**
 * Клас який використовує низькорівневу багатопотоковість
 * для обрахунку простих калькуляцій.
 */
public class MyThread extends Thread {
    private int value;

    public MyThread(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Метод для запуску потоку.
     */
    @Override
    public void run() {
        System.out.println("Виклик для " + value);
        value = value * value;
    }
}
