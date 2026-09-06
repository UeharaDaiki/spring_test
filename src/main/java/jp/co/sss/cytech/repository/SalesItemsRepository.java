package jp.co.sss.cytech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.cytech.entity.SalesItems;

@Repository
public interface SalesItemsRepository extends JpaRepository<SalesItems, Integer> {
}
