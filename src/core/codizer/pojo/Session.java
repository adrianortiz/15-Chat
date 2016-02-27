package core.codizer.pojo;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class Session {
	
	public final static Session session = new Session();
	private String ip;
	
	/**
	 * Constructor base de Session
	 */
	private Session () {}

	/**
	 * Getter para ip
	 * @return ip String
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Setter para ip
	 * @param ip String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * Getter para la instancia de Session
	 * @return Session Session
	 */
	public static Session getIsntace() {
		return session;
	}
}
