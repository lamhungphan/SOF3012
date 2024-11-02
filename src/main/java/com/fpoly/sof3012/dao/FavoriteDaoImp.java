package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.Favorite;
import com.fpoly.sof3012.entity.User;
import com.fpoly.sof3012.utils.XJpa;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FavoriteDaoImp implements Dao {
    static EntityManager em = XJpa.getEntityManager();
    User entity = em.find(Favorite.class, 123).getUser();

    //   User user = entity.getUser();
    @Override
    public List<Favorite> findAll() {
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
}
