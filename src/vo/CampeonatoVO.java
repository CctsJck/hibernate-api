package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import modelo.Club;
import modelo.Falta;
import modelo.Partido;
import modelo.TablaPosiciones;

public class CampeonatoVO implements Serializable{

	private static final long serialVersionUID = -1252277303601326189L;
	private Integer idCampeonato;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private String eliminadoC;
	private String tipo;
	private int categoria;
	
	public CampeonatoVO() {}
	
	

	public CampeonatoVO(Integer idCampeonato, String descripcion, Date fechaInicio, Date fechaFin, String estado,
			String eliminadoC, String tipo, int categoria) {
		this.idCampeonato = idCampeonato;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.eliminadoC = eliminadoC;
		this.tipo = tipo;
		this.categoria = categoria;
	}



	public Integer getIdCampeonato() {
		return idCampeonato;
	}



	public void setIdCampeonato(Integer idCampeonato) {
		this.idCampeonato = idCampeonato;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getEliminadoC() {
		return eliminadoC;
	}



	public void setEliminadoC(String eliminadoC) {
		this.eliminadoC = eliminadoC;
	}


	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getCategoria() {
		return categoria;
	}



	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}



	
	
	
	
	
	

}
