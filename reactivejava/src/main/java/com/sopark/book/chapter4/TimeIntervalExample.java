package com.sopark.book.chapter4;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

public class TimeIntervalExample {
    public void marbleDiagram() {
        String[] data = {"RED", "GREEN", "ORANGE"};

        CommonUtils.exampleStart();
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item -> {
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        TimeIntervalExample demo = new TimeIntervalExample();
        demo.marbleDiagram();
    }
}
