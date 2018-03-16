package com.sopark.chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FlatMapExample {
    public void marbleDiagram(){
        Function<String, Observable<String>> getDoubleStars =
            ball -> Observable.just(ball + "*", ball + "*");

        String[] balls = {"1","2","3"};
        Observable<String> source = Observable.fromArray(balls)
                .flatMap(getDoubleStars);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        FlatMapExample flatMapExample = new FlatMapExample();
        flatMapExample.marbleDiagram();
    }
}
