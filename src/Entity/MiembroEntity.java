package Entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="listaJugadoresPartido")
public class MiembroEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idLista;
	
	@OneToOne()
	@JoinColumn(name="idClub")

	private ClubEntity club;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idPartido")
	private PartidoEntity partido;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="idJugador")
	private JugadorEntity jugador;
	
	private Integer ingreso;
	private Integer egreso;

	public MiembroEntity() {}
	
	public MiembroEntity(ClubEntity club, PartidoEntity partido, JugadorEntity jugador) {
		this.club = club;
		this.partido = partido;
		this.jugador = jugador;
		this.ingreso = null;
		this.egreso = null;
	}
	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	public ClubEntity getClub() {
		return club;
	}

	public void setClub(ClubEntity club) {
		this.club = club;
	}

	public PartidoEntity getPartido() {
		return partido;
	}

	public void setPartido(PartidoEntity partido) {
		this.partido = partido;
	}

	public JugadorEntity getJugador() {
		return jugador;
	}

	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}

	public Integer getIngreso() {
		return ingreso;
	}

	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
	}

	public Integer getEgreso() {
		return egreso;
	}

	public void setEgreso(Integer egreso) {
		this.egreso = egreso;
	}

	
}
