package com.sopark.chapter4;

import com.sopark.common.CommonUtils;
import com.sopark.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SwitchMapExample {

    public void marbleDiagram() {
        CommonUtils.exampleStart();

        String[] balls = {"1" , "2" , "3"};
        //interval은  SchedulerSupport.COMPUTATION 이기 때문에 순서가 다르게 호출될 수 있음.
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "@")
                        .take(2)
                );

        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public void usingDoOnNext() {
        CommonUtils.exampleStart();

        String[] balls = {"1" , "2" , "3"};
        //interval은  SchedulerSupport.COMPUTATION 이기 때문에 순서가 다르게 호출될 수 있음.
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(Log::dt)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "@")
                        .take(2)
                );

        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public static void main(String[] args) {
        SwitchMapExample switchMapExample = new SwitchMapExample();
        //switchMapExample.marbleDiagram();
        switchMapExample.usingDoOnNext();
    }
}
