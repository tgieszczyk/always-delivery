package com.ahold.allwaysdelivery.api.payload;

public enum ErrorCode {

    // GLOBAL
    ERROR_403("403", "Access is denied"),
    ERROR_URL_NOT_REACHABLE("404", "Given url is not reachable: \'%s\'"),
    ERR0002("ERR0002", "Id is in invalid format \'%s\'"),
    ERR0003("ERR0003", "Ride is allready booked \'%s\'"),


    ;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
