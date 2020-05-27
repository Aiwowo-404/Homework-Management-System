package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.entities.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select course.id,course.name from course,student_course where course.id = courseid and studentid = #{studentid}")
    List<Course> queryCourseById(String studentid);

    @Select("select course.id,course.name from teacher_course,course where teacherid = #{teacherid} and courseid = course.id")
    List<Course> queryCourseByTeacherId(String teacherid);

    @Select("select * from course where id =#{courseid}")
    Course queryById(String courseid);
}
