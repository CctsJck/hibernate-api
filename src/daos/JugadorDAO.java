package daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import Entity.ClubEntity;

import Entity.JugadorEntity;
import exceptions.ClubException;
import exceptions.JugadorException;
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
        JugadorEntity jugadorNuevo= toEntity(jugador);
        SessionManager.getInstancia().getSession().beginTransaction();
        SessionManager.getInstancia().getSession().save(jugadorNuevo);
        SessionManager.getInstancia().getSession().getTransaction().commit();
    }
	
	public List<Jugador> obtenerJugadores() {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		SessionManager.getInstancia().getSession().beginTransaction();
		@SuppressWarnings("unchecked")
		List<JugadorEntity> aux = (List<JugadorEntity>) SessionManager.getInstancia().getSession().createQuery("from JugadorEntity").list();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		for(JugadorEntity e : aux)
			jugadores.add(toModelo(e));
		return jugadores;

	}
	
	public Jugador obtenerJugador(int idJugador) throws JugadorException{
		
		SessionManager.getInstancia().getSession().beginTransaction();
		JugadorEntity aux = (JugadorEntity) SessionManager.getInstancia().getSession().createQuery("from JugadorEntity e where e.idJugador= "+ idJugador +"and e.eliminadoJ = 'noEliminado'").uniqueResult();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		if(aux != null) {
			return toModelo(aux);
		}
		throw new JugadorException("No existe el jugador");

	}
	public void eliminarJugador(Jugador aux) {
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().update(toEntity(aux));
		SessionManager.getInstancia().getSession().getTransaction().commit();
		
	}
	public void modificarJugador(Jugador jugador,int idJugador,String tipoDocumento,int numeroDocumento,String nombre,String apellido,int idClub,Date fechaNac) throws ClubException {
		SessionManager.getInstancia().getSession().beginTransaction();
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
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().update(toEntity(jugador));
		SessionManager.getInstancia().getSession().getTransaction().commit();
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
