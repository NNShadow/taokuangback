package com.flying.taokuang.dao;

import com.flying.taokuang.dataobject.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CollectionMapper {
    @Delete({
        "delete from taokuang_collection",
        "where collectorId = #{collectorId,jdbcType=INTEGER} and collectionId = #{collectionId,jdbcType=INTEGER}"

    })
    int deleteByUserIdAndCollectionId(Integer collectorId, Integer collectionId);

    @Insert({
        "insert into taokuang_collection (collectorId, ",
        "collectionId, collectionNum, ",
        "createdDate, updateDate)",
        "values (#{collectorId,jdbcType=INTEGER}, ",
        "#{collectionId,jdbcType=INTEGER}, #{collectionNum,jdbcType=INTEGER}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(Collection record);

    @Select({
        "select",
        "id, collectorId, collectionId, collectionNum, createdDate, updateDate",
        "from taokuang_collection",
        "where collectionId = #{collectionId,jdbcType=INTEGER}"
    })
    @Results(id = "use", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="collectorId", property="collectorId", jdbcType=JdbcType.INTEGER),
        @Result(column="collectionId", property="collectionId", jdbcType=JdbcType.INTEGER),
        @Result(column="collectionNum", property="collectionNum", jdbcType=JdbcType.INTEGER),
        @Result(column="createdDate", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updateDate", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Collection> selectByCollectionId(int collectionId);

    @Select({
            "select",
            "id, collectorId, collectionId, collectionNum, createdDate, updateDate",
            "from taokuang_collection",
            "where collectorId = #{collectorId,jdbcType=INTEGER}"
    })
    @ResultMap("use")
    List<Collection> selectByCollectorId(int collectorId);

    @Select({
            "select",
            "id, collectorId, collectionId, collectionNum, createdDate, updateDate",
            "from taokuang_collection",
            "where collectionId = #{collectionId,jdbcType=INTEGER}",
            "and collectorId = #{collectorId,jdbcType=INTEGER}"
    })
    @ResultMap("use")
    Collection selectByCollectionIdAndCollectorId(int collectionId, int collectorId);

    @Update({
        "update taokuang_collection",
        "set collectorId = #{collectorId,jdbcType=INTEGER},",
          "collectionId = #{collectionId,jdbcType=INTEGER},",
          "collectionNum = #{collectionNum,jdbcType=INTEGER},",
          "createdDate = #{createdDate,jdbcType=TIMESTAMP},",
          "updateDate = #{updateDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Collection record);
}