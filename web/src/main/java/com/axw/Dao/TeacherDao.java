package com.axw.Dao;

import com.axw.Pojo.Teacher;
import com.axw.util.C3P0Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherDao {
    public Teacher getTeacherByID(String id) throws SQLException {
        Connection connection= C3P0Utils.getConnection();
        String sql="select * from axw.teacher where id = '"+id+"';";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        Teacher teacher=new Teacher();
       if(resultSet.next()){
            teacher.setId(resultSet.getString("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setPassword(resultSet.getString("password"));
        }
        C3P0Utils.close(connection,statement,resultSet);
        return teacher;
    }
}
