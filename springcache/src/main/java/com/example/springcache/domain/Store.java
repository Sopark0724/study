package com.example.springcache.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Store {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private List<Book> books;
}
