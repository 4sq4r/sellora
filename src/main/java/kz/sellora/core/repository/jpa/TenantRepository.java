package kz.sellora.core.repository.jpa;

import kz.sellora.core.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, String> {
    boolean existsByName(String name);
}
