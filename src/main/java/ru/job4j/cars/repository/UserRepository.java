package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;
    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class.getName());

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when create " + user, e);
        }
        session.close();
        return user;
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when update " + user, e);
        }
        session.close();
    }

    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = new User();
            user.setId(userId);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when delete user with id" + userId, e);
        }
        session.close();
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> users = List.of();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User u ORDER BY u.id", User.class);
            users = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when findAllOrderById", e);
        }
        session.close();
        return users;
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Optional<User> userOpt = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User u WHERE u.id = :userId", User.class);
            query.setParameter("userId", userId);
            userOpt = Optional.of(query.uniqueResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when findById " + userId, e);
        }
        session.close();
        return userOpt;
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        List<User> users = List.of();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User u WHERE u.login LIKE :loginLike", User.class);
            query.setParameter("loginLike", "%" + key + "%");
            users = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when findByLikeLogin by " + key, e);
        }
        session.close();
        return users;
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> userOpt = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            userOpt = Optional.of(query.uniqueResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error when findByLogin " + login, e);
        }
        session.close();
        return userOpt;
    }
}