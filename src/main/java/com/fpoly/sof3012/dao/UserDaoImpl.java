package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.entity.User;
import com.fpoly.sof3012.utils.XJpa;
import jakarta.persistence.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User> {
    static EntityManager em = XJpa.getEntityManager();
//    User entity = em.find(User.class, "id");
//    List<Favorite> favorites = entity.getFavorites();

    @Override
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
    public User findById(String id) {
        return em.find(User.class, id);
    }

    @Override
    public User create(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(String id) {
        User user = em.find(User.class, id);
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<User> findUsersByRole(boolean isAdmin) {
        String jpql = "SELECT o FROM User o WHERE o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("role", isAdmin);
        return query.getResultList();
    }

    public List<User> findUsersByPage(int pageNumber, int pageSize) {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int getTotalRecords() {
        String jpql = "SELECT COUNT(u) FROM User u";
        Query query = em.createQuery(jpql);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public void countAdmin() {
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.admin = true";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        Long n = query.getSingleResult();
        System.out.println("Total admin is: " + n);
    }

    public static void main(String[] args) {
        String jpql = "SELECT o FROM User o WHERE o.favorites IS NOT EMPTY";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> users = query.getResultList();
    }
}
