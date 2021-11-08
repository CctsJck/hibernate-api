package modelo;

import daos.RepresentanteDAO;
import exceptions.ClubException;
import vo.ResponsableVO;

public class Responsable {

	private Integer legajo;
	private String tipodocumento;
	private Integer DNI;
	private String nombre;
	private Club club;
	private String eliminado;
	private Integer idUsuario;
	
	
	public Responsable(String tipoDocumento,int DNI, String nombre, Club club,Integer idUsuario) {
		this.nombre = nombre;
		this.club = club;
		this.tipodocumento= tipoDocumento;
		this.DNI = DNI;
		this.idUsuario = idUsuario;
	}

	public String getTipoDocumento() {
		return this.tipodocumento;
	}
	public int getDNI() {
		return this.DNI;
	}
	public void setDNI(int DNI) {
		this.DNI = DNI;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipodocumento = tipoDocumento;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Integer getLegajo() {
		return legajo;
	}
	
	public void grabar() {
		RepresentanteDAO.getInstancia().grabar(this);
	}
	
	 public void modificar(int idRepresentante,String nombre,int DNI,String tipoDocumento,int idClub,String eliminado) throws ClubException {
		 RepresentanteDAO.getInstancia().modificarRepresentante(this, idRepresentante, nombre, DNI, tipoDocumento, idClub,eliminado);
		 
	 }

	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
	public ResponsableVO toVO() {
		
		return new ResponsableVO(this.legajo,this.tipodocumento,this.DNI,this.nombre,this.eliminado,this.club.getIdClub(),this.getIdUsuario());
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	 
	 

	
	 
	 
}
