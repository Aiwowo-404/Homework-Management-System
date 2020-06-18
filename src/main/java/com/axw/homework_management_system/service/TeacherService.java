package com.axw.homework_management_system.service;

import com.axw.homework_management_system.dao.HomeworkMapper;
import com.axw.homework_management_system.dao.StudentMapper;
import com.axw.homework_management_system.dao.TeacherMapper;
import com.axw.homework_management_system.dto.HomeworkDto;
import com.axw.homework_management_system.dto.PageDto;
import com.axw.homework_management_system.entities.*;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private HomeworkMapper homeworkMapper;

    public CommonResult checkTeacherLogin(String id, String password, HttpSession session){
        Teacher teacher = teacherMapper.queryTeacherById(id);
        CommonResult commonResult = new CommonResult();
        if (teacher==null){
            commonResult.setCode(441);
            commonResult.setInfo("用户不存在");
            return commonResult;
        }else if (teacher.getPassword().equals(password)){
            session.setAttribute("teacher",teacher);
            commonResult.setCode(200);
            commonResult.setInfo("验证通过");
            return commonResult;
        }else{
            commonResult.setCode(440);
            commonResult.setInfo("密码错误");
            return commonResult;
        }
    }

    public CommonResult checkTeacherRegister(String id,String name,String password,String checkpassword){
        CommonResult commonResult =new CommonResult();
        if(!password.equals(checkpassword)){
            commonResult.setCode(444);
            commonResult.setInfo("两次密码输入不相同");
            return commonResult;
        }
        if(teacherMapper.checkTeacherId(id)==1){
            commonResult.setCode(443);
            commonResult.setInfo("该教师号已存在");
            return commonResult;
        }
        teacherMapper.insertNewTeacher(id,name,password);
        commonResult.setCode(200);
        commonResult.setInfo("注册成功");
        return  commonResult;
    }

    public CommonResult homeworkCenter(String teacherid,int page,int size){
        CommonResult commonResult = new CommonResult();
        PageDto pageDto = new PageDto();
        int totalcount = homeworkMapper.totalcountByTeacher(teacherid);
        int offset = 8 * (page - 1);
        pageDto.setPagination(totalcount,page,size);
        List<Homework> homeworks = homeworkMapper.queryHomeworkByTeacherid(teacherid,offset,size);
        List<HomeworkDto> homeworkDtos = new ArrayList<>();
        for (Homework homework:homeworks){
            HomeworkDto homeworkDto =new HomeworkDto();
            BeanUtils.copyProperties(homework,homeworkDto);
            Student student = studentMapper.queryStudentById(homework.getStudentid());
            Teacher teacher = teacherMapper.queryTeacherById(homework.getTeacherid());
            homeworkDto.setStudent(student);
            homeworkDto.setTeacher(teacher);
            homeworkDtos.add(homeworkDto);
        }
        pageDto.setData(homeworkDtos);
        commonResult.setData(pageDto);
        commonResult.setCode(200);
        commonResult.setInfo("成功");
        return commonResult;
    }

    public CommonResult getStudents(){
        CommonResult commonResult =new CommonResult();
        List<Student> students = studentMapper.queryAllStudent();
        commonResult.setCode(200);
        commonResult.setInfo("成功");
        commonResult.setData(students);
        return commonResult;
    }

    public CommonResult addNewHomework(String[] studentids,String teacherid,String name,String content){
        CommonResult commonResult =new CommonResult();
        for(String studentid:studentids){
            Homework homework = new Homework();
            homework.setId(String.valueOf(System.currentTimeMillis()));
            homework.setName(name);
            homework.setContent(content);
            homework.setStudentid(studentid);
            homework.setTeacherid(teacherid);
            homework.setStatus(0);
            homeworkMapper.insertNewHomework(homework);
        }
        commonResult.setCode(200);
        commonResult.setInfo("成功");
        return commonResult;
    }

    public CommonResult queryHomeworkById(String homeworkid){
        CommonResult commonResult =new CommonResult();
        HomeworkDto homeworkDto = new HomeworkDto();
        Homework homework = homeworkMapper.queryHomeworkById(homeworkid);
        BeanUtils.copyProperties(homework,homeworkDto);
        Student student = studentMapper.queryStudentById(homework.getStudentid());
        Teacher teacher = teacherMapper.queryTeacherById(homework.getTeacherid());
        homeworkDto.setStudent(student);
        homeworkDto.setTeacher(teacher);
        commonResult.setData(homeworkDto);
        commonResult.setCode(200);
        commonResult.setInfo("成功");
        return commonResult;
    }
}
