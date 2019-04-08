package com.example.jpademo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class Category {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);

        if(book.getCategory() != this){
            book.setCategory(this);
        }
    }
}
