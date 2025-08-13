/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javastreamdemo.model;

import java.time.LocalDate;

/**
 *
 * @author 20113
 */
public class Student {
    private long id;
    private String name;
    private String major; // CNTT, KTPM, KHMT, TKDH
    private double gpa;   // 0.0 - 4.0
    private LocalDate dob;

    public Student() {}

    public Student(long id, String name, String major, double gpa, LocalDate dob) {
        this.id = id; this.name = name; this.major = major; this.gpa = gpa; this.dob = dob;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
}
