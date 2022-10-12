package org.bodya.lab3.task1;

public enum Product {
    BREAD("Хліб", 15.30),
    MILK("Молоко", 59.90),
    CHOCOLATE("Шоколадка", 48.99),
    WATER_MORSHINSKA_05("Моршинська 0.5л", 19.90),
    WATER_MORSHINSKA_1_5("Моршинська 0.5л", 25.90),
    WATER_AQUA_05("Аuqa 0.5л", 17.90),
    CREAM("Сметана", 50.25),
    RICE_900("Рис 900г", 31.55);

    private final String name;
    private final Double price;

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
