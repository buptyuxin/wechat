package com.yanmo.wechat.domain;

/**
 * Created by yanmo.yx on 2015/4/30.
 */
public class TestDO {
    private String name;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o instanceof TestDO) {
            return id.equals(((TestDO) o).getId());
        }
        return false;
    }

}
