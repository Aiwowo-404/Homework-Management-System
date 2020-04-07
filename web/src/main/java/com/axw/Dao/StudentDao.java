package com.axw.Dao;

import com.axw.Pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudent();
    Student getStudentById(@Param("sid") String sid);
}
