package org.bodya.lab3.task1.entities;

import org.bodya.lab3.task1.enums.Product;
import org.bodya.lab3.task2.InvalidMoneyOperationException;
import org.bodya.lab3.task2.ShopException;

import java.time.LocalDate;
import java.util.*;

public class Shop {
    // Продавець в магазині лише один
    private Seller seller;
    // Загальний прибуток магазину. Без сетера,
    // Лише додавання та отримання
    private Map<LocalDate, Double> income = new HashMap<>();
    // Склад продуктів [Продукт, Кількість на складі]
    private Map<Product, Integer> store = new HashMap<>();
    // Список чеків виданих покупцям
    private List<Check> checkList = new ArrayList<>();

    // За замовчуванням ініціалізує магазин з кожним товаром
    // кількістю одиниць 10 кожного
    {
        store.put(Product.MILK,10);
        store.put(Product.CHOCOLATE,10);
        store.put(Product.BREAD,10);
        store.put(Product.WATER_MORSHINSKA_1_5,10);
        store.put(Product.WATER_MORSHINSKA_05,10);
        store.put(Product.WATER_AQUA_05,10);
        store.put(Product.RICE_900,10);
    }
    public Shop() {
    }

    /**
     * Метод для перевірки, чи достатньо на складі продуктів для продажі.
     *
     * @param products - кошик продуктів покупця
     * @throws ShopException якщо на складі недостатньо товарів для продажі
     */
    private void checkProductsOnStore(final Map<Product, Integer> products) throws ShopException {
        // Проходимся по кожному продукту з кошика
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Перевіряємо кількість на складі кожного продукту
            // Якщо compareTo повертає -1 або менше, значить продуктів на складі
            // менше аніж в кошику, тому викинути помилку
            if (store.getOrDefault(entry.getKey(), 0).compareTo(entry.getValue()) < 0) {
                // Продуктів недостатньо, або немає на складі
                throw new ShopException(entry.getKey(), "Недостатньо продуктів на складі для продажу.");
            }
        }
    }

    /**
     * Метод для додавання прибутку магазину.
     *
     * @param localDate - день покупки.
     * @param money - дохід за покупку.
     */
    private void addMoney(final LocalDate localDate, final Double money) {
        if (income.containsKey(localDate)) {
            income.replace(localDate, income.get(localDate) + money);
        } else {
            income.put(localDate, money);
        }
    }

    /**
     * Метод для видалення товару зі складу після продажі.
     * Оскільки метод викликається після проходження валідації, то
     * сценарцій, шо на складі недостатньо товару неможливий.
     * Також неможливий сценарцій, що товару немає на складі.
     *
     * @param products - товари для видалення
     */
    private void removeProducts(final Map<Product, Integer> products) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (store.get(entry.getKey()).compareTo(entry.getValue()) == 0) {
                store.remove(entry.getKey());
            } else {
                // Якщо товару на складі більше аніж в кошику покупця, то зменшити
                // кількість товару на складі
                store.replace(entry.getKey(), store.get(entry.getKey()) - entry.getValue());
            }
        }
    }

    public Shop(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Map<LocalDate, Double> getIncome() {
        return income;
    }

    public Map<Product, Integer> getStore() {
        return store;
    }

    public void setStore(Map<Product, Integer> store) {
        this.store = store;
    }

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }

    /**
     * Метод для створення чеку. Спочатку перевіряє, чи продутки є на складі,
     * а потім після успішної валідації додає їх в чек.
     *
     * @param products - продукти покупця.
     * @return чек.
     * @throws ShopException якщо на складі недостатньо продуктів для продажу.
     */
    public Check createCheck(final LocalDate localDate, final Map<Product, Integer> products) throws ShopException {
        checkProductsOnStore(products);
        Check check = new Check(localDate);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            check.putProductToCheck(entry);
        }
        return check;
    }

    /**
     * Метод для виконання продажу товарів.
     * Списує кошти користувача, та дає йому чек, та
     * додає ці кошти на баланс магазину.
     *
     * @param buyer
     * @param check
     * @throws InvalidMoneyOperationException
     */
    public void performCheck(final Buyer buyer, final LocalDate localDate, final Check check) throws InvalidMoneyOperationException {
        Double price = check.getProductsPrice();
        removeProducts(check.getProducts());
        buyer.substractMoney(price);
        addMoney(localDate, price);
        checkList.add(check);
        buyer.getCheckList().add(check);
    }

    /**
     * Метод для отримання загального прибутку магазину
     * за весь період.
     */
    public Double getTotalIncome(){
        Double totalIncome = 0.0;
        for(Map.Entry<LocalDate, Double> entry : income.entrySet()){
            totalIncome+=entry.getValue();
        }
        return totalIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(seller, shop.seller) && Objects.equals(income, shop.income);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seller, income);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "seller=" + seller +
                ", income=" + income +
                ", store=" + store +
                ", checkList=" + checkList +
                '}';
    }
}
