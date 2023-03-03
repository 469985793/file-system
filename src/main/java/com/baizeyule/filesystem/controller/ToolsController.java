package com.baizeyule.filesystem.controller;

import com.baizeyule.filesystem.common.R;
import com.baizeyule.filesystem.entity.UserInfo;
import com.baizeyule.filesystem.service.UserInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tools")
public class ToolsController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 修改用户资产等信息
     * @param phone
     * @return
     */
    @PostMapping("/updateUserInfo/{phone}")
    public R updateUserInfo(@PathVariable String phone){

        UserInfo userInfo = new UserInfo();
        userInfo.setShimingFlag(1);
        userInfo.setIdCard("789");
        userInfo.setTuijianUserId(10000051L);
        userInfo.setPayPassword("96e79218965eb72c92a549dd5a330112");
        userInfo.setKeyCount(10000);
        userInfo.setKindlingCount(10000);
        userInfo.setBlessingCount(10000);

        boolean update = userInfoService.update(userInfo, Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getPhone, phone)
        );
        if(!update){
            return R.failed("手机号码不存在");
        }

        return R.ok(phone);
    }
}
