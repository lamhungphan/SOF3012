package com.fpoly.sof3012.controller;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.fpoly.sof3012.dao.UserDaoImpl;
import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet({"/user/index",
        "/user/edit/*",
        "/user/create",
        "/user/update",
        "/user/delete",
        "/user/reset",
        "/user/favorites"
})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void handleEdit(HttpServletRequest req, UserDaoImpl dao) {
        String editId = req.getPathInfo().substring(1);
        User user = dao.findById(editId);
        req.setAttribute("item", user);

        List<Favorite> favorites = user.getFavorites();
        req.setAttribute("favorites", favorites);
    }

    private void handleCreateOrUpdate(HttpServletRequest req, HttpServletResponse resp, UserDaoImpl dao, boolean isUpdate) throws ServletException, IOException {
        User user = new User();
        String id = req.getParameter("id").trim();

        if (id.isEmpty() || id.contains(" ")) {
            req.setAttribute("error", "ID không được để trống hoặc chứa khoảng trắng");
            req.getRequestDispatcher("/index.jsp");
            return;
        }

        if (dao.isUserIdExists(id)) {
            req.setAttribute("error", "ID đã tồn tại, vui lòng chọn ID khác");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        try {
            BeanUtils.populate(user, req.getParameterMap());
            String adminCheckbox = req.getParameter("admin");
            user.setAdmin(adminCheckbox != null && adminCheckbox.equals("true"));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        try {
            if (isUpdate) {
                dao.update(user);
            } else {
                dao.create(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("item", new User());
    }

    private void handleDelete(HttpServletRequest req, UserDaoImpl dao) {
        String userId = req.getParameter("id");
        dao.deleteById(userId);
        req.setAttribute("item", new User());
    }

    private void handleListUser(HttpServletRequest req, UserDaoImpl dao) {
        String role = req.getParameter("role");
        String keyword = req.getParameter("filterName");
        List<User> list = dao.findAll(); // Mặc định là tìm tất cả user

        // Tìm theo tên nếu có keyword
        if (keyword != null && !keyword.isEmpty()) {
            list = dao.findUsersByName("%" + keyword + "%");
        }

        // Tìm theo role nếu có role
        if (role != null && !role.equals("All")) {
            boolean isAdmin = role.equals("Admin");
            List<User> roleList = dao.findUsersByRole(isAdmin);

            // Giữ lại user có trong cả danh sách tên và role
            list.retainAll(roleList);
        }
        req.setAttribute("list", list);
    }

    private void handleUserFavorites(HttpServletRequest req, HttpServletResponse resp, UserDaoImpl dao) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        User user = dao.findById(userId);
//
//        if (user != null) {
//            List<Favorite> favorites = user.getFavorites();
//            req.setAttribute("user", user);
//            req.setAttribute("favorites", favorites);
//            req.getRequestDispatcher("/userFavorites.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("message", "Người dùng không tồn tại.");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        UserDaoImpl dao = new UserDaoImpl();
        boolean isActionHandled = false;

        if (path.contains("edit")) {
            handleEdit(req, dao);
        } else if (path.contains("create")) {
            handleCreateOrUpdate(req, resp, dao, false);
        } else if (path.contains("update")) {
            handleCreateOrUpdate(req, resp, dao, true);
        } else if (path.contains("delete")) {
            handleDelete(req, dao);
        } else if (path.contains("favorites")) {
            handleUserFavorites(req, resp, dao);
        } else {
            handleListUser(req, dao);
        }

        if (!isActionHandled) {
            handleListUser(req, dao);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}