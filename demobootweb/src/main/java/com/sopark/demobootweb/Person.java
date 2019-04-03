package com.sopark.demobootweb;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
public class Person {
    private Long id;

    private String name;
}
