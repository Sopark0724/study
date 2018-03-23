package com.sopark.chapter4;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class IntervalExample {

    public void printNumbers() throws InterruptedException {
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(data -> (data + 1) *100)
                .take(5);

        source.subscribe((data) -> {
            System.out.println(data);
        });

        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        IntervalExample intervalExample = new IntervalExample();
        intervalExample.printNumbers();
    }
}
