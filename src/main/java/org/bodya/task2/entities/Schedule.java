package org.bodya.task2.entities;

import org.bodya.task2.enums.Subject;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Schedule {

    private Map<LocalDate, List<Subject>> schedule;

    public Schedule() {
    }

    public Schedule(Map<LocalDate, List<Subject>> schedule) {
        this.schedule = schedule;
    }

    public Map<LocalDate, List<Subject>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<LocalDate, List<Subject>> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule1 = (Schedule) o;
        return Objects.equals(schedule, schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }

    @Override
    public String toString() {
        return "\n\t\t\t\tSchedule{" +
                "\n\t\t\t\t\tschedule=" + schedule +
                '}';
    }
}
