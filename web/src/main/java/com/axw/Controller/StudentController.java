package com.axw.Controller;

import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;
import com.axw.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/goLoginStudent")
    public String goLoginStudent(HttpServletRequest request){
        Student student=new Student();
        student.setId(request.getParameter("id"));
        student.setPassword(request.getParameter("password"));
        if(studentService.checkStudent(student)) {
            request.getSession().setAttribute(request.getSession().getId() + "student", studentService.getStudentById(student.getId()));
            return "redirect:/student/studenthome";
        }else {
            return "redirect:/login";
        }
    }
    @RequestMapping("studenthome")
    public String studenthome(HttpServletRequest request){
        Student student= (Student) request.getSession().getAttribute(request.getSession().getId()+"student");
        request.setAttribute("homework",studentService.getAllHomeworkByStudent(student));
        return "StudentHome";
    }

    @RequestMapping("/submitHomework")
    @ResponseBody
    public String submitHomework(HttpServletRequest request){
        Student student= (Student) request.getSession().getAttribute(request.getSession().getId()+"student");
        String homework=request.getParameter("homework");
        String tid=request.getParameter("tid");
        String feedback=request.getParameter("feedback");
        Teacher teacher=new Teacher();
        teacher.setId(tid);
        if(studentService.submitHomework(student,homework,feedback,teacher)){
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute(request.getSession().getId()+"student");
        return "true";
    }
}
