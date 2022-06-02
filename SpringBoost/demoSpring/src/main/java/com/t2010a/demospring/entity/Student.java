package com.t2010a.demospring.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {
    private String rollNumber;
    public String fulllName;
    public String email;
    public String phone;
    private String address;
    private String note;
    private LocalDateTime dob;
    private int status;
}
