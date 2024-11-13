package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.User;

public interface UserDao {
    public User findByIdOrEmail(String keyword);
}
