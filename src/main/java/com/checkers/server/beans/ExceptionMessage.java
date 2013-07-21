package com.checkers.server.beans;

/**
 * ExceptionMessage entity.
 * Applicable as @ExceptionHandler result only
 *
 * @author Pavel_Kuchin
 */
public class ExceptionMessage {
    private Long    code;       //Exception code
    private String  message;    //Short message for developer
    private String  detailsURL; //Detailed description

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }
}
