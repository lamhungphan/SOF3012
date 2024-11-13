package com.fpoly.sof3012.dao.impl;

import com.fpoly.sof3012.dao.Dao;
import com.fpoly.sof3012.dao.VideoDao;
import com.fpoly.sof3012.entity.Video;
import com.fpoly.sof3012.utils.XJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDaoImpl implements Dao<Video>, VideoDao {
    EntityManager em = XJpa.getEntityManager();

    @Override
    public List<Video> findAll() {
        return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
    }

    @Override
    public Video findById(String id) {
        return em.find(Video.class, id);
    }

    @Override
    public Video create(Video video) {
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return video;
    }

    @Override
    public Video update(Video video) {
        try {
            em.getTransaction().begin();
            em.merge(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return video;
    }

    @Override
    public void deleteById(String id) {
        Video video = em.find(Video.class, id);
        try {
            em.getTransaction().begin();
            em.remove(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Video> getVideoSortByViews() {
        String jpql = "select v from Video v order by v.views desc";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        List<Video> list = query.getResultList();
        list.forEach(v -> {
            System.out.println(v.getTitle() + " " + v.getViews());
        });
        return list;
    }

    public List<Video> getTop10Favorite() {
        String jpql = "SELECT f.video FROM Favorite f GROUP BY f.video ORDER BY COUNT(f) DESC";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<Video> getNoFavorite() {
        String jpql = "SELECT v FROM Video v WHERE v NOT IN (SELECT f FROM Favorite f)";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    public List<Video> getVideoShareIn2024() {
        String jpql = "SELECT s.video FROM Share s WHERE YEAR (s.shareDate) = 2024 ORDER BY shareDate";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    public List<Object[]> getVideoShareSummary() {
        String jpql =   "SELECT v.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate) " +
                        "FROM Share s JOIN s.video v " +
                        "GROUP BY v.title";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

    public List<Video> findVideoByKeyword(String keyword) {
        String jpql = "SELECT o FROM Video o WHERE o.title LIKE :title";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("title", keyword);
        return query.getResultList();
    }

    public List<Video> getFavoriteVideo(Object userId) {
        String jpql = "SELECT f.video FROM Favorite f where f.user.id =:userId ";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Object[]> findVideoWithTotalLikes(String keyword) {
        String jpql = "SELECT v, COUNT(f) AS totalLikes " +
                "FROM Video v LEFT JOIN Favorite f ON f.video.id = v.id " +
                "WHERE v.title LIKE :title " +
                "GROUP BY v.id";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("title", "%" + keyword + "%");
        return query.getResultList();
    }

}



