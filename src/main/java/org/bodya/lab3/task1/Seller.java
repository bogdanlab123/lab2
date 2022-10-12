package org.bodya.lab3.task1;

import org.bodya.lab3.task2.ShopException;

import java.util.List;
import java.util.Objects;

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
     * @param productList - кошик товарів покупця
     * @return - чек покупки.
     */
    public Check checkoutProducts(final Buyer buyer, final List<Product> productList) {
        Check check = Shop.createCheck(productList);
        shop.performCheck(buyer, check);
        return check;
    }
}
