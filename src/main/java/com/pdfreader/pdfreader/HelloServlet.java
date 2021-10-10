package com.pdfreader.pdfreader;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {






        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if(isMultipartContent(req)){
            var parts = req.getParts();
//            PDDocument.load()

            byte[] fileBytes = new byte[0];
            
            for(Part p : req.getParts()){
                fileBytes = p.getInputStream().readAllBytes();
            }

            Writer out = new PipedWriter();



        }
    }

    public void destroy() {
    }

    private boolean isMultipartContent(HttpServletRequest request){
        return request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart/form-data");
    }
}