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
        if (userMapper.selectByUsername(user.getUsername()) == null){
            return userMapper.insert(user);
        }
        return 0;
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int update(User user, String oldPassword, String oldName) {
        //判断旧密码
        if (!MD5Util.md5(oldPassword).equals(userMapper.selectByUsername(oldName).getPassword())){
            return 0;
        }
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setUpdatedDate(new Date());
        User select = userMapper.selectByUsername(user.getUsername());
        if (select == null || select.getUsername().equals(user.getUsername())){
            return userMapper.update(user);
        }
        return 0;
    }
}
