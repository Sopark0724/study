package com.example.jpademo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    @Getter
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(@NonNull String title, @NonNull Category category) {
        this.title = title;
        this.setCategory(category);
    }

    public void setCategory(Category category) {
        this.category = category;

        if(!this.category.getBooks().contains(this)){
            this.category.addBook(this);
        }
    }

    public void changeTitle(){
        this.title = "jsp_test";
    }
}
