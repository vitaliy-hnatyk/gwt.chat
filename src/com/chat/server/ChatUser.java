package com.chat.server;

import com.chat.shared.Message;
import net.minidev.json.JSONNavi;
import net.minidev.json.JSONValue;
import org.eclipse.jetty.websocket.WebSocket;

import java.io.IOException;

/**
 * Created by asus on 12.04.2014.
 */
public class ChatUser implements WebSocket.OnTextMessage {
    private final ChatRoom room;
    private final String name;
    private Connection connection;

    public ChatUser(ChatRoom room, String name) {
        this.name = name;
        this.room = room;
    }

    public void sendMessage(String message) {
        try {
            connection.sendMessage(message);
        } catch (IOException e) {
        }
    }

    @Override
    public void onOpen(Connection conn) {
        this.connection = conn;
        room.addUser(this);
    }

    @Override
    public void onMessage(String msg) {
        Message mapTo = new Message();
        JSONValue.parse(msg, mapTo);
        JSONNavi navi = JSONNavi.newInstance();
        navi.set("room",mapTo.getRoom()).set("message","<b>" + name + "</b>: " + mapTo.getMessage());
        room.messageAll(navi.toString());
    }

    @Override
    public void onClose(int exitCode, String exitMsg) {
        room.removeUser(this);
    }
}
