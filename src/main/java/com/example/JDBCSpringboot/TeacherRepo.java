package com.example.JDBCSpringboot;

import com.example.JDBCSpringboot.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepo {


    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Teacher teacher){
        String sql = "INSERT INTO teacher (id,name,teach) VALUES (?,?,?)";

        //added
//        System.out.println(teacher.getId() + " " + teacher.getName()
//        + " " + teacher.getTeach());
  int rows =      jdbcTemplate.update(sql,teacher.getId(),teacher.getName(),teacher.getTeach());

        System.out.println(rows + "row/s affected");

    }

    public List<Teacher> getAll(){

        String sql = "select * from teacher";

        // method 1:::
        /*RowMapper<Teacher> teacherRowMapper = new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTeach(rs.getString(3));
                return teacher;
            }
        };
        List<Teacher> teachers = jdbcTemplate.query(sql,teacherRowMapper);
        return teachers;
    }*/
        List<Teacher> teachers = jdbcTemplate.query(sql,(rs,row) -> {

            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt(1));
            teacher.setName(rs.getString(2));
            teacher.setTeach(rs.getString(3));
            return teacher;

        }) ;
//        List<Teacher> teachers = jdbcTemplate.query(sql,teacherRowMapper);
        return teachers;
    }
}
