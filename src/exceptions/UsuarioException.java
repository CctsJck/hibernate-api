package exceptions;

public class UsuarioException extends Exception{
	
	
	private static final long serialVersionUID = 2L;
	private String mensaje;
	
	public UsuarioException(String mensaje) {
		this.setMensaje(mensaje);
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
