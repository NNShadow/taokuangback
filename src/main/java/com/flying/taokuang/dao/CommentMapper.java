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
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);

    @Insert({
        "insert into taokuang_comment (contentGoodsId, ",
        "contentCommenter, content, ",
        "createdDate, updatedDate)",
        "values (#{contentGoodsId,jdbcType=INTEGER}, ",
        "#{contentCommenter,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{updatedDate,jdbcType=TIMESTAMP})"
    })
    int insert(Comment record);

    //TODO comment 1
    @Select({
        "select",
        "id, contentGoodsId, contentCommenter, content, createdDate, updatedDate",
        "from taokuang_comment",
        "where contentGoodsId = #{contentGoodsId,jdbcType=INTEGER}"
    })
    @Results(id = "use", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="contentGoodsId", property="contentGoodsId", jdbcType=JdbcType.INTEGER),
        @Result(column="contentCommenter", property="contentCommenter", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdDate", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatedDate", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Comment> selectByContentGoodsId(Integer contentGoodsId);

    //TODO comment 2
    @Select({
            "select",
            "id, contentGoodsId, contentCommenter, content, createdDate, updatedDate",
            "from taokuang_comment",
            "where contentCommenter = #{contentCommenter,jdbcType=VARCHAR}"
    })
    @ResultMap("use")
    List<Comment> selectByContentCommenterId(int contentCommenterId);

    @Update({
        "update taokuang_comment",
        "set contentGoodsId = #{contentGoodsId,jdbcType=INTEGER},",
          "contentCommenterId = #{contentCommenterId,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "createdDate = #{createdDate,jdbcType=TIMESTAMP},",
          "updatedDate = #{updatedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Comment comment);
}