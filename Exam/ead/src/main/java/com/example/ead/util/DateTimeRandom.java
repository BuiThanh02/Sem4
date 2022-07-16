package com.example.ead.util;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateTimeRandom {

    public static boolean isLeap(int year){
        boolean leap = false;
        if(year % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( year % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( year % 400 == 0)//chia hết cho 400 là năm nhuận
                    leap = true;
                else
                    leap = false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                leap = true;
        }
        else {
            leap = false;
        }
        return leap;
    }

    public static LocalDateTime randomFull(int min, int max){
        boolean check = false;
        int[] lList = new int[]{1, 3, 5, 7, 8, 10, 12};
        int year = NumberUtil.getRandomNumber(min, max);
        int month = NumberUtil.getRandomNumber(1, 12);
        int day;
        if (isLeap(year) && month == 2){
            day = NumberUtil.getRandomNumber(1, 29);
        } else if (month == 2) {
            day = NumberUtil.getRandomNumber(1, 28);
        } else {
            for (int i = 0 ; i < lList.length; i++){
                if (month == lList[i]){
                    check = true;
                }
            }
            if (check){
                day = NumberUtil.getRandomNumber(1, 31);
            }else {
                day = NumberUtil.getRandomNumber(1, 30);
            }
        }
        int second = NumberUtil.getRandomNumber(0, 60);
        int minute = NumberUtil.getRandomNumber(0, 60);
        int hour = NumberUtil.getRandomNumber(0, 23);

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
