package vo;

import java.io.Serializable;

import modelo.Club;

public class ResponsableVO implements Serializable{
	

	private static final long serialVersionUID = 7410911568366233469L;
	private Integer legajo;
	private String tipodocumento;
	private Integer DNI;
	private String nombre;
	private String eliminado;
	
	public ResponsableVO() {}

	public ResponsableVO(Integer legajo, String tipodocumento, Integer DNI, String nombre,String eliminado) {
		this.legajo = legajo;
		this.tipodocumento = tipodocumento;
		this.DNI = DNI;
		this.nombre = nombre;
		this.eliminado = eliminado;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Integer getDNI() {
		return DNI;
	}

	public void setDNI(Integer dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
	
	
	
	

}
