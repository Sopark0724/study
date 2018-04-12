package com.sopark.chapter5;

import com.sopark.common.CommonUtils;
import com.sopark.common.Log;
import com.sopark.common.Shape;
import com.sopark.util.Printer;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.sopark.common.Shape.*;

public class FlipExample {
    public void marbleDiagram() {
        Printer.print();
        String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> Log.v("Original data = " + data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map(Shape::flip);
        source.subscribe(Log::i);
        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }

    public void observeOnRemoved() {
        Printer.print();
        String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> Log.v("Origianl data = " + data))
                .subscribeOn(Schedulers.newThread())
                //removed .observeOn(Schedulers.newThread())
                .map(Shape::flip);
        source.subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        FlipExample demo = new FlipExample();
        demo.marbleDiagram();
        demo.observeOnRemoved();
    }
}
