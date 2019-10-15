package controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import daos.DuenioDAO;
import daos.EdificioDAO;
import daos.ImagenDAO;
import daos.InquilinoDAO;
import daos.PersonaDAO;
import daos.ReclamoDAO;
import daos.UnidadDAO;
import daos.UsuarioDAO;
import entities.ReclamoEntity;
import exceptions.EdificioException;
import exceptions.ImagenException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import exceptions.UsuarioException;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import modelo.Usuario;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;
import views.UsuarioView;

public class Controlador {

	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	/** OK */
	public List<EdificioView> getEdificios() throws EdificioException, UnidadException {
		List<EdificioView> resultado = new ArrayList<EdificioView>();
		List<Edificio> edificios = EdificioDAO.getInstancia().getAll();
		for (Edificio edificio : edificios)
			resultado.add(edificio.toView());
		return resultado;
	}

	/** OK */
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException, UnidadException {
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades();
		for (Unidad unidad : unidades)
			resultado.add(unidad.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> habilitadosPorEdificio(int codigo)
			throws EdificioException, UnidadException, PersonaException {
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		for (Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> dueniosPorEdificio(int codigo)
			throws EdificioException, UnidadException, PersonaException {
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> duenios = edificio.duenios();
		for (Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> inquilinosPorEdificio(int codigo)
			throws EdificioException, UnidadException, PersonaException {
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> inquilinos = edificio.inquilinos();
		for (Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> habitantesPorEdificio(int codigo)
			throws EdificioException, UnidadException, PersonaException {
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.habitantes();
		for (Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero)
			throws UnidadException, EdificioException, PersonaException {
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for (Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero)
			throws UnidadException, EdificioException, PersonaException {
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for (Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	private Edificio buscarEdificio(int codigo) throws EdificioException, UnidadException {
		return EdificioDAO.getInstancia().findByID(codigo);
	}

	/** OK */
	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException, EdificioException {
		return UnidadDAO.getInstancia().findById(codigo, piso, numero);
	}

	/** OK */
	private Persona buscarPersona(String documento) throws PersonaException {
		return PersonaDAO.getInstancia().findByID(documento);
	}
	
	/** TO DO */
	@SuppressWarnings("unused")
	private Usuario buscarUsuarioByID(int codigo) throws UsuarioException {
		try {
			return UsuarioDAO.getInstancia().getUsuarioByID(codigo);
		} catch (UsuarioException e) {
			throw new UsuarioException("No se pudo obtener el Usuario");
		}
	}
	
	/** TO DO */
	public int registrarUsuario(UsuarioView uw) throws UsuarioException {
		try {
			Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByDocumento(uw.getDocumento());
			if(usuario == null) {
				Persona persona = buscarPersona(uw.getDocumento());
				if(persona != null) {
					usuario = new Usuario(UsuarioDAO.getInstancia().obtenerUltimoId()+1, persona, uw.getContrasena());
					UsuarioDAO.getInstancia().saveUsuario(usuario);
					return usuario.getId();
				}
			}
			else {
				throw new UsuarioException("Ya existe el Usuario");
			}
		} catch (Exception e) {
			throw new UsuarioException("No se pudo registrar el Usuario");
		}
		return -1;
	}
	
	public boolean autenticarUsuario(UsuarioView uw) throws UsuarioException {
		try {
			return UsuarioDAO.getInstancia().getUsuarioAuth(uw.getDocumento(), uw.getContrasena());
		} catch (Exception e) {
			throw new UsuarioException("No se pudo autenticar el Usuario");
		}
	}
	
	/** OK */
	public int crearReclamo(ReclamoView rw) throws ReclamoException { 
		try{
			Persona	persona = PersonaDAO.getInstancia().findByID(rw.getDocumentoPersona()); 
			Edificio edificio =	EdificioDAO.getInstancia().findByID(rw.getCodigoEdificio());
			Unidad unidad = null;
			if(rw.getIdUnidad() > 0)
				unidad = UnidadDAO.getInstancia().findById(rw.getIdUnidad());
			Reclamo reclamo = new Reclamo(ReclamoDAO.getInstancia().obtenerUltimoId()+1, persona, edificio, rw.getPiso(), rw.getUbicacion(), rw.getTitulo(), rw.getDescripcion(), unidad);
			ReclamoEntity re = ReclamoDAO.getInstancia().saveReclamo(reclamo);
			if(rw.getImagenes() != null)
				ImagenDAO.getInstancia().saveImagen(re, rw.getImagenes());
			return reclamo.getId();
		} catch(Exception e) {
			throw new ReclamoException("No se pudo guardar el reclamo");
		}
	}
					 
	/** OK */
	public List<ReclamoView> getReclamos() throws ReclamoException, ImagenException {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().getAll();
		for (Reclamo reclamo : reclamos) {
			reclamo.setImagenes(ImagenDAO.getInstancia().getImagenes(reclamo.getId()));
			resultado.add(reclamo.toView());
		}
		return resultado;
	}
	
	/** OK */
	public List<ReclamoView> getReclamosByEdificio(int idEdificio) throws ReclamoException, ImagenException {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().getReclamosByEdificio(idEdificio);
		for (Reclamo reclamo : reclamos) {
			reclamo.setImagenes(ImagenDAO.getInstancia().getImagenes(reclamo.getId()));
			resultado.add(reclamo.toView());
		}
		return resultado;
	}
	
	/** OK */
	public List<ReclamoView> getReclamosByDocumento(String documento) throws ReclamoException, ImagenException {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().getReclamosByDocumento(documento);
		for (Reclamo reclamo : reclamos) {
			reclamo.setImagenes(ImagenDAO.getInstancia().getImagenes(reclamo.getId()));
			resultado.add(reclamo.toView());
		}
		return resultado;
	}

	/** OK */
	public ReclamoView buscarReclamo(int id) throws ReclamoException, ImagenException {
		return ReclamoDAO.getInstancia().findByID(id).toView();
	}
	
	/** OK */
	public EdificioView buscarEdificioReclamo(int codigo) throws EdificioException, UnidadException {
		return EdificioDAO.getInstancia().findByID(codigo).toView();
	}
	
	/** OK */
	public PersonaView buscarPersonaReclamo(String documento) throws PersonaException {
		return PersonaDAO.getInstancia().findByID(documento).toView();
	}
	
	/** TO DO */
	public void actualizarEstadoReclamo(int id, String estado) throws ReclamoException {
		ReclamoDAO.getInstancia().updateReclamoEstado(id, estado);
	}
	
	/** OK */
	public List<ReclamoView> buscarReclamosAsociados(int id) throws UsuarioException, ReclamoException, ImagenException, PersonaException{
		Usuario u = UsuarioDAO.getInstancia().getUsuarioByID(id);
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
		List<Edificio> edificios = new ArrayList<Edificio>();
		edificios.addAll(DuenioDAO.getInstancia().getEdificiosAsociadosByDocumento(u.getPersona().getDocumento()));
		edificios.addAll(InquilinoDAO.getInstancia().getEdificiosAsociadosByDocumento(u.getPersona().getDocumento()));
		Set<Integer> aux = new HashSet<Integer>();
		for(Edificio e: edificios)
		{
			aux.add(e.getCodigo());
		}
		for(int i: aux)
		{
			reclamos.addAll(ReclamoDAO.getInstancia().getReclamosByEdificio(i));
		}
		for(Reclamo reclamo: reclamos)
		{
			resultado.add(reclamo.toView());
		}
		return resultado;
	}

	/** OK */
	public List<ReclamoView> buscarTodosLosReclamos() throws ReclamoException, ImagenException {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().getAll();
		for(Reclamo reclamo: reclamos)
		{
			resultado.add(reclamo.toView());
		}
		return resultado;
	}
}
