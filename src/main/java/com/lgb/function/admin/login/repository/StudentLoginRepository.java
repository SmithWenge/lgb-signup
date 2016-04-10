package com.lgb.function.admin.login.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.CourseTime;
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
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "TMP.courseId, \n" +
                "departmentName, \n" +
                "majorName,\n" +
                "courseNum,\n" +
                "courseName, \n" +
                "courseLimitNum\n" +
                "FROM (SELECT \n" +
                "courseId, \n" +
                "lgb_department.departmentName, \n" +
                "lgb_major.majorName,\n" +
                "courseNum,\n" +
                "courseName, \n" +
                "courseLimitNum\n" +
                " FROM \n" +
                "\tlgb_course \n" +
                " left join lgb_major\n" +
                "    on lgb_course.majorId = lgb_major.majorId\n" +
                " left join lgb_department\n" +
                "    on lgb_course.departmentId = lgb_department.departmentId\n" +
                " WHERE \n" +
                "\tlgb_course.deleteFlag = 0 \n" +
                "    AND courseId IN (SELECT courseId FROM lgb_course C WHERE C.courseId NOT IN \n" +
                "    (SELECT courseId FROM lgb_studentCourse SC WHERE SC.studentId = ?))) AS TMP LEFT JOIN (SELECT COUNT(studentCourseId) AS NUM, C.courseId FROM lgb_course C LEFT JOIN lgb_studentCourse SC ON SC.courseId = C.courseId GROUP BY C.courseId) CUM ON CUM.courseId = TMP.courseId WHERE TMP.courseLimitNum > CUM.NUM");

        Object[] args = {
                course.getStudentId()
        };

        sql.append(" ORDER BY courseId DESC");

        return courseRepositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
    }

    @Override
    public Page<Course> querySign4Page(Course course, Pageable pageable) {
        String sql = ("SELECT \n" +
                "    lgb_studentCourse.courseId,\n" +
                "\tlgb_course.courseNum,\n" +
                "    lgb_course.courseName,\n" +
                "    lgb_major.majorName,\n" +
                "    lgb_department.departmentName,\n" +
                "    lgb_course.courseLimitNum\n" +
                "FROM\n" +
                "    lgb_course\n" +
                "        LEFT JOIN\n" +
                "    lgb_major ON lgb_course.majorId = lgb_major.majorId\n" +
                "        LEFT JOIN\n" +
                "    lgb_department ON lgb_course.departmentId = lgb_department.departmentId\n" +
                "\t\tleft join\n" +
                "\tlgb_studentCourse on lgb_course.courseId = lgb_studentCourse.courseId\n" +
                "WHERE\n" +
                "    lgb_course.deleteFlag = 0 AND lgb_studentCourse.studentId = ?  ORDER BY courseId DESC\n" +
                "\n");

        Object[] args = {
                course.getStudentId()
        };
        try {
            return courseRepositoryUtils.select4Page(sql, pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public StudentUser selectUnique(StudentUser studentUser) {
        String sql = "SELECT stuCardNum, stuId, stuName FROM lgb_manage.lgb_student WHERE deleteFlag = 0 AND stuCardNum = ? ";
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
        String sql = "SELECT courseId, majorName, departmentName, courseTuition, courseNum, courseName, teacherOneName, teacherTwoName, courseRoom, courseLimitNum, courseStuNum FROM lgb_course C  LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON C.departmentId = d.departmentId WHERE C.deleteFlag = 0 and courseId = ?";
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
    public List<CourseTime> queryForCourseTime(int courseId) {
        String sql = "SELECT \n" +
                "    lgb_course.courseId,\n" +
                "    lgb_courseTime.timeWeek,\n" +
                "    lgb_courseTime.timeSpecific\n" +
                "FROM\n" +
                "    lgb_course\n" +
                "        LEFT JOIN\n" +
                "    lgb_courseTime ON lgb_course.courseId = lgb_courseTime.courseId\n" +
                "WHERE\n" +
                "    lgb_course.deleteFlag = 0\n" +
                "        AND lgb_course.courseId = ?";
        Object[] args = {
                courseId
        };
        try {
            return jdbcTemplate.query(sql, args, new courseTimeRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CourseTime>();
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
    @Override
    public StudentCourse signUpVer(StudentCourse studentCourse) {
        String sql = "SELECT studentId,courseId FROM lgb_studentCourse WHERE studentId = ? AND courseId = ?";
        Object[] args = {
                studentCourse.getStudentId(),
                studentCourse.getCourseId()
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SignUpVerRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(StudentCourse studentCourse) {
        String sql = "DELETE FROM lgb_studentCourse  WHERE studentId = ? AND courseId = ? ";
        Object[] args = {
                studentCourse.getStudentId(),
                studentCourse.getCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }

    }


    private class SelectUniqueRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuName(resultSet.getString("stuName"));

            return studentUser;
        }
    }

    private class Query4PageRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setDepartmentName(resultSet.getString("departmentName"));
            course.setMajorName(resultSet.getString("majorName"));
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
            course.setCourseLimitNum(resultSet.getInt("courseLimitNum"));
            course.setTeacherOneName(resultSet.getString("teacherOneName"));
            course.setTeacherTwoName(resultSet.getString("teacherTwoName"));
            course.setCourseTuition(resultSet.getInt("courseTuition"));
            course.setCourseRoom(resultSet.getInt("courseRoom"));
            course.setCourseStuNum(courseStuNum(course.getCourseId()));
            course.setTimes(queryForCourseTime(course.getCourseId()));

            return course;
        }
    }

    private class courseTimeRowMapper implements RowMapper<CourseTime> {

        @Override
        public CourseTime mapRow(ResultSet resultSet, int i) throws SQLException {
            CourseTime course = new CourseTime();

            course.setTimeWeek(resultSet.getInt("timeWeek"));
            String specific = resultSet.getString("timeSpecific");
            course.setTimeSpecificInt(convertTimeSpecific(specific));
            course.setCourseId(resultSet.getInt("courseId"));

            return course;
        }
    }

    private class SignUpVerRowMapper implements RowMapper<StudentCourse> {

        @Override
        public StudentCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentCourse course = new StudentCourse();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setStudentId(resultSet.getInt("studentId"));

            return course;
        }
    }

    private int convertTimeSpecific(String specific) {
        if (specific.equals("a")) {
            return 1;
        } else if (specific.equals("b")) {
            return 2;
        } else if (specific.equals("c")) {
            return 3;
        } else if (specific.equals("d")) {
            return 4;
        } else if (specific.equals("e")) {
            return 5;
        }

        return 0;
    }
    private int courseStuNum(int courseId) {
        String sql = "SELECT COUNT(studentCourseId) AS NUM FROM lgb_studentCourse WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class);
    }
}
