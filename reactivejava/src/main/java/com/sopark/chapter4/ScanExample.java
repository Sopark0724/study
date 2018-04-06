package com.sopark.chapter4;

import com.sopark.common.Log;
import io.reactivex.Observable;

public class ScanExample {

    public void marbleExample() {
        String[] balls = {"1" , "2", "3"};

        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(Log::i);
    }

    public static void main(String[] args) {
        ScanExample scanExample = new ScanExample();
        scanExample.marbleExample();
    }
}
