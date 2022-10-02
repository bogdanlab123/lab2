package org.bodya.task2.entities;

import java.util.List;
import java.util.Objects;

public class Group {

    private String groupName;
    private List<Student> students;
    private Schedule groupSchedule;

    public Group() {
    }

    public Group(String groupName, List<Student> students, Schedule groupSchedule) {
        this.groupName = groupName;
        this.students = students;
        this.groupSchedule = groupSchedule;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(students, group.students) && Objects.equals(groupSchedule, group.groupSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students, groupSchedule);
    }

    @Override
    public String toString() {
        return "\n\t\tGroup{" +
                "\n\t\t\tgroupName='" + groupName + '\'' +
                "\n\t\t\tstudents=" + students +
                "\n\t\t\tgroupSchedule=" + groupSchedule +
                "}";
    }
}
