package com.example.assignment02.entity.myenum;

public enum ProductStatus {
    DANGBAN(1), DUNGBAN(0), DAXOA(-1), UNDEFINE(-2);

    private int value;

    ProductStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProductStatus of(int value) {
        for (ProductStatus status :
                ProductStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return ProductStatus.UNDEFINE;
    }
}
