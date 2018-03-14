package com.sopark.chapter1;

import io.reactivex.Observable;

public class HelloWorld {
    public void emit(){
        Observable.just("Hello", "World")
            .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.emit();
    }
}
