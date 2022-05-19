package com.example.shoppingcartdemo.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoIDTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void AutoIncrease() {
        int n = 0;
        AutoID testID = new AutoID();
        String id = testID.autoIncrease(n);
        System.out.println(id);
    }
}