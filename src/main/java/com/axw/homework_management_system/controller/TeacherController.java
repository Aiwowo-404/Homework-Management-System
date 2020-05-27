package com.axw.homework_management_system.controller;

import com.axw.homework_management_system.dto.HomeworkByNameDto;
import com.axw.homework_management_system.entities.Course;
import com.axw.homework_management_system.entities.Homework;
import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.entities.Teacher;
import com.axw.homework_management_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/index")
    public String index(){
        return "teacher/index";
    }

    @RequestMapping("/course")
    public String course(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Course> courses = teacherService.queryAllCourse(teacher.getId());
        model.addAttribute("courses",courses);
        return "teacher/course";
    }

    @RequestMapping("/logout")
    public String StudentLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("teacher");
        return "redirect:/login";
    }

    @GetMapping("/course/{courseid}")
    public String gopublish(@PathVariable("courseid")String courseid,Model model){
        List<Student> students = teacherService.queryAllStudentByCourse(courseid);
        Course course =teacherService.queryCourseById(courseid);
        List<HomeworkByNameDto> homeworkByNameDtos = teacherService.getHomeworkByName(courseid);
        model.addAttribute("homeworks",homeworkByNameDtos);
        model.addAttribute("students",students);
        model.addAttribute("course",course);
        return "teacher/publish";
    }

    @PostMapping("/course/{courseid}")
    public String publish(@PathVariable("courseid")String courseid, HttpServletRequest request){
        String students = request.getParameter("students");
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        String[] studentids = students.split(",");
        teacherService.addNewHomework(studentids,courseid,name,content);
        return "redirect:/teacher/course/"+courseid;
    }
}
