package com.flying.taokuang.service;

import com.flying.taokuang.dataobject.Content;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContentService {
    /**
     * 根据 id 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加商品
     * @param content
     * @return
     */
    int insert(Content content);

    /**
     * 根据用户 id 查询商品
     * @param userId
     * @return
     */
    List<Content> selectByUserId(int userId);

    /**
     * 根据买家 id 查询商品
     * @param buyerId
     * @return
     */
    List<Content> selectByBuyerId(int buyerId);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    Content selectById(int id);

    /**
     * 查询全部商品
     * @return
     */
    List<Content> selectAll();

    /**
     * 更新
     * @param record
     * @return
     */
    int update(Content record);

    /**
     * 根据商品类型查询
     * @param type
     * @return
     */
    List<Content> selectByType(String type);

    /**
     * 根据关键字，模糊搜索
     * @param keyword
     * @return
     */
    List<Content> selectByKeyword(String keyword);

    /**
     * 购买
     * @param content
     * @param userId
     * @return
     */
    int buy(Content content, int userId);
}
