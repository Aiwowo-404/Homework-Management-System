package com.axw.homework_management_system.entities;

public class Homework {
    private String id;
    private String name;
    private String content;
    private String resource;
    private int status;
    private String studentid;
    private String teacherid;
    private String answer;

    public Homework() {
    }

    public Homework(String id, String name, String content, String resource, int status, String studentid, String teacherid, String answer) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.resource = resource;
        this.status = status;
        this.studentid = studentid;
        this.teacherid = teacherid;
        this.answer = answer;
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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
