package com.flying.taokuang.dao;

import com.flying.taokuang.dataobject.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CommentMapper {
    @Delete({
            "delete from taokuang_comment",
            "where commentId = #{commentId,jdbcType=INTEGER}"
    })
    int deleteById(Integer commentId);

    @Insert({
            "insert into taokuang_comment (contentId, ",
            "contentCommenterId, text, ",
            "createdDate, updatedDate)",
            "values (#{contentId,jdbcType=INTEGER}, ",
            "#{contentCommenterId,jdbcType=INTEGER}, #{text,jdbcType=VARCHAR}, ",
            "#{createdDate,jdbcType=TIMESTAMP}, #{updatedDate,jdbcType=TIMESTAMP})"
    })
    int insert(Comment record);

    @Select({
            "select comment.commentId, comment.contentId, content.contentName, comment.contentCommenterId, ",
            "user.username, comment.text, comment.createdDate, comment.updatedDate ",
            "from taokuang_comment comment ",
            "left join taokuang_user user on user.userId=comment.contentCommenterId ",
            "left join taokuang_content content on content.contentId=comment.contentId ",
            "where comment.contentId = #{contentId, jdbcType=INTEGER}"
    })
    @Results(id = "use", value = {
            @Result(column = "commentId", property = "commentId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "contentId", property = "contentId", jdbcType = JdbcType.INTEGER),
            @Result(column = "contentName", property = "contentName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contentCommenterId", property = "contentCommenterId", jdbcType = JdbcType.INTEGER),
            @Result(column = "username", property = "contentCommenterName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "text", property = "text", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdDate", property = "createdDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updatedDate", property = "updatedDate", jdbcType = JdbcType.TIMESTAMP)
    })
    List<Comment> selectByContentId(Integer contentId);

    @Select({
            "select comment.commentId, comment.contentId, content.contentName, comment.contentCommenterId, ",
            "user.username, comment.text, comment.createdDate, comment.updatedDate ",
            "from taokuang_comment comment ",
            "left join taokuang_user user on user.userId=comment.contentCommenterId ",
            "left join taokuang_content content on content.contentId=comment.contentId ",
            "where comment.contentCommenterId = #{contentCommenterId, jdbcType=INTEGER}"
    })
    @ResultMap("use")
    List<Comment> selectByContentCommenterId(int contentCommenterId);

    @Update({
            "update taokuang_comment ",
            "set contentId = #{contentId,jdbcType=INTEGER}, ",
            "contentCommenterId = #{contentCommenterId,jdbcType=INTEGER}, ",
            "text = #{text,jdbcType=VARCHAR}, ",
            "createdDate = #{createdDate,jdbcType=TIMESTAMP}, ",
            "updatedDate = #{updatedDate,jdbcType=TIMESTAMP} ",
            "where commentId = #{commentId,jdbcType=INTEGER} "
    })
    int update(Comment comment);
}