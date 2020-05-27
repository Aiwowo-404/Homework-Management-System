package com.axw.homework_management_system.dto;

public class HomeworkByNameDto {
    private String name;
    private int nums;
    private String courseid;

    public HomeworkByNameDto() {
    }

    public HomeworkByNameDto(String name, int nums, String courseid) {
        this.name = name;
        this.nums = nums;
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
}
