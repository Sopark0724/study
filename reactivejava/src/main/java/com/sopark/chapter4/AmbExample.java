package com.sopark.chapter4;

import com.sopark.common.CommonUtils;
import com.sopark.common.Log;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sopark.common.Shape.*;

public class AmbExample {

    public void marbleDiagram() {
        String[] data1 = {RED, GREEN, BLUE};
        String[] data2 = {rectangle(YELLOW), rectangle(SKY)};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete(() -> Log.d("Observable #1 : onComplete()")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Log.d("Observable #2 : onComplete()")));

        Observable.amb(sources)
                .doOnComplete(() -> Log.d("Result : onComplete()"))
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        AmbExample demo = new AmbExample();
        demo.marbleDiagram();
    }
}
