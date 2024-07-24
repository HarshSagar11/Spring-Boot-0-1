package com.week3.JPATutorial.Week3.repositories;

import com.week3.JPATutorial.Week3.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByTitle(String pepsi);
    List<ProductEntity> findBy(Sort sort);
    List<ProductEntity> findByOrderByPrice();
    List<ProductEntity> findByCreatedAtAfter (LocalDateTime after);
    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan (int quantity, BigDecimal price);
    List<ProductEntity> findByTitleLike (String title);
    List<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);
//    Optional<ProductEntity> findByTitleAndPrice (String title, BigDecimal price);
    @Query("select e from ProductEntity e where e.title=:title and e.price=:price")
//    @Query("select e.title from ProductEntity e where e.title=?1 and e.price=?2") // other way for parameter
    Optional <ProductEntity> findByTitleAndPrice (String title, BigDecimal price);
}
