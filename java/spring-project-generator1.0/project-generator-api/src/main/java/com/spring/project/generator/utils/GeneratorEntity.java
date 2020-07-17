/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

import java.io.Serializable;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 17:33
 */
public class GeneratorEntity implements Serializable {

    @Generate("author")
    private String author;

    @Generate("date")
    private String date;

    @Generate("time")
    private String time;

    @Generate("date.year")
    private String dateYear;

    @Generate("PackageBaseName")
    private String packageBaseName;

    @Generate("packageControllerName")
    private String packageControllerName;

    @Generate("packageServiceName")
    private String packageServiceName;

    @Generate("packageServiceImplName")
    private String packageServiceImplName;

    @Generate("packageMapperName")
    private String packageMapperName;

    @Generate("ClassName")
    private String className;

    @Generate("className")
    private String classNameNew;

    @Generate("ClassControllerName")
    private String classControllerName;

    @Generate("classControllerName")
    private String classControllerNameNew;

    @Generate("ClassServiceName")
    private String classServiceName;

    @Generate("classServiceName")
    private String classServiceNameNew;

    @Generate("ClassServiceImplName")
    private String classServiceImplName;

    @Generate("ClassServiceImplName")
    private String classServiceImplNameNew;

    @Generate("classCommit")
    private String classCommit;

    @Generate("classCommitAll")
    private String classCommitAll;

    private String filePath;
    private String fileName;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateYear() {
        return dateYear;
    }

    public void setDateYear(String dateYear) {
        this.dateYear = dateYear;
    }

    public String getPackageBaseName() {
        return packageBaseName;
    }

    public void setPackageBaseName(String packageBaseName) {
        this.packageBaseName = packageBaseName;
        this.packageControllerName = packageBaseName + ".api.controller";
        this.packageServiceName = packageBaseName + ".core.service";
        this.packageServiceImplName = packageBaseName + "core.service.impl";
        this.packageMapperName = packageBaseName + ".mapper";
    }

    public String getPackageControllerName() {
        return packageControllerName;
    }

    public void setPackageControllerName(String packageControllerName) {
        this.packageControllerName = packageControllerName;
    }

    public String getPackageServiceName() {
        return packageServiceName;
    }

    public void setPackageServiceName(String packageServiceName) {
        this.packageServiceName = packageServiceName;
    }

    public String getPackageServiceImplName() {
        return packageServiceImplName;
    }

    public void setPackageServiceImplName(String packageServiceImplName) {
        this.packageServiceImplName = packageServiceImplName;
    }

    public String getPackageMapperName() {
        return packageMapperName;
    }

    public void setPackageMapperName(String packageMapperName) {
        this.packageMapperName = packageMapperName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        this.classControllerName = className + "Controller";
        this.classServiceName = className + "Service";
        this.classServiceImplName = className + "ServiceImpl";
    }

    public String getClassNameNew() {
        return classNameNew;
    }

    public void setClassNameNew(String classNameNew) {
        this.classNameNew = classNameNew;
        this.classControllerNameNew = classNameNew + "Controller";
        this.classServiceNameNew = classNameNew + "Service";
        this.classServiceImplNameNew = classNameNew + "ServiceImpl";
    }

    public String getClassControllerName() {
        return classControllerName;
    }

    public void setClassControllerName(String classControllerName) {
        this.classControllerName = classControllerName;
    }

    public String getClassControllerNameNew() {
        return classControllerNameNew;
    }

    public void setClassControllerNameNew(String classControllerNameNew) {
        this.classControllerNameNew = classControllerNameNew;
    }

    public String getClassServiceName() {
        return classServiceName;
    }

    public void setClassServiceName(String classServiceName) {
        this.classServiceName = classServiceName;
    }

    public String getClassServiceNameNew() {
        return classServiceNameNew;
    }

    public void setClassServiceNameNew(String classServiceNameNew) {
        this.classServiceNameNew = classServiceNameNew;
    }

    public String getClassServiceImplName() {
        return classServiceImplName;
    }

    public void setClassServiceImplName(String classServiceImplName) {
        this.classServiceImplName = classServiceImplName;
    }

    public String getClassServiceImplNameNew() {
        return classServiceImplNameNew;
    }

    public void setClassServiceImplNameNew(String classServiceImplNameNew) {
        this.classServiceImplNameNew = classServiceImplNameNew;
    }

    public String getClassCommit() {
        return classCommit;
    }

    public void setClassCommit(String classCommit) {
        this.classCommit = classCommit;
    }

    public String getClassCommitAll() {
        return classCommitAll;
    }

    public void setClassCommitAll(String classCommitAll) {
        this.classCommitAll = classCommitAll;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
