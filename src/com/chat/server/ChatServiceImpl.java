package com.chat.server;

import com.chat.shared.ChatException;
import com.chat.shared.ChatService;
import com.chat.shared.StatusUpdate;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by asus on 09.04.2014.
 */
public class ChatServiceImpl extends RemoteServiceServlet implements ChatService {
    @Override
    public String getUsername() throws ChatException {
        return null;
    }

    @Override
    public void login(String username) throws ChatException {

    }

    @Override
    public void logout(String username) throws ChatException {

    }

    @Override
    public void send(String message) throws ChatException {

    }

    @Override
    public void setStatus(StatusUpdate.Status status) throws ChatException {

    }
}
