/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.code;

import com.project.utils.lang.DateTimeUtils;
import com.project.utils.lang.FileUtils;
import com.project.utils.lang.StringUtils;
import com.spring.project.generator.core.entity.Columns;
import com.spring.project.generator.core.entity.Tables;
import com.spring.project.generator.utils.Generate;
import com.spring.project.generator.utils.GeneratorEntity;
import com.spring.project.generator.utils.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 16:31
 */
public class GeneratorEntityCode {
    private final Logger logger = LoggerFactory.getLogger(GeneratorControllerCode.class);
    private final String BasePath = "D:\\test\\";

    public void init(List<Tables> tables) {

        //TODO 先清空目标文件
        File file = null;
        String content = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/UserInfo.java.template");
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
        for (Tables table : tables) {
            List<Columns> queryColumns = queryColumn(table.getTableName());
            generator(queryColumns, content, table);
        }
    }

    private List<Columns> queryColumn(String tableName) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        List<Columns> columnsList = jdbcUtils.getColumns(tableName);

        return columnsList;
    }

    private void generator(List<Columns> columns, String content, Tables tables) {
        Columns columnsTable = columns.get(0);
        Date currentDate = new Date();
        GeneratorEntity generatorEntity = new GeneratorEntity();
        generatorEntity.setAuthor("wangqiang");
        generatorEntity.setDateYear(DateTimeUtils.getFormatYear(currentDate));
        generatorEntity.setDate(DateTimeUtils.getFormatDate(currentDate));
        generatorEntity.setTime(DateTimeUtils.getFormatTime(currentDate));
        generatorEntity.setClassName(StringUtils.underToCamelFirst(tables.getTableName()));
        generatorEntity.setClassNameNew(StringUtils.underToCamel(tables.getTableName()));
        generatorEntity.setClassCommit(tables.getTableComment());
        generatorEntity.setPackageBaseName("com.spring.project.web");

        String className = StringUtils.underToCamelFirst(columnsTable.getTableName());
        String classNameNew = StringUtils.underToCamel(columnsTable.getTableName());
        String tableName = columnsTable.getTableName();
        String packageMapperName = "com.spring.project.web.core.entity";
        String keyColumn = columnsTable.getColumnKey();
        String keyProperty = StringUtils.underToCamel(columnsTable.getColumnKey());

        try {

            StringBuilder sb = new StringBuilder();
            StringBuilder sbSetGet = new StringBuilder();
            String columnProperty = "", columnPropertySetGet;
            sb.append("\r\n");
            sbSetGet.append("\r\n");
            for (Columns column : columns) {
                String columnName = StringUtils.underToCamelFirst(column.getColumnName());
                String columnNameNew = StringUtils.underToCamel(column.getColumnName());

                sb.append("\t/**\r\n");
                sb.append("\t* " + column.getColumnComment() + "\r\n");
                sb.append("\t*/\r\n");
                sb.append("\tprivate ");
                sb.append(column.getShortJavaType() + " ");
                sb.append(columnNameNew);
                sb.append(";\r\n\r\n");


                sbSetGet.append("\tpublic " + column.getShortJavaType() + " get" + columnName + "() {\r\n");
                sbSetGet.append("\t\treturn " + columnNameNew + ";\r\n");
                sbSetGet.append("\t}\r\n");
                sbSetGet.append("\tpublic void setId(Integer " + columnNameNew + ") {\r\n ");
                sbSetGet.append("\t\tthis." + columnNameNew + " = " + columnNameNew + ";\r\n");
                sbSetGet.append("\t}\r\n");


            }
            columnProperty = sb.toString();
            columnPropertySetGet = sbSetGet.toString();

            content = content.replace("${columnProperty}", columnProperty);
            content = content.replace("${columnPropertySetGet}", columnPropertySetGet);


            Field[] fields = GeneratorEntity.class.getDeclaredFields();
            for (Field field : fields) {
                String replaceName = "";
                String getname = "", value = "";
                Generate generate = field.getAnnotation(Generate.class);
                if (generate == null || StringUtils.isEmpty(generate.value())) {
                    logger.info("{} generator 为空", field.getName());
                    continue;
                }
                replaceName = generate.value();
                getname = field.getName();
                getname = "get" + getname.substring(0, 1).toUpperCase() + getname.substring(1);
                Method m = generatorEntity.getClass().getMethod(getname);
                value = (String) m.invoke(generatorEntity);
                if (value == null) {
                    continue;
                }
                content = content.replace("${" + replaceName + "}", value);
            }

            String filePath = "project-web-core/src/main/java/com/spring/project/web/core/entity/";
            File newFile = new File(BasePath + filePath + className + ".java");
            FileUtils.write(newFile, content, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
