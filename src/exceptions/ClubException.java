package exceptions;

public class ClubException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String mensaje;

	public ClubException(String mensaje) {
		//super(mensaje);
		this.setMensaje(mensaje);
		
		
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
