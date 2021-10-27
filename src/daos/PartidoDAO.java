package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import Entity.FaltaEntity;
import Entity.JugadorEntity;
import Entity.MiembroEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import exceptions.PartidoException;
import hibernate.HibernateUtil;
import modelo.Club;
import modelo.Falta;
import modelo.Jugador;
import modelo.Miembro;
import modelo.Partido;
import sessionManager.SessionManager;

public class PartidoDAO {
	
	private static PartidoDAO instancia;
	
	private PartidoDAO() {}
	
	public static PartidoDAO getInstancia() {
		if(instancia == null)
			instancia = new PartidoDAO();
		return instancia;
	}
	
	public Partido obtenerPartido(Integer idPartido) throws PartidoException, ClubException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PartidoEntity aux = (PartidoEntity) session.createQuery("from PartidoEntity p where p.idPartido = "+idPartido).uniqueResult();
		session.getTransaction().commit();
		session.close();
		if (aux != null) {
			return toModelo(aux);
		}
		throw new PartidoException("El partido no existe");
	}
	
	Partido toModelo(PartidoEntity e) throws ClubException {
		Partido partido = new Partido(e.getNroFecha(),e.getNroZona(),ClubDAO.getInstancia().toModeloClub(e.getClubLocal()),ClubDAO.getInstancia().toModeloClub(e.getClubVisitante()) ,e.getFechaPartido(),CampeonatoDAO.getInstancia().toCampeonatoModelo(e.getCampeonato()));
		partido.setIdPartido(e.getIdPartido());
		partido.setGolesLocal(e.getGolesLocal());
		partido.setGolesVisitante(e.getGolesVisitante());
		
		partido.setConvalidaLocal(e.isConvalidaLocal());
		partido.setConvalidaVisitante(e.isConvalidaVisitante());
		
		return partido;
		
	}

	public void grabar(Partido partido) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(toEntity(partido));
		session.flush();
		session.clear();
		session.getTransaction().commit();
		
	}
	
	public void actualizar(Partido partido) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.flush();
		session.flush();
		session.update(toEntity(partido));
		session.flush();
		session.clear();
		session.getTransaction().commit();
	}

	PartidoEntity toEntity(Partido partido) {
		PartidoEntity aux = new PartidoEntity(partido.getNroFecha(),partido.getNroZona(),ClubDAO.getInstancia().toEntity(partido.getClubLocal()),ClubDAO.getInstancia().toEntity(partido.getClubVisitante()),partido.getFechaPartido(),CampeonatoDAO.getInstancia().toEntity(partido.getCampeonato()));
		aux.setIdPartido(partido.getIdPartido());
		aux.setGolesLocal(partido.getGolesLocal());
		aux.setGolesVisitante(partido.getGolesVisitante());
		if (partido.isConvalidaLocal()) {
			aux.setConvalidaLocal();
		} 
		if (partido.isConvalidaVisitante()) {
			
			aux.setConvalidaVisitante();
		}
		return aux;
	}
	public List<Miembro> getJugadoresPartido(Integer idPartido, Integer idClub) throws ClubException {
		List<Miembro> miembros  = new ArrayList<Miembro>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<MiembroEntity> aux = (List<MiembroEntity>) session.createQuery("from MiembroEntity m where m.club ="+idClub+" and m.partido="+idPartido).list();
		session.getTransaction().commit();
		session.close();
		for(MiembroEntity m : aux)
			miembros.add(MiembroDAO.getInstancia().toModelo(m));
		return miembros;
		
	}
	public Long obtenerCantidadJugadoresPartidoEquipo(Integer idPartido, Integer idClub) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Long cantidad =  (Long) session.createQuery("select count(*) from MiembroEntity m where m.club ="+idClub+" and m.partido="+idPartido).uniqueResult();
		session.getTransaction().commit();
		return cantidad;
		
	}
	
		public boolean validoParaJugar(int idJugador, int idCampeonato) {
			//Acumulacion de amarillas = 2 AMARILLAS
			Session session = HibernateUtil.getSessionFactory().openSession();
			String tarjeta = "amarilla";
			Long cantidadAmarillas = (Long) session.createQuery("SELECT count(*) FROM FaltaEntity f, PartidoEntity p WHERE p.idPartido = f.partido AND f.campeonato = "+idCampeonato+" AND f.jugador = "+idJugador+" AND f.tipo = 'amarilla'").uniqueResult();
			Integer fechaUltimaAmarilla = (Integer) session.createQuery("SELECT p.nroFecha FROM FaltaEntity f, PartidoEntity p WHERE p.idPartido = f.partido AND f.campeonato = "+idCampeonato+" AND f.jugador = "+idJugador+" AND f.tipo = 'amarilla' ORDER BY nroFecha DESC").setMaxResults(1).uniqueResult();
			
			Integer fechaUltimaRoja = (Integer) session.createQuery("SELECT p.nroFecha FROM FaltaEntity f, PartidoEntity p WHERE p.idPartido = f.partido AND f.campeonato = "+idCampeonato+" AND f.jugador = "+idJugador+" AND f.tipo = 'roja' ORDER BY nroFecha DESC").setMaxResults(1).uniqueResult();
			
			if (cantidadAmarillas == null || cantidadAmarillas == 0) {
				return true;
			} else if (fechaUltimaRoja == null || fechaUltimaRoja == 0) {
				return true;
			
			}
			else if (cantidadAmarillas % 2 != 0 && fechaUltimaAmarilla < this.obtenerUltimaFechaCampeonato(idCampeonato) && fechaUltimaRoja < this.obtenerUltimaFechaCampeonato(idCampeonato)) {
				return true;
			}
			return false;
			
			
		}
		
		public Integer obtenerUltimaFechaCampeonato(int idCampeonato) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Integer ultimaFecha = (Integer) session.createQuery("SELECT max(nroFecha) as nroFecha FROM PartidoEntity p WHERE p.campeonato ="+idCampeonato).uniqueResult();
			session.close();
			return ultimaFecha;
		}
		@SuppressWarnings("unchecked")
        public List<Partido> obtenerPartidoLocal(int idClub) throws ClubException{
			Session session = HibernateUtil.getSessionFactory().openSession();
            List<Partido> partidos = new ArrayList<Partido>();
            List<PartidoEntity>  auxPartidos = (List<PartidoEntity>) session.createQuery("FROM PartidoEntity p where p.idClubLocal ="+ idClub);
            session.close();
            for(PartidoEntity p : auxPartidos) {
                partidos.add(PartidoDAO.getInstancia().toModelo(p));
            }
            return partidos;
        }
		@SuppressWarnings("unchecked")
        public List<Partido> obtenerPartidoVisitante(int idClub) throws ClubException{
			Session session = HibernateUtil.getSessionFactory().openSession();
            List<Partido> partidos = new ArrayList<Partido>();
            List<PartidoEntity>  auxPartidos = (List<PartidoEntity>) session.createQuery("FROM PartidoEntity p where p.idClubVisitante ="+ idClub);
            session.close();
            for(PartidoEntity p : auxPartidos) {
                partidos.add(PartidoDAO.getInstancia().toModelo(p));
            }
            return partidos;
        }
        @SuppressWarnings("unchecked")
        public List<Partido> obtenerPartidoDeCampeonato(int idCampeonato) throws ClubException{
        	Session session = HibernateUtil.getSessionFactory().openSession();
            List<Partido> partidosCampeonato = new ArrayList<Partido>();
            List<PartidoEntity> auxPartidos = session.createQuery("FROM PartidoEntity p WHERE p.campeonato = "+idCampeonato).list(); 
            session.close();
            for (PartidoEntity partido : auxPartidos) {
                partidosCampeonato.add(PartidoDAO.getInstancia().toModelo(partido));
            }
            return partidosCampeonato;
        }

}
