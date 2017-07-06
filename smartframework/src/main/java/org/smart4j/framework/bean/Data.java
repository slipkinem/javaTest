package org.smart4j.framework.bean;

/**
 * 返回JSON，处理JSON
 * Created by slipkinem on 7/3/2017.
 */
public class Data {
    private Object model;

    public Data (Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
