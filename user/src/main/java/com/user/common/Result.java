package com.user.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> {

    private int code;

    private String msg;

    private T date;

    public Result() {
    }

    public Result(HttpStatus httpStatus, String msg, T date) {
        this.code = httpStatus.value();
        this.msg = msg;
        this.date = date;
    }

    public Result(HttpStatus httpStatus, String msg) {
        this.code = httpStatus.value();
        this.msg = msg;
    }

    public Result(HttpStatus httpStatus, T date) {
        this.code = httpStatus.value();
        this.date = date;
    }

    public Result(HttpStatus httpStatus) {
        this.code = httpStatus.value();
    }
}
