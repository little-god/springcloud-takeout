package com.meng.cloud.account.repository;

import com.meng.cloud.account.entity.User;

public interface UserRepository {
    public User login(String username, String password);
}