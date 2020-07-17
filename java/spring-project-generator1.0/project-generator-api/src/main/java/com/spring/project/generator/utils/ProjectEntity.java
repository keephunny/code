/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-26 17:47
 */
public class ProjectEntity {
    private String projectnameComment;
    private String projectName;
    private String groupId;
    private String artifactId;
    private String packaging;
    private String versionName;

    private ProjectEntity parentProject;
    private List<ProjectEntity> moduls;

    public String getProjectnameComment() {
        return projectnameComment;
    }

    public void setProjectnameComment(String projectnameComment) {
        this.projectnameComment = projectnameComment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public ProjectEntity getParentProject() {
        return parentProject;
    }

    public void setParentProject(ProjectEntity parentProject) {
        this.parentProject = parentProject;
    }

    public List<ProjectEntity> getModuls() {
        return moduls;
    }

    public void setModuls(List<ProjectEntity> moduls) {
        this.moduls = moduls;
    }
}
