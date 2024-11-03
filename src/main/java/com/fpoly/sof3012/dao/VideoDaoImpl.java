package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.Video;
import com.fpoly.sof3012.utils.XJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class VideoDaoImpl implements Dao<Video> {
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

    public static void main(String[] args) {
//        String jpql = "SELECT o.video FROM Favorite o WHERE o.user.id='TeoNV'";
//        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
//        List<Video> videos = query.getResultList();
    }
}



