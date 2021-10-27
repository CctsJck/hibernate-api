package daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		session.close();
		for(JugadorEntity e : aux)
			jugadores.add(toModelo(e));
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
		JugadorEntity auxJugador = new JugadorEntity(jugador.getTipoDocumento(),jugador.getNumeroDocumento(),jugador.getNombre(),jugador.getApellido(),auxClub,jugador.getFechaNacimiento());
		auxJugador.setEliminado(jugador.getEliminado());
		auxJugador.setIdJugador(jugador.getIdJugador());
		
		return auxJugador;
	}
	
	
	Jugador toModelo(JugadorEntity entity) {
		Jugador aux = new Jugador(entity.getTipoDocumento(),entity.getNumeroDocumento(),entity.getNombre(),entity.getApellido(),ClubDAO.getInstancia().toModeloClub(entity.getClub()),entity.getFechaNacimiento());
		aux.setIdJugador(entity.getIdJugador());
		aux.setEliminado("noEliminado");
		return aux;
		
	}
	
	
	
	
	
	

}
