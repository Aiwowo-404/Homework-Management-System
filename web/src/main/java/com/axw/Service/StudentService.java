package com.axw.Service;

import com.axw.Dao.HomeworkDao;
import com.axw.Dao.StudentDao;
import com.axw.Pojo.Teacher;
import com.axw.Pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private HomeworkDao homeworkDao;

    public Boolean checkStudent(Student student){
        Student realstudent=studentDao.getStudentById(student.getId());
        if(realstudent==null){
            return false;
        }else if(realstudent.getPassword().equals(student.getPassword())){
            return true;
        }else{
            return false;
        }
    }
    public Student getStudentById(String id){
        return studentDao.getStudentById(id);
    }
    public List<HashMap<String,String>> getAllHomeworkByStudent(Student student){
        return homeworkDao.getAllHomeworkByStudent(student.getId());
    }
    public boolean submitHomework(Student student, String homework,String feedback, Teacher teacher){
        if(homeworkDao.submitHomework(feedback,teacher.getId(),student.getId(),homework,"已提交")==1){
            return true;
        }else{
            return false;
        }
    }
}
