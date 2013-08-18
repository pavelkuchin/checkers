package com.checkers.server.exceptions;

import com.checkers.server.beans.ExceptionMessage;

/**
 *
 *
 * @author  Pavel Kuchin
 */
public class CheckersException extends Exception {
    Long    code;
    String  detailsURL;

    public CheckersException() {}

    public CheckersException(Long code, String message){
        super(message);
        this.code = 200 + code;
        this.detailsURL = "https://github.com/pavelkuchin/checkers/wiki/Errors#code-" + this.code;
    }

    public CheckersException(Long code, String message, String detailsURL){
        super(message);
        this.code = 200 + code;
        this.detailsURL = detailsURL;
    }

    public ExceptionMessage getExceptionMessage(){
        ExceptionMessage eMsg = new ExceptionMessage();

        eMsg.setCode(this.getCode());
        eMsg.setMessage(this.getMessage());
        eMsg.setDetailsURL(this.getDetailsURL());

        return eMsg;
    }

    public String getDetailsURL(){
        return this.detailsURL;
    }

    public Long getCode(){
        return this.code;
    }
}
