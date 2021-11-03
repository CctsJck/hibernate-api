package exceptions;

public class CampeonatoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;


	public CampeonatoException(String mensaje) {
		this.setMensaje(mensaje);
		
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
