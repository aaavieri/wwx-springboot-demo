package com.wwx.tob.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BasePojo implements Serializable {

    private Date createTime;

    private int createUser;

    private Date updateTime;

    private int updateUser;
    
    private int delFlag;
}
