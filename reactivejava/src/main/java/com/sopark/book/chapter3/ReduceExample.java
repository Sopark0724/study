package com.sopark.book.chapter3;

import com.sopark.book.util.Printer;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ReduceExample {

    public void marbleDiagram() {
        Printer.print();
        String[] balls = {"1" , "3" , "5"};
        Maybe<String> source = Observable.fromArray(balls)
                .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(System.out::println);
    }

    public void biFunction(){
        Printer.print();

        BiFunction<String, String, String> mergeBalls =
                (ball1, ball2) -> ball2 + "(" + ball1 + ")";

        String[] balls = {"1" , "3" , "5"};
        Maybe<String> source = Observable.fromArray(balls)
                .reduce(mergeBalls);

        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        ReduceExample reduceExample = new ReduceExample();
        reduceExample.marbleDiagram();
        reduceExample.biFunction();
    }
}
