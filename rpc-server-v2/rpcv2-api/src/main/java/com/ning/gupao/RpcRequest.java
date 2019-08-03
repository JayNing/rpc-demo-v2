package com.ning.gupao;

import java.io.Serializable;

/**
 * @Author JAY
 * @Date 2019/8/3 14:41
 * @Description TODO
 **/
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String className;

    private String methodName;

    private String version;

    private Object[] params;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
