package com.skynet.commons.exceptionHandlers;

import lombok.Data;

import java.util.List;

//@Data
public class SkyNetException extends RuntimeException {

    private int errorCode;
    private int subErrorCode;
    private Object errorData;

    public SkyNetException(int errorCode, int subErrorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.subErrorCode = subErrorCode;
    }

    public SkyNetException(int errorCode, int subErrorCode, String message, Object data) {
        super(message);
        this.errorCode = errorCode;
        this.subErrorCode = subErrorCode;
        this.errorData = data;
    }

    public SkyNetException(List<String> errorMsg) {
        super("Validation Error");
        this.errorData = errorMsg;
    }

}
