package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.entities.Homework;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    @Select("select * from homework where id = #{id}")
    Homework queryHomeworkById(String id);

    @Update("update homework set status = 1,answer = #{answer} where id = #{homeworkid}")
    void updateHomework(@Param("answer")String answer,@Param("homeworkid")String homeworkid);

    @Insert("insert into homework(id,name,content,status,studentid,teacherid) value (#{id},#{name},#{content},#{status},#{studentid},#{teacherid})")
    void insertNewHomework(Homework homework);

    @Select("select * from homework where teacherid = #{teacherid} order by id desc limit #{offset},#{size}")
    List<Homework> queryHomeworkByTeacherid(@Param("teacherid")String teacherid,@Param("offset")int offset,@Param("size")int size);

    @Select("select count(*) from homework where teacherid = #{teacherid}")
    int totalcountByTeacher(String teacherid);

    @Select("select * from homework where studentid = #{studentid} order by id desc limit #{offset},#{size}")
    List<Homework> queryHomeworkByStudentid(@Param("studentid")String studentid,@Param("offset")int offset,@Param("size")int size);

    @Select("select count(*) from homework where studentid = #{studentid}")
    int totalcountByStudent(String studentid);
}
