package com.meng.cloud.account.repository;


import com.meng.cloud.account.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
