package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="representantes")
public class RepresentanteEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idRepresentante")
	private Integer legajo;
	private String tipoDocumento;
	private Integer DNI;
	private String nombre;
	private String eliminadoR;
	private Integer idUsuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idClub")
	private ClubEntity club;
	
	public RepresentanteEntity() {}
	
	public RepresentanteEntity(String tipodocumento,int DNI, String nombre, ClubEntity club,Integer idUsuario) {
		this.tipoDocumento= tipodocumento;
		this.DNI = DNI;
		this.nombre = nombre;
		this.club = club;
		this.idUsuario = idUsuario;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}
	public int getDNI() {
		return this.DNI;
	}
	public void setDNI(int DNI) {
		this.DNI = DNI;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ClubEntity getClub() {
		return club;
	}

	public void setClub(ClubEntity club) {
		this.club = club;
	}

	public String getEliminado() {
		return eliminadoR;
	}

	public void setEliminado(String eliminado) {
		this.eliminadoR = eliminado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	

	

}
