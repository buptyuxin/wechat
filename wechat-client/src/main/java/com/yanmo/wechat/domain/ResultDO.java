package com.yanmo.wechat.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class ResultDO<T> implements Serializable {

    private static final long serialVerisonUID = 6750852764071535411L;
    private T module;
    private boolean success = true;
    private List<Error> errorList;

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    public void addError(Error error) {
        errorList.add(error);
        if (success) {
            success = false;
        }
    }
}
