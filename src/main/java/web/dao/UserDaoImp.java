package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getByName(String name) {
        Query query = entityManager.createNamedQuery("User.getByName");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> allUsers = entityManager.createQuery("SELECT u FROM User u", User.class);
        return allUsers.getResultList();
    }

    @Override
    public Role getRole(String name) {
        TypedQuery<Role> role = entityManager.createQuery("SELECT r FROM Role r where r.roleName = :name", Role.class)
                .setParameter("name", name);

        return role.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> roles = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return roles.getResultList();
    }
}

