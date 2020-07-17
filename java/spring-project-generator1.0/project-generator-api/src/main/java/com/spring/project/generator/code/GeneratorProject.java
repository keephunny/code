/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.code;

import ch.qos.logback.core.util.FileUtil;
import com.project.utils.lang.FileUtils;
import com.spring.project.generator.utils.ProjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-29 15:54
 */
public class GeneratorProject {
    private final Logger logger = LoggerFactory.getLogger(GeneratorProject.class);
    private final String BasePath = "D:\\test";

    public void init() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("project-yantai");
        projectEntity.setGroupId("com.gscitylifeline");
        projectEntity.setArtifactId("proj-yantai-web");
        projectEntity.setVersionName("1.0-SNAPSHOT");
        projectEntity.setPackaging("pom");
        projectEntity.setPackaging("com.gscitylifeline.proj.yantai");
        projectEntity.setProjectnameComment("烟台项目");

        generatFolder(projectEntity);
        generatorProjectWeb(projectEntity);

    }

    private void generatFolder(ProjectEntity projectEntity) {
        String filePath = BasePath + File.separator + projectEntity.getProjectName();
        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //如果文件目录不存在就创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        file.mkdirs();

        File file1 = new File(filePath + File.separator + projectEntity.getArtifactId());
        file1.mkdir();
        file1 = new File(filePath + File.separator + projectEntity.getArtifactId() + "-api");
        file1.mkdir();
        file1 = new File(filePath + File.separator + projectEntity.getArtifactId() + "-core");
        file1.mkdir();
        file1 = new File(filePath + File.separator + projectEntity.getArtifactId() + "-core-base");
        file1.mkdir();
    }

    private void generatorProjectWeb(ProjectEntity projectEntity) {
        File file = null;
        String content = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/pom.xml.template");
            if (!file.exists() || file.isDirectory()) {
                return;
            }
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<module>../" + projectEntity.getArtifactId() + "-api</module>\r\n");
        sb.append("\t\t<module>../" + projectEntity.getArtifactId() + "-core</module>\r\n");
        sb.append("\t\t<module>../" + projectEntity.getArtifactId() + "-core-base</module>");


        content = content.replace("$${project.name}", projectEntity.getProjectName());
        content = content.replace("$${project.version}", projectEntity.getVersionName());
        content = content.replace("$${project.groupId}", projectEntity.getGroupId());
        content = content.replace("$${project.artifactId}", projectEntity.getArtifactId());
        content = content.replace("$${project.nameComment}", projectEntity.getProjectnameComment());
        content = content.replaceAll(" *\\$\\$\\{modules\\}", sb.toString());

        String filePath = BasePath + File.separator + projectEntity.getProjectName() + File.separator + projectEntity.getArtifactId();

        File newFile = new File(filePath + File.separator + "pom.xml");
        try {
            FileUtils.write(newFile, content, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatorProjectWebApi(ProjectEntity projectEntity) {

    }
}
