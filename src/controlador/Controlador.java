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
import daos.JugadoresTorneoDAO;
import daos.MiembroDAO;
import daos.PartidoDAO;
import daos.RepresentanteDAO;
import daos.TablaPosicionesDAO;
import daos.UsuarioDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.FaltaException;
import exceptions.GolException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.ResponsableException;
import exceptions.TablaPosicionesException;
import exceptions.UsuarioException;
import modelo.Campeonato;
import modelo.Club;
import modelo.Falta;
import modelo.Gol;
import modelo.Jugador;
import modelo.JugadoresTorneo;
import modelo.Miembro;
import modelo.Partido;
import modelo.Responsable;
import modelo.TablaPosiciones;
import modelo.Usuario;
import vo.CampeonatoVO;
import vo.ClubVO;
import vo.FaltaVO;
import vo.GolVO;
import vo.JugadorVO;
import vo.JugadoresTorneoVO;
import vo.MiembroVO;
import vo.PartidoVO;
import vo.ResponsableVO;
import vo.TablaPosicionesVO;
import vo.UsuarioVO;


public class Controlador {
	
	private static Controlador instancia;
	
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public Controlador() {}
	
	
	public void agregarGolJugador(int idJugador,int idPartido,int minuto, String tipo) throws JugadorException, PartidoException, ClubException{
		if(existeJugador(idJugador) && existePartido(idPartido)) {
			Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
			Partido auxPartido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
			
			auxPartido.agregarGolJugador(auxJugador, minuto, tipo);
		} else {
			if (!existeJugador(idJugador)) {
				throw new JugadorException("No existe un jugador con ese ID correspondiente");
			}else {
				throw new PartidoException("No existe un partido con ese ID correspondiente");
			}
		}
	}
	
	public boolean existeJugador(int idJugador) {
		return JugadorDAO.getInstancia().existeJugador(idJugador);
	}
	public boolean existePartido(int idPartido) {
		return PartidoDAO.getInstancia().existePartido(idPartido);
	}
	public boolean existeCampeonato(int idCampeonato) {
		return CampeonatoDAO.getInstancia().existeCampeonato(idCampeonato);
	}
	public boolean existeClub(int idClub) {
		return ClubDAO.getInstancia().existeClub(idClub);
	}
	public boolean existeJugadorDNI(int documento) {
		return JugadorDAO.getInstancia().existeJugadorDNI(documento);
	}
	public boolean existeRepresentanteDNI(int documento) {
		return RepresentanteDAO.getInstancia().existeRepresentanteDNI(documento);
		
	}
	public boolean existeUsuario(int idUsuario) {
		return UsuarioDAO.getInstancia().existeUsuario(idUsuario);
	}
		
	
	
	public void agregarFaltaJugador(int idJugador,int idPartido, int idCampeonato,int minuto, String tipo) throws JugadorException, PartidoException, CampeonatoException, ClubException{
		if (existeJugador(idJugador) && existePartido(idPartido) && existeCampeonato(idCampeonato)) {
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Partido auxPartido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		Campeonato auxCamp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		
		Falta nuevaFalta = new Falta(auxJugador,auxPartido,auxCamp,minuto,tipo);
		auxJugador.agregarFalta(nuevaFalta);
		} else {
			if (!existeJugador(idJugador)) {
				throw new JugadorException("No existe un jugador con ese ID correspondiente");
			}
			if (!existePartido(idPartido)) {
				throw new PartidoException("No existe un partido con ese ID correspondiente");
			}
			if (!existeCampeonato(idCampeonato)) {
				throw new CampeonatoException("No existe un campeonato con ese ID correspondiente");
			}
		}
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
		if(existeClub(idClub)) {
			Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
			auxClub.modificar(idClub,nombre,direccion);
		}else {
			throw new ClubException("No existe un club con el ID correspondiente");
		}
		
		
		
		
	}
	
