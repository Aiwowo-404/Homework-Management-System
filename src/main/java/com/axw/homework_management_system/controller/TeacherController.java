package com.axw.homework_management_system.controller;

import com.axw.homework_management_system.entities.*;
import com.axw.homework_management_system.service.TeacherService;
import com.sun.org.apache.regexp.internal.RE;
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

    @GetMapping("/index")
    public String index(){
        return "teacher/index";
    }


    @GetMapping("/logout")
    public String TeacherLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("teacher");
        return "redirect:/login";
    }

    @GetMapping("/homework")
    public String TeacherHomework(@RequestParam(name = "page",defaultValue = "1")int page,
                                  @RequestParam(name = "size",defaultValue = "8")int size,
                                  Model model,
                                  HttpServletRequest request){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        CommonResult commonResult = teacherService.homeworkCenter(teacher.getId(),page,size);
        model.addAttribute("homework",commonResult);
        return "teacher/homework";
    }

    @GetMapping("/publish")
    public String PublishHomework(Model model){
        CommonResult commonResult = teacherService.getStudents();
        model.addAttribute("students",commonResult);
        return "teacher/publish";
    }

    @PostMapping("/releasehomework")
    @ResponseBody
    public CommonResult releaseHomework(@RequestParam("name")String name,
                                  @RequestParam("content")String content,
                                  @RequestParam("students")String students,HttpServletRequest request){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String[] studentids = students.split(",");
        CommonResult commonResult = teacherService.addNewHomework(studentids,teacher.getId(),name,content);
        return commonResult;
    }

    @GetMapping("/preview/{homeworkid}")
    public String previewHomework(@PathVariable("homeworkid")String homeworkid,Model model){
        CommonResult commonResult = teacherService.queryHomeworkById(homeworkid);
        model.addAttribute("homework",commonResult);
        return "teacher/preview";
    }
}
