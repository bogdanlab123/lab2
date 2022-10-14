package org.bodya.lab3.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){

        int[] intArray = {1, 2, 3, 4, 5};
        // Створюємо п'ять потоків низькорівневим способом
        MyThread myThread0 = new MyThread(intArray[0]);
        MyThread myThread1 = new MyThread(intArray[1]);
        MyThread myThread2 = new MyThread(intArray[2]);
        MyThread myThread3 = new MyThread(intArray[3]);
        MyThread myThread4 = new MyThread(intArray[4]);

        // Створюємо список класів які використовують багатопотоковість
        List<MyThread> list = new ArrayList<>();
        list.add(myThread0);
        list.add(myThread1);
        list.add(myThread2);
        list.add(myThread3);
        list.add(myThread4);

        // Запускаємо всі потоки
        myThread0.start();
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();

        List<Integer> list2 = new ArrayList<>();

        // Заповнюємо масив числами від 0 до 19
        for (int i = 0; i < 20; i++) {
            list2.add(i);
        }

        long time = System.currentTimeMillis();
        // Виводимо масив в паралельному режимі виконання.
        // В даному випадку числа будуть виводиться не в порядку
        // додавання, оскільки використовується багатопотоковість.
        // В даному випадку багатопотоковість не дасть приросту
        // швидкості виконання, а наоборот - погіршить швидкодію.
        // В цьому можна переконатися, якщо змінити .parallel
        // на .sequential. та подивитися на час виконання.
        list2.stream().parallel().forEach(System.out::println);
        System.out.println();
        System.out.println("Час виконання: " + (System.currentTimeMillis() - time));

        System.out.println(list.stream()
                .map(MyThread::getValue)
                .collect(Collectors.toList()));

        // Високорівнева багатопотоковість. Створюємо пул з 3 потоків
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Запускаємо потоки
        // В результати для класів myThread0,1,2 будуть знову
        // Запущені методи run, і повторно вирахується квадрат
        // поля value
        System.out.println("\nЗапущені інші потоки");
        executor.execute(myThread0);
        executor.execute(myThread1);
        executor.execute(myThread2);

        // Вивести на екран результат високорівневої багатопотоковості
        System.out.println(list.stream()
                .map(MyThread::getValue)
                .collect(Collectors.toList()));
        executor.shutdown();
        System.out.println("Кінець");
    }
}
