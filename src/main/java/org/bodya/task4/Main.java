package org.bodya.task4;

import org.bodya.task2.entities.Student;
import org.bodya.task2.entities.University;
import org.bodya.task2.enums.Subject;
import org.bodya.task2.initService.InitService;
import org.bodya.task4.MarksJournal;

public class Main {
    public static void main(String[] args) {

        University university = InitService.initUniversity();
        Student student = university.getGroups().get(0).getStudents().get(0);
        MarksJournal marksJournal = university.getGroups().get(0).getMarksJournal();

        // Метод для перегляду усіх оцінок усіх студентів
        marksJournal.soutAllMarks();
        // Метод для перегляду усіх оцінок одного студента
        marksJournal.soutAllMarksForStudent(student);
        // Метод для перегляду усіх оцінок усіх студентів з певного предмету
        marksJournal.soutAllMarksForSubject(Subject.PROGRAMMING);
        // Метод для перегляду оцінки з певного предмету стуента
        marksJournal.soutMarkForSubjectForStudent(student, Subject.ENGLISH);
        // Метод для додавання оцінки з певного предмету
        marksJournal.addMark(student, Subject.SYSTEM_PROGRAMMING, 5);
        marksJournal.soutAllMarksForStudent(student);
        // Метод для зміни оцінки з певного предмету
        marksJournal.changeMark(student, Subject.SYSTEM_PROGRAMMING, 4);
        marksJournal.soutAllMarksForStudent(student);
        // Метод для видалення оцінки з певного предмету
        marksJournal.removeMark(student, Subject.SYSTEM_PROGRAMMING);
        marksJournal.soutAllMarksForStudent(student);

        System.out.println("Happy end");
    }
}