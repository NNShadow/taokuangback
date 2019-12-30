package com.flying.taokuang.dao;

import com.flying.taokuang.dataobject.Content;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ContentMapper {
    @Delete({
            "delete from taokuang_content",
            "where contentId = #{contentId, jdbcType=INTEGER}"
    })
    int deleteById(Integer contentId);

    @Insert({
            "insert into taokuang_content (userId, ",
            "goodspic, agree, ",
            "buyerId, contentName, contact, ",
            "type, money, business, ",
            "buy, place, text, ",
            "createdDate, updatedDate) ",
            "values (#{userId, jdbcType=INTEGER}, ",
            "#{goodsPic, jdbcType=VARCHAR}, #{agree, jdbcType=INTEGER}, ",
            "#{buyerId, jdbcType=INTEGER}, #{contentName, jdbcType=VARCHAR}, #{contact, jdbcType=VARCHAR}, ",
            "#{type, jdbcType=VARCHAR}, #{money, jdbcType=VARCHAR}, #{business, jdbcType=INTEGER}, ",
            "#{buy, jdbcType=INTEGER}, #{place, jdbcType=VARCHAR}, #{text, jdbcType=VARCHAR}, ",
            "#{createdDate, jdbcType=TIMESTAMP}, #{updatedDate, jdbcType=TIMESTAMP})"
    })
    int insert(Content content);

    @Select({
            "select contentId, content.userId, user1.username, goodspic, bird, ",
            "content.buyerId, user2.username, contentName, contact, type, ",
            "money, business, buy, place, text, content.createdDate, content.updatedDate ",
            "from taokuang_content content ",
            "left join taokuang_user user1 on user1.userId = content.userId ",
            "left join taokuang_user user2 on user2.userId = content.buyerId ",
            "where content.userId = #{userId, jdbcType=INTEGER}"
    })
    @Results(id = "use", value = {
            @Result(column = "contentId", property = "contentId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "userId", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "username", property = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "goodspic", property = "goodsPic", jdbcType = JdbcType.VARCHAR),
            @Result(column = "bird", property = "bird", jdbcType = JdbcType.INTEGER),
            @Result(column = "buyerId", property = "buyerId", jdbcType = JdbcType.INTEGER),
            @Result(column = "username", property = "buyerName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contentName", property = "contentName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contact", property = "contact", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "money", property = "money", jdbcType = JdbcType.VARCHAR),
            @Result(column = "business", property = "business", jdbcType = JdbcType.INTEGER),
            @Result(column = "buy", property = "buy", jdbcType = JdbcType.INTEGER),
            @Result(column = "place", property = "place", jdbcType = JdbcType.VARCHAR),
            @Result(column = "text", property = "text", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdDate", property = "createdDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updatedDate", property = "updatedDate", jdbcType = JdbcType.TIMESTAMP)
    })
    List<Content> selectByUserId(int userId);

    @Select({
            "select contentId, content.userId, user1.username, goodspic, bird, ",
            "content.buyerId, user2.username, contentName, contact, type, ",
            "money, business, buy, place, text, content.createdDate, content.updatedDate ",
            "from taokuang_content content ",
            "left join taokuang_user user1 on user1.userId = content.userId ",
            "left join taokuang_user user2 on user2.userId = content.buyerId ",
            "where buy = 0"
    })
    @ResultMap("use")
    List<Content> selectAll();

    //TODO content 3
    @Select({
            "select contentId, content.userId, user1.username, goodspic, bird, ",
            "content.buyerId, user2.username, contentName, contact, type, ",
            "money, business, buy, place, text, content.createdDate, content.updatedDate ",
            "from taokuang_content content ",
            "left join taokuang_user user1 on user1.userId = content.userId ",
            "left join taokuang_user user2 on user2.userId = content.buyerId ",
            "where content.userId = #{userId, jdbcType=INTEGER}"
    })
    @ResultMap("use")
    Content selectByContentId(int contentId);

    @Select({
            "select contentId, content.userId, user1.username, goodspic, bird, ",
            "content.buyerId, user2.username, contentName, contact, type, ",
            "money, business, buy, place, text, content.createdDate, content.updatedDate ",
            "from taokuang_content content ",
            "left join taokuang_user user1 on user1.userId = content.userId ",
            "left join taokuang_user user2 on user2.userId = content.buyerId ",
            "where content.buyerId = #{buyerId, jdbcType=INTEGER}"
    })
    @ResultMap("use")
    List<Content> selectByBuyerId(int buyerId);

    //TODO content 5
    @Select({
            "select contentId, content.userId, user1.username, goodspic, bird, ",
            "content.buyerId, user2.username, contentName, contact, type, ",
            "money, business, buy, place, text, content.createdDate, content.updatedDate ",
            "from taokuang_content content ",
            "left join taokuang_user user1 on user1.userId = content.userId ",
            "left join taokuang_user user2 on user2.userId = content.buyerId ",
            "where type = #{type, jdbcType=VARCHAR}",
            "and buy = 0"
    })
    @ResultMap("use")
    List<Content> selectByType(String type);

    @Select({
            "select contentId, content.userId, user1.username, goodspic, bird, ",
            "content.buyerId, user2.username, contentName, contact, type, ",
            "money, business, buy, place, text, content.createdDate, content.updatedDate ",
            "from taokuang_content content ",
            "left join taokuang_user user1 on user1.userId = content.userId ",
            "left join taokuang_user user2 on user2.userId = content.buyerId ",
            "where contentName like #{keyword, jdbcType=VARCHAR}",
            "and buy = 0"
    })
    @ResultMap("use")
    List<Content> selectByKeyword(String keyword);

    @Update({
            "update taokuang_content",
            "set userId = #{userId,jdbcType=INTEGER},",
            "goodspic = #{goodsPic,jdbcType=VARCHAR},",
            "bird = #{bird,jdbcType=INTEGER},",
            "buyerId = #{buyerId,jdbcType=INTEGER},",
            "contentName = #{contentName,jdbcType=VARCHAR},",
            "contact = #{contact,jdbcType=VARCHAR},",
            "type = #{type,jdbcType=VARCHAR},",
            "money = #{money,jdbcType=VARCHAR},",
            "business = #{business,jdbcType=INTEGER},",
            "buy = #{buy,jdbcType=INTEGER},",
            "place = #{place,jdbcType=VARCHAR},",
            "text = #{text,jdbcType=VARCHAR},",
            "updatedDate = #{updatedDate,jdbcType=TIMESTAMP}",
            "where contentId = #{contentId,jdbcType=INTEGER}"
    })
    int update(Content content);
}