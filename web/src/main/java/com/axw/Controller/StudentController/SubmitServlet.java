package com.axw.Controller.StudentController;

import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;
import com.axw.Service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/submitHomework")
public class SubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String tid=req.getParameter("tid");
        String sid=req.getParameter("sid");
        String feedback=req.getParameter("feedback");
        String homework=req.getParameter("homework");
        Student student=new Student();
        student.setId(sid);
        Teacher teacher=new Teacher();
        teacher.setId(tid);
        StudentService studentService=new StudentService();
        try {
            if(studentService.submitHomework(student,homework,feedback,teacher)){
                resp.sendRedirect("/StudentHome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
