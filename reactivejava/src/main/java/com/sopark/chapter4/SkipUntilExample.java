package com.sopark.chapter4;

import com.sopark.common.CommonUtils;
import com.sopark.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.sopark.common.Shape.*;

public class SkipUntilExample {
    public void marbleDiagram() {
        String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val)
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));

        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        SkipUntilExample demo = new SkipUntilExample();
        demo.marbleDiagram();
    }
}
