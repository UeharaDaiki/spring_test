package jp.co.sss.cytech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.cytech.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
	List<Products> findByProductNameContaining(String keyword);

    // ② カテゴリID検索
    List<Products> findByCategoryId(Integer categoryId);

    // ③ 商品名あいまい ＋ カテゴリID検索
    List<Products> findByProductNameContainingAndCategoryId(String keyword, Integer categoryId);
}