	public void agregarJugador(String tipoDocumento, Integer documento, String nombre,String apellido,int idClub, Date fechaNacimiento,Date fichaje) throws ClubException, UsuarioException, JugadorException{
		if(existeClub(idClub) && !existeJugadorDNI(documento)) {
			Usuario usuario = new Usuario("jugador","1234");
			UsuarioDAO.getInstancia().grabar(usuario);
			Integer idUsuario;
			idUsuario = UsuarioDAO.getInstancia().obtenerIdUsuario();
			Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
	        Jugador jugador = new Jugador(tipoDocumento, documento, nombre, apellido,auxClub,fechaNacimiento,idUsuario,fichaje);
	        jugador.setEliminado("noEliminado");
	        auxClub.agregarJugador(jugador);
		}else {
			if (!existeClub(idClub)) {
				throw new JugadorException("Ya existe un jugador con el documento ingresado");
			}else {
				throw new ClubException("No existe un club con el ID ingresado");
			}
		}
     
    }
	
	public void eliminarJugador(int idJugador) throws JugadorException, ClubException{
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(auxJugador.getClub().getIdClub());
		auxClub.darBajaJugador(auxJugador);
		
		
	}
	
	public void crearRepresentante(String tipoDocumento,int DNI,String nombre,int idClub) throws ClubException, UsuarioException, ResponsableException{
		if(existeClub(idClub) && !existeRepresentanteDNI(DNI)) {
			Usuario usuario = new Usuario("Repre","1234");
			UsuarioDAO.getInstancia().grabar(usuario);
			Integer idUsuario;
			idUsuario = UsuarioDAO.getInstancia().obtenerIdUsuario();
			Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
			Responsable nuevoRepresentante = new Responsable(tipoDocumento,DNI,nombre,auxClub,idUsuario);
			nuevoRepresentante.setEliminado("noEliminado");
			nuevoRepresentante.grabar();
		} else {
			if(!existeClub(idClub)) {
				throw new ClubException("No existe un club con el ID correspondiente");
			}else {
				throw new ResponsableException("Ya existe un representante con el documento ingresado");
			}
		}
	
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
	
	public void crearPartido(int nroFecha,int nroZona,int categoria,Integer clubLocal,Integer clubVisitante,Date fechaPartido,Integer idCampeonato,String fase) throws CampeonatoException, ClubException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		Club local = ClubDAO.getInstancia().obtenerClubPorID(clubLocal);
		Club visitante = ClubDAO.getInstancia().obtenerClubPorID(clubVisitante);
		aux.crearPartidos(nroFecha, nroZona,local , visitante, fechaPartido,fase);
	}
	
