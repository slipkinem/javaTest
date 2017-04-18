package test.servlet.filter;

import test.servlet.util.EncodingWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by slipkinem on 2017/4/18.
 */

//@WebFilter(
//        urlPatterns = {"/*"},
//        initParams = {
//                @WebInitParam(name = "ENCODING", value = "UTF-8")
//        }
//)  // 也可以使用web.xml 的Filter配置
public class EncodingFilter implements Filter {
    private String ENCODING;


    public void init(FilterConfig filterConfig) throws ServletException {
        ENCODING = filterConfig.getInitParameter("ENCODING");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        if ("GET".equals(request1.getMethod())) {
            request1 = new EncodingWrapper(request1, ENCODING);
        } else {
            request1.setCharacterEncoding(ENCODING);
        }
        chain.doFilter(request1, response);
    }

    public void destroy() {
    }
}
