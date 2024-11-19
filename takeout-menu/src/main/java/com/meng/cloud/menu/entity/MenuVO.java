package com.meng.cloud.menu.entity;

import lombok.Data;

import java.util.List;


@Data
public class MenuVO {
    private int code;
    private String msg;
    private int count;
    private List<Menu> data;
}
