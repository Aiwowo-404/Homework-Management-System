package com.axw.Controller;

import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;
import com.axw.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/goLoginTeacher")
    public String goLoginTeacher(HttpServletRequest request){
        Teacher teacher=new Teacher();
        teacher.setId(request.getParameter("id"));
        teacher.setPassword(request.getParameter("password"));
        if(teacherService.CheckTeacher(teacher)) {
            request.getSession().setAttribute(request.getSession().getId() + "teacher", teacherService.getTeacherById(teacher.getId()));
            return "redirect:/teacher/teacherhome";
        }else{
            return "redirect:/login";
        }
    }

    @RequestMapping("/teacherhome")
    public String teacherhome(HttpServletRequest request){
        Teacher teacher= (Teacher) request.getSession().getAttribute(request.getSession().getId()+"teacher");
        request.setAttribute("students",teacherService.getAllStudent());
        request.setAttribute("homework",teacherService.getAllHomeworkByTeacher(teacher));
        return "TeacherHome";
    }

    @RequestMapping("/releaseHomework")
    @ResponseBody
    public String releaseHomework(HttpServletRequest request) throws UnsupportedEncodingException {
        List<Student> list=new ArrayList<Student>();
        String[] ids= request.getParameterValues("student");
        Teacher teacher= (Teacher) request.getSession().getAttribute(request.getSession().getId()+"teacher");
        String homework=request.getParameter("homework");
        for (String id:ids) {
            Student student=new Student();
            student.setId(id);
            list.add(student);
        }
        if(teacherService.addHomework(list,homework,teacher)){
            return "true";
        }else {
            return "false";
        }
    }
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute(request.getSession().getId()+"teacher");
        return "true";
    }
}
