package jp.co.sss.cytech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.cytech.entity.Companies;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer> {
	Optional<Companies> findById(Integer companyId);
}
