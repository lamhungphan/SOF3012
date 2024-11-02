package com.fpoly.sof3012.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Lớp GenericRepository cung cấp các phương thức CRUD (tạo, sửa, xóa, tìm kiếm)
 * chung cho các entity trong cơ sở dữ liệu, dễ dàng mở rộng cho nhiều bảng khác nhau.
 *
 * @param <T> Lớp của entity cần thao tác (ví dụ: User, Video, Favorite).
 * @param <E> Kiểu dữ liệu của ID cho entity (ví dụ: Long, String).
 */
public class GenericDao<T, E> {
    /**
     * Lớp của entity cần thao tác
     */
    private final Class<T> entityClass;

    /**
     * Quản lý các thao tác với cơ sở dữ liệu
     */
    protected final EntityManager em;

    /**
     * Khởi tạo repository với lớp entity cụ thể và EntityManager để thực hiện các thao tác.
     *
     * @param entityClass Lớp của entity cần thao tác.
     * @param em          Đối tượng EntityManager để kết nối với cơ sở dữ liệu.
     */
    public GenericDao(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    /**
     * Thêm bản ghi và trả về đối tượng đã được thêm, hoặc null nếu có lỗi.
     *
     * @param entity Đối tượng muốn thêm vào cơ sở dữ liệu.
     * @return entity đã thêm, hoặc null nếu có lỗi xảy ra.
     */
    public T createAndReturnEntity(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        return entity;
    }

    /**
     * Cập nhật bản ghi trong cơ sở dữ liệu và trả về đối tượng đã cập nhật.
     *
     * @param entity Đối tượng muốn cập nhật.
     * @return entity đã cập nhật, hoặc null nếu có lỗi xảy ra.
     */
    public T updateAndReturnEntity(T entity, E id) {
        try {
            T entityFinded = findByIdAndReturnEntity(id);
            em.getTransaction().begin();
            if (entityFinded != null) {
                em.merge(entity);
            } else {
                return null;
            }
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        return entity;
    }

    /**
     * Xóa bản ghi dựa trên ID và trả về kết quả.
     *
     * @param id ID của đối tượng muốn xóa.
     * @return true nếu xóa thành công, false nếu có lỗi hoặc không tìm thấy đối tượng.
     */
    public boolean deleteAndReturnResult(E id) {
        try {
            T entity = findByIdAndReturnEntity(id);
            em.getTransaction().begin();
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Tìm và trả về đối tượng dựa trên ID.
     *
     * @param id ID của đối tượng cần tìm.
     * @return entity nếu tìm thấy, hoặc null nếu không tìm thấy.
     */
    public T findByIdAndReturnEntity(E id) {
        return em.find(entityClass, id);
    }

    /**
     * Lấy danh sách tất cả các đối tượng trong bảng tương ứng.
     *
     * @return danh sách các entity hoặc danh sách rỗng nếu có lỗi.
     */
    public List<T> getAllAndReturnList() {
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return List.of();
        }
    }
}
