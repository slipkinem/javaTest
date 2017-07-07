package org.smart4j.chapter3.controller;


import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.Map;

/**
 * Created by slipkinem on 7/3/2017.
 */

@Controller
public class TestControlelr {

    @Action("get:/hello")
    public View test(Param param) {
        System.out.println(param);
        Map<String, Object> paramMap = param.getParamMap();

        View view = new View("index.jsp");
        view.addModel("test", "hehe");
        for (String key : paramMap.keySet()) {
            view.addModel(key, paramMap.get(key));
        }
        return view;
    }

    @Action("get:/test")
    public View hello() {
        return new View("hello.jsp");
    }
}
