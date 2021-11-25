package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Entity.ClubEntity;
import Entity.FaltaEntity;
import Entity.JugadorEntity;
import Entity.JugadoresTorneoEntity;
import Entity.MiembroEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Jugador;
import modelo.JugadoresTorneo;
import modelo.Miembro;

public class JugadoresTorneoDAO {
	private static JugadoresTorneoDAO instancia;
	
	private JugadoresTorneoDAO() {}
	
	public static JugadoresTorneoDAO getInstancia() {
		if(instancia == null)
			instancia = new JugadoresTorneoDAO();
		return instancia;
	}
	
	JugadoresTorneo toModelo(JugadoresTorneoEntity m) throws ClubException {
		JugadoresTorneo jugadoresTorneo = new JugadoresTorneo (JugadorDAO.getInstancia().toModelo(m.getJugador()), CampeonatoDAO.getInstancia().toCampeonatoModelo(m.getCampeonato()),m.isEstado());
		jugadoresTorneo.setIdJugadorTorneo(m.getIdJugadorTorneo());
		return jugadoresTorneo;
		
	}
	
	JugadoresTorneoEntity toEntity(JugadoresTorneo m) {
		JugadoresTorneoEntity jugadorTorneo = new JugadoresTorneoEntity(JugadorDAO.getInstancia().toEntity(m.getJugador()),CampeonatoDAO.getInstancia().toEntity(m.getCampeonato()),m.isEstado());
		jugadorTorneo.setIdJugadorTorneo(m.getIdJugadorTorneo());
		return jugadorTorneo;
		
	}

	public void agregarJugadorTorneo(Jugador auxJugador, Campeonato campeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.close();
		
	}

	public void save(JugadoresTorneo jugadorTorneo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(this.toEntity(jugadorTorneo));
		session.getTransaction().commit();
		session.close();
		
	}

	public JugadoresTorneo getJugadorById(int idJugadorTorneo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		JugadoresTorneoEntity auxJugadorTorneo = (JugadoresTorneoEntity) session.createQuery("from JugadoresTorneoEntity m where m.idJugadorTorneo = "+idJugadorTorneo).uniqueResult();
		JugadoresTorneo jugador = this.toModelo(auxJugadorTorneo);
		session.close();
		return jugador;
	}

	public void update(JugadoresTorneo auxJugadorTorneo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.merge(this.toEntity(auxJugadorTorneo));
		session.getTransaction().commit();
		session.close();
		
	}

	public List<JugadoresTorneo> obtenerJugadoresHabilitados(int idCampeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<JugadoresTorneo> jugadoresHabilitados =  new ArrayList<>();
		List<JugadoresTorneoEntity> auxJugadoresHabilitados = session.createQuery("FROM JugadoresTorneoEntity m WHERE m.campeonato = "+idCampeonato).list();
		session.close();
		if(auxJugadoresHabilitados != null) {
			for (JugadoresTorneoEntity jugadores : auxJugadoresHabilitados) {
				System.out.println("SOY EL JUGADOR CON EL ID: "+this.toModelo(jugadores).getIdJugadorTorneo());
				jugadoresHabilitados.add(this.toModelo(jugadores));
			}
		}
		return jugadoresHabilitados;
	}
	
}