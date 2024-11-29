package com.fpoly.sof3012.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/account/change-password", "/account/edit-profile", "/video/like/*", "/video/share/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Lấy session hiện tại
        HttpSession session = httpRequest.getSession(false);

        // Kiểm tra đăng nhập
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        String currentUserRole = (session != null) ? (String) session.getAttribute("role") : null;

        // URL đang truy cập
        String requestURI = httpRequest.getRequestURI();

        // Yêu cầu đăng nhập nếu chưa đăng nhập
        if (!isLoggedIn) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        // Nếu truy cập URL bắt đầu bằng "/admin", yêu cầu vai trò admin
        if (requestURI.startsWith(httpRequest.getContextPath() + "/admin") && !"admin".equals(currentUserRole)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource.");
            return;
        }

        // Cho phép tiếp tục nếu đã đăng nhập và phân quyền hợp lệ
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp filter nếu cần
    }
}
