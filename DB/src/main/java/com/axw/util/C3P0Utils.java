package com.axw.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class C3P0Utils {

    //通过标识名来创建相应连接池
    static ComboPooledDataSource dataSource=new ComboPooledDataSource("mysql");
    //从连接池中取用一个连接
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return connection;
    }
    //释放连接回连接池
    public static void close(Connection conn, Statement pst, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
    }
}