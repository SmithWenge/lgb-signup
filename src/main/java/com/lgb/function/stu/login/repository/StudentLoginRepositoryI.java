package com.lgb.function.stu.login.repository;

import com.lgb.function.stu.course.Course;
import com.lgb.function.stu.course.CourseTime;
import com.lgb.function.stu.course.Department;
import com.lgb.function.stu.course.Major;
import com.lgb.function.stu.login.StudentCourse;
import com.lgb.function.stu.login.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentLoginRepositoryI {
    Page<Course> query4Page(Course course, Pageable pageable);
    StudentUser selectUnique(StudentUser studentUser);
    Course queryForCourse(int courseId);
    boolean signUp(StudentCourse studentCourse);
    Page<Course> querySign4Page(Course course, Pageable pageable);
    List<CourseTime> queryForCourseTime(int courseId);
    StudentCourse signUpVer(StudentCourse studentCourse);
    boolean delete(StudentCourse studentCourse);
    List<Department> selectDepartments();
    List<Major> selectMajors(int departmentId);
}
