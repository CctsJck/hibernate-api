package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.JugadoresTorneo;

import javax.persistence.JoinColumn;




@Entity
@Table(name="campeonatos")
public class CampeonatoEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idCampeonato;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	private String estado;
	private String eliminadoC;
	@Column(name = "tipoCampeonato")
	private String tipo;
	
	@OneToMany(mappedBy="campeonato", cascade = CascadeType.ALL)
	private List<JugadoresTorneoEntity> jugadoresHabilitados;


	private int categoria;


	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	@JoinTable(
			name = "clubesCampeonato",
			joinColumns = {@JoinColumn(name="idCampeonato")},
			inverseJoinColumns = {@JoinColumn(name ="idClub")}
			)
	private List<ClubEntity> inscriptos;
	
	@OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
	private List<PartidoEntity> partidos;
	
	@OneToMany(mappedBy="campeonato", cascade = CascadeType.ALL) 
	private List<FaltaEntity> faltas;
	
	@OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
	private List<TablaPosicionesEntity> tablaPosiciones;
	
	
 	
	
	public CampeonatoEntity() {}
	
	
	public CampeonatoEntity(String descripcion, Date fechaInicio, Date fechaFin, String tipo,int categoria) {
		
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.inscriptos = new ArrayList<ClubEntity>();
		this.faltas = new ArrayList<FaltaEntity>();
		this.partidos = new ArrayList<PartidoEntity>();
		this.tipo = tipo;
		this.categoria=categoria;
	}

	public int getCategoria() {
		return categoria;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


	public Integer getIdCampeonato() {
		return idCampeonato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public List<ClubEntity> getInscriptos() {
		return inscriptos;
	}
	
	


	public void setInscriptos(List<ClubEntity> inscriptos) {
		this.inscriptos = inscriptos;
	}
	
	public void setIdCampeonato(Integer id) {
		this.idCampeonato=id;
	}
	
	public String getEliminado() {
		return eliminadoC;
	}


	public void setEliminado(String eliminado) {
		this.eliminadoC = eliminado;
	}
	
}
