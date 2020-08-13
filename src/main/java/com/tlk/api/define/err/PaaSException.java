package com.tlk.api.define.err;

public class PaaSException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "No message";

    private final int errCode;

    private String message = "";

    public PaaSException(int errCode) {
        super(DEFAULT_MESSAGE);
        this.errCode = errCode;
        this.message = DEFAULT_MESSAGE;
    }

    public PaaSException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.message = message;
    }

    public PaaSException(PaaSErrCode paaSErrCode) {
        super(paaSErrCode.msg());
        this.errCode = paaSErrCode.code();
        this.message = paaSErrCode.msg();
    }

    public PaaSException(PaaSErrCode paaSErrCode, String message) {
        this(paaSErrCode.code(), message);
    }

    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
