package com.meng.cloud.common.entity;

import lombok.Data;


@Data
public class Menu {
    private long id;
    private String name;
    private double price;
    private String flavor;
    private Type type;
    private String author;

    public long getId() {
        return id;
    }
}
