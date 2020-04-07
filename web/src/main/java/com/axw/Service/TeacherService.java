package com.axw.Service;

import com.axw.Dao.HomeworkDao;
import com.axw.Dao.StudentDao;
import com.axw.Dao.TeacherDao;
import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private HomeworkDao homeworkDao;
    public Boolean CheckTeacher(Teacher teacher){
        Teacher realteacher=teacherDao.getTeacherById(teacher.getId());
        if(realteacher==null){
            return false;
        }else if (teacher.getPassword().equals(realteacher.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    public Teacher getTeacherById(String id){
        return teacherDao.getTeacherById(id);
    }
    public List<Student> getAllStudent(){
        return studentDao.getAllStudent();
    }
    public boolean addHomework(List<Student> students,String homework,Teacher teacher){
        Boolean flag=false;
        for (Student student: students) {
            if(homeworkDao.addHomework(teacher.getId(),student.getId(),homework,"未提交")==1){
                flag=true;
            }else {
                flag=false;
                break;
            }
        }
        return flag;
    }
    public List<HashMap<String,String>> getAllHomeworkByTeacher(Teacher teacher){
        return homeworkDao.getAllHomeworkByTeacher(teacher.getId());
    }
}
