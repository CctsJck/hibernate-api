package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import daos.CampeonatoDAO;
import daos.ClubDAO;
import daos.FaltaDAO;
import daos.GolDAO;
import daos.JugadorDAO;
import daos.PartidoDAO;
import daos.RepresentanteDAO;
import daos.TablaPosicionesDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.ResponsableException;
import exceptions.TablaPosicionesException;
import modelo.Campeonato;
import modelo.Club;
import modelo.Falta;
import modelo.Gol;
import modelo.Jugador;
import modelo.Miembro;
import modelo.Partido;
import modelo.Responsable;
import modelo.TablaPosiciones;
import vo.CampeonatoVO;
import vo.ClubVO;
import vo.FaltaVO;
import vo.GolVO;
import vo.JugadorVO;
import vo.MiembroVO;
import vo.PartidoVO;
import vo.ResponsableVO;
import vo.TablaPosicionesVO;


public class Controlador {
	
	private static Controlador instancia;
	
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public Controlador() {}
	
	
	public void agregarGolJugador(int idJugador,int idPartido,int minuto, String tipo) throws JugadorException, PartidoException, ClubException{
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Partido auxPartido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		auxPartido.agregarGolJugador(auxJugador, minuto, tipo);
	}
		
	
	
	public void agregarFaltaJugador(int idJugador,int idPartido, int idCampeonato,int minuto, String tipo) throws JugadorException, PartidoException, CampeonatoException, ClubException{
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Partido auxPartido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		Campeonato auxCamp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		
		Falta nuevaFalta = new Falta(auxJugador,auxPartido,auxCamp,minuto,tipo);
		auxJugador.agregarFalta(nuevaFalta);
	
	}
	
	public void crearClub(String nombre,String direccion) {
		Club club = new Club(nombre,direccion);
		club.grabar();
	}

	public void eliminarClub(int idClub) throws ClubException {
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		auxClub.borrar();
	}
	
	public void modificarClub(int idClub,String nombre, String direccion) throws ClubException{ 
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		
		auxClub.modificar(idClub,nombre,direccion);
		
	}
	
	public void agregarJugador(String tipoDocumento, Integer documento, String nombre,String apellido,int idClub, Date fechaNacimiento) throws ClubException{
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
        	Jugador jugador = new Jugador(tipoDocumento, documento, nombre, apellido,auxClub,fechaNacimiento);
        	jugador.setEliminado("noEliminado");
        	auxClub.agregarJugador(jugador);
     
    }
	
	public void eliminarJugador(int idJugador) throws JugadorException, ClubException{
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(auxJugador.getClub().getIdClub());
		auxClub.darBajaJugador(auxJugador);
		
		
	}
	
	public void crearRepresentante(String tipoDocumento,int DNI,String nombre,int idClub) throws ClubException{
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		Responsable nuevoRepresentante = new Responsable(tipoDocumento,DNI,nombre,auxClub);
		nuevoRepresentante.setEliminado("noEliminado");
		nuevoRepresentante.grabar();
	}
	
			
	public void modificarRepresentante(int idRepresentante,String nombre, int DNI, String tipoDocumento, int idClub) throws ResponsableException, ClubException{
		Responsable responsableAux = RepresentanteDAO.getInstancia().obtenerRepresentanteporID(idRepresentante);
		responsableAux.modificar(idRepresentante, nombre, DNI, tipoDocumento, idClub,"noEliminado");
		
	}
	
	public void eliminarRepresentante(int idRepresentante) throws ResponsableException, ClubException {
		Responsable auxResp = RepresentanteDAO.getInstancia().obtenerRepresentanteporID(idRepresentante);
		
		auxResp.setEliminado("eliminado");
		auxResp.modificar(auxResp.getLegajo(), auxResp.getNombre(), auxResp.getDNI(), auxResp.getTipoDocumento(), auxResp.getClub().getIdClub(),auxResp.getEliminado());
		
	}
	
	public void modificarJugador(int idJugador,String tipoDocumento,int numeroDocumento,String nombre,String apellido,int idClub,Date fechaNac) throws JugadorException, ClubException {
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		
		auxJugador.modificar(idJugador, tipoDocumento, numeroDocumento, nombre,apellido, idClub,fechaNac);
			
		
	}

