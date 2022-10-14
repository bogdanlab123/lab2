package org.bodya.lab3.task3.service;

import org.bodya.lab3.task1.entities.Buyer;
import org.bodya.lab3.task1.entities.Check;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuyerService {

    /**
     * Метод для визначення всіх витрат заданого користувача за
     * заданий період часу
     *
     * @param buyer - покупець.
     * @param from - дата від якої здійснено покупки.
     * @param buyer - дата по яку здійснено покупки.
     * @return загальні витрати користувача.
     */
    public static Double getBuyerCosts(final Buyer buyer, final LocalDate from, final LocalDate till){
        return buyer.getCheckList()
                .stream()
                .filter(check -> check.getLocalDate().isAfter(from) && check.getLocalDate().isBefore(till))
                .mapToDouble(Check::getProductsPrice)
                .sum();
    }

    /**
     * Метод для отримання даних про сумарну кількість кожного
     * купленого продукту заданого користувача (кількість продуктів,
     * не вартість).
     */
    public static Integer getTotalProductCount(final Buyer buyer){
        return buyer.getCheckList()
                .stream()
                .flatMapToInt(check -> check.getProducts().values().stream().mapToInt(e->e))
                .sum();


    }
}
