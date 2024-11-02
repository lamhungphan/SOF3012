package com.fpoly.sof3012.controller;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.fpoly.sof3012.dao.UserDaoImp;
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

    private void handleEdit(HttpServletRequest req, UserDaoImp dao) {
        String editId = req.getPathInfo().substring(1);
        User user = dao.findById(editId);
        req.setAttribute("item", user);
    }

    private void handleCreateOrUpdate(HttpServletRequest req, UserDaoImp dao, boolean isUpdate) {
        User user = new User();

        try {
            BeanUtils.populate(user, req.getParameterMap());
            String adminCheckbox = req.getParameter("admin");
            user.setAdmin(adminCheckbox != null && adminCheckbox.equals("true"));
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

    private void handleDelete(HttpServletRequest req, UserDaoImp dao) {
        String userId = req.getParameter("id");
        dao.deleteById(userId);
        req.setAttribute("item", new User());
    }

    private void handleListUser(HttpServletRequest req, UserDaoImp dao) {
        String role = req.getParameter("role");
        List<User> list;

        if (role == null || role.equals("All")) {
            list = dao.findAll();
        } else {
            boolean isAdmin = role.equals("Admin");
            list = dao.findUsersByRole(isAdmin);
        }
        req.setAttribute("list", list);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        UserDaoImp dao = new UserDaoImp();
        boolean isActionHandled = false;

        if (path.contains("edit")) {
            handleEdit(req, dao);
        } else if (path.contains("create")) {
            handleCreateOrUpdate(req, dao, false);
        } else if (path.contains("update")) {
            handleCreateOrUpdate(req, dao, true);
        } else if (path.contains("delete")) {
            handleDelete(req, dao);
        }
        if (!isActionHandled) {
            handleListUser(req, dao);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}