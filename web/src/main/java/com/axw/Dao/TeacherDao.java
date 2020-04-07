package com.axw.Dao;

import com.axw.Pojo.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherDao {
    Teacher getTeacherById(@Param("tid") String tid);
}
