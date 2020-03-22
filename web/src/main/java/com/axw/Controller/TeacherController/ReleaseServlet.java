package com.axw.Controller.TeacherController;

import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;
import com.axw.Service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/releaseHomework")
public class ReleaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        List<Student> list=new ArrayList<Student>();
        String[] ids= req.getParameterValues("student");
        Teacher teacher= (Teacher) req.getSession().getAttribute(req.getSession().getId()+"teacher");
        String homework=req.getParameter("homework");
        for (String id:ids) {
            Student student=new Student();
            student.setId(id);
            list.add(student);
        }
        TeacherService teacherService=new TeacherService();
        try {
            teacherService.addHomework(list,homework,teacher);
            resp.sendRedirect("/TeacherHome");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
