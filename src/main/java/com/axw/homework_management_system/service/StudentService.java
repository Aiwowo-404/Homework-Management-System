package com.axw.homework_management_system.service;

import com.axw.homework_management_system.dao.CourseMapper;
import com.axw.homework_management_system.dao.HomeworkMapper;
import com.axw.homework_management_system.dao.StudentMapper;
import com.axw.homework_management_system.entities.Course;
import com.axw.homework_management_system.entities.Homework;
import com.axw.homework_management_system.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    public String checkStudentLogin(String id, String password, HttpSession session){
        Student student = studentMapper.queryStudentById(id);
        if (student==null){
            return "用户不存在";
        }else if (student.getPassword().equals(password)){
            session.setAttribute("student",student);
            return "验证通过";
        }else{
            return "密码错误";
        }
    }

    public List<Course> queryAllCourseById(String studentid){
        return courseMapper.queryCourseById(studentid);
    }

    public List<Homework> queryAllHomework(String studentid,String courseid){
        return homeworkMapper.queryAllHomeworkById(studentid,courseid);
    }

    public Homework queryHomeworkById(String homeworkid){
        return homeworkMapper.queryHomeworkById(homeworkid);
    }
    public void submitHomework(String answer,String homeworkid){
        homeworkMapper.updateHomework(answer,homeworkid);
    }
}
