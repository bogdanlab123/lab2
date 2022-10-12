package org.bodya.lab3.task1;

import org.bodya.lab3.task2.InvalidMoneyOperationException;
import org.bodya.lab3.task2.ShopException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Shop {
    // Продавець в магазині лише один
    private Seller seller;
    // Загальний прибуток магазину. Без сетера,
    // Лише додавання та отримання
    private Double income;
    // Склад продуктів [Продукт, Кількість на складі]
    private Map<Product, Integer> store = new HashMap<>();
    // Блок ініціалізації. Викликається перед конструкторами
    // при створені об'єкта.
    {
        income = 0.0;
    }

    public Shop() {
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

    public Double getIncome() {
        return income;
    }

    public Map<Product, Integer> getStore() {
        return store;
    }

    public void setStore(Map<Product, Integer> store) {
        this.store = store;
    }

    public static Check createCheck(final List<Product> productList) {
        Check check = new Check();
        for (Product product : productList) {
            check.putProductToCheck(product);
        }
        return check;
    }

    public void performCheck(final Buyer buyer, final Check check) throws InvalidMoneyOperationException {
        buyer.substractMoney(check.getProductsPrice());
        income+= check.getProductsPrice();
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
                '}';
    }
}
