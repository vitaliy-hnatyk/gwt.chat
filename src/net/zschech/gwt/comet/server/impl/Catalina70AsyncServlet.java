package net.zschech.gwt.comet.server.impl;

import org.apache.catalina.Session;
import org.apache.catalina.session.StandardSessionFacade;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;

/**
 * An extension of {@link net.zschech.gwt.comet.server.impl.BlockingAsyncServlet} for Catalina/Tomcat 6.
 *
 * This extension improves on the default session keep alive strategy, refreshing the connection just before the session
 * expires, by updating the session managers last access time when ever sending data down the Comet connection
 *
 * @author Richard Zschech
 */
public class Catalina70AsyncServlet extends BlockingAsyncServlet {

	private final Field sessionField;

	public Catalina70AsyncServlet() throws SecurityException, NoSuchFieldException {
		sessionField = StandardSessionFacade.class.getDeclaredField("session");
		sessionField.setAccessible(true);
	}
	
	@Override
	protected boolean access(HttpSession httpSession) {
		try {
			Session catalinaSession = (Session) sessionField.get(httpSession);
			catalinaSession.access();
			return true;
		}
		catch (IllegalArgumentException e) {
			log("Error updating session last access time", e);
			return false;
		}
		catch (IllegalAccessException e) {
			log("Error updating session last access time", e);
			return false;
		}
	}
}
