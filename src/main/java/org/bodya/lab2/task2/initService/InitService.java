package org.bodya.lab2.task2.initService;

import org.bodya.lab2.task2.entities.Group;
import org.bodya.lab2.task2.entities.Schedule;
import org.bodya.lab2.task2.entities.Student;
import org.bodya.lab2.task2.enums.Subject;
import org.bodya.lab2.task2.entities.University;
import org.bodya.lab2.task4.MarksJournal;

import java.time.LocalDate;
import java.util.*;

/**
 * Допоміжний клас для ініціалізаії всіх полів класу.
 */
public class InitService {

    /**
     * Цей метод потрібен для ініцалізації списку студентів.
     * Викликає метод "initStudent" для ініціалізації одного студента.
     *
     * @param names    список імен.
     * @param surnames список прізвищ.
     * @return список студентів.
     */
    private static List<Student> initStudents(final List<String> names, final List<String> surnames) {
        // Якщо списки не рівні, викинути помилку
        if (names.size() != surnames.size()) {
            throw new RuntimeException("Кількість імен не дорівнює кількості прізвищ");
        }

        List<Student> students = new ArrayList<>();

        // Оскільки списки однакового розміру, проходимося по будь якому з них.
        // Ініціалізуємо почергово студента з іменем списку "names" з індексом "i"
        // та прізвищем списку "surname" з індексом "i"
        for (int i = 0; i < names.size(); ++i) {
            students.add(initStudent(names.get(i), surnames.get(i)));
        }

        return students;
    }

    /**
     * Цей метод потрібен для ініціалізації студента. Для ініцалізації оцінок
     * використовується генератор рандомних чисел від 1 до 5.
     *
     * @param name    ім'я студента
     * @param surname прізвище студента
     * @return студента з ініціалізованими ім'ям та прізвищем та рандомними
     * оцінками зі всіх предметів.
     */
    private static Student initStudent(final String name, final String surname) {
        return new Student(name, surname);
    }

    /**
     * Метод ініціалізації розкладу.
     * За замовчуванням ініалізує лише два предмети (дві пари) на день.
     * За замовчуванням ініціалізує пари за 2022 рік, 10 місяць, 1-5 дні місяця.
     *
     * @return розклад з предметами.
     */
    private static Schedule initSchedule(final List<Subject> first, final List<Subject> second) {
        // Якщо довжина списків не дорівнює 5 (5 робочих днів), то
        // викинути помилку
        if (first.size() != 5 || second.size() != 5) {
            throw new RuntimeException("Кількість перших та других пар повинна бути 5.");
        }

        Map<LocalDate, List<Subject>> map = new HashMap<>();
        // Використовується конструктор ArrayList і всередині List.of тому що
        // List.of повертає незмінний список, а нам потрібен змінний
        for (int i = 0; i < 5; i++) {
            map.put(LocalDate.of(2022, 10, i + 1), new ArrayList<>(List.of(first.get(i), second.get(i))));
        }

        return new Schedule(map);
    }

    /**
     * Метод для ініціалізації журналу відвідування всіх студентів групи.
     *
     * @param students - список студентів.
     * @return - ініціалізовану Map відвідування предметів студентами.
     */
    private static Map<Student, Map<Subject, Boolean>> initAttendBook(final List<Student> students) {
        Map<Student, Map<Subject, Boolean>> map = new HashMap<>();
        Map<Subject, Boolean> subjectBooleanMap = initSubjectBooleanMap();

        for (Student student : students){
            map.put(student, subjectBooleanMap);
        }

        return map;
    }

    /**
     * Метод для ініціалізації Map відвідування предметів.
     * За замовчуванням для всіх предметів відвідування є true,
     * тобто студент присутній.
     */
    private static Map<Subject, Boolean> initSubjectBooleanMap() {
        Map<Subject, Boolean> booleanMap = new HashMap<>();

        booleanMap.put(Subject.ENGLISH, true);
        booleanMap.put(Subject.UKRAINIAN, true);
        booleanMap.put(Subject.MATHEMATICS, true);
        booleanMap.put(Subject.PHYSICS, true);
        booleanMap.put(Subject.LITERATURE, true);
        booleanMap.put(Subject.PROGRAMMING, true);

        return booleanMap;
    }

    /**
     * Метод для ініціалізації журнала оцінок.
     * @param students - список студентів.
     * @return - журнал з оцінками студентів.
     */
    private static MarksJournal initMarksJournal(final List<Student> students) {
        MarksJournal marksJournal = new MarksJournal();

        // Для кожного студента встановити рандомні оцінки
        // з кожного предмету
        for (Student student : students) {
            Map<Subject, Integer> subjectIntegerMap = new HashMap<>();

            subjectIntegerMap.put(Subject.UKRAINIAN, new Random().nextInt(5) + 1);
            subjectIntegerMap.put(Subject.ENGLISH, new Random().nextInt(5) + 1);
            subjectIntegerMap.put(Subject.PROGRAMMING, new Random().nextInt(5) + 1);
            subjectIntegerMap.put(Subject.MATHEMATICS, new Random().nextInt(5) + 1);
            subjectIntegerMap.put(Subject.PHYSICS, new Random().nextInt(5) + 1);
            subjectIntegerMap.put(Subject.LITERATURE, new Random().nextInt(5) + 1);

            marksJournal.getMap().put(student, subjectIntegerMap);
        }

        return marksJournal;
    }
        /**
         * Метод для ініціалізації університету.
         * @return об'єкт університету з ініціалізованими всіма полями.
         */
        public static University initUniversity () {
            List<Student> students1 = initStudents(
                    List.of("Олег", "Дмитро", "Андрій", "Степан", "Артур"),
                    List.of("Шевченко", "Франко", "Сніговик", "Вареник", "Король"));
            Schedule schedule1 = initSchedule(
                    List.of(Subject.ENGLISH, Subject.UKRAINIAN, Subject.PROGRAMMING, Subject.PROGRAMMING, Subject.PHYSICS),
                    List.of(Subject.MATHEMATICS, Subject.LITERATURE, Subject.ENGLISH, Subject.PROGRAMMING, Subject.UKRAINIAN));
            Map<Student, Map<Subject, Boolean>> attendBook1 = initAttendBook(students1);
            MarksJournal marksJournal1 = initMarksJournal(students1);

            Group group1 = new Group("IT-21", students1, schedule1, attendBook1, marksJournal1);

            List<Student> students2 = initStudents(
                    List.of("Сергій", "Богдан", "Дмитро", "Орест", "Макар"),
                    List.of("Піп", "Борщ", "Вареник", "Кульчицький", "Лукаш")
            );
            Schedule schedule2 = initSchedule(
                    List.of(Subject.PROGRAMMING, Subject.ENGLISH, Subject.PHYSICS, Subject.MATHEMATICS, Subject.LITERATURE),
                    List.of(Subject.UKRAINIAN, Subject.ENGLISH, Subject.PROGRAMMING, Subject.UKRAINIAN, Subject.PROGRAMMING)
            );
            Map<Student, Map<Subject, Boolean>> attendBook2 = initAttendBook(students2);
            MarksJournal marksJournal2 = initMarksJournal(students2);

            Group group2 = new Group("IT-22", students2, schedule2, attendBook2, marksJournal2);

            return new University(new ArrayList<>(List.of(group1, group2)));
        }
    }
