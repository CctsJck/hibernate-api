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
	private int visitante;
	private int local;
	private Integer golesLocal;
	private Integer golesVisitante;
	private Date fechaPartido;
	private boolean convalidaLocal;
	private boolean convalidaVisitante;
	private int idCampeonato;
	private String fase;


	public PartidoVO() {}

	public PartidoVO(Integer idPartido,int local,int visitante, int nroFecha, int nroZona,
			Integer golesLocal, Integer golesVisitante, Date fechaPartido, boolean convalidaLocal,
			boolean convalidaVisitante, int idCampeonato,String fase) {
		this.visitante = visitante;
		this.local = local;
		this.idPartido = idPartido;
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = convalidaLocal;
		this.convalidaVisitante = convalidaVisitante;
		this.idCampeonato = idCampeonato;
		this.fase = fase;
		
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
	
	public int getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(int idCampeonato) {
		this.idCampeonato = idCampeonato;
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

	public int getVisitante() {
		return visitante;
	}

	public void setVisitante(int visitante) {
		this.visitante = visitante;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
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
	
	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	
}
