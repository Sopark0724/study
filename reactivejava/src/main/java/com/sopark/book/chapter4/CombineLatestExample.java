package com.sopark.book.chapter4;

import java.util.concurrent.TimeUnit;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import com.sopark.book.common.Shape;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static com.sopark.book.common.Shape.*;


public class CombineLatestExample{

    public void marbleDiagram() {
        String[] data1 = {PUPPLE, ORANGE, SKY, YELLOW}; //6, 7, 4, 2
        String[] data2 = {DIAMOND, STAR, PENTAGON};

        Observable<String> source = Observable.combineLatest(
                Observable.fromArray(data1)
                        .zipWith( //zipWith()로 깔끔하게 코드 정리
                                Observable.interval(100L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getColor(shape)),
                Observable.fromArray(data2)
                        .zipWith(
                                Observable.interval(500L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getSuffix(shape)),
                (v1, v2) -> v1 + v2);

        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public void tmp01() {
        Observable<String> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(CommonUtils::numberToAlphabet);
        Observable<Long> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS);
        BiFunction<String, Long, String> combiner =
                (val1, val2) -> val1 + val2;

        Observable<String> source = Observable.combineLatest(source1, source2, combiner);
        source.subscribe(System.out::println);

        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        CombineLatestExample demo = new CombineLatestExample();
        demo.marbleDiagram();
    }
}