package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 请求信息
 * Created by slipkinem on 7/3/2017.
 */
public class Request {
    private String requestMethod;

    private String requestPath;

    /**
     * 构造函数，设置请求的路径和请求的方法
     * @param requestMethod 请求方法
     * @param requestPath 请求路径
     */
    public Request (String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod () {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public int hashCode () {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }
}
