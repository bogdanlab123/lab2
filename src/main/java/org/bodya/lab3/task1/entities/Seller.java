package org.bodya.lab3.task1.entities;

import org.bodya.lab3.task1.enums.Product;
import org.bodya.lab3.task2.ShopException;

import java.time.LocalDate;
import java.util.Map;

public class Seller extends Person {

    private Shop shop;

    public Seller() {
    }

    public Seller(String name, String surname, Shop shop) {
        super(name, surname);
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Метод для продажі продуктів (на касі). За задумкою,
     * продавець рахує спочатку створює чек після додавання
     * їх до кінецвого чеку, магазин (термінал) опрацьовує
     * чек (списує кошти покупця), а потім чек повертається
     * покупцю.
     *
     * @param buyer - покупець.
     * @param products - кошик товарів покупця (товар, кількість в кошику).
     * @param localDate - день покупки. Для спрощення реалізації передається
     *                  як параметр, а не вираховується з поточного дня.
     * @return - чек покупки.
     */
    public Check checkoutProducts(final Buyer buyer, final LocalDate localDate, final Map<Product, Integer> products) throws ShopException {
        try {
            Check check = shop.createCheck(localDate, products);
            shop.performCheck(buyer, localDate, check);
            return check;
        } catch (ShopException e){
            // Вивести повідомлення та повернути null у разі ShopException
            System.out.println(e.getMessage());
            return null;
        }
    }
}
