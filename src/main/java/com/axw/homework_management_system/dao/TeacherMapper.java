package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.entities.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where id = #{id}")
    Teacher queryTeacherById(String id);

    @Select("select count(*) from teacher where id = #{id}")
    int checkTeacherId(String id);

    @Insert("insert into teacher(id,name,password) values (#{id},#{name},#{password})")
    int insertNewTeacher(@Param("id")String id,@Param("name")String name,@Param("password")String password);
}
