package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Entity.CampeonatoEntity;
import Entity.FaltaEntity;
import Entity.JugadorEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import exceptions.FaltaException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Club;
import modelo.Falta;
import modelo.Jugador;
import modelo.Partido;
import sessionManager.SessionManager;

public class FaltaDAO {
	
	private static FaltaDAO instancia;
	
	private FaltaDAO() {}
	
	public static FaltaDAO getInstancia() {
		if(instancia == null)
			instancia = new FaltaDAO();
		return instancia;
	}
	
	public List<Falta> obtenerFaltasCampeonato(Integer idCampeonato) throws ClubException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Falta> resultado = new ArrayList<Falta>();
		@SuppressWarnings("unchecked")
		List<FaltaEntity> aux = session.createQuery("from FaltaEntity f where f.campeonato = "+idCampeonato).list();
		session.close();
		for (FaltaEntity e : aux) {
			resultado.add(toModelo(e)); 
		}
		return resultado;
	}
	
	
	
	

	
	public List<Falta> obtenerFaltasJugadorX(Integer idJugador) throws ClubException {
		List<Falta> resultado = new ArrayList<Falta>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<FaltaEntity> aux = session.createQuery("from FaltaEntity f where f.jugador ="+ idJugador).list();
		session.getTransaction().commit();
		session.close();
		if(aux.size() != 0)
			for(FaltaEntity e : aux)
				resultado.add(toModelo(e));
		return resultado;
	}
	
	public Long obtenerCantidadFaltasJugadorX(Integer idJugador , Integer idCampeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Long aux = (Long) session.createQuery("select count(*) from FaltaEntity f where f.jugador ="+ idJugador +" and f.campeonato="+idCampeonato).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return aux;
	}
	
	public void agregarFaltaJugador(Falta faltaNuevo) {
		FaltaEntity aux = toEntity(faltaNuevo);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(aux);
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	public List<Falta> getFaltasPartido(int idPartido) throws FaltaException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Falta> faltas = new ArrayList<>();
		List<FaltaEntity> faltasEntity = session.createQuery("FROM FaltaEntity f WHERE f.partido="+idPartido+" ORDER BY f.minuto asc").list();
		if (faltasEntity != null) {
			for (FaltaEntity falta : faltasEntity) {
				faltas.add(this.toModelo(falta));
			}
			session.close();
			return faltas;
		}
		throw new FaltaException("No hubo faltas en ese partido");
	}
	
	FaltaEntity toEntity(Falta falta) {
		JugadorEntity auxJugador = JugadorDAO.getInstancia().toEntity(falta.getJugador());
		PartidoEntity auxPartido = PartidoDAO.getInstancia().toEntity(falta.getPartido());
		CampeonatoEntity auxCampeonato = CampeonatoDAO.getInstancia().toEntity(falta.getCampeonato());
		FaltaEntity aux = new FaltaEntity(auxJugador,auxPartido,auxCampeonato,falta.getMinuto(),falta.getTipo());
		aux.setIdFalta(falta.getIdFalta());
		
		return aux;
	} 
	
	Falta toModelo(FaltaEntity falta) throws ClubException {
		Club auxClub = ClubDAO.getInstancia().toModeloClub(falta.getJugador().getClub());
		Jugador auxJugador = new Jugador(falta.getJugador().getTipoDocumento(),falta.getJugador().getNumeroDocumento(),falta.getJugador().getNombre(),falta.getJugador().getApellido(),auxClub,falta.getJugador().getFechaNacimiento(),falta.getJugador().getIdUsuario(),falta.getJugador().getFichaje());
		Partido auxPartido = PartidoDAO.getInstancia().toModelo(falta.getPartido());
		Campeonato auxCampeonato =	CampeonatoDAO.getInstancia().toCampeonatoModelo(falta.getCampeonato());
		auxJugador.setIdJugador(falta.getJugador().getIdJugador());
		Falta aux = new Falta(auxJugador, auxPartido, auxCampeonato,falta.getMinuto(),falta.getTipo());
		aux.setIdFalta(falta.getIdFalta());
		return aux;
	}
	
}
