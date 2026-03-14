package kz.sellora.core.repository.jpa;

import kz.sellora.core.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
