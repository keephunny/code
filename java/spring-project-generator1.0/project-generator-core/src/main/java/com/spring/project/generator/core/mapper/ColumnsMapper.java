package com.spring.project.generator.core.mapper;

import com.spring.project.generator.core.entity.Columns;
import com.spring.project.generator.core.entity.Tables;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnsMapper {
    int insert(Columns record);

    List<Columns> selectAll();

    /**
     * 按数据库名查询
     *
     * @param schema    数据库名
     * @param tableName 表名
     * @return
     */
    List<Columns> queryBySchema(@Param("schema") String schema, @Param("tableName") String tableName);


}