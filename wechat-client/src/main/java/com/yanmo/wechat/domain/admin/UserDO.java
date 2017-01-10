package com.yanmo.wechat.domain.admin;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class UserDO {
    private String name;
    private String pwd;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
