package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.entities.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student")
    List<Student> queryAllStudent();

    @Select("select * from student where id = #{id}")
    Student queryStudentById(String id);

    @Select("select student.id,student.name from student,student_course where studentid = student.id and courseid = #{courseid}")
    List<Student> queryAllStudentByCourseId(String courseid);
}
