package com.sopark.book.chapter4;

import com.sopark.book.common.CommonUtils;
import com.sopark.book.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ConcatMapExample {

    // 속도가 flatMap 보다 느림
    // 순서를 보장함 (끼어들기 안됨)
    public void marbleDiagram() {
        CommonUtils.exampleStart();

        String[] balls = {"1" , "2" , "3"};
        //interval은  SchedulerSupport.COMPUTATION 이기 때문에 순서가 다르게 호출될 수 있음.
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .map(notUsed -> ball + "@")
                    .take(2)
                );

        source.subscribe(Log::it);
        CommonUtils.sleep(2000);

    }

    // concatMap() 함수를 flatMap() 함수로 변경하기
    // 순서를 보장하지 않음 (끼어들기가 발생할 수 있음)
    // 속도가 concatMap 보다 빠름
    public void transform(){
        CommonUtils.exampleStart();

        String[] balls = {"1" , "2" , "3"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "@")
                        .take(2)

                );

        source.subscribe(Log::it);
        CommonUtils.sleep(2000);

    }

    public static void main(String[] args) {
        ConcatMapExample concatMapExample = new ConcatMapExample();
        //concatMapExample.marbleDiagram();
        concatMapExample.transform();
    }
}
