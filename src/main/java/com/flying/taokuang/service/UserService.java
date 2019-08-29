package com.flying.taokuang.service;

import com.flying.taokuang.dataobject.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加学生
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 通过学生学号选择学生
     * @param id
     * @return
     */
    User selectById(int id);

    /**
     * 通过用户名选择学生
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 更新
     * @param user
     * @return
     */
    int update(User user);
}
