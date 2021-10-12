package com.pdfeditor.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pdfeditor.responsemodels.PDFUploadResponse;
import com.pdfeditor.sqlserver.SQLServerManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

@WebServlet(name = "PDFUploader", value = "/pdf-uploader")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class PDFUploader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isMultipartContent(request)){
            try{
                Part filePart = request.getPart("file");
                String fileName = filePart.getSubmittedFileName();
                for (Part part : request.getParts()) {
                    part.write(getServletContext().getRealPath("/") + fileName);
                }

                SQLServerManager manager = new SQLServerManager();
                manager.saveUpload(fileName);

                PDFUploadResponse uploadResponse = new PDFUploadResponse(){{
                   setPDF_URL(URI.create(getBaseUrl(request)) + "/" + fileName);
                }};
                ObjectMapper mapper = new ObjectMapper();
                PrintWriter out = response.getWriter();
                out.println(mapper.writeValueAsString(uploadResponse));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
    }

    private boolean isMultipartContent(HttpServletRequest request){
        return request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart/form-data");
    }
}
