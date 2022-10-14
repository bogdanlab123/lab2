package org.bodya.lab3.task1.entities;

import org.bodya.lab3.task1.enums.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Клас який відображає чек покупки покупкя.
 * Не містить жодних даних окрім товарів та їх кількості
 * в чеку.
 */
public class Check {
    // Без сетера. Лише отримати, додати, видалити один, видалити всі
    private Map<Product, Integer> products = new HashMap<>();
    // Дата покупки. Без сетера. Лише отримати
    private LocalDate localDate;

    public Check() {
    }

    public Check(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Check(Map<Product, Integer> products, LocalDate localDateTime) {
        this.products = products;
        this.localDate = localDateTime;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Метод для обрахунку вартості всіх товарів в чеку.
     * Не виноситься як змінна, оскільки при добавлення
     * товару кожен раз треба перераховувати вартість.
     */
    public Double getProductsPrice() {
        Double price = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        return price;
    }

    /**
     * Метод для додавання товару в чек (корзину).
     *
     * @param product - продукт для додавання в чек (корзину).
     */
    public void putProductToCheck(final Map.Entry<Product, Integer> product) {
        products.put(product.getKey(), product.getValue());
    }

    /**
     * Метод для видалення товару з чеку (корзини).
     * Якщо товару нема в корзині, нічого не робити, якщо є зменшити,
     * кількість товару на count.
     *
     * @param product - товар для видалення з чеку (корзини).
     * @param count   - кількість товару для видалення
     */
    public void removeProductFromCheck(final Product product, final Integer count) {
        if (products.containsKey(product)) {
            // Якщо кількість продуктів в чеку > count, зменшити кількість на count
            if (products.get(product) > count) {
                products.replace(product, products.get(product) - 1);
            } else {
                // Якщо кількість товарів не є більша за count, то видалити товар
                // з чеку
                products.remove(product);
            }
        }
    }

    /**
     * Метод для видалення всіх товарів з чеку (корзини).
     * Якщо товарів нема в корзині, нічого не робити.
     */
    public void flashCheck() {
        products = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return Objects.equals(products, check.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    /**
     * Метод для форматованого виведення чеку.
     *
     * @return - строку у вигляді чеку.
     */
    public String asString() {
        StringBuilder check = new StringBuilder();
        check.append("#######################\n");
        check.append("#                     #\n");
        check.append("#         ЧЕК         #\n");
        check.append("#                     #\n");
        check.append("#######################\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            check.append("# ");
            check.append(entry.getKey().getName());
            check.append(" ".repeat(19 - entry.getKey().getName().length()));
            check.append(" #\n#");
            check.append(" ".repeat(18 - String.valueOf(entry.getValue()).length()));
            check.append("x ");
            check.append(entry.getValue());
            check.append(" #\n#");
            BigDecimal price = BigDecimal.valueOf(entry.getKey().getPrice());
            price = price.setScale(2, RoundingMode.HALF_UP);
            check.append(" ".repeat(20 - price.toString().length()));
            check.append(price);
            check.append(" #\n");
        }
        check.append("#######################\n#");
        BigDecimal productPrice = BigDecimal.valueOf(getProductsPrice());
        productPrice = productPrice.setScale(2, RoundingMode.HALF_UP);
        check.append(" ".repeat(13 - productPrice.toString().length()));
        check.append("Разом: ");
        check.append(productPrice);
        check.append(" #\n#");
        check.append(" ".repeat(20 - localDate.toString().length()));
        check.append(localDate);
        check.append(" #\n");
        check.append("#######################");
        return check.toString();
    }

    @Override
    public String toString() {
        return "Check{" +
                "\n\tproducts=" + products +
                "\n}";
    }
}