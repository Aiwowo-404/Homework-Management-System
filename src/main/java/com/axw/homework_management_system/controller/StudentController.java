package com.axw.homework_management_system.controller;


import com.axw.homework_management_system.entities.CommonResult;
import com.axw.homework_management_system.entities.Homework;
import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/homework")
    public String StudentHomework(@RequestParam(name = "page",defaultValue = "1")int page,
                                  @RequestParam(name = "size",defaultValue = "8")int size,
                                  Model model,
                                  HttpServletRequest request){
        HttpSession session =request.getSession();
        Student student = (Student) session.getAttribute("student");
        CommonResult commonResult = studentService.getHomeworks(student.getId(),page,size);
        model.addAttribute("homework",commonResult);
        return "student/homework";
    }


    @RequestMapping("homework/{homeworkid}")
    public String Homework(@PathVariable("homeworkid")String homeworkid,Model model){
        CommonResult commonResult = studentService.queryHomeworkById(homeworkid);
        model.addAttribute("homework",commonResult);
        return "student/commit";
    }
    @PostMapping("/dohomework/{homeworkid}")
    public String submitHomework(@PathVariable("homeworkid")String homeworkid,
                                 @RequestParam("anwser")String anwser,
                                 HttpServletRequest request){
        studentService.submitHomework(anwser,homeworkid);
        return "redirect:/student/homework";
    }
    @RequestMapping("/logout")
    public String StudentLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("student");
        return "redirect:/login";
    }
}
