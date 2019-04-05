package com.example.jpademo.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
