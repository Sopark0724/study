package com.sopark.chapter5;

import com.sopark.common.CommonUtils;
import com.sopark.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.sopark.common.Shape.*;

public class NewThreadSchedulerExample {
    public void basic() {
        String[] orgs = {RED, GREEN, BLUE};
        Observable.fromArray(orgs)
                .doOnNext(data -> Log.v("Original data : " + data))
                .map(data -> "<<" + data + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::i);
        CommonUtils.sleep( 500);

        Observable.fromArray(orgs)
                .doOnNext(data -> Log.v("Original data : " + data))
                .map(data -> "##" + data + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        NewThreadSchedulerExample demo = new NewThreadSchedulerExample();
        demo.basic();
    }
}
