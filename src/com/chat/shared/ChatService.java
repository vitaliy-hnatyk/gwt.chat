package com.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import static com.chat.shared.StatusUpdate.Status;

/**
 * Created by asus on 09.04.2014.
 */

@RemoteServiceRelativePath("chat")
public interface ChatService extends RemoteService {

    public String getUsername() throws ChatException;

    public void login(String username) throws ChatException;

    public void logout(String username) throws ChatException;

    public void send(String message) throws ChatException;

    public void setStatus(Status status) throws ChatException;
}
