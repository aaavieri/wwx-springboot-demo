package com.wwx.tob.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
    
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    private int errCode = 0;

    public int getErrCode() {
        return this.errCode;
    }

    public ApplicationException errCode(int errCode) {
        this.errCode = errCode;
        return this;
    }
}
