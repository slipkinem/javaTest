package test.servlet.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * Created by slipkinem on 2017/4/18.
 */
public class CompressionWrapper extends HttpServletResponseWrapper {
    private GZipServletOutputStream gZipServletOutputStream;
    private PrintWriter printWriter;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response 返回值
     * @throws IllegalArgumentException if the response is null
     */
    public CompressionWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) throw new IllegalStateException();
        if (gZipServletOutputStream == null)
            gZipServletOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());

        return gZipServletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (gZipServletOutputStream != null) throw new IllegalStateException();

        if (printWriter == null) {
            gZipServletOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(gZipServletOutputStream, getResponse().getCharacterEncoding());
            printWriter = new PrintWriter(outputStreamWriter);
        }

        return printWriter;

    }

    @Override
    public void setContentLength(int length) {
    }

    public GZIPOutputStream getgZipServletOutputStream() {
        if (this.gZipServletOutputStream == null) {
            return null;
        }
        return this.gZipServletOutputStream.getGzipOutputStream();
    }
}
