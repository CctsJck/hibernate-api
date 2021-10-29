package daos;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Session;

import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import Entity.JugadorEntity;
import exceptions.ClubException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Club;
import modelo.Jugador;
import sessionManager.SessionManager;

public class ClubDAO {
	
	private static ClubDAO instancia;
	
	private ClubDAO() {}
	
	public static ClubDAO getInstancia() {
		if(instancia == null)
			instancia = new ClubDAO();
		return instancia;
	}
	
	
	
	public List<Club> obtenerClubes() throws ClubException {
		List<Club> clubes = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<ClubEntity> aux = (List<ClubEntity>) session.createQuery("from ClubEntity").list();
		session.close();
		
		if(aux != null) {
			for(ClubEntity e : aux) {
				clubes.add(toModeloClub(e));
			}
	        return clubes;
		}
		
		throw new ClubException("No se encontraron los clubes");
		
    }
	
	public Club obtenerClubPorID(Integer id) throws ClubException{
		Session session = HibernateUtil.getSessionFactory().openSession();
        ClubEntity aux= (ClubEntity) session.createQuery("from ClubEntity c where c.idClub = "+ id).uniqueResult();
		session.close();
        		
        if(aux!=null) {
            return toModeloClub(aux);
        }
        throw new ClubException("No existe el club");
    }
	
	Club toModeloClub(ClubEntity c) {
		Club auxClub = new Club(c.getNombre(),c.getDireccion());
		auxClub.setIdClub(c.getIdClub());
		return auxClub;
	}

	public void modificarClub(Club club, int idClub, String nombre, String direccion) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		club.setDireccion(direccion);
		club.setNombre(nombre);
		club.setIdClub(idClub);
		session.flush();
		session.clear();
		session.merge(toEntity(club));
		session.getTransaction().commit();
		
	}

	 ClubEntity toEntity(Club club) {
		 List<CampeonatoEntity> aux2= new ArrayList<CampeonatoEntity>();
		ClubEntity aux = new ClubEntity();
		aux.setDireccion(club.getDireccion());
		aux.setNombre(club.getNombre());
		aux.setIdClub(club.getIdClub());
		if (club.getParticipaciones() != null) {
			for (Campeonato campeonato : club.getParticipaciones()) {
				aux2.add((CampeonatoDAO.getInstancia().toEntityExcepcion(campeonato)));
			}
		aux.setParticipaciones(aux2);
		}
		return aux;
		
	}
	 
	ClubEntity toEntityExcepcion(Club club) {
		ClubEntity aux = new ClubEntity();
		aux.setDireccion(club.getDireccion());
		aux.setNombre(club.getNombre());
		aux.setIdClub(club.getIdClub());
		return aux;
	}
	 
	 public void grabar(Club club) {
			Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
	        session.save(toEntity(club));
	        session.getTransaction().commit();
	        session.close();
		}

		public void eliminarClub(Club club) {
			Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
			session.flush();
			session.clear();
			session.delete(toEntity(club));
			session.getTransaction().commit();
			session.close();
			
		}
		
		public void actualizarClub(Club club) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.flush();
			session.clear();
			session.update(toEntity(club));
			session.getTransaction().commit();
			session.close();
		}
		
		public List<Jugador> obtenerJugadoresClub(int idClub) throws ClubException {
			List<Jugador> jugadores = new ArrayList<Jugador>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			List<JugadorEntity> auxJugadores = session.createQuery("from JugadorEntity j where j.club = "+idClub).list();
			session.close();

			if(auxJugadores != null) {
				for (JugadorEntity jugador : auxJugadores) {
					jugadores.add(JugadorDAO.getInstancia().toModelo(jugador));
				}
				return jugadores;
			}
			throw new ClubException("No existe el club");

			
		}
		
		public Club obtenerClubPorIdRepresentante(int idRepresentante) throws ClubException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			ClubEntity auxClub = (ClubEntity) session.createQuery("select distinct c from ClubEntity c INNER JOIN c.responsables r where r.legajo="+idRepresentante).uniqueResult();
			System.out.println(auxClub.getNombre());
			if(auxClub != null) {
				Club clubModelo = toModeloClub(auxClub);
				session.close();
				return clubModelo;
			}
			throw new ClubException("No existe el club");
			
		}
	
	
	
	
	
}
