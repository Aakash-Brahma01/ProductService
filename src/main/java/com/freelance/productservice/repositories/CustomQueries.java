package com.freelance.productservice.repositories;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE = "SELECT * FROM product WHERE title =:title";
}
