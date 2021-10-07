package Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;



@Entity
@Table(name="partidos")
public class PartidoEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idPartido;
	private int nroFecha;
	private int nroZona;
	//private int categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idClubLocal")
	private ClubEntity clubLocal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idClubVisitante")
	private ClubEntity clubVisitante;
	
	private Integer golesLocal;
	private Integer golesVisitante;
	private Date fechaPartido;
	@Column(name="validadoLocal")
	private boolean convalidaLocal;
	
	@Column(name="validadoVisitante")
	private boolean convalidaVisitante;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="idCampeonato")
	private CampeonatoEntity campeonato;
	
	@OneToMany(mappedBy="partido", cascade = CascadeType.ALL)
	private List<MiembroEntity> jugadoresLocales;
	
	@OneToMany(mappedBy="partido", cascade = CascadeType.ALL)
	private List<MiembroEntity> jugadoresVisitantes;
	
	@OneToMany(mappedBy="partido",cascade = CascadeType.ALL)
	private List<GolEntity> golesLocales;
	@OneToMany(mappedBy="partido")
	private List<GolEntity> golesVisitantes;
	
	@OneToMany(mappedBy="partido")
	private List<FaltaEntity> faltas;
	
	
	
	public PartidoEntity() {}
	
	public PartidoEntity(int nroFecha, int nroZona, ClubEntity clubLocal, ClubEntity clubVisitante,Date fechaPartido, CampeonatoEntity campeonato) {
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		//this.categoria = categoria;
		this.clubLocal = clubLocal;
		this.clubVisitante = clubVisitante;
		this.golesLocal = 0;
		this.golesVisitante = 0;
		this.fechaPartido = fechaPartido;
		//this.convalidaLocal = false;
		//this.convalidaVisitante = false;
		this.campeonato = campeonato;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public int getNroFecha() {
		return nroFecha;
	}

	public int getNroZona() {
		return nroZona;
	}

	

	public ClubEntity getClubLocal() {
		return clubLocal;
	}

	public ClubEntity getClubVisitante() {
		return clubVisitante;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public Date getFechaPartido() {
		return fechaPartido;
	}

	public boolean isConvalidaLocal() {
		return convalidaLocal;
	}

	public boolean isConvalidaVisitante() {
		return convalidaVisitante;
	}

	public CampeonatoEntity getCampeonato() {
		return campeonato;
	}

	public List<MiembroEntity> getJugadoresLocales() {
		return jugadoresLocales;
	}

	public List<MiembroEntity> getJugadoresVisitantes() {
		return jugadoresVisitantes;
	}

	public void setNroFecha(int nroFecha) {
		this.nroFecha = nroFecha;
	}

	public void setNroZona(int nroZona) {
		this.nroZona = nroZona;
	}

	

	public void setClubLocal(ClubEntity clubLocal) {
		this.clubLocal = clubLocal;
	}

	public void setClubVisitante(ClubEntity clubVisitante) {
		this.clubVisitante = clubVisitante;
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public void setConvalidaLocal() {
		this.convalidaLocal = true;
	}

	public void setConvalidaVisitante() {
		this.convalidaVisitante = true;
	}

	public void agregarJugadoresLocales(MiembroEntity miembro) {
		this.jugadoresLocales.add(miembro);
	}

	public void agregarJugadoresVisitantes(MiembroEntity miembro) {
		this.jugadoresVisitantes.add(miembro);
	}
	
	public void setIdPartido(Integer id) {
		this.idPartido = id;
	}
	
	
	
}