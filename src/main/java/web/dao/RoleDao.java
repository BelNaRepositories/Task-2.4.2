package web.dao;

import web.model.Role;
import java.util.List;

public interface RoleDao {
      List<Role> getAllRoles();
      Role save(Role role);
      Role getById(Long id);
      Role getByName(String name);
}
