package Entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.JugadoresTorneo;



@Entity
@Table(name="jugadores")
public class JugadorEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idJugador;
	private String tipoDocumento;
	private int numeroDocumento;
	private String nombre;
	private String apellido;
	private String eliminadoJ;
	private Integer idUsuario;
	private Date fichaje;
	 
	@ManyToOne()
	@JoinColumn(name="idClub") 
	private ClubEntity club;
	
	@Column(name="fechaNac")
	private Date fechaNacimiento;
	private int categoria;
	
	@OneToMany(mappedBy="jugador", cascade = CascadeType.ALL)
	private List<GolEntity> goles;
	
	@OneToMany(mappedBy="jugador", cascade = CascadeType.ALL) //Aca va "jugador" , debe ser el nombre del campo de la clase (NO LA BD) que usamos para vincular
	private List<FaltaEntity> faltas;
	
	@OneToMany(mappedBy="jugador", cascade = CascadeType.ALL)
	private List<MiembroEntity> miembro;
	
	@OneToMany(mappedBy="jugador", cascade = CascadeType.ALL)
	private List<JugadoresTorneoEntity> campeonatosHabilitados; 
	
	public JugadorEntity() {}
	
	public JugadorEntity(String tipoDocumento,int numeroDocumento, String nombre,String apellido, ClubEntity club, Date fechaNacimiento,Integer idUsuario,Date fichaje) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.club = club;
		this.fechaNacimiento = fechaNacimiento;
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int auxCategoria = Integer.parseInt(getYearFormat.format(this.fechaNacimiento));
		if(Integer.compare(auxCategoria, 2000) == 1) {
        	this.categoria = auxCategoria - 2000;
        }else {
        	this.categoria = auxCategoria - 1900;
        	}
        this.goles = new ArrayList<GolEntity>();
        this.faltas = new ArrayList<FaltaEntity>();
        this.idUsuario = idUsuario;
        this.fichaje = fichaje;
	}
	
	

	public Date getFichaje() {
		return fichaje;
	}

	public void setFichaje(Date fichaje) {
		this.fichaje = fichaje;
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

	public ClubEntity getClub() {
		return club;
	}

	public void setClub(ClubEntity club) {
		this.club = club;
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
	
	public List<GolEntity> getGoles() {
		return goles;
	}

	public void setGoles(List<GolEntity> goles) {
		this.goles = goles;
	}
	
	public List<FaltaEntity> getFaltas() {
		return faltas;
	}

	public void setFaltas(List<FaltaEntity> faltas) {
		this.faltas = faltas;
	}

	public String getEliminado() {
		return eliminadoJ;
	}

	public void setEliminado(String eliminado) {
		this.eliminadoJ = eliminado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
}
