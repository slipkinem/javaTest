package test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by slipkinem on 2017/4/17.
 */
@MultipartConfig
@WebServlet("/upload.do")
public class uploadGetPart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8"); // 防止文件名乱码
        Part part = request.getPart("photo");
        String filename = getFilename(part);
        writeTo(filename, part);
    }

    private String getFilename(Part part) {
        String header = part.getHeader("Content-Disposition");
        String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        return filename;
    }

    private void writeTo(String filename, Part part) throws IOException {
        InputStream inputStream = part.getInputStream();
        OutputStream outputStream = new FileOutputStream("C:/workspace/" + filename);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
}
