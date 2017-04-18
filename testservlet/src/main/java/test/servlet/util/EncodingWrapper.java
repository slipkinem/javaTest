package test.servlet.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * Created by slipkinem on 2017/4/18.
 */
public class EncodingWrapper extends HttpServletRequestWrapper {
    private String ENCODEING;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request 请求
     * @throws IllegalArgumentException if the request is null
     */
    public EncodingWrapper(HttpServletRequest request, String ENCODEING) {
        super(request);
        this.ENCODEING = ENCODEING;
    }

    @Override
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);
        if (value != null) {
            try {
                byte[] bytes = value.getBytes("ISO-8859-1");
                value = new String(bytes, ENCODEING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
