/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.code;

import com.project.utils.lang.FileUtils;
import com.project.utils.lang.StringUtils;
import com.spring.project.generator.core.entity.Columns;
import com.spring.project.generator.core.entity.Tables;
import com.spring.project.generator.utils.JDBCUtils;
import com.spring.project.generator.utils.TableColumnEntity;
import com.spring.project.generator.utils.TableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 16:31
 */
public class GeneratorMapperXml {
    private final Logger logger = LoggerFactory.getLogger(GeneratorControllerCode.class);
    private final String BasePath = "D:\\test\\";

    public void init(List<Tables> tables) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        //TODO 先清空目标文件
        File file = null;
        String content = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/UserInfoBaseMapper.xml.template");
            if (!file.exists() || file.isDirectory()) {
                return;
            }
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(content)) {
            return;
        }
        List<TableEntity> tableEntities = jdbcUtils.getTableInfo();
        for (TableEntity table : tableEntities) {

            generator(table, content);
        }
    }

    private List<Columns> queryColumn(String tableName) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        List<Columns> columnsList = jdbcUtils.getColumns(tableName);

        return columnsList;
    }

    private void generator(TableEntity table, String content) {
        List<TableColumnEntity> columns = table.getColumnsAll();
        String className = table.getClassName();
        String classNameNew = table.getClassNameNew();
        String tableName = table.getTableName();
        String packageMapperName = "com.spring.project.web.core.mapper";


        StringBuilder sb = new StringBuilder();
        String sqlColumn = "";
        StringBuilder resultMapBuild = new StringBuilder();
        StringBuilder updateColumnBuild = new StringBuilder();
        StringBuilder insertColumnsBuild = new StringBuilder();
        StringBuilder insertColumnsDataBuild = new StringBuilder();
        resultMapBuild.append("\r\n");
        int i = 0;
        String keyColumn = "", keyProperty = "", keyPropertyNew = "";
        for (TableColumnEntity column : columns) {
            if (i > 0) {
                sb.append(",");
                updateColumnBuild.append(",");
            }
            sb.append(column.getColumnName());

            if (column.isColumnIsKey()) {
                keyColumn = column.getColumnName();
                keyProperty = column.getPropertyName();
                keyPropertyNew =column.getPropertyNameNew();
                resultMapBuild.append("\t\t<id ");
            } else {
                resultMapBuild.append("\t\t<result ");
            }
            String columnPropertyNameNew = StringUtils.underToCamel(column.getColumnName());

            if (!column.isColumnAutoIncrement()) {
                insertColumnsBuild.append("," + column.getColumnName());
                insertColumnsDataBuild.append(",#{item.");
                insertColumnsDataBuild.append(column.getPropertyNameNew());
                insertColumnsDataBuild.append(",jdbcType = ");
                insertColumnsDataBuild.append(column.getJdbcTypeName() + "}");
            }

            resultMapBuild.append("tcolumn=\"" + column.getColumnName() + "\" ");
            resultMapBuild.append("jdbcType=\"" + column.getJdbcTypeName() + "\" ");
            resultMapBuild.append("property=\"" + columnPropertyNameNew + "\" ");
            resultMapBuild.append("/>");
            resultMapBuild.append("\r\n");

            updateColumnBuild.append(column.getColumnName());
            updateColumnBuild.append("=");
            updateColumnBuild.append("#{");
            updateColumnBuild.append(columnPropertyNameNew + ".jdbcType=");
            updateColumnBuild.append(column.getJdbcTypeName());
            updateColumnBuild.append("}");

            updateColumnBuild.append("#{userName,jdbcType=VARCHAR}");

            i++;
        }

        sqlColumn = sb.toString();
        sb.setLength(0);

        content = content.replace("${sqlColumn}", sqlColumn);
        content = content.replace("${tableName}", tableName);
        content = content.replace("${ClassName}", className);
        content = content.replace("${className}", classNameNew);
        content = content.replace("${packageMapperName}", packageMapperName);
        content = content.replace("${keyColumn}", keyColumn);
        content = content.replace("${keyProperty}", keyProperty);
        content = content.replace("${keyPropertyNew}", keyPropertyNew);

        content = content.replace("${resultMap}", resultMapBuild.toString());
        content = content.replace("${updateColumn}", updateColumnBuild.toString());
        content = content.replace("${insertColumns}", insertColumnsBuild.toString().substring(1));
        content = content.replace("${insertColumnsData}", insertColumnsDataBuild.toString().substring(1));

        String queryByColumn = generatorQueryByColumn(table);
        String countByColumn = generatorCountByColumn(table);
        String getByUnique = generatorGetByUnique(table);
        String queryByExampleColumn=queryByExample(table);
        content = content.replace("${queryByColumn}", queryByColumn);
        content = content.replace("${countByColumn}", countByColumn);
        content = content.replace("${getByUnique}", getByUnique);
        content = content.replaceAll(" *\\$\\{queryByExampleColumn\\}", queryByExampleColumn);

        String filePath = "project-web-core/src/main/resources/mapper/";

        File newFile = new File(BasePath + filePath + className + "Mapper.xml");
        try {
            FileUtils.write(newFile, content, "utf-8");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private String generatorQueryByColumn(TableEntity table) {
        StringBuilder sb = new StringBuilder();
        StringBuilder countBuild = new StringBuilder();
        List<TableColumnEntity> columnsAll = table.getColumnsAll();
        for (TableColumnEntity column : columnsAll) {
            sb.append("\t<!-- 按" + column.getColumnComment() + "字段查询 -->\r\n");
            sb.append("\t<select id=\"");
            sb.append("queryBy");
            sb.append(column.getPropertyName());
            sb.append("\"");
            sb.append(" resultMap=\"BaseResultMap\">\r\n");

            //查询sql语句
            sb.append("\t\tselect ");
            sb.append(table.getColumnsName() + " ");
            sb.append("from " + table.getTableName() + " ");
            sb.append("where " + column.getColumnName() + "=");
            sb.append("#{" + column.getPropertyNameNew() + ",jdbcType=" + column.getJdbcTypeName() + "}");
            sb.append("\r\n");
            sb.append(" \t</select>\n\n");
        }
        return sb.toString();
    }

    /**
     * 按列名进行统计
     *
     * @param table
     * @return
     */
    private String generatorCountByColumn(TableEntity table) {
        StringBuilder countBuild = new StringBuilder();
        StringBuilder countMethodBuild = new StringBuilder();
        List<TableColumnEntity> columnsAll = table.getColumnsAll();
        for (TableColumnEntity column : columnsAll) {
            countBuild.append("\t<!-- 按" + column.getColumnComment() + "字段统计 -->\r\n");
            countBuild.append("\t<select id=\"");
            countBuild.append("countBy");
            countBuild.append(column.getPropertyName());
            countBuild.append("\"");
            countBuild.append(" resultType=\"long\">\r\n");
            //查询sql语句
            countBuild.append("\t\tselect count(");
            countBuild.append(column.getColumnName() + ") ");
            countBuild.append("from " + table.getTableName() + " ");
            countBuild.append("where " + column.getColumnName() + "=");
            countBuild.append("#{" + column.getPropertyNameNew() + ",jdbcType=" + column.getJdbcTypeName() + "}");
            countBuild.append("\r\n");
            countBuild.append(" \t</select>\n\n");


            countMethodBuild.append("\t**\r\n");
            countMethodBuild.append("\t* 按" + column.getColumnComment() + "字段统计\r\n");
            countMethodBuild.append("\t*\r\n");
            countMethodBuild.append("\t* @param " + column.getPropertyNameNew() + " " + column.getColumnComment() + "\r\n");
            countMethodBuild.append("\t* @return 统计结果\r\n");
            countMethodBuild.append("\tList<" + table.getClassName() + "> ");
            countMethodBuild.append("countBy");
            countMethodBuild.append(column.getPropertyName() + "(");
            countMethodBuild.append("@Param(\"" + column.getPropertyNameNew() + "\") " + column.getJavaTypeShort() + " " + column.getPropertyNameNew());
            countMethodBuild.append(");\r\n\r\n");
        }
//        System.out.println(countMethodBuild.toString());
        return countBuild.toString();
    }

    private String generatorGetByUnique(TableEntity table) {
        StringBuilder sb = new StringBuilder();
        Map<String, List<TableColumnEntity>> mapColumn = table.getColumnsUnique();
        Iterator<Map.Entry<String, List<TableColumnEntity>>> entries = mapColumn.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<TableColumnEntity>> entry = entries.next();
            List<TableColumnEntity> columnEntity = entry.getValue();
            String columnNams = "";
            for (TableColumnEntity column : columnEntity) {
                columnNams += column.getPropertyName();
            }
            sb.append("\t<!-- 按" + columnNams + " 唯一索引字段获取对象 -->\r\n");
            sb.append("\t<select id=\"");
            sb.append("getBy");
            sb.append(columnNams);
            sb.append("\"");
            sb.append(" resultType=\"BaseResultMap\">\r\n");
            //查询sql语句
            sb.append("\t\tselect ");
            sb.append(table.getColumnsName() + " ");
            sb.append("from " + table.getTableName() + " \r\n");
            sb.append("\t\twhere \r\n");

            int i = 0;
            for (TableColumnEntity col : columnEntity) {
                sb.append("\t\t");
                if (i > 0) {
                    sb.append("and ");
                }
                sb.append(col.getColumnName() + "= ");
                sb.append("#{" + col.getPropertyNameNew() + ",jdbcType=" + col.getJdbcTypeName() + "} \r\n");
                i++;
            }

            sb.append(" \t</select>\n\n");
        }

        return sb.toString();
    }

    private String queryByExample(TableEntity table) {
        StringBuilder sb = new StringBuilder();
        List<TableColumnEntity> columnsAll = table.getColumnsAll();
        for (TableColumnEntity column : columnsAll) {
            sb.append("\t\t<if test=\"" + column.getPropertyNameNew() + "!= null\">\r\n");
            sb.append("\t\t\tand " + column.getColumnName() + "=#{" + column.getPropertyNameNew() + ",jdbcType=" + column.getJdbcTypeName() + "}\r\n");
            sb.append("\t\t</if>\r\n");
        }


        return sb.toString();
    }
}
