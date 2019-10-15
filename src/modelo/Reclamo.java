package modelo;

import java.util.ArrayList;
import java.util.List;

import views.ReclamoView;

public class Reclamo {
	
	private int id;
	private Persona persona;
	private Edificio edificio;
	private String piso;
	private String ubicacion; //Si es null significa que es una parte comun del edificio
	private String titulo;
	private String descripcion;
	private List<Imagen> imagenes;
	private String estado;
	private Unidad unidad; //Identificador es el numero de unidad
	private String fecha;

	public Reclamo(int id, Persona persona, Edificio edificio, String piso, String ubicacion, String titulo, String descripcion, Unidad unidad) {
		this.id = id; // Identificador univoco de cada reclamo (idReclamo)
		this.persona = persona; //Identificacion de cada persona, due�o o inquilino. (documento)
		this.edificio = edificio; //Identificacion el n� de edificio (codigo)
		this.piso = piso;	// n� de piso donde se encuentra el desperfecto
		this.ubicacion = ubicacion; // Seran opciones prefijas en donde el usuario tendra que elegir una de ellas
		this.titulo = titulo; // Breve info del reclamo
		this.descripcion = descripcion; // M�s detalles sobre el desperfecto
		this.imagenes = new ArrayList<Imagen>(); // Conjunto de imagenes que complementa la explicacion del desperfecto
		this.estado = "Pendiente"; //cual es la situacion actual en la que se encuentra el reclamo
		this.unidad = unidad; //NUll si es una ubicacion generica sino es un lugar del depto (identificador)
	}
	
	public Reclamo(int id, Persona persona, Edificio edificio, String piso, String ubicacion, String titulo, String descripcion, Unidad unidad, String fecha) {
		this.id = id; // Identificador univoco de cada reclamo (idReclamo)
		this.persona = persona; //Identificacion de cada persona, due�o o inquilino. (documento)
		this.edificio = edificio; //Identificacion el n� de edificio (codigo)
		this.piso = piso;	// n� de piso donde se encuentra el desperfecto
		this.ubicacion = ubicacion; // Seran opciones prefijas en donde el usuario tendra que elegir una de ellas
		this.titulo = titulo; // Breve info del reclamo
		this.descripcion = descripcion; // M�s detalles sobre el desperfecto
		this.imagenes = new ArrayList<Imagen>(); // Conjunto de imagenes que complementa la explicacion del desperfecto
		this.estado = "Pendiente"; //cual es la situacion actual en la que se encuentra el reclamo
		this.unidad = unidad; //NUll si es una ubicacion generica sino es un lugar del depto (identificador)
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Edificio getEdificio() {
		return edificio;
	}

	public String getPiso() {
		return piso;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setEstado(String estado){
		this.estado = estado;
	}
	
	public void setImagenes(List<Imagen> imagenes){
		this.imagenes = imagenes;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Unidad getUnidad() {
		return unidad;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public ReclamoView toView() {
		if(unidad != null)
			return new ReclamoView(id, persona.getDocumento(), edificio.getCodigo(), piso, ubicacion, titulo, descripcion, unidad.getId(), estado, getImagenes(), fecha);
		else
			return new ReclamoView(id, persona.getDocumento(), edificio.getCodigo(), piso, ubicacion, titulo, descripcion, 0, estado, getImagenes(), fecha);
	}
	
}
