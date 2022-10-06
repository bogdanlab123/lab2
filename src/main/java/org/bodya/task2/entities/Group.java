package org.bodya.task2.entities;

import org.bodya.task2.enums.Subject;
import org.bodya.task4.MarksJournal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Group {

    private String groupName;
    private List<Student> students;
    private Schedule groupSchedule;
    // Для спрощення журналу відвідувань використовуєтья наступе:
    // Група має Map де кожен студент має Map <Предмет,Boolean>,
    // де Boolean - true/false (відвідує, не відвідує)
    private Map<Student, Map<Subject, Boolean>> attendJournal;
    private MarksJournal marksJournal;

    public Group() {
    }

    public Group(String groupName, List<Student> students, Schedule groupSchedule, Map<Student, Map<Subject, Boolean>> attendJournal, MarksJournal marksJournal) {
        this.groupName = groupName;
        this.students = students;
        this.groupSchedule = groupSchedule;
        this.attendJournal = attendJournal;
        this.marksJournal = marksJournal;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Schedule getGroupSchedule() {
        return groupSchedule;
    }

    public void setGroupSchedule(Schedule groupSchedule) {
        this.groupSchedule = groupSchedule;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Map<Student, Map<Subject, Boolean>> getAttendJournal() {
        return attendJournal;
    }

    public void setAttendJournal(Map<Student, Map<Subject, Boolean>> attendJournal) {
        this.attendJournal = attendJournal;
    }

    public MarksJournal getMarksJournal() {
        return marksJournal;
    }

    public void setMarksJournal(MarksJournal marksJournal) {
        this.marksJournal = marksJournal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(students, group.students) && Objects.equals(groupSchedule, group.groupSchedule) && Objects.equals(attendJournal, group.attendJournal) && Objects.equals(marksJournal, group.marksJournal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students, groupSchedule, attendJournal, marksJournal);
    }

    @Override
    public String toString() {
        return "Group{" +
                "\n\tgroupName='" + groupName + '\'' +
                "\n\tstudents=\n\t\t" + students +
                "\n\tgroupSchedule=\n\t\t" + groupSchedule +
                "\n\tattendBook=\n\t\t" + attendJournal +
                "\n\tmarksJournal=\n\t\t" + marksJournal +
                "\n}";
    }
}
