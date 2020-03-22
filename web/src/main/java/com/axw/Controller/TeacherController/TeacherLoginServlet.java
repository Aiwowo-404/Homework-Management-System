package com.axw.Controller.TeacherController;


import com.axw.Pojo.Teacher;
import com.axw.Service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goLoginTeacher")
public class TeacherLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher=new Teacher();
        teacher.setId(req.getParameter("id"));
        teacher.setPassword(req.getParameter("password"));

        TeacherService teacherService=new TeacherService();
        try {
            if(teacherService.CheckTeacher(teacher)){
                req.getSession().setAttribute(req.getSession().getId()+"teacher",teacherService.getTeacherById(teacher.getId()));
                resp.setHeader("content-type","text/html;charset=utf-8");
                resp.getWriter().print("<script language='javascript'>alert('登录成功');window.location.href='TeacherHome';</script>");
            }else{
                resp.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
