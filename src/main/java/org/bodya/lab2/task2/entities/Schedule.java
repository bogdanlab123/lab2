package org.bodya.lab2.task2.entities;

import org.bodya.lab2.task2.enums.Subject;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Schedule {

    private Map<LocalDate, List<Subject>> scheduleMap;

    public Schedule() {
    }

    public Schedule(Map<LocalDate, List<Subject>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public Map<LocalDate, List<Subject>> getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap(Map<LocalDate, List<Subject>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule1 = (Schedule) o;
        return Objects.equals(scheduleMap, schedule1.scheduleMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleMap);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "\n\tscheduleMap=" + scheduleMap +
                '}';
    }
}
