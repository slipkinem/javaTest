package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图对象，可以是JSP
 * Created by slipkinem on 7/3/2017.
 */
public class View {
    // 视图路径
    private String path;

    private Map<String, Object> model = new HashMap<String, Object>();

    public View(String path) {
        this.path = path;
    }
    // 向model添加数据
    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public String getPath() {
        return path;
    }
}
