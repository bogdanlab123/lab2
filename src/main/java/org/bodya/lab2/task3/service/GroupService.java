package org.bodya.lab2.task3.service;

import org.bodya.lab2.task2.entities.Group;
import org.bodya.lab2.task2.entities.Student;
import org.bodya.lab2.task2.enums.Subject;

import java.time.LocalDate;
import java.util.*;

/**
 * Клас сервіс для дій на класом University.
 */
public class GroupService {

    /**
     * Метод для добавлення нового предмету усім студентам
     * певної групи. За замовчуванням, якщо студент має цей
     * предмет, то він не буде додаватися. Оцінка не оновиться.
     *
     * @param group - група студентів.
     */
    public static void addSubjectToAllStudentsOfGroup(final Group group, final Subject subject, final Integer mark) {
        for (Map.Entry<Student, Map<Subject, Integer>> entry : group.getMarksJournal().getMap().entrySet()) {
            if (!entry.getValue().containsKey(subject)) {
                group.getMarksJournal().getMap().get(entry.getKey()).put(subject, mark);
            } else {
                System.out.printf("Студент " + entry.getKey().getName() + ' ' + entry.getKey().getSurname() + " вже має предмет " + subject + '.');
            }
        }
    }

    /**
     * Метод для додавання нового предмету в розклад групи
     *
     * @param group     - групи з студентами.
     * @param subject   - предмет який слід додати.
     * @param localDate - день для якого слід додати предмет в розклад.
     */
    public static void addSubjectToGroupSchedule(final Group group, final Subject subject, final LocalDate localDate) {
        Map<LocalDate, List<Subject>> schedule = group.getGroupSchedule().getScheduleMap();

        // Якщо для цього дня вже є розклад, то оновити його
        if (schedule.containsKey(localDate)) {
            schedule.get(localDate).add(subject);
        } else {
            // Якщо нема, то додати новий розклад за день
            schedule.put(localDate, new ArrayList<>(List.of(subject)));
        }

        // Встановити новий розклад
        group.getGroupSchedule().setScheduleMap(schedule);
    }

    /**
     * Метод для виведення усіх студентів групи в алфавітному порядку.
     * Використовує метод compareTo в класі Student для порівняння
     * студентів.
     */
    public static void soutStudentsByAlphabetical(final Group group) {
        List<Student> students = group.getStudents();
        Collections.sort(students);
        System.out.println(students);
    }

    /**
     * Метод для виведення усіх студентів, які відвідують певний
     * предмет.
     */
    public static void soutStudentThatAttendSubject(final Map<Student, Map<Subject, Boolean>> map, final Subject subject) {
        List<Student> students = new ArrayList<>();

        // Проходимся по кожному студенту з журналу.
        for (Map.Entry<Student, Map<Subject, Boolean>> studentEntry : map.entrySet()) {
            // Якщо з Map<Subject,Boolean> для заданого ключа значення == true,
            // додати студента в список для виведення.
            if (studentEntry.getValue().get(subject)) {
                students.add(studentEntry.getKey());
            }
        }

        System.out.println(students);
    }
}
