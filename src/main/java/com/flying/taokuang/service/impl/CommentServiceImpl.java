package com.flying.taokuang.service.impl;

import com.flying.taokuang.dao.CommentMapper;
import com.flying.taokuang.dataobject.Comment;
import com.flying.taokuang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NNShadow
 * @date 2019/8/27 16:26
 */
@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int deleteById(Integer id) {
        return commentMapper.deleteById(id);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public List<Comment> selectByContentGoodsId(Integer contentGoodsId) {
        return commentMapper.selectByContentGoodsId(contentGoodsId);
    }

    @Override
    public List<Comment> selectByContentCommenterId(int contentCommenterId) {
        return commentMapper.selectByContentCommenterId(contentCommenterId);
    }

    @Override
    public int update(Comment record) {
        return commentMapper.update(record);
    }
}
