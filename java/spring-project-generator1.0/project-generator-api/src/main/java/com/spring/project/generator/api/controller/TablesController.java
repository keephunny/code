/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.api.controller;

import com.project.utils.lang.FileUtils;
import com.project.utils.lang.StringUtils;
import com.spring.project.generator.code.GeneratorControllerCode;
import com.spring.project.generator.code.GeneratorEntityCode;
import com.spring.project.generator.code.GeneratorMapperXml;
import com.spring.project.generator.code.GeneratorServiceCode;
import com.spring.project.generator.core.entity.Columns;
import com.spring.project.generator.core.entity.Tables;
import com.spring.project.generator.core.service.ColumnsService;
import com.spring.project.generator.core.service.TablesService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 10:52
 */
@RestController
@RequestMapping(value = "/tables")
public class TablesController {
    private final Logger logger = LoggerFactory.getLogger(TablesController.class);
    @Autowired
    private TablesService tablesService;
    @Autowired
    private ColumnsService columnsService;

    private final String BasePath = "D:\\test\\";


    @GetMapping("/queryAll")
    public List<Tables> queryAll() {
        List<Tables> queryList = tablesService.queryBySchema("jx_test");

        GeneratorControllerCode generatorControllerCode = new GeneratorControllerCode();
        GeneratorServiceCode generatorServiceCode = new GeneratorServiceCode();
        GeneratorMapperXml generatorMapperXml = new GeneratorMapperXml();
        GeneratorEntityCode generatorEntityCode = new GeneratorEntityCode();
//        generatorControllerCode.init(queryList);
//        generatorServiceCode.init(queryList);

//            List<Columns> queryColumns = columnsService.queryBySchema(table.getTableSchema(), table.getTableName());

        generatorMapperXml.init(queryList);
//        generatorEntityCode.init(queryList);

        return queryList;
    }

    private void generator(List<Tables> queryList) {
        String className;
        for (Tables tables : queryList) {
            logger.info("{} {} {} ", tables.getTableName(), StringUtils.underToCamelFirst(tables.getTableName()), tables.getTableComment());
            className = StringUtils.underToCamelFirst(tables.getTableName());
            File file = new File(BasePath + "com//spring//project//" + className + "Controller.java");

            StringBuilder sb = new StringBuilder();
            sb.append("package com.spring.project.web.api.controller;\r\n");
            sb.append("import com.github.pagehelper.PageInfo;\r\n");
            sb.append("import com.spring.project.web.core.entity.base.UserInfo;\r\n");
            sb.append("import com.spring.project.web.core.service.UserInfoService;\r\n");
            sb.append("import com.spring.project.web.vo.RespResult;\r\n");
            sb.append("import com.spring.project.web.vo.UserInfoQueryPageVo;\r\n");
            sb.append("import io.swagger.annotations.Api;\r\n");
            sb.append("import io.swagger.annotations.ApiOperation;\r\n");
            sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
            sb.append("import org.springframework.http.HttpStatus;\r\n");
            sb.append("import org.springframework.validation.BindingResult;\r\n");
            sb.append("import org.springframework.web.bind.annotation.DeleteMapping;\r\n");
            sb.append("import org.springframework.web.bind.annotation.GetMapping;\r\n");
            sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
            sb.append("import org.springframework.web.bind.annotation.RestController;\r\n");
            sb.append("import javax.servlet.http.HttpServletResponse;\r\n");
            sb.append("import javax.validation.Valid;\r\n");
            sb.append("import java.util.ArrayList;\r\n");
            sb.append("import java.util.Date;\r\n");
            sb.append("import java.util.List;\r\n");
            sb.append("import java.util.UUID;\r\n");
            sb.append("@RestController\r\n");
            sb.append("@RequestMapping(value = \"/" + StringUtils.underToCamel(tables.getTableName()) + "\")\r\n");
            sb.append("public class " + className + "Controller extends BaseController {\r\n");

            sb.append("}");
            try {
                FileUtils.write(file, sb.toString(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
