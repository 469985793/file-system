package com.baizeyule.filesystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfo {

    @TableId
    private Long id;

    private Integer shimingFlag;

    private String idCard;

    private Long tuijianUserId;

    private String payPassword;

    private Integer keyCount;

    private Integer kindlingCount;

    private Integer blessingCount;

    private String phone;
}
