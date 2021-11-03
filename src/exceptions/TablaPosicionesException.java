package exceptions;

public class TablaPosicionesException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String mensaje;

	
	public TablaPosicionesException(String mensaje) {
		setMensaje(mensaje); 
		 
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
