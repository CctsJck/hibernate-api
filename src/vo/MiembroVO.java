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
	private Integer idJugador;
	private Integer idPartido;
	
	public MiembroVO() {}

	public MiembroVO(Integer idLista, Integer ingreso, Integer egreso,Integer idJugador,Integer idPartido) {
		this.idPartido = idPartido;
		this.idJugador = idJugador;
		this.idLista = idLista;
		this.ingreso = ingreso;
		this.egreso = egreso;
	}
	
	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
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
