package org.bodya.lab3.task1;

import java.util.List;

import static org.bodya.lab3.task1.Product.*;

public class Main {
    public static void main(String[] args) {
        // Створюємо магазин без продавця
        Shop shop = new Shop();
        Buyer buyer = new Buyer("Степан", "Бандера", 2500.0);
        // Створюємо продавця з посиланням на магазин в якому він працює
        Seller seller = new Seller("Дональд", "Трамп", shop);
        // Встановлюємо посилання на продавця для магазину
        shop.setSeller(seller);
        // Здійснюємо покупку для buyer з списком продуктів. Предмети можуть
        // повторюватися
        Check check = seller.checkoutProducts(buyer, List.of(MILK, CHOCOLATE, MILK, WATER_AQUA_05));
        System.out.println(check.asString());
        System.out.println("\n"+buyer);
        System.out.println("\n"+shop);
    }
}
