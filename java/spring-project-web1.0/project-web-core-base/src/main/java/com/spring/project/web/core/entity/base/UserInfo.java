package com.spring.project.web.core.entity.base;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author Michael Chow
 * @date 2018/11/01
 */
@Alias("userInfo")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 职位
     */
    private String position;

    /**
     * 排序字段
     */
    private Integer sortNo;

    /**
     * 用户状态 1正常 0锁定
     */
    private Boolean status;

    /**
     * 是否允许删除标记
     */
    private Boolean notDelete;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    /**
     * 最后修改用户
     */
    private String updateUser;

    /**
     * 权限api
     */
    private Map<String, String> menuApis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getNotDelete() {
        return notDelete;
    }

    public void setNotDelete(Boolean notDelete) {
        this.notDelete = notDelete;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Map<String, String> getMenuApis() {
        return menuApis;
    }

    public void setMenuApis(Map<String, String> menuApis) {
        this.menuApis = menuApis;
    }
}