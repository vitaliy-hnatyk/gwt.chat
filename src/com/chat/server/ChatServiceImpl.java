package com.chat.server;

import com.chat.shared.ChatException;
import com.chat.shared.ChatService;
import com.chat.shared.Message;
import com.chat.shared.StatusUpdate;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by asus on 09.04.2014.
 */
public class ChatServiceImpl extends RemoteServiceServlet implements ChatService {

	private ConcurrentMap<String, CometSession> users = new ConcurrentHashMap<String, CometSession>();

    @Override
    public String getUsername() throws ChatException {
	    HttpSession httpSession = getThreadLocalRequest().getSession(false);
	    if (httpSession == null) {
		    return null;
	    }

	    return (String) httpSession.getAttribute("username");
    }

    @Override
    public void login(String username) throws ChatException {
	    HttpSession httpSession = getThreadLocalRequest().getSession();

	    CometSession cometSession = CometServlet.getCometSession(httpSession);

	    httpSession.setAttribute("username", username);

	    if (users.putIfAbsent(username, cometSession) != null) {
		    httpSession.invalidate();
		    throw new ChatException("User: " + username + " already logged in");
	    }
    }

    @Override
    public void logout(String username) throws ChatException {
	    HttpSession httpSession = getThreadLocalRequest().getSession(false);
	    if (httpSession == null) {
		    throw new ChatException("User: " + username + " is not logged in: no http session");
	    }

	    CometSession cometSession = CometServlet.getCometSession(httpSession, false);
	    if (cometSession == null) {
		    throw new ChatException("User: " + username + " is not logged in: no comet session");
	    }

	    // check the user name parameter matches the HTTP sessions user name
	    if (!username.equals(httpSession.getAttribute("username"))) {
		    throw new ChatException("User: " + username + " is not logged in on this session");
	    }

	    users.remove(username, cometSession);
	    httpSession.invalidate();
    }

    @Override
    public void send(String message) throws ChatException {
	    HttpSession httpSession = getThreadLocalRequest().getSession(false);
	    if (httpSession == null) {
		    throw new ChatException("not logged in: no http session");
	    }

	    String username = (String) httpSession.getAttribute("username");
	    if (username == null) {
		    throw new ChatException("not logged in: no http session username");
	    }

	    Message chatMessage = new Message();
	    chatMessage.setUserName(username);
	    chatMessage.setMessage(message);

	    for (Map.Entry<String, CometSession> entry : users.entrySet()) {
		    entry.getValue().enqueue(chatMessage);
	    }
    }

    @Override
    public void setStatus(StatusUpdate.Status status) throws ChatException {
	    HttpSession httpSession = getThreadLocalRequest().getSession(false);
	    if (httpSession == null) {
		    throw new ChatException("not logged in: no http session");
	    }

	    String username = (String) httpSession.getAttribute("username");
	    if (username == null) {
		    throw new ChatException("not logged in: no http session username");
	    }

	    // create the chat message
	    StatusUpdate statusUpdate = new StatusUpdate();
	    statusUpdate.setUserName(username);
	    statusUpdate.setStatus(status);

	    for (Map.Entry<String, CometSession> entry : users.entrySet()) {
		    entry.getValue().enqueue(statusUpdate);
	    }
    }
}
