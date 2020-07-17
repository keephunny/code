/*
 * Copyright (c) 2020.
 */
package com.spring.project.web.core.mapper.sql;


import com.spring.project.web.core.entity.base.UserInfo;

import java.util.List;

/**
 * 生成sql语句
 * 所有语句不要分号结束，预留分页插件
 *
 * @author 汪强
 * 创建时间 2020-04-23 9:52
 */
public class UserInfoSql {
    private final String tableName = "user_info";


    public String deleteByIds(int[] ids) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + tableName);
        sb.append(" ");
        sb.append("where id in(");
        int len = ids.length;
        //TODO 解决sql注入 int主键和字符主键
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(ids[i]);
        }
        sb.append(")");
        return sb.toString();
    }

    public String updateById(UserInfo userInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("update  " + tableName + " ");
        sb.append("set user_name = #{userName,jdbcType=VARCHAR}, ");
        sb.append("user_pwd = #{userPwd,jdbcType=VARCHAR}, ");
        sb.append("real_name = #{realName,jdbcType=VARCHAR}, ");
        sb.append("phone = #{phone,jdbcType=VARCHAR}, ");
        sb.append("department_id = #{departmentId,jdbcType=INTEGER}, ");
        sb.append("position = #{position,jdbcType=VARCHAR}, ");
        sb.append("sort_no = #{sortNo,jdbcType=INTEGER}, ");
        sb.append("status = #{status,jdbcType=BIT}, ");
        sb.append("not_delete = #{notDelete,jdbcType=BIT}, ");
        sb.append("role_id = #{roleId,jdbcType=INTEGER}, ");
        sb.append("create_time = #{createTime,jdbcType=TIMESTAMP}, ");
        sb.append("create_user = #{createUser,jdbcType=VARCHAR}, ");
        sb.append("update_time = #{updateTime,jdbcType=TIMESTAMP}, ");
        sb.append("update_user = #{updateUser,jdbcType=VARCHAR} ");
        sb.append("where id = #{id,jdbcType=INTEGER} ");
        return sb.toString();
    }

    public String insert(UserInfo userInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + tableName + " ");
        sb.append("(user_name, user_pwd, real_name, ");
        sb.append("phone, department_id, position, ");
        sb.append("sort_no, status, not_delete, ");
        sb.append("role_id, create_time, create_user, ");
        sb.append("update_time, update_user ");
        sb.append(") ");
        sb.append("values (#{userName,jdbcType=VARCHAR}, #{userPwd,jdbcType=VARCHAR}, #{realName,jdbcType=VARCHAR}, ");
        sb.append("#{phone,jdbcType=VARCHAR}, #{departmentId,jdbcType=INTEGER}, #{position,jdbcType=VARCHAR}, ");
        sb.append("#{sortNo,jdbcType=INTEGER}, #{status,jdbcType=BIT}, #{notDelete,jdbcType=BIT}, ");
        sb.append("#{roleId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, #{createUser,jdbcType=VARCHAR}, ");
        sb.append("#{updateTime,jdbcType=TIMESTAMP}, #{updateUser,jdbcType=VARCHAR} ");
        sb.append(") ");
        return sb.toString();
    }

    public String insertBatch(List<UserInfo> userInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + tableName + " ");
        sb.append("(user_name, user_pwd, real_name, ");
        sb.append("phone, department_id, position, ");
        sb.append("sort_no, status, not_delete, ");
        sb.append("role_id, create_time, create_user, ");
        sb.append("update_time, update_user ");
        sb.append(") ");
        sb.append("values");
        sb.append("<foreach collection=\"list\" item=\"item\" separator=\",\">");
        sb.append("(#{item.userName,jdbcType=VARCHAR}, #{item.userPwd,jdbcType=VARCHAR}, #{item.realName,jdbcType=VARCHAR}, ");
        sb.append("#{item.phone,jdbcType=VARCHAR}, #{item.departmentId,jdbcType=INTEGER}, #{item.position,jdbcType=VARCHAR}, ");
        sb.append("#{item.sortNo,jdbcType=INTEGER}, #{item.status,jdbcType=BIT}, #{item.notDelete,jdbcType=BIT}, ");
        sb.append("#{item.roleId,jdbcType=INTEGER}, #{item.createTime,jdbcType=TIMESTAMP}, #{item.createUser,jdbcType=VARCHAR}, ");
        sb.append("#{item.updateTime,jdbcType=TIMESTAMP}, #{item.updateUser,jdbcType=VARCHAR} ");
        sb.append(") ");
        sb.append(" </foreach>");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
