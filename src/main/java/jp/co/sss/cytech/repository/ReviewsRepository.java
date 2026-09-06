package jp.co.sss.cytech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.cytech.entity.Reviews;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
	List<Reviews> findTop1ByProductIdOrderByCreatedAtDesc(Integer productId);
}
