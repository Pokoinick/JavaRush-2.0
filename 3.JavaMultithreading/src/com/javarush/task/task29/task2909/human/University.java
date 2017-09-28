package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University  {

    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age){
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double grade) {
        //TODO:
        for (Student student : students) {
            if(student.getAverageGrade() == grade)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Double grade = 0.;
        for (Student student: students) {
            if(student.getAverageGrade() > grade) {
                grade = student.getAverageGrade();
            }
        }
        //TODO:
        return getStudentWithAverageGrade(grade);
    }

    public Student getStudentWithMinAverageGrade() {
        Double grade = 0.;
        for (Student student: students) {
            if(student.getAverageGrade() < grade || grade == 0.) {
                grade = student.getAverageGrade();
            }
        }
        //TODO:
        return getStudentWithAverageGrade(grade);
    }
    public void expel(Student student) {
        students.remove(student);

        //TODO:
    }
}