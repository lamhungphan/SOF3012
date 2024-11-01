package com.fpoly.sof3012.controller;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.fpoly.sof3012.dao.UserManager;
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
        "/user/reset"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void handleEdit(HttpServletRequest req, UserManager dao) {
        String editId = req.getPathInfo().substring(1);
        User user = dao.findById(editId);
        req.setAttribute("item", user);
    }

    private void handleCreateOrUpdate(HttpServletRequest req, UserManager dao, boolean isUpdate) {
        User user = new User();
//        user.setId(req.getParameter("id"));
//        user.setPassword(req.getParameter("password"));
//        user.setFullname(req.getParameter("fullname"));
//        user.setEmail(req.getParameter("email"));
//        user.setAdmin(req.getParameter("admin") != null);
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        try {
            if (isUpdate) {
                dao.update(user);
            } else {
                dao.create(user);
            }
        } catch (Exception e) {
            req.setAttribute("message", isUpdate ? "Cập nhật thất bại" : "Trùng khóa chính");
        }
        req.setAttribute("item", new User());
    }

    private void handleDelete(HttpServletRequest req, UserManager dao) {
        String userId = req.getParameter("id");
        dao.deleteById(userId);
        req.setAttribute("item", new User());
    }

    private void handleListUser(HttpServletRequest req, UserManager dao) {
        String role = req.getParameter("role");
        List<User> list;

        if (role != null && !role.equals("All")) {
            boolean isAdmin = role.equals("Admin");
            list = dao.findUsersByRole(isAdmin);
        } else {
            list = dao.findAll();
        }
        req.setAttribute("list", list);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        UserManager dao = new UserManager();

        if (path.contains("edit")) {
            handleEdit(req, dao);
        } else if (path.contains("create")) {
            handleCreateOrUpdate(req, dao, false);
        } else if (path.contains("update")) {
            handleCreateOrUpdate(req, dao, true);
        } else if (path.contains("delete")) {
            handleDelete(req, dao);
        }
        handleListUser(req, dao);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}