package org.bodya.lab2.task3;

import org.bodya.lab2.task2.entities.Group;
import org.bodya.lab2.task2.enums.Subject;
import org.bodya.lab2.task2.entities.University;
import org.bodya.lab2.task3.service.GroupService;

import java.time.LocalDate;

import static org.bodya.lab2.task2.initService.InitService.initUniversity;

public class Main {

    public static void main(String[] args) {
        University university = initUniversity();

        Group group1 = university.getGroups().get(0);
        System.out.println(group1);

        // Метод для добавлення нового предмету усім студентам
        // певної групи
        GroupService.addSubjectToAllStudentsOfGroup(university.getGroups().get(0), Subject.SYSTEM_PROGRAMMING, 3);
        System.out.println(group1);

        // Метод, який буде додавати у навчальний розклад дані про
        // предмет, групу та дату заняття
        GroupService.addSubjectToGroupSchedule(group1, Subject.SYSTEM_PROGRAMMING, LocalDate.of(2022,10,1));
        System.out.println(group1.getGroupSchedule());

        // Метод для виведення усіх студентів, які відвідують певний
        // предмет
        GroupService.soutStudentsByAlphabetical(group1);

        System.out.println(university);
    }
}
