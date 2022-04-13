package com.romannumeralconverter.valueobject;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResultVO<T> {
    //error code
    private HttpStatus code;
    private String msg;
    // return content
    private T data;
}
