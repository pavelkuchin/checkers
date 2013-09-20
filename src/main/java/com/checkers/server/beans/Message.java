package com.checkers.server.beans;

import javax.persistence.*;
import java.util.Date;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class Message {
    private Long    muid;       // Message unique identifier

    private String  message;

    private Long    fromUuid;   // From user unique identifier

    private Long    toUuid;     // To user unique identifier

    // Date of creation
    private Date    created;

    public Long getMuid() {
        return muid;
    }

    public void setMuid(Long muid) {
        this.muid = muid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getFromUuid() {
        return fromUuid;
    }

    public void setFromUuid(Long fromUuid) {
        this.fromUuid = fromUuid;
    }

    public Long getToUuid() {
        return toUuid;
    }

    public void setToUuid(Long toUuid) {
        this.toUuid = toUuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
