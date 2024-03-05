package com.example.JDBCSpringboot;

import com.example.JDBCSpringboot.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcSpringbootApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(JdbcSpringbootApplication.class, args);
        Teacher teacher = context.getBean(Teacher.class);

        teacher.setId(1000);
        teacher.setName("milad");
        teacher.setTeach("javaBook");
        TeacherRepo teacherRepo = context.getBean(TeacherRepo.class);
        teacherRepo.save(teacher);
        System.out.println(teacherRepo.getAll());

    }
}
