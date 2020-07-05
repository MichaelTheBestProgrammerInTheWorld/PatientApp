package com.michaelmagdy.patientapp;

public class PatientModel {

    String fullname, email;
    char gender;
    int age;

    public PatientModel(String fullname, String email, char gender, int age) {
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
