package com.axw.homework_management_system.service;

import com.axw.homework_management_system.dao.CourseMapper;
import com.axw.homework_management_system.dao.HomeworkMapper;
import com.axw.homework_management_system.dao.StudentMapper;
import com.axw.homework_management_system.dao.TeacherMapper;
import com.axw.homework_management_system.dto.HomeworkByNameDto;
import com.axw.homework_management_system.entities.Course;
import com.axw.homework_management_system.entities.Homework;
import com.axw.homework_management_system.entities.Student;
import com.axw.homework_management_system.entities.Teacher;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    public String checkTeacherLogin(String id, String password, HttpSession session){
        Teacher teacher = teacherMapper.queryTeacherById(id);
        if (teacher==null){
            return "用户不存在";
        }else if (teacher.getPassword().equals(password)){
            session.setAttribute("teacher",teacher);
            return "验证通过";
        }else{
            return "密码错误";
        }
    }

    public List<Course> queryAllCourse(String teacherid){
        return courseMapper.queryCourseByTeacherId(teacherid);
    }

    public List<Student> queryAllStudentByCourse(String courseid){
        return studentMapper.queryAllStudentByCourseId(courseid);
    }

    public Course queryCourseById(String courseid){
        return courseMapper.queryById(courseid);
    }

    public void addNewHomework(String[] studentids,String courseid,String name,String content){
        for(String studentid:studentids){
            Homework homework = new Homework();
            homework.setId(String.valueOf(System.currentTimeMillis()));
            homework.setName(name);
            homework.setContent(content);
            homework.setStudentid(studentid);
            homework.setCourseid(Integer.parseInt(courseid));
            homework.setStatus(0);
            homeworkMapper.insertNewHomework(homework);
        }
    }

    public List<HomeworkByNameDto> getHomeworkByName(String courseid){
        List<String> names = homeworkMapper.queryAllNameByCourseId(courseid);
        List<HomeworkByNameDto> homeworks =new ArrayList<>();
        for (String name:names){
           HomeworkByNameDto homeworkByNameDto =new HomeworkByNameDto();
           homeworkByNameDto.setName(name);
           int nums = homeworkMapper.queryNumByName(courseid,name);
           homeworkByNameDto.setNums(nums);
           homeworkByNameDto.setCourseid(courseid);
           homeworks.add(homeworkByNameDto);
        }
        return homeworks;
    }
}
