package exceptions;

public class ResponsableException extends Exception {

	private static final long serialVersionUID = 1238736284217240676L;
	private String mensaje;

	public ResponsableException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}

}
