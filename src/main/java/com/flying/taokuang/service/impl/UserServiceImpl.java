package com.flying.taokuang.service.impl;

import com.flying.taokuang.dao.UserMapper;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return userMapper.insert(user);
    }

    @Override
    public User selectByStudentId(String studentId) {
        return userMapper.selectByStudentId(studentId);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int update(User record) {
        return userMapper.update(record);
    }
}
