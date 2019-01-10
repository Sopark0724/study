package com.sopark.book.chapter4;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import com.sopark.book.common.Shape;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ZipExample {

    public void marbleDiagram(){
        String[] shapes = {"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix
        );

        source.subscribe(Log::i);
    }

    public void zipNumbers() {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a, b, c) -> a + b + c );
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public void zipInterval() {
        Observable<String> source = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value,i) -> value);

        CommonUtils.exampleStart();
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public void zipWithNumbers() {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (a, b) -> a + b)
                .zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        ZipExample zipExample = new ZipExample();
//        zipExample.marbleDiagram();
        zipExample.zipWithNumbers();
    }
}
