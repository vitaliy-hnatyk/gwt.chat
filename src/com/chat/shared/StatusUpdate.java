package com.chat.shared;

import java.io.Serializable;

/**
 * Created by asus on 09.04.2014.
 */
public class StatusUpdate implements Serializable {
    public enum Status {
        ONLINE, BUSY, AWAY, OFFLINE
    }

    private static final long serialVersionUID = 903010139L;

    private String username;
    private Status status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
