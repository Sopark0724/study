package com.sopark.book.chapter4;

import com.sopark.book.common.Log;
import io.reactivex.Observable;

public class RepeatExample {

    public void marbleDiagram(){
        String[] balls = {"1" , "3" , "5"};
        Observable<String> source = Observable.fromArray(balls).repeat(3);

        source.doOnComplete(() -> Log.d("onComplete"))
                .subscribe(Log::i);
    }

    public static void main(String[] args) {
        RepeatExample repeatExample = new RepeatExample();
        repeatExample.marbleDiagram();
    }
}
