package com.example.ITBC_Project1.entity;

public enum LogType {
    Warning(1), Error(2),OK(3);

    private final int value;

    private LogType(int value) {
        this.value = value;
    }
}







