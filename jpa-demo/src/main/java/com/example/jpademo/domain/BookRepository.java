package com.example.jpademo.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select book from Book book join fetch book.category")
    List<Book> findAllWithCategory();

    @EntityGraph(attributePaths = "category", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select book from Book book")
    List<Book> findAllByQueryAndEntityGraphWithCategory();
}
