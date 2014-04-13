package com.chat.server;



import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by asus on 12.04.2014.
 */

@WebServlet("/ws")
public class ChatSocketServletImpl extends WebSocketServlet implements Servlet{


    private static final long serialVersionUID = 3104251441291007572L;
    private static final ChatRoom room = new ChatRoom();
    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest req, String s) {
        String name = req.getParameter("name");
       // String name = req.getParameter("name");
        return new ChatUser(room, name);
    }
}
