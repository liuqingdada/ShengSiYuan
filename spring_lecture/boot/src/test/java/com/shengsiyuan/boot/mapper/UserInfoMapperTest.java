package com.shengsiyuan.boot.mapper;

import com.shengsiyuan.boot.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by cooper
 * 2021/3/29.
 * Email: 1239604859@qq.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testInsert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(0);
        userInfo.setName("suhen");
        userInfo.setAge("26");
        userInfo.setPhone("16712864914");
        int ret = userInfoMapper.insert(userInfo);
        System.out.println(ret);
    }

    @Test
    public void testGetAll() {
        List<UserInfo> users = userInfoMapper.findByAge("26");
        users.forEach(it -> {
            System.out.println(it.toString());
        });
    }
}
