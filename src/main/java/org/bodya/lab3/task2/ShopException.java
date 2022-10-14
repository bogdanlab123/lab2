package org.bodya.lab3.task2;

import org.bodya.lab3.task1.enums.Product;

/**
 * Клас для виключень пов'язаних з операціями пов'язаниз з
 * магазином та операціями з магазином.
 * Наслідує Exception, отож його обов'язково обробляти в
 * блоці try catch
 */
public class ShopException extends Exception{

    private Product product;
    private String message;

    public ShopException() {
    }

    public ShopException(Product product, String message) {
        this.product = product;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ShopException{" +
                "\n\tproduct=" + product +
                "\n\tmessage='" + message + '\'' +
                '}';
    }
}
