package com.meng.cloud.service.Impl;

import com.meng.cloud.account.entity.Admin;
import com.meng.cloud.account.mapper.AdminMapper;
import com.meng.cloud.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public int add(Admin admin) {
        return adminMapper.insertSelective(admin);
    }

    @Override
    public int delete(int id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Admin getById(int id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectAll();
    }
}
