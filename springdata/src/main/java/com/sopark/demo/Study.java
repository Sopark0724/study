package com.sopark.demo;

import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Study {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Account owner;

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
}
