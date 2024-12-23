package com.meng.cloud.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private String telephone;
    private Date registerdate;
    private String address;
}
