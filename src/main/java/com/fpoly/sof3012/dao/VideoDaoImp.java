package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.entity.User;
import com.fpoly.sof3012.entity.Video;
import com.fpoly.sof3012.utils.XJpa;
import com.sun.java.accessibility.util.EventID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDaoImp implements Dao {
    static EntityManager em = XJpa.getEntityManager();
    Video entity = em.find(Video.class, 123);

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public Object create(Object entity) {
        return null;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    public static void main(String[] args) {
        String jpql = "SELECT o.video FROM Favorite o WHERE o.user.id='TeoNV'";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        List<Video> videos = query.getResultList();
    }
}
