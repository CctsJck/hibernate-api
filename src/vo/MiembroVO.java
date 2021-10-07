package vo;

import java.io.Serializable;

import modelo.Club;
import modelo.Jugador;
import modelo.Partido;

public class MiembroVO implements Serializable{

	private static final long serialVersionUID = 1867702700647429603L;
	
	private Integer idLista; 
	private Integer ingreso;
	private Integer egreso;
	
	public MiembroVO() {}

	public MiembroVO(Integer idLista, Integer ingreso, Integer egreso) {
		this.idLista = idLista;
		this.ingreso = ingreso;
		this.egreso = egreso;
	}

	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}


	public Integer getIngreso() {
		return ingreso;
	}

	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
	}

	public Integer getEgreso() {
		return egreso;
	}

	public void setEgreso(Integer egreso) {
		this.egreso = egreso;
	}

	
	
	
	
	

}
