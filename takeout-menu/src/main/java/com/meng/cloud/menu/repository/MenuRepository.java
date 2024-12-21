package com.meng.cloud.menu.repository;

import com.meng.cloud.common.entity.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> findAll(int index, int limit);

    int count();

    Menu findById(long id);

    void save(Menu menu);

    void update(Menu menu);

    void deleteById(long id);
}