	public void activarCampeonato(int idCampeonato) throws CampeonatoException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		this.crearPartidosNuevos(idCampeonato);
		aux.setEstado("activo");
		aux.actualizar();
			
	}
	
	public void agregarJugadorPartido(int idPartido, int idJugador,int idClub) throws ClubException, PartidoException, JugadorException, CampeonatoException {
        Club club = ClubDAO.getInstancia().obtenerClubPorID(idClub);
        Partido par = PartidoDAO.getInstancia().obtenerPartido(idPartido);
        if(JugadoresTorneoDAO.getInstancia().existeJugador(idJugador, par.getCampeonato().getIdCampeonato())) {
        	Jugador jug = JugadorDAO.getInstancia().obtenerJugador(idJugador);
            if(juegaPartido(par,jug) != true && verificacionFechaFichaje(par.getCampeonato().getIdCampeonato(),jug) != true) {
                par.agregarJugadorPartido(club, jug);
            }else {
            	if (juegaPartido(par,jug) == true) {
            		throw new PartidoException("El jugador ya se encuentra inscripto a un partido en ese día");
            	}else if (verificacionFechaFichaje(par.getCampeonato().getIdCampeonato(),jug) == true) {
            		throw new JugadorException("El jugador fue registrado una vez iniciado el torneo");
            	}
            }
        }else {
        	throw new JugadorException("El jugador no esta inscripto en este torneo");
        }
    }
	
	private boolean verificacionFechaFichaje(int idCampeonato,Jugador jugador) throws CampeonatoException {
		boolean resultado = false;
		Campeonato camp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		
		if (camp.getFechaInicio().compareTo(jugador.getFichaje()) < 0) {
			
			resultado = true;
		}else {
	
		}
		
		return resultado;
	}
	
	private boolean juegaPartido(Partido partido,Jugador jugador) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean resultado = false;
        cal1.setTime(partido.getFechaPartido());
        List<Partido> partidosFecha = PartidoDAO.getInstancia().obtenerPartidosPorFecha(jugador.getClub().getIdClub());
        for (Partido p : partidosFecha) {
            cal2.setTime(p.getFechaPartido());
            if (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
            		List<Miembro> jugadoresPartido = MiembroDAO.getInstancia().obtenerJugadoresPartido(p.getIdPartido());
            		for(Miembro m: jugadoresPartido) {
            			int x = m.getJugador().getIdJugador();
            			if (Integer.compare(jugador.getIdJugador(), x) == 0) {
            				resultado = true;
            			}
            		}
            }
        }
        return resultado;
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
	
	public String[] getEstaditicaJugadorCampeonato(int idJugador, int idCampeonato) throws CampeonatoException, JugadorException {
        Campeonato campeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
        return campeonato.getEstaditicaJugadorCampeonato(idJugador);
    }
	
	public void finalizarCargaEquiposTorneo(int idCampeonato) throws CampeonatoException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		aux.setEstado("activo");
		aux.actualizar();
		
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
	
	public List<CampeonatoVO> obtenerCampeonatosPorIdJugador(int idJugador){
		List<Campeonato> aux = CampeonatoDAO.getInstancia().getCampeonatosPorIdJugador(idJugador);
		return this.convertirCampeonatosACampeonatosVO(aux);
	}
	
	
	public List<ClubVO> obtenerClubesDisponiblesCampeonato(int idCampeonato) throws CampeonatoException, ClubException  {
		Campeonato campeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		List<Club> clubesInscriptos = campeonato.getInscriptos();
		List<ClubVO> resultado = new ArrayList<>();
		List<Club> clubes = ClubDAO.getInstancia().obtenerClubes();
		List<Integer> auxiliar = new ArrayList<>();
		for (Club c: clubesInscriptos) {
			auxiliar.add(c.getIdClub());
		}
		for (Club c: clubes){
			if (!auxiliar.contains(c.getIdClub())){
				resultado.add(c.toVO());
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
	
	public List<TablaPosicionesVO> obtenerTablasCampeonato(int idCampeonato) throws TablaPosicionesException, CampeonatoException{
		if(existeCampeonato(idCampeonato)) {
			List<TablaPosiciones> tablas = TablaPosicionesDAO.getInstancia().obtenerTablaCampeonato(idCampeonato);
			return this.convertirTablasATablasVO(tablas);
		} else {
			throw new CampeonatoException("No existe un campeonato con el ID correspondiente");
		}
		
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
	
	public List<ResponsableVO> obtenerRepresentantes() throws ResponsableException{
		List<Responsable> representantes = RepresentanteDAO.getInstancia().obtenerRepresentantes();
		return this.convertirResponsablesAResponsablesVO(representantes);
	}
	
	public List<ClubVO> obtenerClubes() throws ClubException{
		return this.convertirClubesAClubesVO(ClubDAO.getInstancia().obtenerClubes());
	}
	
	public List<MiembroVO> obtenerJugadoresPartido(int idPartido) throws ClubException, PartidoException{
		if(existePartido(idPartido)) {
			List<MiembroVO> resultado = new ArrayList<MiembroVO>();
			List<Miembro> miembros = MiembroDAO.getInstancia().obtenerJugadoresPartido(idPartido);
			for(Miembro m: miembros) {
				resultado.add(m.toVO());
			}
			return resultado;
		} else {
			throw new PartidoException("No existe un partido con el ID correspondiente");
		}
	}
	
	public List<PartidoVO> obtenerPartidosPendientesValidar(int idClub) throws PartidoException, ClubException {
		if(existeClub(idClub)) {
			List<Partido> partidos = PartidoDAO.getInstancia().obtenerPartidosPendientesValidar(idClub);
			return this.convertirPartidosAPartidosVO(partidos);
		} else {
			throw new ClubException("No existe un club con el ID correspondiente");
		}
	}
	
	public UsuarioVO getUsuarioByIdAndPassword(int idUsuario, String password) throws UsuarioException {
		Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByIdAndPassword(idUsuario, password);
		return usuario.toVO();
	} 

	
	public JugadorVO getJugadorByIdUsuario(int idUsuario) throws JugadorException {
        Jugador jugador = JugadorDAO.getInstancia().getJugadorByIdUsuario(idUsuario);
        return jugador.toVO();
    }
	
	public ResponsableVO getRepresentanteByIdUsuario(int idUsuario) throws ResponsableException, UsuarioException {
		if(existeUsuario(idUsuario)) {
			return RepresentanteDAO.getInstancia().getRepresentanteByIdUsuario(idUsuario).toVO();
		} else {
			throw new UsuarioException("No existe un Usuario con el ID correspondiente");
			
		}
    }
	

	public UsuarioVO getUsuarioByIdJugador(int idJugador) throws UsuarioException {
		return UsuarioDAO.getInstancia().getUsuarioByIdJugador(idJugador).toVO();
	}
	
	public void updateUserPassword(int idJugador, String password) throws UsuarioException {
		UsuarioDAO.getInstancia().updateJugadorPassword(idJugador, password);
	}
	
	public UsuarioVO getUsuarioByIdRepresentante(int idRepresentante) throws UsuarioException {
		return UsuarioDAO.getInstancia().getUsuarioByIdRepresentante(idRepresentante).toVO();
	}
	
	public void updateReprePassword(int idRepre, String password) throws UsuarioException {
		UsuarioDAO.getInstancia().updateReprePassword(idRepre, password);
	}
	
	public List<FaltaVO> getFaltasPartido(int idPartido) throws FaltaException{
		List<Falta> faltas = FaltaDAO.getInstancia().getFaltasPartido(idPartido);
		return this.convertirFaltasAFaltasVO(faltas);
	}
	
	public List<GolVO> getGolesPartido(int idPartido) throws GolException{
		List<Gol> goles = GolDAO.getInstancia().getGolesPartido(idPartido);
		return this.convertirGolesAGolesVO(goles);
	}
	
	public PartidoVO getPartidoById(int idPartido) throws ClubException, PartidoException {
		return PartidoDAO.getInstancia().obtenerPartido(idPartido).toVO();
		
	}
	
	public List<JugadorVO> getJugadoresDisponiblesPartido(int idPartido, int idClub) throws JugadorException{
		return this.convertirJugadoresAJugadoresVO(JugadorDAO.getInstancia().getJugadoresDisponiblePartido(idPartido, idClub));
	}
	
	public CampeonatoVO getCampeonatoById(int idCampeonato) throws CampeonatoException {
		return CampeonatoDAO.getInstancia().getCampeonatoById(idCampeonato).toVO();
	}
	
	public void agregarJugadorTorneo(int idJugador,int idCampeonato) throws JugadorException, CampeonatoException {
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Campeonato auxCampeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		auxCampeonato.agregarJugadorTorneo(auxJugador);
	}
	
	public void deshabilitarJugadorTorneo (int idJugadorTorneo) {
		JugadoresTorneo auxJugadorTorneo = JugadoresTorneoDAO.getInstancia().getJugadorById(idJugadorTorneo);
		auxJugadorTorneo.setEstado(false);
		JugadoresTorneoDAO.getInstancia().update(auxJugadorTorneo);
	}
	
	public List<JugadoresTorneoVO> getJugadoresHabilitados(int idCampeonato){
		List<JugadoresTorneo> jugadoresHabilitados = JugadoresTorneoDAO.getInstancia().obtenerJugadoresHabilitados(idCampeonato);
		return this.convertirJugadoresTorneoAJugadoresTorneoVO(jugadoresHabilitados);
		
		
	}
	
	public List<JugadoresTorneoVO> convertirJugadoresTorneoAJugadoresTorneoVO(List<JugadoresTorneo> jugadoresHabilitados){
		
		List<JugadoresTorneoVO> jugadoresHabilitadosVO = new ArrayList<JugadoresTorneoVO>();
		for (JugadoresTorneo jugador : jugadoresHabilitados) {
			jugadoresHabilitadosVO.add(jugador.toVO());
		}
		
		return jugadoresHabilitadosVO;
		
	}
	
	public List<CampeonatoVO> getCampeontosByIdClub(int idClub) throws CampeonatoException{
		return this.convertirCampeonatosACampeonatosVO(CampeonatoDAO.getInstancia().getCampeonatosByIdClub(idClub));
	}
	
	public List<JugadorVO> getJugadoresDisponiblesTorneo(int idClub, int idCampeonato) throws JugadorException{
		return this.convertirJugadoresAJugadoresVO(JugadorDAO.getInstancia().getJugadoresDisponiblePartido(idCampeonato, idClub));
	}
	


	
	
}
