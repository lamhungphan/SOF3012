package com.fpoly.sof3012.dao.impl;

import com.fpoly.sof3012.dao.Dao;
import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.utils.XJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavoriteDaoImpl implements Dao<Favorite> {
    static EntityManager em = XJpa.getEntityManager();

    @Override
    public List<Favorite> findAll() {
        return em.createQuery("SELECT f FROM Favorite f ", Favorite.class).getResultList();
    }

    @Override
    public Favorite findById(String id) {
        return em.find(Favorite.class, id);
    }

    @Override
    public Favorite create(Favorite favorite) {
        try {
            em.getTransaction().begin();
            em.persist(favorite);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public Favorite update(Favorite favorite) {
        try {
            em.getTransaction().begin();
            em.merge(favorite);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public void deleteById(String id) {
        Favorite favorite = em.find(Favorite.class, id);
        try {
            em.getTransaction().begin();
            em.remove(favorite);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Object[]> getFavoriteVideo() {
        String jpql = "SELECT f.video.title, COUNT(f),  MIN(f.likeDate), MAX(f.likeDate) " +
                "FROM Favorite f " +
                "GROUP BY f.video.title";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

}
