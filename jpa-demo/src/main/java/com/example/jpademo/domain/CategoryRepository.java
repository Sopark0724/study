package com.example.jpademo.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = "books")
    @Query("select category from Category category")
    List<Category> findAllWithEntityGraph();

    @Query("select distinct category from Category category join fetch category.books")
    List<Category> findAllWithFetchJoin();
}
