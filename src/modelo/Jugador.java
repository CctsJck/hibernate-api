package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.MiembroEntity;
import controlador.Controlador;
import daos.FaltaDAO;
import daos.GolDAO;
import daos.JugadorDAO;
import daos.MiembroDAO;
import exceptions.ClubException;
import vo.JugadorVO;

public class Jugador {

	private Integer idJugador;
	private String tipoDocumento;
	private int numeroDocumento;
	private String nombre;
	private String apellido;
	private Club club;
	private Date fechaNacimiento;
	private int categoria;
	private String estado;
	private String eliminado;
	private List<Gol> goles;
	private List<Falta> faltas;
	private Miembro miembro;
	private Integer idUsuario;
	
	public Jugador(String tipoDocumento,Integer numeroDocumento, String nombre, String apellido, Club club, Date fechaNacimiento,Integer idUsuario) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.club = club;
		this.fechaNacimiento = fechaNacimiento;
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int auxCategoria = Integer.parseInt(getYearFormat.format(this.fechaNacimiento));
        if(auxCategoria > 1999)
        	this.categoria = auxCategoria - 1900;
        else
        	this.categoria = auxCategoria - 2000;
        this.goles = new ArrayList<Gol>();
        this.faltas = new ArrayList<Falta>();
        this.idUsuario = idUsuario;
	}

	

	public Integer getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setFechaNacimiento(Date Fecha) {
		this.fechaNacimiento = Fecha;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getCategoria() {
		return categoria;
	}

	public List<Gol> getGoles() throws ClubException {
		return GolDAO.getInstancia().obtenerGolesJugador(this.getIdJugador()); 
	}

	public void agregarGol(Gol gol) {
		GolDAO.getInstancia().agregarGolJugador(gol);
	}
	
	public List<Falta> getFaltas() throws ClubException {
		return FaltaDAO.getInstancia().obtenerFaltasJugadorX(this.getIdJugador()); 
	}
	
	public void agregarFalta(Falta falta) {
		FaltaDAO.getInstancia().agregarFaltaJugador(falta); 
	}

	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	
	public boolean soyEseJugador(Jugador o) { 
		if (o.getIdJugador() == this.getIdJugador()) {
			return true;
		}
		return false;
	}
	
	public void setMiembro(Miembro m) {
		this.miembro = m;
	}
	
	public Miembro getMiembro() throws ClubException {
		return MiembroDAO.getInstancia().getMiembro(this.getIdJugador());
	}
	
	public void grabar() {
		JugadorDAO.getInstancia().grabar(this);
	}

	public void borrar() {
		JugadorDAO.getInstancia().eliminarJugador(this);
	}
	public void modificar(int idJugador,String tipoDocumento,int numeroDocumento,String nombre,String apellido,int idClub,Date fechaNac) throws ClubException {
		JugadorDAO.getInstancia().modificarJugador(this,idJugador,tipoDocumento, numeroDocumento, nombre, apellido, idClub, fechaNac);
	}

	public String getEliminado() {
		return this.eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public JugadorVO toVO() {
		return new JugadorVO(this.idJugador,this.tipoDocumento,this.numeroDocumento,this.nombre,this.apellido,this.fechaNacimiento,this.categoria,this.estado,this.eliminado,this.club.getIdClub(),this.idUsuario);
	}
	
	
	
	
}
