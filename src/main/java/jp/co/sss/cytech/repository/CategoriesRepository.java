package jp.co.sss.cytech.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.cytech.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}