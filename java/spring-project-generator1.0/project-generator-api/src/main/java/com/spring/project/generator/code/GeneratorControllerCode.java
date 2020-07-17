/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.code;

import com.project.utils.lang.DateTimeUtils;
import com.project.utils.lang.FileUtils;
import com.project.utils.lang.StringUtils;
import com.spring.project.generator.core.entity.Tables;
import com.spring.project.generator.utils.Generate;
import com.spring.project.generator.utils.GeneratorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 16:30
 */
public class GeneratorControllerCode {
    private final Logger logger = LoggerFactory.getLogger(GeneratorControllerCode.class);
    private final String BasePath = "D:\\test\\";

    public void init(List<Tables> queryList) {
        //TODO 先清空目标文件
        File file = null;
        String content = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/Controller.template");
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
        long n1 = System.currentTimeMillis();
        for (Tables table : queryList) {
            generator(table, content);
        }
        long n2 = System.currentTimeMillis();
        System.out.println(((n2 - n1) / 1000d));
    }


    private void generator(Tables tables, String content) {
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
        String filePath = generatorEntity.getPackageControllerName().replace(".", File.separator) + File.separator;
        generatorEntity.setFilePath("project-web-api" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + filePath);
        generatorEntity.setFileName(generatorEntity.getClassName() + "Controller.java");

        try {
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

            File newFile = new File(BasePath + generatorEntity.getFilePath() + generatorEntity.getFileName());
            FileUtils.write(newFile, content, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void generator2(Tables tables, String content) {
        Date currentDate = new Date();
        String ClassName = StringUtils.underToCamelFirst(tables.getTableName());
        String className = StringUtils.underToCamel(tables.getTableName());
        String classCommit = tables.getTableComment();

        String dateYear = DateTimeUtils.getFormatYear(currentDate);
        String date = DateTimeUtils.getFormatDate(currentDate);
        String time = DateTimeUtils.getFormatTime(currentDate);
        String author = "wang";
        String ServiceName = ClassName + "Service";
        String serviceName = className + "Service";
        String ClassControllerName = ClassName + "Controller";
        String classControllerName = className + "Controller";
        String packageName = "com.spring.project.web.api";
        String packageFileName = "project-controller//" + "com//spring//project//web//api//";

        try {
            content = content.replace("${date.year}", dateYear);
            content = content.replace("${date}", date);
            content = content.replace("${time}", time);
            content = content.replace("${packageName}", packageName);
            content = content.replace("${ClassName}", ClassName);
            content = content.replace("${className}", className);
            content = content.replace("${ClassControllerName}", ClassControllerName);
            content = content.replace("${classControllerName}", classControllerName);
            content = content.replace("${ServiceName}", serviceName);
            content = content.replace("${ServiceName}", ServiceName);
            content = content.replace("${classCommit}", classCommit);
            content = content.replace("${author}", author);

            File newFile = new File(BasePath + packageFileName + className + "Controller.java");
            FileUtils.write(newFile, content, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
