package org.bodya.task4;

import org.bodya.task2.entities.Student;
import org.bodya.task2.enums.Subject;

import java.util.*;

/**
 * Класс, завданням якого буде містити журнал оцінок студента
 * (оцінка за предмет за семестр).
 */
public class MarksJournal {

    private Map<Student, Map<Subject, Integer>> map = new HashMap<>();

    public MarksJournal() {
    }

    public MarksJournal(Map<Student, Map<Subject, Integer>> map) {
        this.map = map;
    }

    public Map<Student, Map<Subject, Integer>> getMap() {
        return map;
    }

    public void setMap(Map<Student, Map<Subject, Integer>> map) {
        this.map = map;
    }

    /**
     * Метод додавання оцінки студенту з предмету. Якщо оцінка
     * за предмет вже є, нічого не робити.
     *
     * @param student - студент, якому додати оцінку.
     * @param subject - предмет, з якого додати оцінку.
     * @param integer - оцінка.
     */
    public void addMark(final Student student, final Subject subject, final Integer integer) {
        map.get(student).putIfAbsent(subject, integer);
    }

    /**
     * Метод зміни оцінки студенту з предмету. Якщо оцінка
     * з предмету немає, нічого не робити.
     *
     * @param student - студент, якому додати оцінку.
     * @param subject - предмет, з якого додати оцінку.
     */
    public void removeMark(final Student student, final Subject subject) {
        map.get(student).remove(subject);
    }

    /**
     * Метод зміни оцінки студенту з предмету. Якщо оцінки
     * за предмет немає, вивести на екран попередження.
     *
     * @param student - студент, якому додати оцінку.
     * @param subject - предмет, з якого додати оцінку.
     * @param integer - оцінка.
     */
    public void changeMark(final Student student, final Subject subject, final Integer integer) {
        if (map.get(student).containsKey(subject)) {
            map.get(student).replace(subject, integer);
        } else {
            System.out.println("Студент " + student.getName() + ' ' + student.getSurname() + " не має оцінки з предмету " + subject + ". Зміни оцінки не відбулося");
        }
    }

    /**
     * Метод для перегляду усіх оцінок усіх студентів.
     */
    public void soutAllMarks() {
        for (Map.Entry<Student, Map<Subject, Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey().toString() + ", оцінки=" + entry.getValue());
        }
    }

    /**
     * Метод для перегляду усіх оцінок певного студента.
     */
    public void soutAllMarksForStudent(final Student student) {
        System.out.println("Оцінки студента " + student.getName() + ' ' + student.getSurname() + " = " + map.get(student).toString());
    }

    /**
     * Метод для перегляду оцінок за предмет усіх студентів.
     */
    public void soutAllMarksForSubject(final Subject subject) {
        for (Map.Entry<Student, Map<Subject, Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey().toString() + ", оцінка=" + entry.getValue().get(subject));
        }
    }

    /**
     * Метод для перегляду оцінки студента з предмету.
     */
    public void soutMarkForSubjectForStudent(final Student student, final Subject subject) {
        System.out.println("Оцінка студента " + student.getName() + ' ' + student.getSurname() + " за предмет " + subject + " = " + map.get(student).get(subject));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarksJournal that = (MarksJournal) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return "MarksJournal{" +
                "map=" + map +
                '}';
    }
}
