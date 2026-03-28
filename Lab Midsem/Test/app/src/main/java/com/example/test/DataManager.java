package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    // Static list to store Student objects across activities
    public static ArrayList<Student> studentList = new ArrayList<>();
    
    // Changed to ArrayList to allow adding new courses
    public static ArrayList<String> courses = new ArrayList<>(Arrays.asList("CSE", "IT", "ECE", "MECH", "AI", "DS"));

    // Helper method to get students registered for a specific course
    public static ArrayList<Student> getStudentsForCourse(String courseName) {
        ArrayList<Student> filteredList = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getCourse().equalsIgnoreCase(courseName)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }
}
