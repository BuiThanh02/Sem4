package com.example.wcdexam.entity.myenum;

public enum BrandStatus {
    ACTIVE(1) , DEACTIVE(0), DELETED(-1), UNDEFINE(-2);

    private  int value;

    BrandStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static BrandStatus of(int value){
        for (BrandStatus status:
                BrandStatus.values()) {
            if (status.getValue()== value){
                return status;
            }
        }
        return BrandStatus.UNDEFINE;
    }
}
