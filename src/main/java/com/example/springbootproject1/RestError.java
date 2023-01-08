package com.example.springbootproject1;

public class RestError {
    private String errorMassage;
    private int errorCode;
    private Object data;

    public RestError(String errorMassage, int errorCode) {
        this.errorMassage = errorMassage;
        this.errorCode = errorCode;
    }

    public RestError(Object data) {
        this.data = data;
        this.errorCode = 0;
    }

}
