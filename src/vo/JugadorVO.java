package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import modelo.Club;
import modelo.Falta;
import modelo.Gol;
import modelo.Miembro;

public class JugadorVO implements Serializable{


	private static final long serialVersionUID = 7409161064254512414L;
	
	private Integer idJugador;
	private String tipoDocumento;
	private int numeroDocumento;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private int categoria;
	private String estado;
	private String eliminado;
	private Integer idClub;
	
	public JugadorVO() {}

	public JugadorVO(Integer idJugador, String tipoDocumento, int numeroDocumento, String nombre, String apellido, Date fechaNacimiento, int categoria, String estado, String eliminado, Integer idClub) {
		this.idJugador = idJugador;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.categoria = categoria;
		this.estado = estado;
		this.eliminado = eliminado;
		this.idClub = idClub;
	}

	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	public Integer getIdClub() {
		return idClub;
	}

	public void setIdClub(Integer idClub) {
		this.idClub = idClub;
	}

	
	
}
