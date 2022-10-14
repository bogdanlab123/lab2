package org.bodya.lab3.task1.entities;

import org.bodya.lab3.task2.InvalidMoneyOperationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Клас покупець. Наслідує клас Person.
 */
public class Buyer extends Person {
    // Кошти покупця. Сетер недоступний
    private Double money;
    private List<Check> checkList = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String name, String surname, Double money) {
        super(name, surname);
        this.money = money;
    }

    public Double getMoney() {
        return money;
    }

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }

    /**
     * Метод для додавання коштів покупця.
     *
     * @param money - кошти для додавання. Повинен бути додатнім.
     */
    public void addMoney(final Double money) {
        if (money > 0) {
            this.money += money;
        } else {
            throw new InvalidMoneyOperationException(this, "Значення суми для додавання повинне бути додатнє");
        }
    }
    /**
     * Метод для віднімання коштів покупця.
     *
     * @param money - кошти для віднімання. Повинен бути додатнім.
     */
    public void substractMoney(final Double money) {
        if (money > 0) {
            this.money -= money;
        } else {
            throw new InvalidMoneyOperationException(this, "Значення суми для віднімання повинне бути додатнє");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(money, buyer.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), money);
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "money=" + money +
                ", checkList=" + checkList +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
