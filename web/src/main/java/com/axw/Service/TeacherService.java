package com.axw.Service;

import com.axw.Dao.HomeworkDao;
import com.axw.Dao.StudentDao;
import com.axw.Dao.TeacherDao;
import com.axw.Pojo.Student;
import com.axw.Pojo.Teacher;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TeacherService {
    private TeacherDao teacherDao=new TeacherDao();
    private StudentDao studentDao=new StudentDao();
    private HomeworkDao homeworkDao=new HomeworkDao();
    public Boolean CheckTeacher(Teacher teacher) throws SQLException {
        Teacher realteacher=teacherDao.getTeacherByID(teacher.getId());
        if(realteacher==null){
            return false;
        }else if (teacher.getPassword().equals(realteacher.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    public Teacher getTeacherById(String id) throws SQLException {
        return teacherDao.getTeacherByID(id);
    }
    public List<Student> getAllStudent() throws SQLException {
        return studentDao.getAllStudent();
    }
    public boolean addHomework(List<Student> students,String homework,Teacher teacher) throws SQLException {
        Boolean flag=false;
        for (Student student: students) {
            if(homeworkDao.addHomework(student,homework,teacher)==1){
                flag=true;
            }else {
                flag=false;
                break;
            }
        }
        return flag;
    }
    public List<HashMap<String,String>> getAllHomeworkByTeacher(Teacher teacher) throws SQLException {
        return homeworkDao.getAllHomeworkByTeacher(teacher);
    }
}
