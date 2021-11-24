package daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;

import Entity.ClubEntity;

import Entity.JugadorEntity;
import exceptions.ClubException;
import exceptions.JugadorException;
import hibernate.HibernateUtil;
import modelo.Club;

import modelo.Jugador;
import sessionManager.SessionManager;

public class JugadorDAO {
	
	private static JugadorDAO instancia;
	
	private JugadorDAO() {}
	
	public static JugadorDAO getInstancia() {
		if(instancia == null)
			instancia = new JugadorDAO();
		return instancia;
	}
	
	
	
	
	
	public void grabar(Jugador jugador) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        JugadorEntity jugadorNuevo= toEntity(jugador);
        session.beginTransaction();
        session.save(jugadorNuevo);
        session.getTransaction().commit();
        session.close();
    }
	
	public List<Jugador> obtenerJugadores() {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<JugadorEntity> aux = (List<JugadorEntity>) session.createQuery("from JugadorEntity").list();
		session.getTransaction().commit();
		for(JugadorEntity e : aux)
			jugadores.add(toModelo(e));
		session.close();
		return jugadores;

	}
	
	public Jugador obtenerJugador(int idJugador) throws JugadorException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		JugadorEntity aux = (JugadorEntity) session.createQuery("from JugadorEntity e where e.idJugador= "+ idJugador +"and e.eliminadoJ = 'noEliminado'").uniqueResult();
		session.getTransaction().commit();
		session.close();
		if(aux != null) {
			return toModelo(aux);
		}
		throw new JugadorException("No existe el jugador");

	}
	public void eliminarJugador(Jugador aux) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.update(toEntity(aux));
		session.getTransaction().commit();
		session.close();
	}
	public void modificarJugador(Jugador jugador,int idJugador,String tipoDocumento,int numeroDocumento,String nombre,String apellido,int idClub,Date fechaNac) throws ClubException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		jugador.setIdJugador(idJugador);
		jugador.setTipoDocumento(tipoDocumento);
		jugador.setNumeroDocumento(numeroDocumento);
		jugador.setNombre(nombre);
		jugador.setApellido(apellido);
		jugador.setFechaNacimiento(fechaNac);
		
		if (jugador.getClub().getIdClub() != idClub) {
			
			Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
			jugador.setClub(auxClub);
		}
		session.flush();
		session.clear();
		session.update(toEntity(jugador));
		session.getTransaction().commit();
		session.close();
	}
	
	JugadorEntity toEntity(Jugador jugador) {
		ClubEntity auxClub = new ClubEntity(jugador.getClub().getNombre(),jugador.getClub().getDireccion());
		auxClub.setIdClub(jugador.getClub().getIdClub());
		JugadorEntity auxJugador = new JugadorEntity(jugador.getTipoDocumento(),jugador.getNumeroDocumento(),jugador.getNombre(),jugador.getApellido(),auxClub,jugador.getFechaNacimiento(),jugador.getIdUsuario(),jugador.getFichaje());
		auxJugador.setEliminado(jugador.getEliminado());
		auxJugador.setIdJugador(jugador.getIdJugador());
		
		return auxJugador;
	}
	
	
	Jugador toModelo(JugadorEntity entity) {
		Jugador aux = new Jugador(entity.getTipoDocumento(),entity.getNumeroDocumento(),entity.getNombre(),entity.getApellido(),ClubDAO.getInstancia().toModeloClub(entity.getClub()),entity.getFechaNacimiento(),entity.getIdUsuario(),entity.getFichaje());
		aux.setIdJugador(entity.getIdJugador());
		aux.setEliminado(entity.getEliminado());
		return aux;
		
	}
	
	public boolean existeJugador(int idJugador) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		JugadorEntity aux  = (JugadorEntity) session.createQuery("from JugadorEntity e where e.idJugador= " + idJugador).uniqueResult();
		session.close();
		if (aux != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Jugador getJugadorByIdUsuario(int idUsuario) throws JugadorException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        JugadorEntity jugadorEntity = (JugadorEntity) session.createQuery("from JugadorEntity j where j.idUsuario="+idUsuario).uniqueResult();
        if (jugadorEntity != null) {
            Jugador jugadorModelo = toModelo(jugadorEntity);
            session.close();
            return jugadorModelo;
        }

        throw new JugadorException("No existe un jugador con ese idUsuario");
    }
	
	public boolean existeJugadorDNI(int documento) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		JugadorEntity aux = (JugadorEntity) session.createQuery("from JugadorEntity j where j.numeroDocumento= "+documento).uniqueResult();
		session.close();
		if (aux != null) {
			return true;
		} else {
			return false;
		}
	}
	public Long obtenerAmarillas(int idJugador, Integer idCampeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Long aux = (Long) session.createQuery("select count(*) from PartidoEntity p INNER JOIN p.faltas f where p.campeonato = "+idCampeonato+" and f.jugador= "+idJugador+" and tipo = 'Amarilla'").uniqueResult();
		session.getTransaction().commit();
		session.close();
		return aux;

	}

	public Long obtenerRojas(int idJugador, Integer idCampeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Long aux = (Long) session.createQuery("select count(*) from PartidoEntity p INNER JOIN p.faltas f where p.campeonato = "+idCampeonato+" and f.jugador= "+idJugador+" and tipo = 'Roja'").uniqueResult();
		session.getTransaction().commit();
		session.close();
		return aux;
	}
	
	public List<Jugador> getJugadoresDisponiblePartido(int idPartido, int idClub) throws JugadorException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<JugadorEntity> jugadoresEntity = session.createQuery("FROM JugadorEntity j WHERE j.idJugador NOT IN (SELECT m.jugador FROM MiembroEntity m WHERE m.partido="+idPartido+") AND j.club = "+idClub).list();
		if (jugadoresEntity != null) {
			List<Jugador> jugadores = new ArrayList<Jugador>();
			for (JugadorEntity jugador : jugadoresEntity) {
				jugadores.add(this.toModelo(jugador));
			}
			session.close();
			return jugadores;
		}
		
		throw new JugadorException("No hay mas jugadores del club "+idClub+" para el partido "+idPartido);
	}
	
	
	
	
	
	

}
