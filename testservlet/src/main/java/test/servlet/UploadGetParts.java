package test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by slipkinem on 2017/4/17.
 */
@MultipartConfig(location = "c:/workspace")
@WebServlet("/multiUpload.do")  // 一定要注意写 "/"
public class UploadGetParts extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        for (Part part : request.getParts()) {
            if (part.getName().startsWith("photo")) {
                String filename = getFilename(part);
                part.write(filename);
            }
        }
    }

    private String getFilename(Part part) {
        String header = part.getHeader("Content-Disposition");
        String filename = header.substring(
                header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\"")
        );
        return filename;
    }

}
