package com.flying.taokuang.service;

import com.flying.taokuang.dataobject.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 删除
     * @param userId
     * @return
     */
    int deleteById(Integer userId);

    /**
     * 添加学生
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 通过学生 id 选择学生
     * @param userId
     * @return
     */
    User selectByUserId(int userId);

    /**
     * 更新
     * @param user
     * @param oldPassword
     * @param oldId
     * @return
     */
    int update(User user, String oldPassword, int oldId);
}
