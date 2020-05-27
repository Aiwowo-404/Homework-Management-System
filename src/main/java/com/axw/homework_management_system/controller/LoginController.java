package com.axw.homework_management_system.controller;

import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.service.StudentService;
import com.axw.homework_management_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/student/login")
    public String studentlogin(@RequestParam("id") String id,@RequestParam("password") String password, Model model, HttpSession session){
        String sd = studentService.checkStudentLogin(id,password,session);
       if (sd.equals("验证通过")){
           return "redirect:/student/index";
       }else{
           model.addAttribute("msg",sd);
           return "login";
       }
    }

    @RequestMapping("/teacher/login")
    public String teacherlogin(@RequestParam("id") String id,@RequestParam("password") String password, Model model, HttpSession session){
        String sd = teacherService.checkTeacherLogin(id,password,session);
        if (sd.equals("验证通过")){
            return "redirect:/teacher/index";
        }else{
            model.addAttribute("msg",sd);
            return "login";
        }
    }
}
