package com.axw.Dao;

import com.axw.Pojo.Student;
import com.axw.util.C3P0Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<Student> getAllStudent() throws SQLException {
        Connection connection= C3P0Utils.getConnection();
        Statement statement=connection.createStatement();
        String sql="select * from axw.student";
        ResultSet resultSet=statement.executeQuery(sql);
        List<Student> list=new ArrayList<Student>();
        while(resultSet.next()){
            Student student=new Student();
            student.setId(resultSet.getString("id"));
            student.setName(resultSet.getString("name"));
            student.setPassword(resultSet.getString("password"));
            list.add(student);
        }
        C3P0Utils.close(connection,statement,resultSet);
        return list;
    }

    public Student getStudentByID(String id) throws SQLException {
        Connection connection= C3P0Utils.getConnection();
        String sql="select * from axw.student where id = '"+id+"';";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        Student student=new Student();
        if(resultSet.next()){
            student.setId(resultSet.getString("id"));
            student.setName(resultSet.getString("name"));
            student.setPassword(resultSet.getString("password"));
        }
        C3P0Utils.close(connection,statement,resultSet);
        return student;
    }
}
