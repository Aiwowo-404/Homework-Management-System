package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.entities.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where id = #{id}")
    Teacher queryTeacherById(String id);
}
