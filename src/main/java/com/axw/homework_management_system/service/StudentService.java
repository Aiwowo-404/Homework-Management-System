package com.axw.homework_management_system.service;

import com.axw.homework_management_system.dao.HomeworkMapper;
import com.axw.homework_management_system.dao.StudentMapper;
import com.axw.homework_management_system.dao.TeacherMapper;
import com.axw.homework_management_system.dto.HomeworkDto;
import com.axw.homework_management_system.dto.PageDto;
import com.axw.homework_management_system.entities.CommonResult;
import com.axw.homework_management_system.entities.Homework;
import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.entities.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private HomeworkMapper homeworkMapper;

    public CommonResult checkStudentLogin(String id, String password, HttpSession session){
        Student student = studentMapper.queryStudentById(id);
        CommonResult commonResult = new CommonResult();
        if (student==null){
            commonResult.setCode(441);
            commonResult.setInfo("用户不存在");
            return commonResult;
        }else if (student.getPassword().equals(password)){
            session.setAttribute("student",student);
            commonResult.setCode(200);
            commonResult.setInfo("验证通过");
            return commonResult;
        }else{
            commonResult.setCode(440);
            commonResult.setInfo("密码错误");
            return commonResult;
        }
    }
    public CommonResult checkStudentRegister(String id, String name, String password, String checkpassword){
        CommonResult commonResult =new CommonResult();
        if(!password.equals(checkpassword)){
            commonResult.setCode(444);
            commonResult.setInfo("两次密码输入不相同");
            return commonResult;
        }
        if(studentMapper.checkStudentId(id)==1){
            commonResult.setCode(443);
            commonResult.setInfo("该学号已存在");
            return commonResult;
        }
        studentMapper.insertNewStudent(id,name,password);
        commonResult.setCode(200);
        commonResult.setInfo("注册成功");
        return  commonResult;
    }

    public CommonResult getHomeworks(String studentid,int page,int size){
        CommonResult commonResult = new CommonResult();
        PageDto pageDto = new PageDto();
        int totalcount = homeworkMapper.totalcountByStudent(studentid);
        int offset = 8 * (page - 1);
        pageDto.setPagination(totalcount,page,size);
        List<Homework> homeworks = homeworkMapper.queryHomeworkByStudentid(studentid,offset,size);
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
    public void submitHomework(String answer,String homeworkid){
        homeworkMapper.updateHomework(answer,homeworkid);
    }
}
