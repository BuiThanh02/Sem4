package com.example.shoppingcartdemo.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoID {
    String pre;
    AtomicInteger num;

    public AutoID() {
        this.pre = "CartItem";
        this.num = new AtomicInteger();
    }

    public String autoIncrease(int n){
        num.set(n);
        return pre + num.incrementAndGet();
    }
}
