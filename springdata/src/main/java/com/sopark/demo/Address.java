package com.sopark.demo;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String zipCode;
}
