package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import modelo.Campeonato;
import modelo.Club;
import modelo.Miembro;

public class PartidoVO implements Serializable{

	private static final long serialVersionUID = 3448353020914716843L;
	private Integer idPartido;
	private int nroFecha;
	private int nroZona;
	private Integer golesLocal;
	private Integer golesVisitante;
	private Date fechaPartido;
	private boolean convalidaLocal;
	private boolean convalidaVisitante;

	
	public PartidoVO() {}

	public PartidoVO(Integer idPartido, int nroFecha, int nroZona,
			Integer golesLocal, Integer golesVisitante, Date fechaPartido, boolean convalidaLocal,
			boolean convalidaVisitante) {
		this.idPartido = idPartido;
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = convalidaLocal;
		this.convalidaVisitante = convalidaVisitante;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}

	public int getNroFecha() {
		return nroFecha;
	}

	public void setNroFecha(int nroFecha) {
		this.nroFecha = nroFecha;
	}

	public int getNroZona() {
		return nroZona;
	}

	public void setNroZona(int nroZona) {
		this.nroZona = nroZona;
	}

	

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Date getFechaPartido() {
		return fechaPartido;
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public boolean isConvalidaLocal() {
		return convalidaLocal;
	}

	public void setConvalidaLocal(boolean convalidaLocal) {
		this.convalidaLocal = convalidaLocal;
	}

	public boolean isConvalidaVisitante() {
		return convalidaVisitante;
	}

	public void setConvalidaVisitante(boolean convalidaVisitante) {
		this.convalidaVisitante = convalidaVisitante;
	}

	
}
