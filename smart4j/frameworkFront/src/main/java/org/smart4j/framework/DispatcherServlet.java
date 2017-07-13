package org.smart4j.framework;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器，MVC核心
 * Created by slipkinem on 7/3/2017.
 */

// servlet配置，拦截所有请求
// loadOnStartup = 0 表示在tomcat启动时候加载此servlet
// 负数表示启动后匹配加载，正数值越小，优先级越高
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    /**
     * <h2>初始化所有Helper类
     * <p>在启动tomcat的时候运行，tomcat初始化会传进来一个config参数
     * <p>此参数保存了初始化的参数以及context对象，初始化的参数通常是配置文件里面的配置
     * <p>servletConfig.getServletConfig 获取的是<servlet><init-param></init-param></servlet>里面的配置
     * <p>
     * <p>servletConfig.getServletContext 获取的是整个web-app的初始值，<context-param></context-param>里面的参数
     *
     * @param servletConfig servlet配置，一个servletConfig仅对一个servlet有用
     * @throws ServletException servlet异常
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        /**
         * 初始化IOC容器等工作
         */
        HelperLoader.init();
        // 获取全局的上下文
        ServletContext servletContext = servletConfig.getServletContext();
        // servlet运行时会调用此接口，可以用来配置servlet对应的JSP文件路径
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 用来配置静态资源的路径，default一般会用来在web.xml里面配置静态资源类型
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    /**
     * 从公共服务方法接收标准的HTTP请求，并将它们发送到这个类中定义的doXXX方法。此方法是Servlet的特定于http的版本。服务方法。不需要重写这个方法。
     * 在这里重写就是让所有的请求都被拦截，而不被派发到doXXX方法中
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     * @see javax.servlet.Servlet#service
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            // 获取参数 url/path ? key=value
            Enumeration<String> paramNames = request.getParameterNames();

            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            // 获取request body(请求体)里面的参数  @RequestBody
            // get 参数在 url ? k = v存储
            // post:
            // request payload form提交 contentType 为 x-www-form-urlencoded 数据用k-v存储直接用parameter获取
            // request payload ajax传输，ContentType 为 text/plain;charset=UTF-8，由于数据格式不固定，
            // 所以需要先读取原始的数据流，然后进行处理
            // 这里不做json的处理, 处理这种 res.send('key=val&k=v&k=v')
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));

            if (StringUtil.isNotEmpty(body)) {
                String[] params = StringUtil.splitString(body, '&');
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {

                        String[] array = StringUtil.splitString(param, '=');

                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }

            Method actionMethod = handler.getActionMethod();
            Object result;
            if (CollectionUtil.isNotEmpty(paramMap)) {
                Param param = new Param(paramMap);
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            } else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            }

            if (result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();

                        if (CollectionUtil.isNotEmpty(model)) {
                            for (Map.Entry<String, Object> entry : model.entrySet()) {
                                request.setAttribute(entry.getKey(), entry.getValue());
                            }
                        }

                        String fullPath = ConfigHelper.getAppJspPath() + path;
                        request.getRequestDispatcher(fullPath).forward(request, response);
                    }
                }

            } else if (result instanceof Data) {
                Data data = (Data) result;
                Object model = data.getModel();

                if (model != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }

}
