package com.sopark.chapter3;

import com.sopark.util.Printer;
import io.reactivex.Observable;
import io.reactivex.Single;

public class FilterExample {

    public void marbleDiagram() {
        Printer.print();
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 XEAGON"};

        Observable observable = Observable.fromArray(objs)
                .filter(obj -> obj.endsWith("CIRCLE"));

        observable.subscribe(System.out::println);

    }

    public void otherFilters(){
        Printer.print();

        Integer[] numbers = {100,200,300,400,500};
        Single<String> single;
        Observable<String> source;

        // 1. first
        single = Observable.fromArray(numbers).first(-1).map(num -> "first() value = " + num);
        single.subscribe(System.out::println);

        // 2. last
        single = Observable.fromArray(numbers).last(999).map(num -> "last() value = " + num);
        single.subscribe(System.out::println);

        // 3. take(N)
        source = Observable.fromArray(numbers).take(3).map(num -> "take(3) value = " + num);
        source.subscribe(System.out::println);

        // 4. takeLast(N)
        source = Observable.fromArray(numbers).takeLast(3).map(num -> "takeLast(3) value = " + num);
        source.subscribe(System.out::println);

        // 5. skip(N)
        source = Observable.fromArray(numbers).skip(2).map(num -> "skip(N) = " + num);
        source.subscribe(System.out::println);

        // 6. skipLast(N)
        source = Observable.fromArray(numbers).skipLast(2).map(num -> "skipLast(2) = " + num);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        FilterExample filterExample = new FilterExample();
        filterExample.marbleDiagram();
        filterExample.otherFilters();
    }
}
