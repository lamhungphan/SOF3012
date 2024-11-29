package com.fpoly.sof3012.controller;

import com.fpoly.sof3012.utils.RestIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
@MultipartConfig
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Điều hướng về trang upload.jsp nếu yêu cầu GET
        response.sendRedirect(request.getContextPath() + "/views/upload.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chỉ định thư mục để lưu trữ file
        String uploadDir = getServletContext().getRealPath("/uploads");
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        // Nhận file upload từ request
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();

        // Lưu file vào thư mục
        File file = new File(uploadDirectory, fileName);
        filePart.write(file.getAbsolutePath());

        // Chuyển thông tin file thành JSON
        String fileInfoJson = String.format("{\"name\": \"%s\", \"type\": \"%s\", \"size\": %d}", fileName, fileType, fileSize);

        // Gửi JSON về client
        RestIO.writeJson(response, fileInfoJson);
    }
}
