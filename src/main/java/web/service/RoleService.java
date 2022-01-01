package web.service;

import web.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role save(Role role);
    Role getById(Long id);
    Role getByName(String name);
}
