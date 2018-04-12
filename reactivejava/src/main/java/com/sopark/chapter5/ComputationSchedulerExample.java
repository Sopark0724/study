package com.sopark.chapter5;

import com.sopark.common.CommonUtils;
import com.sopark.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static com.sopark.common.Shape.*;

public class ComputationSchedulerExample {
    public void basic() {
        String[] orgs = {RED, GREEN, BLUE};
        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (a,b) -> a);

        //Subscription #1
        source.map(item -> "<<" + item + ">>")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);

        //Subscription #2
        source.map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        ComputationSchedulerExample demo = new ComputationSchedulerExample();
        demo.basic();
    }
}
