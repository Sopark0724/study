package com.sopark.chapter4;

import com.sopark.common.Log;
import com.sopark.common.Shape;
import com.sopark.util.Printer;
import io.reactivex.Observable;
import io.reactivex.Single;

import static com.sopark.common.Shape.*;

public class AllFunctionExample {
    public void marbleDiagram() {
        Printer.print();
        String[] data = {RED, YELLOW, GREEN, SKY};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);
        //.all(val -> Shape.BALL.equals(Shape.getShape(val)));
        source.subscribe(System.out::println);
    }

    public void wrongCase() {
        Printer.print();
        String[] data = {RED, rectangle(YELLOW), GREEN, SKY};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .doOnNext(Log::d)
                .doOnComplete(() -> Log.d("onComplete"))    // 완료 처리 부분은 호출 하지 않음.
                .all(Shape.BALL::equals)
                .doOnSuccess(v -> Log.d("onSuccess : val = " + v));

        //.all(val -> Shape.BALL.equals(Shape.getShape(val)));
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        AllFunctionExample demo = new AllFunctionExample();
        demo.marbleDiagram();
        demo.wrongCase();
    }
}
