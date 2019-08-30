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
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);

    @Insert({
        "insert into taokuang_content (username, ",
        "goodspic, gezi, ",
        "buyer, title, contact, ",
        "type, money, business, ",
        "buy, place, context, ",
        "createdDate, updatedDate)",
        "values (#{username,jdbcType=VARCHAR}, ",
        "#{goodsPic,jdbcType=VARCHAR}, #{gezi,jdbcType=INTEGER}, ",
        "#{buyer,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{contact,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{money,jdbcType=VARCHAR}, #{business,jdbcType=INTEGER}, ",
        "#{buy,jdbcType=INTEGER}, #{place,jdbcType=VARCHAR}, #{context,jdbcType=VARCHAR}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{updatedDate,jdbcType=TIMESTAMP})"
    })
    int insert(Content content);

    @Select({
        "select",
        "id, username, goodspic, gezi, buyer, title, contact, type, money, business, ",
        "buy, place, context, createdDate, updatedDate",
        "from taokuang_content",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results(id = "use", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="goodspic", property="goodsPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="gezi", property="gezi", jdbcType=JdbcType.INTEGER),
        @Result(column="buyer", property="buyer", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.VARCHAR),
        @Result(column="business", property="business", jdbcType=JdbcType.INTEGER),
        @Result(column="buy", property="buy", jdbcType=JdbcType.INTEGER),
        @Result(column="place", property="place", jdbcType=JdbcType.VARCHAR),
        @Result(column="context", property="context", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdDate", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatedDate", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Content> selectByUsername(String username);

    @Select({
        "select",
        "id, username, goodspic, gezi, buyer, title, contact, type, money, business, ",
        "buy, place, context, createdDate, updatedDate",
        "from taokuang_content"
    })
    @ResultMap("use")
    List<Content> selectAll();

    @Select({
            "select",
            "id, username, goodspic, gezi, buyer, title, contact, type, money, business, ",
            "buy, place, context, createdDate, updatedDate",
            "from taokuang_content",
            "where type = #{type,jdbcType=VARCHAR}"
    })
    @ResultMap("use")
    List<Content> selectByType(String type);

    @Select({
            "select",
            "id, username, goodspic, gezi, buyer, title, contact, type, money, business, ",
            "buy, place, context, createdDate, updatedDate",
            "from taokuang_content",
            "where title like #{title,jdbcType=VARCHAR}"
    })
    @ResultMap("use")
    List<Content> selectByKeyword(String keyword);

    @Update({
        "update taokuang_content",
        "set username = #{username,jdbcType=VARCHAR},",
          "goodspic = #{goodsPic,jdbcType=VARCHAR},",
          "gezi = #{gezi,jdbcType=INTEGER},",
          "buyer = #{buyer,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "money = #{money,jdbcType=VARCHAR},",
          "business = #{business,jdbcType=INTEGER},",
          "buy = #{buy,jdbcType=INTEGER},",
          "place = #{place,jdbcType=VARCHAR},",
          "context = #{context,jdbcType=VARCHAR},",
          "createdDate = #{createdDate,jdbcType=TIMESTAMP},",
          "updatedDate = #{updatedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Content content);
}