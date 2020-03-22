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

@WebServlet("/TeacherHome")
public class TeacherHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherService teacherService=new TeacherService();
        Teacher teacher= (Teacher) req.getSession().getAttribute(req.getSession().getId()+"teacher");
        try {
            req.setAttribute("students",teacherService.getAllStudent());
            req.setAttribute("homework",teacherService.getAllHomeworkByTeacher(teacher));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/jsp/TeacherHome.jsp").forward(req,resp);
    }
}
