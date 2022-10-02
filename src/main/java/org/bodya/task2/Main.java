package org.bodya.task2;

import org.bodya.task2.entities.University;

import static org.bodya.task2.initService.InitService.initUniversity;

public class Main {

    public static void main(String[] args) {

        University university = initUniversity();

        System.out.println(university);
    }
}
