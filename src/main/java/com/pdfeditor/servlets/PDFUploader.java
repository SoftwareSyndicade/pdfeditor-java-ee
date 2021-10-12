package com.pdfeditor.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pdfeditor.datamodels.RecentUpload;
import com.pdfeditor.responsemodels.PDFUploadResponse;
import com.pdfeditor.sqlserver.SQLServerManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PDFUploader", value = "/pdf-uploader")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class PDFUploader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List<RecentUpload> recentUploads;
        SQLServerManager manager = new SQLServerManager();
        try {
            recentUploads = manager.fetchUploads();
            recentUploads.forEach(upload ->{
                upload.setDOC_URL(URI.create(getBaseUrl(request)) + "/" + upload.getFILE_NAME());
            });

            PrintWriter pw = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            pw.println(mapper.writeValueAsString(recentUploads));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
