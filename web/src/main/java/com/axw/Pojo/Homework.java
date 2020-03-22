package com.axw.Pojo;

public class Homework {
    private String sid;
    private String tid;
    private String Homework;
    private String feedback;

    public Homework(String sid, String tid, String homework, String feedback) {
        this.sid = sid;
        this.tid = tid;
        Homework = homework;
        this.feedback = feedback;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getHomework() {
        return Homework;
    }

    public void setHomework(String homework) {
        Homework = homework;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "sid='" + sid + '\'' +
                ", tid='" + tid + '\'' +
                ", Homework='" + Homework + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
