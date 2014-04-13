package com.chat.client.core;

public interface MessageHandler {

  public void onMessage(WebSocketClient webSocket, MessageEvent event);
}
