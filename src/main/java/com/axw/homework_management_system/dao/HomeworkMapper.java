package com.axw.homework_management_system.dao;

import com.axw.homework_management_system.dto.HomeworkByNameDto;
import com.axw.homework_management_system.entities.Homework;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeworkMapper {
    @Select("select * from homework where studentid = #{studentid} and courseid = #{courseid}")
    List<Homework> queryAllHomeworkById(@Param("studentid")String studentid,@Param("courseid")String courseid);

    @Select("select * from homework where id = #{id}")
    Homework queryHomeworkById(String id);

    @Update("update homework set status = 1,answer = #{answer} where id = #{homeworkid}")
    void updateHomework(@Param("answer")String answer,@Param("homeworkid")String homeworkid);

    @Insert("insert into homework(id,name,content,status,studentid,courseid) value (#{id},#{name},#{content},#{status},#{studentid},#{courseid})")
    void insertNewHomework(Homework homework);

    @Select("select name from homework where courseid = #{courseid} group by name")
    List<String> queryAllNameByCourseId(String courseid);

    @Select("select count(*) from homework where courseid = #{courseid} group by #{name}")
    int queryNumByName(@Param("courseid")String courseid,@Param("name") String name);
}
