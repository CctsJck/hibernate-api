package sessionManager;

import org.hibernate.classic.Session;

import hibernate.HibernateUtil;

public class SessionManager {
	
	/* Singleton */
	private static SessionManager instancia;
	private Session session;

	
	public SessionManager() {}
	
	public static SessionManager getInstancia() {
		if (instancia == null) {
			instancia = new SessionManager();
		}
		return instancia;
	}
	
	public void openSession() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public Session getSession() {
		if (session != null) {
			return session;
		}
		return null;
	}
	
	
	
	public void closeSession() {
		session.close();
	}
	
	

}
