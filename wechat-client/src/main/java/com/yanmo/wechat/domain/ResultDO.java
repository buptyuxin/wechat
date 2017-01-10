package com.yanmo.wechat.domain;

import com.yanmo.wechat.domain.error.Errors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class ResultDO<T> implements Serializable {

    private static final long serialVerisonUID = 6750852764071535411L;
    private T module;
    private boolean success = true;
    private List<Errors> errorList;

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

    public List<Errors> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Errors> errorList) {
        this.errorList = errorList;
    }

    public void addError(Errors error) {
        errorList.add(error);
        if (success) {
            success = false;
        }
    }
}
