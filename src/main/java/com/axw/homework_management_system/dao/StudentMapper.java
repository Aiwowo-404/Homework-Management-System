package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.entities.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student")
    List<Student> queryAllStudent();

    @Select("select * from student where id = #{id}")
    Student queryStudentById(String id);

    @Select("select count(*) from student where id = #{id}")
    int checkStudentId(String id);

    @Insert("insert into student(id,name,password) values (#{id},#{name},#{password})")
    int insertNewStudent(@Param("id")String id, @Param("name")String name, @Param("password")String password);
}
