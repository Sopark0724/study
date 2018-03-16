package com.sopark.chapter3;

import com.sopark.util.Printer;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapExample {
    public void marbleDiagram(){
        Printer.print();
        String[] balls = {"1" , "2" , "3" , "4" , "5"};
        Observable<String> source = Observable.fromArray(balls)
                .map(ball -> ball + "@");

        source.subscribe(System.out::println);
    }

    public void mapFunction(){
        Printer.print();
        Function<String, String> getStar = ball -> ball + "*";

        String[] balls = {"1","2","3","4","5"};
        Observable<String> source = Observable.fromArray(balls)
                .map(getStar);

        source.subscribe(System.out::println);
    }

    public void mappingType(){
        Printer.print();
        Function<String, Integer> ballToIndex = ball -> {
            switch (ball){
                case "RED": return 1;
                case "YELLOW": return 2;
                case "GREEN": return 3;
                case "BLUE": return 4;
                default:    return -1;
            }
        };

        String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
        Observable<Integer> source = Observable.fromArray(balls)
                .map(ballToIndex);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        MapExample mapExample = new MapExample();
        mapExample.marbleDiagram();
        mapExample.mapFunction();
        mapExample.mappingType();
    }
}
