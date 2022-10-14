package org.bodya.lab3.task3.service;

import org.bodya.lab3.task1.entities.Shop;
import org.bodya.lab3.task1.enums.Product;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class ShopService {

    /**
     * Метод для фільтрування та сортування усіх продуктів на
     * складі. Виводить результат в консоль.
     *
     * @param shop - магазин.
     */
    public static String getSortedStore(final Shop shop) {
        StringBuilder stringBuilder = new StringBuilder();
        // Спочатку беремо всі продукти з складу (сет ключів, бо
        // сет значень це їх кількість), приводимо їх до стріма,
        // сортуємо за значенням (за кількістю), приводимо до строки,
        // та записуємо строку в stringBuilder.
        shop.getStore().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(e -> e.getKey().getName() + ": " + e.getValue()+", ")
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    /**
     * Метод для визначення середньої ціни всіх продуктів в магазині.
     *
     * @param shop - магазин.
     * @return середню вартість всіх продуктів або нуль, якщо продуктів
     * в магазині (на складі) немає.
     */
    public static Double getAvgProductPrice(final Shop shop) {
        // Спочатку беремо всі продукти з складу (сет ключів, бо
        // сет значень це їх кількість), приводимо їх до стріма
        // Double, та шукаємо їх середнє арифметичне, або нуль,
        // якщо чисел нема.
        return shop.getStore().keySet()
                .stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
    }

    /**
     * Метод для знаходження найпопулярнішого продукту.
     * Найпопулярніший той, який найчастіше купують.
     * @param shop - магазин
     * @return продукт, який найчастіше купувлаи.
     */
    public static Product getTheMostPopularProduct(final Shop shop) {
        // Створюємо мапу, в яку будемо записувати продукт як ключ,
        // та скільки його купляли як значення.
        Map<Product, Integer> map = new HashMap<>();
        // Приведемо всі чеки до стріма. Потім з стріма чеків
        // приводимо його до стріма Map.Entry<Product,Integer>.
        // Для кожного значення додаємо кількість покупок товару в
        // нашу мапу.
        shop.getCheckList()
                .stream()
                .flatMap(check -> check.getProducts().entrySet().stream())
                .forEach(entry -> {
                    if(map.containsKey(entry.getKey())){
                        map.replace(entry.getKey(), map.get(entry.getKey())+entry.getValue());
                    } else {
                        map.put(entry.getKey(), entry.getValue());
                    }
                });
        // Вибираємо з мапи ключ (продукт), в якого найбільше
        // value (кількість покупок).
        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow().getKey();

    }

    /**
     * Метод для знаходження найбільшого доходу за день.
     * Проходиться по всім чекам за вказаний день, та обирає
     * серед них той, в якому прибуток є найбільший.
     *
     * @param shop - магазин.
     * @param localDate - день для якого потрібно знайти найбільший дохід.
     * @return найбільший дохід за день
     */
    public static Double getBestIncome(final Shop shop, final LocalDate localDate){
        return shop.getCheckList()
                .stream()
                .filter(check -> check.getLocalDate().isEqual(localDate))
                .flatMapToDouble(check -> DoubleStream.of(check.getProductsPrice()))
                .max()
                .orElse(0.0);
    }
}
