package org.bodya.lab3.task1;

import org.bodya.lab3.task1.entities.Buyer;
import org.bodya.lab3.task1.entities.Check;
import org.bodya.lab3.task1.entities.Seller;
import org.bodya.lab3.task1.entities.Shop;
import org.bodya.lab3.task2.ShopException;
import org.bodya.lab3.task3.service.BuyerService;
import org.bodya.lab3.task3.service.ShopService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bodya.lab3.task1.enums.Product.*;

public class Main {

    public static void main(String[] args) {

        // Створюємо магазин без продавця
        Shop shop = new Shop();
        Buyer buyer1 = new Buyer("Степан", "Бандера", 2500.0);
        Buyer buyer2 = new Buyer("Іван", "Маск", 2000.0);
        Buyer buyer3 = new Buyer("Анатолій", "Вареник", 2200.0);
        // Створюємо продавця з посиланням на магазин в якому він працює
        Seller seller = new Seller("Дональд", "Трамп", shop);
        // Встановлюємо посилання на продавця для магазину
        shop.setSeller(seller);
        // Здійснюємо покупку для buyer з Map продуктів, де продукт
        // це ключ, а значення - кількість.
        try {
            seller.checkoutProducts(buyer1, LocalDate.of(2022,11,11), Map.of(MILK, 2, BREAD, 1, CHOCOLATE, 1, WATER_MORSHINSKA_1_5, 4));
            seller.checkoutProducts(buyer1, LocalDate.of(2022,11,11), Map.of(BREAD, 1, CHOCOLATE, 1, WATER_MORSHINSKA_1_5, 2));
            seller.checkoutProducts(buyer2, LocalDate.of(2022,12,5), Map.of(WATER_AQUA_05, 1, CHOCOLATE, 1, RICE_900, 1));
            seller.checkoutProducts(buyer2, LocalDate.of(2022,12,7), Map.of(MILK, 2, BREAD, 1, CHOCOLATE, 1, WATER_MORSHINSKA_1_5, 4));
            seller.checkoutProducts(buyer3, LocalDate.of(2022,12,1), Map.of(MILK, 4, WATER_MORSHINSKA_05, 1, CHOCOLATE, 1));
        } catch (ShopException e) {
            // Якщо сталася помилка, вивести повідомлення
            System.out.println(e.getMessage());
        }

        // Виводимо весь магазин в консоль
        System.out.println(shop);
        // Виводимо в консоль загальний прибуток магазину
        System.out.println("Загальний прибуток магазину: " + shop.getTotalIncome());
        // Виводимо в консоль всі чеки всіх покупців, привівши їх в список аби
        // не дублювати код виведення для кожного покупця
        for(Buyer buyer : List.of(buyer1,buyer2,buyer3)){
            System.out.println("Чеки " + buyer.getName() + ' ' + buyer.getSurname());
            for (Check check : buyer.getCheckList()){
                System.out.println(check.asString());
                System.out.println();
            }
            System.out.println("\n\n");
        }


        // Демонстрація роботи Stream API завдання 3
        // Вивести на екран сортовані товари з складу за найбільшою кількістю
        System.out.println("Відсортовані товари на складі за кількістю: " );
        System.out.println(ShopService.getSortedStore(shop));
        // Вивести на екран середню вартість товарів магазину
        System.out.println("Середня вартість продуктів в магазині: ");
        System.out.println(ShopService.getAvgProductPrice(shop));
        // Вивести на екран витрати покупця за заданий період часу
        System.out.println("Витрати покупця за певний період: ");
        System.out.println(BuyerService.getBuyerCosts(buyer1, LocalDate.of(2022,1,1), LocalDate.of(2022,12,30)));
        // Вивести на екран загальну кількість куплених продуктів покупця
        System.out.println("Загальна кількість усіх куплених продуктів покупця: ");
        System.out.println(BuyerService.getTotalProductCount(buyer1));
        // Вивести на екран найпопулярніший товар
        System.out.println("Найпопулярніший продукт: ");
        System.out.println(ShopService.getTheMostPopularProduct(shop));
        // Вивести на екран найбільший прибуток за 2022.11.11
        System.out.println("Найбільший прибуток за 2022.11.11: ");
        System.out.println(ShopService.getBestIncome(shop, LocalDate.of(2022,11,11)));
    }
}
