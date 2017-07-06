package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Controller类 将@Controller类和里面包括@Action方法放进ACTION_MAP中
 * Created by slipkinem on 7/3/2017.
 */
public class ControllerHelper {
    /**
     * {requestMethod, requestPath} => { controllerClass, Method }
     * 存储 request => handler的映射
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        // 获取所有的带@Controller的类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            // 遍历所有的Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取controller类里面所有的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        // 如果方法有@Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            // 获取action对应的实例
                            Action action = method.getAnnotation(Action.class);
                            // 拿到action里面声明的值
                            // @Action('post:api/test')
                            String mapping = action.value();
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     * @param requestMethod 请求方法
     * @param requestPath 请求路径
     * @return handler
     */
    public static Handler getHandler (String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
