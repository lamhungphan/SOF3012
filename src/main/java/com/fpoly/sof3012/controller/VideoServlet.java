package com.fpoly.sof3012.controller;

import com.fpoly.sof3012.dao.impl.FavoriteDaoImpl;
import com.fpoly.sof3012.dao.impl.VideoDaoImpl;
import com.fpoly.sof3012.entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/video/index",
        "/video/search"
})
public class VideoServlet extends HttpServlet {
    VideoDaoImpl videoDao = new VideoDaoImpl();
    FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("search-video");
        List<Video> videos;

        if (keyword != null && !keyword.trim().isEmpty()) {
            videos = videoDao.findVideoByKeyword("%" + keyword + "%");
        } else {
            videos = videoDao.findAll();
        }

        List<Object[]> favoriteDetails = favoriteDao.getFavoriteVideo();
        req.setAttribute("favoriteDetails", favoriteDetails);
        req.setAttribute("list", videos);
        req.setAttribute("keyword", keyword != null ? keyword : "");

        req.getRequestDispatcher("/views/user/video.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
