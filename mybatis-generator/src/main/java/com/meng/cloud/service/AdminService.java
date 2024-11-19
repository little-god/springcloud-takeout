package com.meng.cloud.service;


import com.meng.cloud.account.entity.Admin;

import java.util.List;

public interface AdminService {
    public int add(Admin admin);
    public int delete(int id);
    public int update(Admin admin);
    public Admin getById(int id);
    public List<Admin > getAll();
}
