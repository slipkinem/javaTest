package org.smart4j.chapter3.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.Map;

/**
 * testController
 * Created by slipkinem on 7/3/2017.
 */

@Controller
public class TestControlelr {
    private  static final Logger logger = LoggerFactory.getLogger(TestControlelr.class);

    @Action("get:/")
    public View index () {
        return new View("index.jsp");
    }

    @Action("get:/hello")
    public View test(Param param) {
        logger.info(String.valueOf(param.getParamMap()));
        Map<String, Object> paramMap = param.getParamMap();

        View view = new View("test.jsp");
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