	public void crearCampeonato(String descripcion,Date fechaInicio,Date fechaFin, String tipo,int categoria) {
		Campeonato campeonato = new Campeonato(descripcion,fechaInicio,fechaFin,tipo,categoria);
		campeonato.setEliminado("noEliminado");
		campeonato.grabar();
	}
	
	public void crearPartido(int nroFecha,int nroZona,int categoria,Integer clubLocal,Integer clubVisitante,Date fechaPartido,Integer idCampeonato) throws CampeonatoException, ClubException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		Club local = ClubDAO.getInstancia().obtenerClubPorID(clubLocal);
		Club visitante = ClubDAO.getInstancia().obtenerClubPorID(clubVisitante);
		aux.crearPartidos(nroFecha, nroZona,local , visitante, fechaPartido);
	}
	
	public void activarCampeonato(int idCampeonato) throws CampeonatoException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		aux.setEstado("activo");
		aux.actualizar();
		
	}
	
	
	public void agregarJugadorPartido(int idPartido, int idJugador,int idClub) throws ClubException, PartidoException, JugadorException {
		Club club = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		Partido par = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		Jugador jug = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		par.agregarJugadorPartido(club, jug);
		
		
	}
	
	public List<ResponsableVO> getResponsableClub(int idClub) throws ClubException{
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		List<ResponsableVO> ResponsableVO = new ArrayList<ResponsableVO>();
		List<Responsable> Responsables = new ArrayList<Responsable>();
		Responsables = auxClub.getResponsables();
		for(Responsable resp: Responsables) {
			ResponsableVO.add(resp.toVO());
		}
		
		return ResponsableVO;
	}
	
	
	public List<JugadorVO> getJugadoresClub(int idClub) throws ClubException{
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		System.out.println(auxClub.getJugadores().size());
		List<Jugador> auxJugadores = auxClub.getJugadores();
		return convertirJugadoresAJugadoresVO(auxJugadores);
	}
	
	public void asignarParticipaciones(int idClub, int idCampeonato) throws CampeonatoException, ClubException {
        Campeonato auxCampeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
        Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
        auxCampeonato.inscribirClub(auxClub);
        auxClub.participar(auxCampeonato);
        
    }
	
	public void validarPartido(int idClub, int idPartido) throws ClubException, PartidoException {
        Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		Partido par = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		par.validarPartido(auxClub);
	}
	
	public String[][] getEstaditicaJugadoresCampeonato(int idCampeonato) throws CampeonatoException, ClubException {
		Campeonato campeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		return campeonato.getEstaditicaJugadoresCampeonato();
		
	}
	public void eliminarCampeonato(int idCampeonato) throws CampeonatoException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		aux.setEliminado("eliminado");
		aux.actualizar();
		
	
	}
	
	public void finalizarCargaEquiposTorneo(int idCampeonato) throws CampeonatoException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		aux.setEstado("activo");
		aux.actualizar();
		//aux.crearPartidos();
		
	}
	
	public List<ClubVO> convertirClubesAClubesVO(List<Club> clubes){
		List<ClubVO> clubesVO = new ArrayList<ClubVO>();
		for (Club club : clubes) {
			clubesVO.add(club.toVO());
		}
		return clubesVO;
	}
	
	public List<FaltaVO> convertirFaltasAFaltasVO(List<Falta> faltas){
		List<FaltaVO> faltasVO = new ArrayList<FaltaVO>();
		for (Falta falta : faltas) {
			faltasVO.add(falta.toVO());
		}
		return faltasVO;
	}
	
	public List<PartidoVO> convertirPartidosAPartidosVO(List<Partido> partidos){
		List<PartidoVO> partidosVO = new ArrayList<PartidoVO>();
		for (Partido partido : partidos) {
			partidosVO.add(partido.toVO());
		}
		
		return partidosVO;
	}
	
	public List<TablaPosicionesVO> convertirTablasATablasVO(List<TablaPosiciones> tablas){
		List<TablaPosicionesVO> tablasVO = new ArrayList<TablaPosicionesVO>();
		for (TablaPosiciones tabla : tablas) {
			tablasVO.add(tabla.toVO());
		}
		return tablasVO;
	} 
	
	public List<ResponsableVO> convertirResponsablesAResponsablesVO(List<Responsable> responsables){
		List<ResponsableVO> responsablesVO = new ArrayList<ResponsableVO>();
		for (Responsable responsable : responsables) {
			responsablesVO.add(responsable.toVO());
		}
		
		return responsablesVO;
	}
	
	public List<JugadorVO> convertirJugadoresAJugadoresVO(List<Jugador> jugadores){
		System.out.println(jugadores.size());
		List<JugadorVO> jugadoresVO = new ArrayList<JugadorVO>();
		for (Jugador jugador : jugadores) {
			jugadoresVO.add(jugador.toVO());
		}
		
		
		return jugadoresVO;
	}
	
	public List<CampeonatoVO> convertirCampeonatosACampeonatosVO(List<Campeonato> campeonatos){
		List<CampeonatoVO> campeonatosVO = new ArrayList<CampeonatoVO>();
		for (Campeonato campeonato : campeonatos) {
			campeonatosVO.add(campeonato.toVO());
		}
		return campeonatosVO;
	}
	
	public List<GolVO> convertirGolesAGolesVO(List<Gol> goles){
		List<GolVO> golesVO = new ArrayList<GolVO>();
		for (Gol gol : goles) {
			golesVO.add(gol.toVO());
		}
		
		return golesVO;
	}
	
	public List<MiembroVO> convertirMiembrosAMiembrosVO(List<Miembro> miembros){
		List<MiembroVO> miembrosVO = new ArrayList<MiembroVO>();
		for (Miembro miembro : miembros) {
			miembrosVO.add(miembro.toVO());
		}
		
		return miembrosVO;
	}
	
	//Pruebas creacion partidos
	public void crearPartidosNuevos(int idCampeonato) throws CampeonatoException, ClubException {
		//Torneo por puntos
		Campeonato auxCamp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		auxCamp.crearPartidosGrupos();
	}
	
	public List<PartidoVO> obtenerPartidosCampeonato(int idCampeonato) throws CampeonatoException, ClubException{
		Campeonato camp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		List<Partido> partidos = new ArrayList<Partido>();
		partidos = camp.getPartidos();
		return this.convertirPartidosAPartidosVO(partidos);
		
		
	}
	
	public List<CampeonatoVO> obtenerCampeonatos(){
		List<Campeonato> aux = CampeonatoDAO.getInstancia().getCampeonatos();
		return this.convertirCampeonatosACampeonatosVO(aux);
	}
	
	
	public List<ClubVO> obtenerClubesDisponiblesCampeonato(int idCampeonato) throws CampeonatoException  {
		Campeonato campeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		List<Club> clubesInscriptos = campeonato.getInscriptos();
		List<ClubVO> resultado = new ArrayList<>();
		List<Club> clubes = ClubDAO.getInstancia().obtenerClubes();
		List<Integer> auxiliar = new ArrayList<>();
		for (Club c: clubesInscriptos) {
			auxiliar.add(c.getIdClub());
			System.out.println(c.getNombre());
		}
		System.out.println(clubesInscriptos.isEmpty());
		for (Club c: clubes){
			if (!auxiliar.contains(c.getIdClub())){
				resultado.add(c.toVO());
				System.out.println(c.getNombre());
			}
		}
		
		return resultado;

	}
	
	public JugadorVO getJugadorPorId(int idJugador) throws JugadorException {
		Jugador jugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		return jugador.toVO();
	}
	
	public ResponsableVO getResponsablePorId(int idRepresentante) throws ResponsableException {
		Responsable representante = RepresentanteDAO.getInstancia().obtenerRepresentanteporID(idRepresentante);
		return representante.toVO();
	}
	
	public ClubVO getClubPorId(int idClub) throws ClubException {
		Club club = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		return club.toVO();
	}
	
	public ClubVO getClubPorIdRepresentante(int idRepresentante) throws ClubException {
		Club club = ClubDAO.getInstancia().obtenerClubPorIdRepresentante(idRepresentante);
		return club.toVO();
	}
	
	public List<TablaPosicionesVO> obtenerTablasCampeonato(int idCampeonato) throws TablaPosicionesException{
		List<TablaPosiciones> tablas = TablaPosicionesDAO.getInstancia().obtenerTablaCampeonato(idCampeonato);
		return this.convertirTablasATablasVO(tablas);
	}
	
	
	public List<ClubVO> getClubesCampeonato(int idCampeonato) throws CampeonatoException {
        Campeonato campeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
        List<Club> clubesInscriptos = campeonato.getInscriptos();
        List<ClubVO> resultado = new ArrayList<>();
        for (Club c: clubesInscriptos) {
            resultado.add(c.toVO());
        }
        return resultado;

    }
	
	
}
