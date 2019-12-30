package com.flying.taokuang.service;

import com.flying.taokuang.dataobject.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     * 通过 id 删除
     * @param commentId
     * @return
     */
    int deleteById(Integer commentId);

    /**
     * 添加评论
     * @param record
     * @return
     */
    int insert(Comment record);

    /**
     * 通过物品 id 查找
     * @param contentId
     * @return
     */
    List<Comment> selectByContentId(Integer contentId);

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
