package com.example.jpademo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final BookRepository bookRepository;

    @Transactional
    public void printAllBooks(){
        List<Category> categories = categoryRepository.findAllWithFetchJoin();
        List<Category> categories2 = categoryRepository.findAllWithEntityGraph();

        categories.stream()
                .map(category -> category.getBooks().get(0))
                .forEach(System.out::println);
    }
}
