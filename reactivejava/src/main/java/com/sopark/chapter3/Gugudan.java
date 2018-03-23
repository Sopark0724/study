package com.sopark.chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class Gugudan {

    public void reactiveV1(){
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input :");
        int dan = Integer.parseInt(in.nextLine());

        Observable<Integer> source = Observable.range(1,9);
        source.subscribe(row -> System.out.println(dan + " * " + row + " = " + dan*row ));
    }

    public void reactiveV2(){
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input :");
        int dan = Integer.parseInt(in.nextLine());

        Function<Integer, Observable<String>> gugudan = num ->
                Observable.range(1,9).map(row -> num + " * " + row + " = " + num * row);
        Observable<String> source = Observable.just(dan).flatMap(gugudan);

        source.subscribe(System.out::println);
        in.close();
    }

    public void reactiveV3(){
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input :");
        int dan = Integer.parseInt(in.nextLine());

        Observable<String> source = Observable.just(dan)
                .flatMap(num -> Observable.range(1, 9))
                .map(row -> dan + " * " + row + " = " + dan * row);

        source.subscribe(System.out::println);
        in.close();
    }

    public void usingResultSelector(){
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input :");
        int dan = Integer.parseInt(in.nextLine());

        Observable<String> source = Observable.just(dan)
                .flatMap(bb -> {
                            System.out.println(bb);
                            return Observable.range(1,9);
                        } ,  // aa
                        (gugu, i) -> gugu + " * " + i + " = " + gugu*i);
        source.subscribe(System.out::println);
        in.close();

    }

    public static void main(String[] args) {
        Gugudan gugudan = new Gugudan();
//        gugudan.reactiveV1();
//        gugudan.reactiveV2();
//        gugudan.reactiveV3();
        gugudan.usingResultSelector();
    }
}
  