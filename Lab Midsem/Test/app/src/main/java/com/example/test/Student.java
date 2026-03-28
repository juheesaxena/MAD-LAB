package com.example.test;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String gender;
    private String course;
    private String skills;
    private String experience;
    private String date;

    public Student(String name, String id, String gender, String course, String skills, String experience, String date) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.course = course;
        this.skills = skills;
        this.experience = experience;
        this.date = date;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getGender() { return gender; }
    public String getCourse() { return course; }
    public String getSkills() { return skills; }
    public String getExperience() { return experience; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return name; // This is what the ArrayAdapter will display in the ListView
    }
}
