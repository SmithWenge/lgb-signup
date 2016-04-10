package com.lgb.function.admin.course;

import com.lgb.arc.Entry;

import java.util.List;

/**
 * Created by Samuel on 16/4/8.
 */
public class Course extends Entry {
    private int courseId;
    private int departmentId;
    private int majorId;
    private String courseNum;
    private String courseName;
    private int courseEnrollmentNum;
    private int courseStuNum;
    private int courseTeacherOne;
    private int courseTeacherTwo;
    private int courseTuition;
    private int courseLimitNum;
    private int courseYears;
    private int courseGraLimitNum;
    private int courseSumFlag;
    private String courseRemark;
    private int deleteFlag;
    private String departmentName;
    private String majorName;
    private int timeWeek;
    private String timeSpecific;
    private int courseRoom;
    private int studentId;
    private String teacherOneName;
    private String teacherTwoName;
    private List<CourseTime> times;

    public List<CourseTime> getTimes() {
        return times;
    }

    public void setTimes(List<CourseTime> times) {
        this.times = times;
    }

    public String getTeacherOneName() {
        return teacherOneName;
    }

    public void setTeacherOneName(String teacherOneName) {
        this.teacherOneName = teacherOneName;
    }

    public String getTeacherTwoName() {
        return teacherTwoName;
    }

    public void setTeacherTwoName(String teacherTwoName) {
        this.teacherTwoName = teacherTwoName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTimeWeek() {
        return timeWeek;
    }

    public void setTimeWeek(int timeWeek) {
        this.timeWeek = timeWeek;
    }

    public String getTimeSpecific() {
        return timeSpecific;
    }

    public void setTimeSpecific(String timeSpecific) {
        this.timeSpecific = timeSpecific;
    }

    public int getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(int courseRoom) {
        this.courseRoom = courseRoom;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseEnrollmentNum() {
        return courseEnrollmentNum;
    }

    public void setCourseEnrollmentNum(int courseEnrollmentNum) {
        this.courseEnrollmentNum = courseEnrollmentNum;
    }

    public int getCourseStuNum() {
        return courseStuNum;
    }

    public void setCourseStuNum(int courseStuNum) {
        this.courseStuNum = courseStuNum;
    }

    public int getCourseTeacherOne() {
        return courseTeacherOne;
    }

    public void setCourseTeacherOne(int courseTeacherOne) {
        this.courseTeacherOne = courseTeacherOne;
    }

    public int getCourseTeacherTwo() {
        return courseTeacherTwo;
    }

    public void setCourseTeacherTwo(int courseTeacherTwo) {
        this.courseTeacherTwo = courseTeacherTwo;
    }

    public int getCourseTuition() {
        return courseTuition;
    }

    public void setCourseTuition(int courseTuition) {
        this.courseTuition = courseTuition;
    }

    public int getCourseLimitNum() {
        return courseLimitNum;
    }

    public void setCourseLimitNum(int courseLimitNum) {
        this.courseLimitNum = courseLimitNum;
    }

    public int getCourseYears() {
        return courseYears;
    }

    public void setCourseYears(int courseYears) {
        this.courseYears = courseYears;
    }

    public int getCourseGraLimitNum() {
        return courseGraLimitNum;
    }

    public void setCourseGraLimitNum(int courseGraLimitNum) {
        this.courseGraLimitNum = courseGraLimitNum;
    }

    public int getCourseSumFlag() {
        return courseSumFlag;
    }

    public void setCourseSumFlag(int courseSumFlag) {
        this.courseSumFlag = courseSumFlag;
    }

    public String getCourseRemark() {
        return courseRemark;
    }

    public void setCourseRemark(String courseRemark) {
        this.courseRemark = courseRemark;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
