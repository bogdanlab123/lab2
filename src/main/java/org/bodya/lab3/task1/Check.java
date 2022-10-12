package org.bodya.lab3.task1;

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

    public Check() {
    }

    public Check(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
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
     * Якщо товар вже є в чеку, то збільшити кількість на 1,
     * якщо нема, додати в чек.
     *
     * @param product - продукт для додавання в чек (корзину).
     */
    public void putProductToCheck(final Product product) {
        if (!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            products.replace(product, products.get(product) + 1);

        }
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
        Double productPrice = getProductsPrice();
        check.append("#######################\n");
        check.append("#                     #\n");
        check.append("#         ЧЕК         #\n");
        check.append("#                     #\n");
        check.append("#######################\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            check.append("# ");
            check.append(entry.getKey().getName());
            check.append(" ".repeat(19-entry.getKey().getName().length()));
            check.append(" #\n#");
            check.append(" ".repeat(18-String.valueOf(entry.getValue()).length()));
            check.append("x ");
            check.append(entry.getValue());
            check.append(" #\n#");
            check.append(" ".repeat(20-String.valueOf(entry.getKey().getPrice()).length()));
            check.append(entry.getKey().getPrice());
            check.append(" #\n");
        }
        check.append("#######################\n#");
        check.append(" ".repeat(13-String.valueOf(productPrice).length()));
        check.append("Разом: ");
        check.append(productPrice);
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