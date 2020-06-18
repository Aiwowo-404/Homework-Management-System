package com.axw.homework_management_system.dto;

import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.entities.Teacher;

public class HomeworkDto {
    private String id;
    private String name;
    private String content;
    private String resource;
    private int status;
    private String answer;
    private Student student;
    private Teacher teacher;

    public HomeworkDto() {
    }

    public HomeworkDto(String id, String name, String content, String resource, int status, String answer, Student student, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.resource = resource;
        this.status = status;
        this.answer = answer;
        this.student = student;
        this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
