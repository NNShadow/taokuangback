package com.flying.taokuang.service;

import com.flying.taokuang.dataobject.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     * 通过 id 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加评论
     * @param record
     * @return
     */
    int insert(Comment record);

    /**
     * 通过物品 id 查找
     * @param contentGoodsId
     * @return
     */
    List<Comment> selectByContentGoodsId(Integer contentGoodsId);

    /**
     * 通过评论人 id 查找
     * @param contentCommenterId
     * @return
     */
    List<Comment> selectByContentCommenterId(int contentCommenterId);

    /**
     * 更新
     * @param record
     * @return
     */
    int update(Comment record);
}
