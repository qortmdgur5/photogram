package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException{

    //객체를 구분하기위해
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }
}
