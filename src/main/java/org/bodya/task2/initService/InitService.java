package org.bodya.task2.initService;

import org.bodya.task2.entities.Group;
import org.bodya.task2.entities.Schedule;
import org.bodya.task2.entities.Student;
import org.bodya.task2.entities.University;
import org.bodya.task2.enums.Subject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.bodya.task2.enums.Subject.*;

/**
 * Допоміжний клас для ініціалізаії всіх полів класу.
 */
public class InitService {

    /**
     * Цей метод потрібен для ініцалізації списку студентів.
     * Викликає метод "initStudent" для ініціалізації одного студента.
     * @param names список імен.
     * @param surnames список прізвищ.
     * @return список студентів.
     */
    private static List<Student> initStudents(final List<String> names, final List<String> surnames){
        // Якщо списки не рівні, викинути помилку
        if(names.size()!= surnames.size()){
            throw new RuntimeException("Кількість імен не дорівнює кількості прізвищ");
        }

        List<Student> students = new ArrayList<>();

        // Оскільки списки однакового розміру, проходимося по будь якому з них.
        // Ініціалізуємо почергово студента з іменем списку "names" з індексом "i"
        // та прізвищем списку "surname" з індексом "i"
        for(int i = 0; i < names.size(); ++i){
            students.add(initStudent(names.get(i), surnames.get(i)));
        }

        return students;
    }

    /**
     * Цей метод потрібен для ініціалізації студента. Для ініцалізації оцінок
     * використовується генератор рандомних чисел від 1 до 5.
     * @param name ім'я студента
     * @param surname прізвище студента
     * @return студента з ініціалізованими ім'ям та прізвищем та рандомними
     *         оцінками зі всіх предметів.
     */
    private static Student initStudent(final String name, final String surname) {
        Map<Subject, Integer> subjectIntegerMap = new HashMap<>();

        subjectIntegerMap.put(UKRAINIAN, new Random().nextInt(5) + 1);
        subjectIntegerMap.put(ENGLISH, new Random().nextInt(5) + 1);
        subjectIntegerMap.put(PROGRAMMING, new Random().nextInt(5) + 1);
        subjectIntegerMap.put(MATHEMATICS, new Random().nextInt(5) + 1);
        subjectIntegerMap.put(PHYSICS, new Random().nextInt(5) + 1);
        subjectIntegerMap.put(LITERATURE, new Random().nextInt(5) + 1);

        return new Student(name, surname, subjectIntegerMap);
    }

    /**
     * Метод ініціалізації розкладу.
     * За замовчуванням ініалізує лише два предмети (дві пари) на день.
     * За замовчуванням ініціалізує пари за 2022 рік, 10 місяць, 1-5 дні місяця.
     * @return розклад з предметами.
     */
    private static Schedule initSchedule(final List<Subject> first, final List<Subject> second){
        // Якщо довжина списків не дорівнює 5 (5 робочих днів), то
        // викинути помилку
        if(first.size() != 5 || second.size() != 5){
            throw new RuntimeException("Кількість перших та других пар повинна бути 5.");
        }

        Map<LocalDate, List<Subject>> map = new HashMap<>();
        // Використовується конструктор ArrayList і всередині List.of тому що
        // List.of повертає незмінний список, а нам потрібен змінний
        for(int i = 0; i < 5; i++){
            map.put(LocalDate.of(2022,10,i+1), new ArrayList<>(List.of(first.get(i),second.get(i))));
        }

        return new Schedule(map);
    }
    /**
     * Метод для ініціалізації університету.
     * @return об'єкт університету з ініціалізованими всіма полями.
     */
    public static University initUniversity() {
        List<Student> students1 = initStudents(
                List.of("Олег", "Іван", "Андрій", "Степан", "Артур"),
                List.of("Шевченко", "Франко", "Сніговик", "Вареник", "Король"));
        Schedule schedule1 = initSchedule(
                List.of(ENGLISH, UKRAINIAN, PROGRAMMING, PROGRAMMING, PHYSICS),
                List.of(MATHEMATICS, LITERATURE, ENGLISH, PROGRAMMING, UKRAINIAN));
        Group group1 = new Group("IT-21", students1, schedule1);

        List<Student> students2 = initStudents(
                List.of("Сергій", "Богдан", "Іван", "Орест", "Макар"),
                List.of("Піп", "Борщ", "Вареник", "Кульчицький", "Лукаш")
        );
        Schedule schedule2 = initSchedule(
                List.of(PROGRAMMING, ENGLISH, PHYSICS, MATHEMATICS, LITERATURE),
                List.of(UKRAINIAN, ENGLISH, PROGRAMMING, UKRAINIAN, PROGRAMMING)
        );
        Group group2 = new Group("IT-22", students2, schedule2);

        return new University(new ArrayList<>(List.of(group1, group2)));
    }
}
