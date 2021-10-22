package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addRole(Set<Role> roles) {
        for (Role rol : roles) {
            em.persist(rol);
        }
//        em.persist(roles.forEach(role -> roles));
    }

    @Override
    public Role getRole(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public List <Role> getAllRoles() {
            return em.createQuery("select r from Role r").getResultList();
            }
}
