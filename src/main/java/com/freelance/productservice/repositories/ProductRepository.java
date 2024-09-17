package com.freelance.productservice.repositories;

import com.freelance.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product,UUID> {

    //Product findByTitle(String title);
    List<Product> findByTitle(String title);

    Product findByTitleAndPrice_Price(String title,Double price);

    List<Product> findAllByTitleOrderByPrice_PriceDesc(String title);

    List<Product> findAllByPrice_Currency(String currency);

    Long countAllByPrice_Currency(String currency);

    List<Product> findByTitleContainingIgnoreCase(String regextitle);

    List<Product> findByTitleLike(String titleRegex);

    // Native Query -- mySql based query
    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAByTitle(String title);

    // Hibernate query
    @Query("select p from Product p where p.title = :title and p.price.currency = :currency")
    List<Product> doSomething(String title, String currency);
    //List<Product> doSomething(@Param("title")String title, @Param("currency")String currency);
}
