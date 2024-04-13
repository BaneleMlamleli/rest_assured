package com.learn;

import java.util.Arrays;

public class Student {
    String name;
    String location;
    String phone;
    String course[];

    public Student(String name, String location, String phone, String course[]) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.course = course;
    }

    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getCourse() {
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student [name = " + name + ", location = " + location + ", phone = " + phone + ", course = "
                + Arrays.toString(course) + "]";
    }
}
