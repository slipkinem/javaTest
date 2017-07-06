package org.smart4j.chapter3.controller;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.bean.View;

/**
 * Created by slipkinem on 7/3/2017.
 */

@Controller
public class TestControlelr {

    @Action("get:/hello")
    public View test () {
        return new View("index.jsp");
    }
}
