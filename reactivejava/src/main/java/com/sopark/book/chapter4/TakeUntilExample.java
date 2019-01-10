package com.sopark.book.chapter4;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.sopark.book.common.Shape.*;

public class TakeUntilExample {
    public void marbleDiagram() {
        String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));

        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        TakeUntilExample demo = new TakeUntilExample();
        demo.marbleDiagram();
    }
}
