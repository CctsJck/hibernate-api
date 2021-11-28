package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		System.out.println("FechaInicio:"+fechaInicio);
		System.out.println("FechaFin:"+fechaFin);
		System.out.println("-------");
		Campeonato campeonato = new Campeonato(descripcion,fechaInicio,fechaFin,tipo,categoria);
		campeonato.setEliminado("noEliminado");
		campeonato.grabar();
	}
	
	public void crearPartido(int nroFecha,int nroZona,Integer clubLocal,Integer clubVisitante,Date fechaPartido,Integer idCampeonato,String fase) throws CampeonatoException, ClubException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		Club local = ClubDAO.getInstancia().obtenerClubPorID(clubLocal);
		Club visitante = ClubDAO.getInstancia().obtenerClubPorID(clubVisitante);
		aux.crearPartidos(nroFecha, nroZona,local , visitante, fechaPartido,fase);
	}
	
	public void activarCampeonato(int idCampeonato) throws CampeonatoException {
		Campeonato aux = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		if (aux.getTipo().equals("Puntos")) {
			this.crearPartidosNuevos(idCampeonato);
		}
		aux.setEstado("activo");
		aux.actualizar();
			
	}
	
	public void agregarJugadorPartido(int idPartido, int idJugador,int idClub) throws ClubException, PartidoException, JugadorException, CampeonatoException {
        Club club = ClubDAO.getInstancia().obtenerClubPorID(idClub);
        Partido par = PartidoDAO.getInstancia().obtenerPartido(idPartido);
        if(JugadoresTorneoDAO.getInstancia().existeJugador(idJugador, par.getCampeonato().getIdCampeonato())) {
        	Jugador jug = JugadorDAO.getInstancia().obtenerJugador(idJugador);
            if(juegaPartido(par,jug) != true && PartidoDAO.getInstancia().obtenerCantidadJugadoresPartidoEquipo(par.getIdPartido(), club.getIdClub())<17 && PartidoDAO.getInstancia().validoParaJugar(idJugador,par.getCampeonato().getIdCampeonato()) == true) {
            	Miembro miem = new Miembro(club,par,jug);
                miem.grabar();
            }else {
            	if (juegaPartido(par,jug) == true) {
            		throw new PartidoException("El jugador ya se encuentra inscripto a un partido en ese día");
            	}else if (PartidoDAO.getInstancia().obtenerCantidadJugadoresPartidoEquipo(par.getIdPartido(),club.getIdClub()) >= 17) {
            		throw new JugadorException("El jugador fue registrado una vez iniciado el torneo");
            	}else {
            		throw new JugadorException("El jugador no se encuentra habilitado para jugar el partido");
            	}
            }
        }else {
        	throw new JugadorException("El jugador no esta inscripto en este torneo");
        }
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
	
	public void agregarJugadorCampeonato(int idJugador,int idCampeonato) throws CampeonatoException, JugadorException {
		Jugador auxJugador = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		Campeonato auxCampeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		auxCampeonato.agregarJugadorCampeonato(auxJugador);
	}
	
	public void cambiarEstadoJugadorTorneo (int idJugadorTorneo,boolean estado) {
		JugadoresTorneo auxJugadorTorneo = JugadoresTorneoDAO.getInstancia().getJugadorByIdTorneo(idJugadorTorneo); 
		auxJugadorTorneo.setEstado(estado);
		JugadoresTorneoDAO.getInstancia().update(auxJugadorTorneo);
	}
	
	public void cambiarEstadoJugadorTorneoByIdJugador (int idJugador,boolean estado,int idCampeonato) {
		JugadoresTorneo auxJugadorTorneo = JugadoresTorneoDAO.getInstancia().getJugadorById(idJugador, idCampeonato); 
		auxJugadorTorneo.setEstado(estado);
		JugadoresTorneoDAO.getInstancia().update(auxJugadorTorneo);
	}
	
	
	public List<JugadoresTorneoVO> getJugadoresHabilitadosClub(int idCampeonato, int idClub){
		List<JugadoresTorneo> jugadoresHabilitados = JugadoresTorneoDAO.getInstancia().obtenerJugadoresHabilitados(idCampeonato,idClub);
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
	
	public List<String> getFasesByIdCampeonato(int idCampeonato) throws CampeonatoException{
		Campeonato camp = CampeonatoDAO.getInstancia().getCampeonatoById(idCampeonato);
		return PartidoDAO.getInstancia().getFasesByIdCampeonato(idCampeonato);
	}
	
	public List<TablaPosicionesVO> getTablaPosicionesByFaseAndIdCampeonato(int idCampeonato, String fase) throws TablaPosicionesException{
		List<TablaPosiciones> tablas = TablaPosicionesDAO.getInstancia().getTablaPosicionesByIdCampeonatoAndFase(idCampeonato, fase);
		List<TablaPosicionesVO> tablasVO = new ArrayList<TablaPosicionesVO>();
		for (TablaPosiciones tabla : tablas) {
			tablasVO.add(tabla.toVO());
		}
		return tablasVO;
	}
	
	public List<JugadorVO> getJugadoresAgregarATorneo(int idCampeonato, int idClub) throws JugadorException{
		return this.convertirJugadoresAJugadoresVO(JugadorDAO.getInstancia().getJugadoresAgregarATorneo(idCampeonato, idClub));
	}
	
	public void ingresaJugadorPartido(int idPartido, int idJugador, int ingreso) throws ClubException, PartidoException {
		Partido partido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		Miembro miembro = MiembroDAO.getInstancia().getMiembroByIdPartidoAndJugador(idJugador, idPartido);
		miembro.setIngreso(ingreso);
		miembro.update();
	}
	
	public void egresaJugadorPartido(int idPartido, int idJugador, int egreso) throws ClubException, PartidoException {
		Partido partido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		Miembro miembro = MiembroDAO.getInstancia().getMiembro(idJugador);
		miembro.setEgreso(egreso);
		miembro.update();
	}
	
	public List<MiembroVO> getIngresosEgresosPartido(int idPartido) throws ClubException, PartidoException{
		Partido partido = PartidoDAO.getInstancia().obtenerPartido(idPartido);
		List<MiembroVO> miembrosVO = new ArrayList<MiembroVO>();
		List<Miembro> miembros = MiembroDAO.getInstancia().getIngresosEgresosPartido(idPartido);
		for (Miembro miembro : miembros) {
			miembrosVO.add(miembro.toVO());
		}
		return miembrosVO;
	}
	
	public TablaPosicionesVO[][] crearTablasGrupos(int idCampeonato) throws CampeonatoException {
		Campeonato auxCampeonato = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		List<Partido> auxPartidos = PartidoDAO.getInstancia().obtenerPartidosPorFase(idCampeonato);
		Set<Integer> zonas = new HashSet<>();
		Set<Integer> idClubesZona = new HashSet<>();
		for(Partido p: auxPartidos) {
			if (!zonas.contains(p.getNroZona())) {
				zonas.add(p.getNroZona());
			}
		}
		List<Integer> auxZonas = new ArrayList<>(zonas);
		int cantidadGrupos = auxZonas.size();
		System.out.println("La cantidad de Zonas es: "+ cantidadGrupos);
		int cantEquiposZona;
		List<Partido> auxPartidosZona = PartidoDAO.getInstancia().obtenerPartidosPorFaseYZona(idCampeonato,auxZonas.get(0));
		for (Partido par: auxPartidosZona) {
			if(!idClubesZona.contains(par.getClubLocal().getIdClub())){
				idClubesZona.add(par.getClubLocal().getIdClub());
			}
		}
		List<Club> clubesZona = new ArrayList<>();
		List<Integer> idClubes = new ArrayList<>(idClubesZona);
		for (Integer i: idClubes) {
			clubesZona.add(ClubDAO.getInstancia().obtenerClubPorID(i));
		}
		cantEquiposZona = idClubes.size();
		System.out.println("La cantidad de Equipos por zona es: "+ cantEquiposZona);
		TablaPosiciones tabla[][]= new TablaPosiciones[cantidadGrupos][cantEquiposZona];
		TablaPosicionesVO tablaVO[][] =  new TablaPosicionesVO[cantidadGrupos][cantEquiposZona];
		for (Integer nroZona: auxZonas) {
			System.out.println("El numero de Zona es"+nroZona);
			 auxPartidosZona = PartidoDAO.getInstancia().obtenerPartidosPorFaseYZona(idCampeonato,nroZona);
			for (Partido par: auxPartidosZona) {
				if(!idClubesZona.contains(par.getClubLocal().getIdClub())){
					idClubesZona.add(par.getClubLocal().getIdClub());
				}
			}
			
			clubesZona = new ArrayList<>();
			idClubes = new ArrayList<>(idClubesZona);
			for (Integer i: idClubes) {
				clubesZona.add(ClubDAO.getInstancia().obtenerClubPorID(i));
			}
			idClubesZona = new HashSet<>();
			for (int x=0;x<cantEquiposZona;x++) {
				TablaPosiciones t = new TablaPosiciones(clubesZona.get(x),auxCampeonato);
				tabla[nroZona-1][x] = t;
				System.out.println("Se creo la tabla de posiciones del Club:"+t.getClub().getNombre());
			}
			
			for (Partido p: auxPartidosZona) {
				System.out.println("El ID del partido es:"+p.getIdPartido());
				System.out.println("La cantidad de equipos por zona:"+cantEquiposZona);
				for (int z=0;z<cantEquiposZona;z++) {
					System.out.println(tabla[nroZona-1][z].getClub().getIdClub());
					System.out.println(p.getClubLocal().getIdClub());
					if (Integer.compare(tabla[nroZona-1][z].getClub().getIdClub(), p.getClubLocal().getIdClub()) == 0) {
						System.out.println("Entre al if del empate del local");
						tabla[nroZona-1][z] = modificarTablaGrupos(tabla[nroZona-1][z],p,p.getClubLocal());
					}
					if (Integer.compare(tabla[nroZona-1][z].getClub().getIdClub(), p.getClubVisitante().getIdClub()) == 0) {
						System.out.println("Entre al if del empate del visitante");
						tabla[nroZona-1][z] = modificarTablaGrupos(tabla[nroZona-1][z],p,p.getClubVisitante());
					}

				}
			}
		
		}
		 
		for (int c = 1;c<cantidadGrupos+1;c++) {
			System.out.println("ZONA NUMERO:"+c);
			for (int k =0;k<cantEquiposZona;k++) {
				tablaVO[c-1][k] = tabla[c-1][k].toVO();
				System.out.println("Club:"+tablaVO[c-1][k].getIdClub() + "Puntos: " +  tablaVO[c-1][k].getPuntos() );
				System.out.println("Cantidad de partidos empatados"+tablaVO[c-1][k].getCantidadempatados());
				System.out.println("Cantidad de partidos jugados"+tablaVO[c-1][k].getCantidadJugados());
			}
		}
		return tablaVO;
	}
	
	private TablaPosiciones modificarTablaGrupos(TablaPosiciones tabla,Partido partido,Club club) throws ClubException {
		tabla.setCantidadJugados(tabla.getCantidadJugados() + 1);
		if (Integer.compare(partido.getGolesLocal(), partido.getGolesVisitante())  == 0 ) {
			//Empataron el partido
			System.out.println("Entre al if de empatados del local");
			tabla.setCantidadempatados(tabla.getCantidadempatados() + 1);
			tabla.setPuntos(tabla.getPuntos() + 1);
		} else if (Integer.compare(club.getIdClub(), partido.getClubLocal().getIdClub()) == 0) {
			//El id que me pasan es el del club local
			if (Integer.compare(partido.getGolesLocal() , partido.getGolesVisitante()) == 1) {
				//El partido lo gano el equipo local
				tabla.setCantidadganados(tabla.getCantidadganados() + 1);
				
				//Actualizo los puntos
				tabla.setPuntos(tabla.getPuntos() + 3);
			} else if (Integer.compare(partido.getGolesLocal() , partido.getGolesVisitante()) == -1 ) {
				//El partido lo perdio el local
				tabla.setCantidadperdidos(tabla.getCantidadperdidos() + 1);
			}
			//Goles a favor del club local
			tabla.setGolesFavor(tabla.getGolesFavor() + partido.getGolesLocal());
			
			//Goles en contra del club local
			tabla.setGolesContra(tabla.getGolesContra() + partido.getGolesVisitante());
			 
			//Actualizo la diferencia
			tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());

				
		}  else if (Integer.compare(club.getIdClub(), partido.getClubVisitante().getIdClub()) == 0)  {
			//El id que me pasan es el del club visitante
			System.out.println("Entre al if de empatados del visitante");
			if (Integer.compare(partido.getGolesVisitante(), partido.getGolesLocal()) == 1 ) {
				//Gano el visitante
				tabla.setCantidadganados(tabla.getCantidadganados() + 1);
				tabla.setPuntos(tabla.getPuntos() + 3);
			} else if (Integer.compare(partido.getGolesVisitante(), partido.getGolesLocal()) == -1 ) {
				//Perdio el visitante
				tabla.setCantidadperdidos(tabla.getCantidadperdidos() + 1);
			}
			
			//Goles a favor del club visitante
			tabla.setGolesFavor(tabla.getGolesFavor() + partido.getGolesVisitante());
			
			//Goles en contra del club visitante
			tabla.setGolesContra(tabla.getGolesContra() + partido.getGolesLocal());
			
			//Actualizo la diferencia
			tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());
		}
		
		//Actualizo el promedio
		tabla.setPromedio((float) tabla.getPuntos() / (float) tabla.getCantidadJugados()); 
		
		
		return tabla;
	}
	
	public List<JugadorVO> getJugadoresPartido(int idPartido) throws JugadorException{
		List<Jugador> jugadores = JugadorDAO.getInstancia().getJugadoresPartido(idPartido);
		return this.convertirJugadoresAJugadoresVO(jugadores);
	}
	
	
	


	
	
}
