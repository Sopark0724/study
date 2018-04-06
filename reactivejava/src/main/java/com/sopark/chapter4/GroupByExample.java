package com.sopark.chapter4;

import com.sopark.common.CommonUtils;
import com.sopark.common.Shape;
import com.sopark.util.Printer;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupByExample {

    public void marbleDiagram(){
        Printer.print();
        String[] objs = {"6", "4", "2-T" , "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(CommonUtils::getSharpe);

        source.subscribe(obj -> {
            obj.subscribe(val -> System.out.println("GROUP:" + obj.getKey() + "\t Value : " + val));
        });
    }


    public void filterBallGroup(){
        Printer.print();
        String[] objs = {"6", "4", "2-T" , "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(CommonUtils::getSharpe);

        source.subscribe(obj -> {
            obj.filter(val -> obj.getKey().equals(Shape.BALL))
            .subscribe(val -> System.out.println("GROUP:" + obj.getKey() + "\t Value : " + val));
        });
    }

    public static void main(String[] args) {
        GroupByExample groupByExample = new GroupByExample();
        groupByExample.marbleDiagram();
        groupByExample.filterBallGroup();
    }
}
