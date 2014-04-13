package com.chat.client.core;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Created by asus on 12.04.2014.
 */
public class WebSocketClient extends JavaScriptObject {
    public static final int CONNECTING = 0;
    public static final int OPEN = 1;
    public static final int CLOSED = 2;

    protected WebSocketClient() {
    }

    public static native WebSocketClient create(String url) /*-{
        return new WebSocket(url);
    }-*/;

    public static native WebSocketClient create(String url, String protocol) /*-{
        return new WebSocket(url, protocol);
    }-*/;


    public final native int getReadyState() /*-{
        return this.readyState;
    }-*/;

    public final native int getBufferedAmount() /*-{
        return this.bufferedAmount;
    }-*/;

    public final native void send(String room, String data) /*-{
        this.send(JSON.stringify({
            room: room,
            message: data
        }));
    }-*/;

    public final native void close() /*-{
        this.close();
    }-*/;

    public final native void setOnOpen(OpenHandler handler) /*-{
        var _this = this;
        this.onopen = $entry(function() {
            handler.@com.chat.client.core.OpenHandler::onOpen(Lcom/chat/client/core/WebSocketClient;)(_this);
        });
    }-*/;

    public final native void setOnClose(CloseHandler handler) /*-{
        var _this = this;
        this.onclose = $entry(function() {
            handler.@com.chat.client.core.CloseHandler::onClose(Lcom/chat/client/core/WebSocketClient;)(_this);
        });
    }-*/;

    public final native void setOnError(ErrorHandler handler) /*-{
        var _this = this;
        this.onerror = $entry(function() {
            handler.@com.chat.client.core.ErrorHandler::onError(Lcom/chat/client/core/WebSocketClient;)(_this);
        });
    }-*/;

    public final native void setOnMessage(MessageHandler handler) /*-{
        var _this = this;
        this.onmessage = $entry(function(event) {
            handler.@com.chat.client.core.MessageHandler::onMessage(Lcom/chat/client/core/WebSocketClient;Lcom/chat/client/core/MessageEvent;)(_this, event);
        });
    }-*/;
}
