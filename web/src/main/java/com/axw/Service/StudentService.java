package com.axw.Service;

import com.axw.Dao.HomeworkDao;
import com.axw.Pojo.Teacher;
import com.axw.Dao.StudentDao;
import com.axw.Pojo.Student;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class StudentService {
    private StudentDao studentDao=new StudentDao();
    private HomeworkDao homeworkDao=new HomeworkDao();
    public Boolean checkStudent(Student student) throws SQLException {
        Student realstudent=studentDao.getStudentByID(student.getId());
        if(realstudent==null){
            return false;
        }else if(realstudent.getPassword().equals(student.getPassword())){
            return true;
        }else{
            return false;
        }
    }
    public Student getStudentById(String id) throws SQLException {
        return studentDao.getStudentByID(id);
    }
    public List<HashMap<String,String>> getAllHomeworkByStudent(Student student) throws SQLException {
        return homeworkDao.getAllHomeworkByStudent(student);
    }
    public boolean submitHomework(Student student, String homework,String feedback, Teacher teacher) throws SQLException {
        if(homeworkDao.SubmitHomework(student,homework,feedback,teacher)==1){
            return true;
        }else{
            return false;
        }
    }
}
