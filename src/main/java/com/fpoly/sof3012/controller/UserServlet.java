package com.fpoly.sof3012.controller;

import java.io.*;
import java.util.List;

import com.fpoly.sof3012.dao.UserManager;
import com.fpoly.sof3012.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/index")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserManager userManager = new UserManager();

        int totalRecords = userManager.getTotalRecords();
        int recordsPerPage = 5;
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // Lấy trang hiện tại từ tham số trong yêu cầu
        int pageNumber = 1; // Mặc định là trang 1
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                pageNumber = 1; // Nếu có lỗi, quay lại trang 1
            }
        }

        List<User> listDefaultInTable = userManager.findUsersByPage(pageNumber, recordsPerPage);
        req.setAttribute("list", listDefaultInTable); // Cập nhật danh sách cho trang hiện tại
        req.setAttribute("totalPages", totalPages); // Đưa số trang vào yêu cầu
        req.setAttribute("currentPage", pageNumber); // Đưa trang hiện tại vào yêu cầu

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}