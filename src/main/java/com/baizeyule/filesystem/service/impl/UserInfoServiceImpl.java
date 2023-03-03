package com.baizeyule.filesystem.service.impl;

import com.baizeyule.filesystem.dao.UserInfoMapper;
import com.baizeyule.filesystem.entity.UserInfo;
import com.baizeyule.filesystem.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements UserInfoService {
}
