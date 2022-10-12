package org.bodya.lab3.task2;

import org.bodya.lab3.task1.Person;
import org.bodya.lab3.task1.Product;

/**
 * Клас для виключень пов'язаних з операціями обробки коштів.
 * Наслідує RuntimeException, отож його не обов'язково обробляти в
 * блоці try catch
 */
public class InvalidMoneyOperationException extends RuntimeException{

    private Person person;
    private String message;

    public InvalidMoneyOperationException() {
    }

    public InvalidMoneyOperationException(Person person, String message) {
        this.person = person;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ShopException{" +
                "\n\tperson=" + person +
                "\n\tmessage='" + message + '\'' +
                '}';
    }
}
