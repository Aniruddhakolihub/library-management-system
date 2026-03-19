package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    
    private String password;

    private String course;

    private String branch;

    private String semester;

    private int booksTaken;

    public Student(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName){
        this.studentName=studentName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course=course;
    }

    public String getBranch(){
        return branch;
    }

    public void setBranch(String branch){
        this.branch=branch;
    }

    public String getSemester(){
        return semester;
    }

    public void setSemester(String semester){
        this.semester=semester;
    }

    public int getBooksTaken(){
        return booksTaken;
    }

    public void setBooksTaken(int booksTaken){
        this.booksTaken=booksTaken;
    }

}