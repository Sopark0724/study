package com.sopark.common;

public class Order {
    private String mId;

    public Order(String id){
        this.mId = id;
    }

    public String getId(){
        return mId;
    }

    @Override
    public String toString() {
        return "Order ID: " + mId;
    }
}
