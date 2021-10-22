package web.dao;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Set<Role> roles);
    Role getRole(Long id);
    List<Role> getAllRoles();
}
