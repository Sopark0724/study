package com.sopark.book.chapter4;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import com.sopark.book.common.MarbleDiagram;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.sopark.book.common.Shape.*;

public class MergeExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] data1 = {RED, GREEN}; //1, 3
        String[] data2 = {YELLOW, SKY, PUPPLE}; //2, 4, 6

        Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data1[idx])
                .take(data1.length);
        Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length);

        Observable<String> source = Observable.merge(source1, source2);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        MergeExample demo = new MergeExample();
        demo.marbleDiagram();
    }
}