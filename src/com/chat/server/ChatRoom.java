package com.chat.server;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 12.04.2014.
 */
public class ChatRoom {
    private Set<ChatUser> users = new HashSet<ChatUser>();

    public synchronized void messageAll(String msg) {
        for (ChatUser user : users) {
            user.sendMessage(msg);
        }
    }

    public synchronized void addUser(ChatUser user) {
        users.add(user);
    }

    public synchronized void removeUser(ChatUser user) {
        users.remove(user);
    }
}
