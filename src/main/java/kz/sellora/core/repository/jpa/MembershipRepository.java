package kz.sellora.core.repository.jpa;

import kz.sellora.core.model.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, String> {
}
