package com.example.wcdexam.entity.myenum;

public enum PhoneStatus {
    ACTIVE(1) , DEACTIVE(0), DELETED(-1), UNDEFINE(-2);

    private  int value;

    PhoneStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static PhoneStatus of(int value){
        for (PhoneStatus status:
                PhoneStatus.values()) {
            if (status.getValue()== value){
                return status;
            }
        }
        return PhoneStatus.UNDEFINE;
    }
}
