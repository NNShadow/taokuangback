package com.flying.taokuang.service.impl;

import com.flying.taokuang.dao.UserMapper;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author NNShadow
 * @date 2019/8/27 16:25
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User user) {
        user.setPassword(MD5Util.md5(user.getPassword()));
        if (userMapper.selectById(user.getId()) == null){
            return userMapper.insert(user);
        }
        return 0;
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int update(User user, String oldPassword, int oldId) {
        //判断旧密码
        if (!MD5Util.md5(oldPassword).equals(userMapper.selectById(oldId).getPassword())){
            return 0;
        }
        if(oldPassword.isEmpty()){
            return 0;
        }
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setUpdatedDate(new Date());
        User newUser = userMapper.selectById(user.getId());
        if (newUser == null || newUser.getUsername().equals(user.getUsername())){
            return userMapper.update(user);
        }
        return 0;
    }

}
