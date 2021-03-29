package com.shengsiyuan.boot.mapper;

import com.shengsiyuan.boot.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cooper
 * 2021/3/29.
 * Email: 1239604859@qq.com
 */

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> findByAge(@Param("age") String age);

    int insertList(@Param("list") List<UserInfo> list);

    List<UserInfo> findByIdGreaterThanOrderById(@Param("minId") Integer minId);


}
