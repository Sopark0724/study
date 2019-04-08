package com.example.jpademo;

import com.example.jpademo.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaDemoApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp(){
        bookRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        Category category = new Category("IT");
        categoryRepository.save(category);
        Category category2 = new Category("자연");
        categoryRepository.save(category2);

        Book book1 = new Book("JPA 책", category);
        bookRepository.save(book1);
        Book book2 = new Book("자연관찰", category2);
        bookRepository.save(book2);

        bookRepository.findAll();
    }

    @Test
    public void findAll_JPQL_fetch_join(){
        Category category = new Category("IT");
        categoryRepository.save(category);
        Category category2 = new Category("자연");
        categoryRepository.save(category2);

        Book book1 = new Book("JPA 책", category);
        bookRepository.save(book1);
        Book book2 = new Book("자연관찰", category2);
        bookRepository.save(book2);

        bookRepository.findAllWithCategory();
    }

    @Test
    public void findAll_JPQL_fetch_join_and_entityGraph(){
        Category category = new Category("IT");
        categoryRepository.save(category);
        Category category2 = new Category("자연");
        categoryRepository.save(category2);

        Book book1 = new Book("JPA 책", category);
        bookRepository.save(book1);
        Book book2 = new Book("자연관찰", category2);
        bookRepository.save(book2);

        bookRepository.findAllByQueryAndEntityGraphWithCategory();
    }

    @Test
    public void find_oneToMany_test(){
        Category category = new Category("IT");
        categoryRepository.save(category);
        Category category2 = new Category("자연");
        categoryRepository.save(category2);

        Book book1 = new Book("JPA 책", category);
        bookRepository.save(book1);
        Book book1_1 = new Book("JPA 책2", category);
        bookRepository.save(book1_1);
        Book book2 = new Book("자연관찰", category2);
        bookRepository.save(book2);
        Book book2_2 = new Book("자연관찰2", category2);
        bookRepository.save(book2_2);

        List<Category> allWithEntityGraph = categoryRepository.findAllWithEntityGraph();
        List<Category> allWithFetchJoin = categoryRepository.findAllWithFetchJoin();

        Assertions.assertThat(allWithEntityGraph.size()).isEqualTo(2);
        Assertions.assertThat(allWithFetchJoin.size()).isEqualTo(2);
    }
}
