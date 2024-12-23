package com.meng.cloud.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private long id;
    private User user;
    private Menu menu;
    private Admin admin;
    private Date date;
    private int state;

    public Menu getMenu() {
        return menu;
    }
}
