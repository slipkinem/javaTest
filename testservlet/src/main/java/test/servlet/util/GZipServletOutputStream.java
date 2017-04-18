package test.servlet.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by slipkinem on 2017/4/18.
 */
public class GZipServletOutputStream extends ServletOutputStream {
    private GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream (ServletOutputStream servletOutputStream) throws IOException {
        this.gzipOutputStream = new GZIPOutputStream(servletOutputStream);
    }

    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }

    public GZIPOutputStream getGzipOutputStream() {
        return gzipOutputStream;
    }

    public boolean isReady() {
        return false;
    }

    public void setWriteListener(WriteListener writeListener) {

    }
}
