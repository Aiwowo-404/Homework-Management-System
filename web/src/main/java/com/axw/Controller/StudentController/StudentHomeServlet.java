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

@WebServlet("/StudentHome")
public class StudentHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService=new StudentService();
        Student student= (Student) req.getSession().getAttribute(req.getSession().getId()+"student");
        try {
            req.setAttribute("homework",studentService.getAllHomeworkByStudent(student));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/jsp/StudentHome.jsp").forward(req,resp);
    }
}

