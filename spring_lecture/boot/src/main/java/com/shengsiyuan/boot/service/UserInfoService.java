package com.shengsiyuan.boot.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.shengsiyuan.boot.mapper.UserInfoMapper;
import com.shengsiyuan.boot.domain.UserInfo;

/**
 * Created by cooper
 * 2021/3/29.
 * Email: 1239604859@qq.com
 */

@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }


    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }


    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }


    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

}


