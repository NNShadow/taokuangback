package com.flying.taokuang.dao;

import com.flying.taokuang.dataobject.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from taokuang_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);

    @Insert({
        "insert into taokuang_user (studentId, ",
        "studentIdCard, authority, renz, ",
        "username, password, ",
        "picture, ",
        "mobilePhoneNumber, mobilePhoneNumberVerified, ",
        "email, emailVerified, ",
        "createdDate, updatedDate)",
        "values (#{studentId,jdbcType=VARCHAR}, ",
        "#{studentIdCard,jdbcType=VARCHAR}, #{authority,jdbcType=VARCHAR}, #{renz,jdbcType=INTEGER}, ",
        "#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{picture,jdbcType=VARCHAR}, ",
        "#{mobilePhoneNumber,jdbcType=VARCHAR}, #{mobilePhoneNumberVerified,jdbcType=INTEGER}, ",
        "#{email,jdbcType=VARCHAR}, #{emailVerified,jdbcType=INTEGER}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{updatedDate,jdbcType=TIMESTAMP})"
    })
    int insert(User user);

    @Select({
        "select",
        "id, studentId, studentIdCard, authority, renz, username, password, picture, mobilePhoneNumber, ",
        "mobilePhoneNumberVerified, email, emailVerified, createdDate, updatedDate",
        "from taokuang_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results(id = "use", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="studentId", property="studentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="studentIdCard", property="studentIdCard", jdbcType=JdbcType.VARCHAR),
        @Result(column="authority", property="authority", jdbcType=JdbcType.VARCHAR),
        @Result(column="renz", property="renz", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="picture", property="picture", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobilePhoneNumber", property="mobilePhoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobilePhoneNumberVerified", property="mobilePhoneNumberVerified", jdbcType=JdbcType.INTEGER),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="emailVerified", property="emailVerified", jdbcType=JdbcType.INTEGER),
        @Result(column="createdDate", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatedDate", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectById(int id);

    @Select({
            "select",
            "id, studentId, studentIdCard, authority, renz, username, password, picture, mobilePhoneNumber, ",
            "mobilePhoneNumberVerified, email, emailVerified, createdDate, updatedDate",
            "from taokuang_user",
            "where username = #{username,jdbcType=INTEGER}"
    })
    @ResultMap("use")
    User selectByUsername(String username);

    @Update({
        "update taokuang_user",
        "set studentId = #{studentId,jdbcType=VARCHAR},",
          "studentIdCard = #{studentIdCard,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "picture = #{picture,jdbcType=VARCHAR},",
          "mobilePhoneNumber = #{mobilePhoneNumber,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "updatedDate = #{updatedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(User user);
}