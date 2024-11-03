package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.entity.User;
import com.fpoly.sof3012.utils.XJpa;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FavoriteDaoImpl implements Dao<Favorite> {
    static EntityManager em = XJpa.getEntityManager();
    User entity = em.find(Favorite.class, 123).getUser();

    //   User user = entity.getUser();
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
}
