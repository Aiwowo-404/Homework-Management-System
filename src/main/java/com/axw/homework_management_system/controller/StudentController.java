package com.axw.homework_management_system.controller;


import com.axw.homework_management_system.entities.Course;
import com.axw.homework_management_system.entities.Homework;
import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/index")
    public String StudentHome(){
        return "student/index";
    }

    @RequestMapping("/homework")
    public String StudentCourse(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute("student");
        List<Course> courses = studentService.queryAllCourseById(student.getId());
        model.addAttribute("courses",courses);
        return "student/course";
    }

    @RequestMapping("/course/{courseid}")
    public String StudentHomework(@PathVariable("courseid")String courseid,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute("student");
        List<Homework> homeworks = studentService.queryAllHomework(student.getId(),courseid);
        model.addAttribute("homeworks",homeworks);
        return "student/homework";
    }

    @RequestMapping("/course/homework/{homeworkid}")
    public String DoHomework(@PathVariable("homeworkid")String homeworkid,Model model){
        Homework homework = studentService.queryHomeworkById(homeworkid);
        model.addAttribute("homework",homework);
        return "student/commit";
    }
    @PostMapping("/course/{courseid}/dohomework/{homeworkid}")
    public String submitHomework(@PathVariable("courseid")String courseid,@PathVariable("homeworkid")String homeworkid,HttpServletRequest request){
        String answer = request.getParameter("answer");
        studentService.submitHomework(answer,homeworkid);
        return "redirect:/student/course/"+courseid;
    }
    @RequestMapping("/logout")
    public String StudentLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("student");
        return "redirect:/login";
    }
}
