package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override

    public List<Role> getAllRoles() {
        return entityManager.createQuery("select  role from Role role", Role.class).getResultList();
    }

    @Override
    public Role save(Role role) {
        List<Role> roles = getAllRoles();
        String name = role.getRole();
        boolean containRole = roles.stream().map(Role::getRole).anyMatch(name::equals);
        if (!containRole) {
            role = new Role(name);
            entityManager.persist(role);
        }
        return role;
    }

    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.role = :name", Role.class);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }
}