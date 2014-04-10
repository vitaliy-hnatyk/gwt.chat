package com.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import static com.chat.shared.StatusUpdate.Status;

/**
 * Created by asus on 09.04.2014.
 */
public interface ChatServiceAsync {
    public void getUsername(AsyncCallback<String> callback);

    public void login(String username, AsyncCallback<Void> callback);

    public void logout(String username, AsyncCallback<Void> callback);

    public void send(String message, AsyncCallback<Void> callback);

    public void setStatus(Status status, AsyncCallback<Void> callback);
}
