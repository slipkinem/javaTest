package test.servlet.filter;

import test.servlet.util.CompressionWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by slipkinem on 2017/4/18.
 */
//@WebFilter(filterName = "CompressionFilter", urlPatterns = {"/*"})
public class CompressionFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String encoding = request1.getHeader("accept-encoding");
        if (encoding != null && encoding.contains("gzip")) {
            CompressionWrapper compressionWrapper = new CompressionWrapper(response1);
            compressionWrapper.setHeader("content-encoding", "gzip");
            chain.doFilter(request, compressionWrapper);

            GZIPOutputStream gzipOutputStream = compressionWrapper.getgZipServletOutputStream();

            if (gzipOutputStream != null) {
                gzipOutputStream.finish();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
