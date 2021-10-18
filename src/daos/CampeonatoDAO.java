package daos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import controlador.Controlador;
import exceptions.CampeonatoException;
import modelo.Campeonato;
import modelo.Club;
import modelo.Jugador;
import modelo.Partido;
import sessionManager.SessionManager;
import vo.JugadorVO;

public class CampeonatoDAO {
	
	private static CampeonatoDAO instancia;
	
	private CampeonatoDAO() {}
	
	public static CampeonatoDAO getInstancia() {
		if(instancia == null)
			instancia = new CampeonatoDAO();
		return instancia;
	}
	

	CampeonatoEntity toEntity(Campeonato campeonato) {
		List<ClubEntity> aux2 = new ArrayList<ClubEntity>();
		CampeonatoEntity aux = new CampeonatoEntity(campeonato.getDescripcion(),campeonato.getFechaInicio(),campeonato.getFechaFin(), campeonato.getTipo(),campeonato.getCategoria());
		aux.setIdCampeonato(campeonato.getIdCampeonato());
		aux.setEliminado(campeonato.getEliminado());
		if (campeonato.getInscriptos() != null) {
			for (Club club : campeonato.getInscriptos()) {
				aux2.add((ClubDAO.getInstancia().toEntityExcepcion(club)));
				
			}
			aux.setInscriptos(aux2);
		}
		System.out.println("Llegue");
		return aux;
	}
	
	CampeonatoEntity toEntityExcepcion(Campeonato campeonato) {
		CampeonatoEntity aux = new CampeonatoEntity(campeonato.getDescripcion(),campeonato.getFechaInicio(),campeonato.getFechaFin(), campeonato.getTipo(),campeonato.getCategoria());
		aux.setIdCampeonato(campeonato.getIdCampeonato());
		return aux;
	}
	
	
	
	Campeonato toCampeonatoModelo (CampeonatoEntity entity) {
		Campeonato campeonato = new Campeonato(entity.getDescripcion(),entity.getFechaInicio(),entity.getFechaFin(),entity.getTipo(),entity.getCategoria());
		campeonato.setIdCampeonato(entity.getIdCampeonato());
		campeonato.setEliminado("noEliminado");
		for(ClubEntity x: entity.getInscriptos()) {
			campeonato.agregarClub(ClubDAO.getInstancia().toModeloClub(x));
		}
		return campeonato;
	}
	



	public void grabar(Campeonato campeonato) {
		
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().save(toEntity(campeonato));
		SessionManager.getInstancia().getSession().getTransaction().commit();
	}
	
	public Campeonato obtenerCampeonatoPorID(Integer id) throws CampeonatoException{
		SessionManager.getInstancia().getSession().beginTransaction();
        CampeonatoEntity aux = (CampeonatoEntity) SessionManager.getInstancia().getSession().createQuery("from CampeonatoEntity c where c.eliminadoC = 'noEliminado' and c.idCampeonato = "+ id).uniqueResult();
        SessionManager.getInstancia().getSession().getTransaction().commit();
        if(aux!=null) {
            return toCampeonatoModelo(aux);
        }
        throw new CampeonatoException("No existe el campeonato");
    }
	
	public void actualizar(Campeonato c) {
		SessionManager.getInstancia().getSession().beginTransaction();
		CampeonatoEntity aux = toEntity(c);
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().update(aux);
		SessionManager.getInstancia().getSession().getTransaction().commit();
	}
	
	
	public List<Jugador> getJugadoresCampeonato(Integer idCampeonato) throws CampeonatoException {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Campeonato camp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		List<Club> clubes = camp.getInscriptos();
		for(Club auxClub : clubes ) {
			List<Jugador> auxjugadores = auxClub.getJugadores();
			for(Jugador player : auxjugadores) {
				jugadores.add(player);
			}
		}
		return jugadores;
	}

	public void crearPartidos(List<Club> inscriptos, List<Club> inscriptos2,Campeonato campeonato) {
		int nroFecha = 1;
		int nroZona = 0;
		int cont = 0;
		Date fecha = campeonato.getFechaInicio();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		for( int fila = 0; fila< inscriptos.size(); fila++) {
			for (int columna = 0 ; columna< inscriptos2.size(); columna++) {
				if(inscriptos.get(fila).getIdClub() != inscriptos2.get(columna).getIdClub() ) {
					//Integer ultimaFecha = PartidoDAO.getInstancia().obtenerUltimaFechaCampeonato(campeonato.getIdCampeonato());
					/*if(ultimaFecha == null || fila == 0) {
						nroFecha = 1;
					}else {
						calendar.add(Calendar.DAY_OF_YEAR, 5);
						fecha = calendar.getTime();
						nroFecha = ultimaFecha+1;
						
					}
					*/
					if(campeonato.getTipo() == "Puntos") {
						nroZona = 0;
					} else {
						//Para grupos pares con un maximo de 4 por grupo
						if (cont > 4) {
							nroZona = nroZona + 1;
							cont = 0;
						} else {
							cont+=1;
						}
					}
					Partido partido = new Partido(nroFecha,nroZona,inscriptos.get(fila),inscriptos.get(columna),fecha, campeonato);
					partido.grabar();
					nroFecha+=1;
				}
			}
			nroFecha = 1;
			fecha = campeonato.getFechaInicio();
		}
		
	}

	public List<Campeonato> getCampeonatos() {
		List<Campeonato> campeonatos = new ArrayList<Campeonato>();
        List<CampeonatoEntity> auxCampeonatos = SessionManager.getInstancia().getSession().createQuery("FROM CampeonatoEntity p").list(); 
        for (CampeonatoEntity camp : auxCampeonatos) {
            campeonatos.add(CampeonatoDAO.getInstancia().toCampeonatoModelo(camp));
        }
        return campeonatos;
	}
	

}
