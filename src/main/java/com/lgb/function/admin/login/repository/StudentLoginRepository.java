package com.lgb.function.admin.login.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.login.StudentCourse;
import com.lgb.function.admin.login.StudentUser;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentLoginRepository implements StudentLoginRepositoryI {
    @Autowired
    private RepositoryUtils<Course> courseRepositoryUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<Course> query4Page(Course course, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT courseId, departmentId, majorId, courseNum, courseName, courseLimitNum FROM lgb_course WHERE deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();

        Optional<Course> optional = Optional.fromNullable(course);
        if (optional.isPresent()) {
            if (course.getDepartmentId() > 0) {
                sql.append(" AND departmentId = ?");
                list.add(course.getDepartmentId());
            }

            if (course.getMajorId() > 0) {
                sql.append(" AND majorId = ?");
                list.add(course.getMajorId());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY courseId DESC");

        return courseRepositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
    }
    @Override
    public StudentUser selectUnique(StudentUser studentUser) {
        String sql = "SELECT stuCardNum,stuId FROM lgb_manage.lgb_student WHERE deleteFlag = 0 AND stuCardNum = ? ";
        Object[] args = {
                studentUser.getStuCardNum(),
        };

        StudentUser returnUser = null;

        try {
            returnUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueRowMapper());
        } catch (Exception e) {
            return null;
        }

        return returnUser != null ? returnUser : null;
    }

    @Override
    public Course queryForCourse(int courseId) {
        String sql = "SELECT courseId, lgb_course.courseName, lgb_course.courseNum, lgb_major.majorName, lgb_department.departmentName FROM lgb_course left join lgb_major on lgb_course.majorId = lgb_major.majorId left join lgb_department on lgb_course.departmentId = lgb_department.departmentId WHERE lgb_course.deleteFlag = 0 AND courseId = ?";
        Object[] args = {
                courseId
        };
        try {
            return jdbcTemplate.queryForObject(sql, args, new courseRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public boolean signUp(StudentCourse studentCourse) {
        String sql = "INSERT INTO lgb_studentCourse (courseId, studentId) VALUES (?, ?)";
        Object[] args = {
                studentCourse.getCourseId(),
                studentCourse.getStudentId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;

    }


    private class SelectUniqueRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));

            return studentUser;
        }
    }

    private class Query4PageRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setDepartmentId(resultSet.getInt("departmentId"));
            course.setMajorId(resultSet.getInt("majorId"));
            course.setCourseNum(resultSet.getString("courseNum"));
            course.setCourseName(resultSet.getString("courseName"));
            course.setCourseLimitNum(resultSet.getInt("courseLimitNum"));

            return course;
        }
    }

    private class courseRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setCourseName(resultSet.getString("courseName"));
            course.setCourseNum(resultSet.getString("courseNum"));
            course.setDepartmentName(resultSet.getString("departmentName"));
            course.setMajorName(resultSet.getString("majorName"));

            return course;
        }
    }
}
