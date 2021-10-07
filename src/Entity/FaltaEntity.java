package Entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="faltas")
public class FaltaEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idFalta;
	
	@ManyToOne
	@JoinColumn(name="idJugador")
	private JugadorEntity jugador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idPartido")
	private PartidoEntity partido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCampeonato")
	private CampeonatoEntity campeonato;
	
	private int minuto;
	private String tipo;
	
	public FaltaEntity() {}
	
	public FaltaEntity(JugadorEntity jugador, PartidoEntity partido, CampeonatoEntity campeonato, int minuto, String tipo) {
		this.jugador = jugador;
		this.partido = partido;
		this.campeonato = campeonato;
		this.minuto = minuto;
		this.tipo = tipo;
	}

	public Integer getIdFalta() {
		return idFalta;
	}

	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}
	
	public JugadorEntity getJugador() {
		return jugador;
	}

	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}

	public PartidoEntity getPartido() {
		return partido;
	}

	public void setPartido(PartidoEntity partido) {
		this.partido = partido;
	}

	public CampeonatoEntity getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(CampeonatoEntity campeonato) {
		this.campeonato = campeonato;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
