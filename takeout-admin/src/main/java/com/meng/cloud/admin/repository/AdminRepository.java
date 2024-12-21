package com.meng.cloud.admin.repository;


import com.meng.cloud.common.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
