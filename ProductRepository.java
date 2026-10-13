package com.shopnest.repository;

import com.shopnest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where (:q = '' or lower(p.name) like lower(concat('%', :q, '%')) or lower(p.description) like lower(concat('%', :q, '%')) or lower(p.category) like lower(concat('%', :q, '%'))) and (:category = '' or p.category = :category) order by p.id")
    List<Product> search(@Param("q") String query, @Param("category") String category);
    List<Product> findTop4ByOrderByIdAsc();
    List<Product> findByCategoryOrderByNameAsc(String category);
}
