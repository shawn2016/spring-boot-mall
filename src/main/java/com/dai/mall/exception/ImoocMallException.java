package com.dai.mall.exception;

public class ImoocMallException extends RuntimeException {

    private final Integer code;
    private  final  String message;

    public ImoocMallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ImoocMallException(ImoocMallExceptionEnum ex) {
        this(ex.getCode(),ex.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }



}
