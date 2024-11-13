package com.fpoly.sof3012.controller;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.sof3012.dao.FavoriteDaoImpl;
import com.fpoly.sof3012.dao.UserDaoImpl;
import com.fpoly.sof3012.dao.VideoDao;
import com.fpoly.sof3012.dao.VideoDaoImpl;
import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.entity.User;
import com.fpoly.sof3012.entity.Video;
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
        "/user/favorites",
})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDaoImpl userDao = new UserDaoImpl();
    FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
    VideoDaoImpl videoDao = new VideoDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getPath(req);

        if (path.contains("edit")) {
            handleEdit(req, userDao);
            handleVideo(req);
            handleListUser(req, userDao);
        } else if (path.contains("create")) {
            handleCreateOrUpdate(req, resp, userDao, false);
            handleListUser(req, userDao);
        } else if (path.contains("update")) {
            handleCreateOrUpdate(req, resp, userDao, true);
            handleListUser(req, userDao);
        } else if (path.contains("delete")) {
            handleDelete(req, userDao);
        }
        handleListUser(req, userDao);
        handleListFavorites(req, favoriteDao);
        req.getRequestDispatcher("/views/user/demo.jsp").forward(req, resp);
    }

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
            req.getRequestDispatcher("/views/user/demo.jsp");
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
                if (dao.isUserIdExists(id)) {
                    req.setAttribute("error", "ID đã tồn tại, vui lòng chọn ID khác");
                    req.getRequestDispatcher("/views/user/demo.jsp").forward(req, resp);
                    return;
                }
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

        if (keyword == null || keyword.trim().isEmpty()) {
            list = dao.findAll();
        } else {
            list = dao.findUsersByName("%" + keyword + "%");
        }

        if (role != null && !role.equals("All")) {
            boolean isAdmin = role.equals("Admin");
            List<User> roleList = dao.findUsersByRole(isAdmin);

            // Giữ lại user có trong cả danh sách tên và role
            list.retainAll(roleList);
        }
        req.setAttribute("list", list);
    }

    private void handleListFavorites(HttpServletRequest req, FavoriteDaoImpl favoriteDao) {
        List<Favorite> favorites = favoriteDao.findAll();
        req.setAttribute("favorites", favorites);
    }
     private void handleVideo(HttpServletRequest req ){
        String editId = req.getPathInfo().substring(1);
        List<User> users = userDao.findAll();
        List<Video> videos = videoDao.getFavoriteVideo(editId);
         System.out.println(videos);
        req.setAttribute("users", users);
        req.setAttribute("videos", videos);
    }

    String getPath(HttpServletRequest req){
        return req.getServletPath();
    }
}