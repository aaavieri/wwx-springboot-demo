package com.wwx.tob.dao;

import com.wwx.tob.pojo.ClientPojo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClientMapper {

    @Select("select clientID, channelID, wechatID, phoneNum, standard, payType, status, score, attentionTime, bindTime, cancelTime, recommendPhone, " +
            "sceneStr from t_client where clientID = #{clientID}")
    ClientPojo findByClientId(@Param("clientID") final int clientID);

    @Insert("insert into t_client (clientID, channelID, status, score, insertUser, insertTime, updateUser, updateTime) values (" +
            "#{clientID}, #{channelID}, #{status}, 0, -1, sysdate(), -1, sysdate())")
    int saveClient(final ClientPojo clientPojo);
}
