package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Entity.GolEntity;
import Entity.JugadorEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import exceptions.GolException;
import hibernate.HibernateUtil;
import modelo.Club;
import modelo.Gol;
import modelo.Jugador;
import modelo.Partido;
import sessionManager.SessionManager;

public class GolDAO {
	
private static GolDAO instancia;
	
	private GolDAO() {}
	
	public static GolDAO getInstancia() {
		if(instancia == null)
			instancia = new GolDAO();
		return instancia;
	}
	
	public List<Gol> obtenerGolesJugador(Integer idJugador) throws ClubException{
		List<Gol> resultado = new ArrayList<Gol>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<GolEntity> aux = session.createQuery("from FaltaEntity f where f.campeonato = "+idJugador).list();
		session.close();
		for (GolEntity e : aux) {
			resultado.add(toModelo(e)); 
		}
		return resultado;
	}
	
	public void agregarGolJugador(Gol golNuevo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		GolEntity aux = toEntity(golNuevo);
		session.beginTransaction();
		session.save(aux);
		session.getTransaction().commit();		
		session.close();
	}
	
	public Long obtenerCantidadGolesJugadorCampeonato(Integer idJugador, Integer idCampeonato){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long aux = (Long) session.createQuery("select count(*) from GolEntity g , PartidoEntity p where g.partido = p.idPartido and g.jugador = "+idJugador+" and p.campeonato = "+idCampeonato).uniqueResult();
		session.close();
		return aux;
	}
	
	public List<Gol> getGolesPartido(int idPartido) throws GolException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Gol> goles = new ArrayList<>();
		List<GolEntity> golesEntity = session.createQuery("FROM GolEntity g WHERE g.partido = "+idPartido+" ORDER BY g.minuto asc").list();
		if (golesEntity != null) {
			for(GolEntity gol : golesEntity) {
				goles.add(this.toModelo(gol));
			}
			session.close();
			return goles;
		}
		throw new GolException("No hubo goles en ese partido");
		

	}
	
	Gol toModelo(GolEntity gol) throws ClubException {
		Club auxClub = ClubDAO.getInstancia().toModeloClub(gol.getJugador().getClub());
		Jugador auxJugador = new Jugador(gol.getJugador().getTipoDocumento(),gol.getJugador().getNumeroDocumento(),gol.getJugador().getNombre(),gol.getJugador().getApellido(),auxClub,gol.getJugador().getFechaNacimiento(),gol.getJugador().getIdUsuario());
		auxJugador.setIdJugador(gol.getJugador().getIdJugador());
		Partido auxPartido = PartidoDAO.getInstancia().toModelo(gol.getPartido());
		System.out.println(auxJugador.getIdJugador());
		Gol golModelo = new Gol(auxJugador,auxPartido,gol.getMinuto(),gol.getTipo());
		golModelo.setIdGol(gol.getIdGol());
		return golModelo;
	}
	
	GolEntity toEntity(Gol gol) {

		JugadorEntity auxJugador = JugadorDAO.getInstancia().toEntity(gol.getJugador());
		PartidoEntity auxPartido = new PartidoEntity(gol.getPartido().getNroFecha(),gol.getPartido().getNroZona(),ClubDAO.getInstancia().toEntity(gol.getPartido().getClubLocal()),ClubDAO.getInstancia().toEntity(gol.getPartido().getClubVisitante()),gol.getPartido().getFechaPartido(),CampeonatoDAO.getInstancia().toEntity(gol.getPartido().getCampeonato()),gol.getPartido().getFase());
		auxPartido.setIdPartido(gol.getPartido().getIdPartido());
		
		GolEntity nuevoGol = new GolEntity(auxJugador,auxPartido,gol.getMinuto(),gol.getTipo());
		nuevoGol.setIdGol(gol.getIdGol());
		
		return nuevoGol;
	}

}
