package Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name= "clubes")
public class ClubEntity{
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idClub;
	
	private String nombre;
	private String direccion;
	
	@OneToMany(mappedBy="club",cascade = CascadeType.ALL) 
	private List<RepresentanteEntity> responsables;
	
	@OneToMany(mappedBy="club",cascade = CascadeType.ALL) 
	private List<JugadorEntity> jugadores; 
	
	@ManyToMany(mappedBy="inscriptos",fetch=FetchType.EAGER)
	private List<CampeonatoEntity> participaciones; 
	
	@OneToMany(mappedBy="clubLocal",cascade = CascadeType.ALL) //ver hay dos idclub en el partidos
	private List<PartidoEntity> partidosLocal;
	
	@OneToMany(mappedBy="clubVisitante",cascade = CascadeType.ALL) //ver hay dos idclub en el partidos
	private List<PartidoEntity> partidosVisitante;
	
	@OneToMany(mappedBy="club", cascade=CascadeType.ALL)
	private List<TablaPosicionesEntity> tablaPosiciones;
	
	
	
	public ClubEntity(){}
	
	
	public ClubEntity(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		partidosLocal = new ArrayList<PartidoEntity>();
		partidosVisitante = new ArrayList<PartidoEntity>();
		jugadores = new ArrayList<JugadorEntity>();
		responsables = new ArrayList<RepresentanteEntity>();
		participaciones = new ArrayList<CampeonatoEntity>();
		
	}


	public Integer getIdClub() {
		return idClub;
	}


	public void setIdClub(Integer idClub) {
		this.idClub = idClub;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public List<RepresentanteEntity> getResponsables() {
		return responsables;
	}


	public void setResponsables(List<RepresentanteEntity> responsables) {
		this.responsables = responsables;
	}


	public List<JugadorEntity> getJugadores() {
		return jugadores;
	}


	public void setJugadores(List<JugadorEntity> jugadores) {
		this.jugadores = jugadores;
	}


	public List<CampeonatoEntity> getParticipaciones() {
		return participaciones;
	}


	public void setParticipaciones(List<CampeonatoEntity> participaciones) {
		this.participaciones = participaciones;
	}


	public List<PartidoEntity> getPartidosLocal() {
		return partidosLocal;
	}


	public void setPartidosLocal(List<PartidoEntity> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}


	public List<PartidoEntity> getPartidosVisitante() {
		return partidosVisitante;
	}


	public void setPartidosVisitante(List<PartidoEntity> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}


	public List<TablaPosicionesEntity> getTablaPosiciones() {
		return tablaPosiciones;
	}


	public void setTablaPosiciones(List<TablaPosicionesEntity> tablaPosiciones) {
		this.tablaPosiciones = tablaPosiciones;
	}


	
	
	

	
	
	
		
		
}