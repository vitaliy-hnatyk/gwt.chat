package com.chat.shared;

import java.io.Serializable;

/**
 * Created by asus on 09.04.2014.
 */
public class StatusUpdate implements Serializable {

    public enum Status {
        ONLINE, BUSY, AWAY, OFFLINE
    }

    private static final long serialVersionUID = -2665972403950940847L;

    private String userName;

    private Status status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusUpdate that = (StatusUpdate) o;

        if (status != that.status) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
