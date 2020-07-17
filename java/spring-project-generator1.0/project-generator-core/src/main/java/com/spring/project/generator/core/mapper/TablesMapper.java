package com.spring.project.generator.core.mapper;

import com.spring.project.generator.core.entity.Columns;
import com.spring.project.generator.core.entity.Tables;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesMapper {
    int insert(Tables tables);

    List<Tables> selectAll();

    /**
     * 按数据库名查询
     *
     * @param schema
     * @return
     */
    List<Tables> queryBySchema(@Param("schema") String schema);
}