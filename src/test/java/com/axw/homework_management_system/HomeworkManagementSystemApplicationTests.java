package com.axw.homework_management_system;

import com.axw.homework_management_system.dao.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeworkManagementSystemApplicationTests {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    void contextLoads() {

    }

    @Test
    void mybatistest(){
        System.out.println(studentMapper.queryAllStudent());
    }

}
