package com.meng.cloud.user.repository;


import com.meng.cloud.common.entity.User;

import java.util.List;

public interface UserRepository {

    public User login(String username, String password);

    public List<User> findAll(int index, int limit);

    public int count();

    public void save(User user);

    public void deleteById(long id);

    User findById(long id);

    void update(User user);
}
