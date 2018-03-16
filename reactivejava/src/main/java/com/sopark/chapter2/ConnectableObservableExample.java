package com.sopark.chapter2;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class ConnectableObservableExample {
    public void marbleDiagram() throws InterruptedException {
        String[] dt = {"1","3","5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> dt[i])
                .take(dt.length);

        ConnectableObservable<String> source = balls.publish();

        // subscribe 를 호출하더라도 출력문은 실행되지 않음
        // connect() 메소드 호출시 실행됨.
        source.subscribe(data -> System.out.println("Subscriber #1 => "+ data));
        source.subscribe(data -> System.out.println("Subscriber #2 => "+ data));
        source.connect();
        sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 => "+ data));
        sleep(100);
    }

    public static void main(String[] args) throws InterruptedException {
        ConnectableObservableExample connectableObservableExample = new ConnectableObservableExample();
        connectableObservableExample.marbleDiagram();
    }
}
