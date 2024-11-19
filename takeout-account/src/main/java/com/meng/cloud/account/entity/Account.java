package com.meng.cloud.account.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Account {
    @Id
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private String telephone;
    private Date registerdate;
    private String address;
}
