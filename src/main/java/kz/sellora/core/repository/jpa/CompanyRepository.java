package kz.sellora.core.repository.jpa;

import kz.sellora.core.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {

}
