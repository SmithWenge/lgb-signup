package com.lgb.function.stu.register.repository;

import com.lgb.function.stu.login.StudentUser;

public interface RegisterRepositoryI {
    boolean insert(StudentUser studentUser);
    int selectCardNum(StudentUser studentUser);
}
