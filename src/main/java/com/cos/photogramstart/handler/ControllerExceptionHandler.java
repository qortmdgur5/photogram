package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice   //모든 예외를 다 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)  //예외를 낚아챌 어노테이션을 설정하고, 변수로 어떤 예외 클래스를 낚아챌지 넣는다.
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    }
}