/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.utils.lang.FileUtils;
import com.spring.project.generator.code.GeneratorProject;
import com.spring.project.generator.utils.JDBCUtils;
import com.spring.project.generator.utils.TableColumnEntity;
import com.spring.project.generator.utils.TableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-27 15:32
 */
public class AppTest {
    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    public static void main(String[] args) {
        GeneratorProject generatorProject=new GeneratorProject();

        generatorProject.init();
    }
}
