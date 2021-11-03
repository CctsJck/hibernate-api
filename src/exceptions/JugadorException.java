package exceptions;

public class JugadorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public JugadorException( String mensaje) {
		setMensaje(mensaje);
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
