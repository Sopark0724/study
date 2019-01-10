package com.sopark.book.chapter4;


import com.sopark.book.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class IntervalExample {

    public void printNumbers() throws InterruptedException {
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(data -> (data + 1) *100)
                .take(5);

        source.subscribe(Log::it);

        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        IntervalExample intervalExample = new IntervalExample();
        intervalExample.printNumbers();
    }
}

