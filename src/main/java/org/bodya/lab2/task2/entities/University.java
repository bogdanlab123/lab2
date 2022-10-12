package org.bodya.lab2.task2.entities;

import java.util.List;
import java.util.Objects;

public class University {

    private List<Group> groups;

    public University() {
    }

    public University(List<Group> groupList) {
        this.groups = groupList;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groups);
    }

    @Override
    public String toString() {
        return "University{" +
                "\n\t" + groups +
                "\n}";
    }
}
