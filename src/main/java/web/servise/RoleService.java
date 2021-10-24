package web.servise;

import web.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Set<Role> roles);
    List<Role> getAllRoles();
    Role findRole(Role role);
}
