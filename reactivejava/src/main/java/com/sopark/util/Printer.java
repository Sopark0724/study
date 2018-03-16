package com.sopark.util;

public class Printer {
    public static void print(){
        System.out.println("=================== " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ===================");
    }
}
