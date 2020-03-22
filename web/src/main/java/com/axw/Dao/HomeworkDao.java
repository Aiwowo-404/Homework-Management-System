package com.axw.Dao;

import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;
import com.axw.util.C3P0Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeworkDao {
    public int addHomework(Student student, String homework, Teacher teacher) throws SQLException {
        Connection connection= C3P0Utils.getConnection();
        Statement statement=connection.createStatement();
        String sql="insert into axw.homework (tid,sid,homework,flag) values ('"+teacher.getId()+"','"+student.getId()+"','"+homework+"','未提交');";
        int num=statement.executeUpdate(sql);
        C3P0Utils.close(connection,statement,null);
        return num;
    }
    public List<HashMap<String,String>> getAllHomeworkByTeacher(Teacher teacher) throws SQLException {
        List<HashMap<String,String>> homework=new ArrayList<HashMap<String, String>>();
        Connection connection=C3P0Utils.getConnection();
        Statement statement=connection.createStatement();
        String sql="select student.name as sname,homework,flag,feedback from axw.student,axw.homework where tid='"+teacher.getId()+"' and sid = student.id";
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            HashMap<String,String> hashMap=new HashMap<String, String>();
            hashMap.put("sname",resultSet.getString("sname"));
            hashMap.put("flag",resultSet.getString("flag"));
            hashMap.put("homework",resultSet.getString("homework"));
            hashMap.put("feedback",resultSet.getString("feedback"));
            homework.add(hashMap);
        }
        C3P0Utils.close(connection,statement,resultSet);
        return homework;
    }
    public List<HashMap<String,String>> getAllHomeworkByStudent(Student student) throws SQLException {
        List<HashMap<String,String>> homework=new ArrayList<HashMap<String, String>>();
        Connection connection=C3P0Utils.getConnection();
        Statement statement=connection.createStatement();
        String sql="select teacher.name as tname,teacher.id as tid,homework,flag from axw.teacher,axw.homework where sid='"+student.getId()+"' and tid = teacher.id";
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            HashMap<String,String> hashMap=new HashMap<String, String>();
            hashMap.put("tname",resultSet.getString("tname"));
            hashMap.put("flag",resultSet.getString("flag"));
            hashMap.put("homework",resultSet.getString("homework"));
            hashMap.put("tid",resultSet.getString("tid"));
            hashMap.put("sid",student.getId());
            homework.add(hashMap);
        }
        C3P0Utils.close(connection,statement,resultSet);
        return homework;
    }
    public int SubmitHomework(Student student,String homework,String feedback,Teacher teacher) throws SQLException {
        Connection connection= C3P0Utils.getConnection();
        Statement statement=connection.createStatement();
        String sql="update axw.homework set feedback = '"+feedback+"',flag = '已提交' where sid ='"+student.getId()+"' and tid ='"+teacher.getId()+"' and homework = '"+homework+"';";
        int num=statement.executeUpdate(sql);
        C3P0Utils.close(connection,statement,null);
        return num;
    }
}
