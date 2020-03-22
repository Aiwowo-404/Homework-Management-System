package com.axw.Controller.StudentController;

import com.axw.Pojo.Student;
import com.axw.Service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goLoginStudent")
public class StudentLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student=new Student();
        student.setId(req.getParameter("id"));
        student.setPassword(req.getParameter("password"));
        StudentService studentService=new StudentService();
        try {
            if(studentService.checkStudent(student)){
                req.getSession().setAttribute(req.getSession().getId()+"student",studentService.getStudentById(student.getId()));
                resp.setHeader("content-type","text/html;charset=utf-8");
                resp.getWriter().print("<script language='javascript'>alert('登录成功');window.location.href='StudentHome';</script>");
            }else{
                resp.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
