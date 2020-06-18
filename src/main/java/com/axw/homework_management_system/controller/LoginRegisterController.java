package com.axw.homework_management_system.controller;

import com.axw.homework_management_system.entities.CommonResult;
import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.service.StudentService;
import com.axw.homework_management_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginRegisterController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/student/login")
    public String studentlogin(@RequestParam("id") String id,@RequestParam("password") String password, Model model, HttpSession session){
        CommonResult commonResult = studentService.checkStudentLogin(id,password,session);
       if (commonResult.getCode()==200){
           return "redirect:/student/index";
       }else{
           model.addAttribute("commonResult",commonResult);
           return "login";
       }
    }
    @PostMapping("/student/register")
    public String studentregister(@RequestParam("id")String id,
                                  @RequestParam("name")String name,
                                  @RequestParam("password")String password,
                                  @RequestParam("checkpassword")String checkpassword,Model model){
        CommonResult commonResult = studentService.checkStudentRegister(id, name, password, checkpassword);
        if (commonResult.getCode()==200){
            return "redirect:/login";
        }else{
            model.addAttribute("commonResult",commonResult);
            return "register";
        }
    }

    @PostMapping("/teacher/login")
    public String teacherlogin(@RequestParam("id") String id,@RequestParam("password") String password, Model model, HttpSession session){
        CommonResult commonResult = teacherService.checkTeacherLogin(id,password,session);
        if (commonResult.getCode()==200){
            return "redirect:/teacher/index";
        }else{
            model.addAttribute("commonResult",commonResult);
            return "login";
        }
    }

    @PostMapping("/teacher/register")
    public String teacherregister(@RequestParam("id")String id,
                                  @RequestParam("name")String name,
                                  @RequestParam("password")String password,
                                  @RequestParam("checkpassword")String checkpassword,Model model){
        CommonResult commonResult = teacherService.checkTeacherRegister(id, name, password, checkpassword);
        if (commonResult.getCode()==200){
            return "redirect:/login";
        }else{
            model.addAttribute("commonResult",commonResult);
            return "register";
        }
    }

}
