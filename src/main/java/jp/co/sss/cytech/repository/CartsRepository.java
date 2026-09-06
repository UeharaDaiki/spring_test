package jp.co.sss.cytech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.cytech.entity.Carts;

@Repository
public interface CartsRepository extends JpaRepository<Carts, Integer> {
	Optional<Carts> findByUserIdAndProductId(Integer userId, Integer productId);
	List<Carts> findByUserId(Integer userId);
}
