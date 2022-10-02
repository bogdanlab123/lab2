package org.bodya.task2.entities;

import org.bodya.task2.enums.Subject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student {

    private String name;
    private String surname;
    private Map<Subject, Integer> marks = new HashMap<>();

    public Student() {
    }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, Map<Subject, Integer> marks) {
        this.name = name;
        this.surname = surname;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Map<Subject, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<Subject, Integer> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, marks);
    }

    @Override
    public String toString() {
        return "\n\t\t\t\tStudent{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", marks=" + marks +
                '}';
    }
}
