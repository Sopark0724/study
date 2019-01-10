package com.sopark.book.chapter4;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import io.reactivex.Observable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerExample {

    public void showTime(){
        CommonUtils.exampleStart();

        Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map(notUsed -> {
                    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(new Date());
                });

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new TimerExample().showTime();
    }
}
