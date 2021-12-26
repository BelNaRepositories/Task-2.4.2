package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select  user from User user", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) { entityManager.persist(user); }

    @Override
    public User readUser(Long id) { return entityManager.find(User.class, id); }

    @Override
    public User delete(Long id) {
        User user = readUser(id);
        entityManager.remove(user);
        return  user;
    }

    @Override
    public void update(User user) { entityManager.merge(user);
    }

    @Override
    public User getUserByUsername(String ssoId) {
        return entityManager.createQuery("select user from User user where user.ssoId = :ssoId", User.class)
                .setParameter("ssoId", ssoId)
                .getSingleResult();
    }
}
