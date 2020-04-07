package com.axw.Dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface HomeworkDao {
    int addHomework(@Param("tid") String tid, @Param("sid") String sid, @Param("homework") String homework, @Param("flag") String flag);
    List<HashMap<String,String>> getAllHomeworkByTeacher(@Param("tid") String tid);
    List<HashMap<String,String>> getAllHomeworkByStudent(@Param("sid") String sid);
    int submitHomework(@Param("feedback") String feedback,@Param("tid") String tid,@Param("sid") String sid,@Param("homework") String homework,@Param("flag") String flag);
}
