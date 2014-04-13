package com.chat.shared;

import java.io.Serializable;

/*
 * Created by asus on 10.04.2014.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 5057319449222375743L;
    private String room;

    private String message;

    public Message() {
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (message != null ? !message.equals(message1.message) : message1.message != null) return false;
        if (room != null ? !room.equals(message1.room) : message1.room != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = room != null ? room.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "room='" + room + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
