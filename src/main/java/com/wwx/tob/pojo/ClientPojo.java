package com.wwx.tob.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class ClientPojo extends BasePojo {

    private int clientID;

    private int channelID;

    private String wechatID;

    private String phoneNum;

    private String standard;

    private String payType;

    private String status;

    private int score;

    private Timestamp attentionTime;

    private Timestamp bindTime;

    private Timestamp cancelTime;

    private String recommendPhone;

    private String sceneStr;
}
