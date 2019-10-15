package views;

import java.util.List;

import modelo.Imagen;

public class ReclamoView {
	
	private int id;
	private String documentoPersona;
	private int codigoEdificio; 
	private String piso;
	private String ubicacion; 
	private String titulo;
	private String descripcion;
	private List<Imagen> imagenes;
	private int idUnidad;
	private String estado;
	private String fecha;
	
	public ReclamoView() { }

	public ReclamoView(int id, String documentoPersona, int codigoEdificio, String piso, String ubicacion, String titulo, String descripcion, int idUnidad, String estado, List<Imagen> imagenes) {
		this.id = id;
		this.documentoPersona = documentoPersona;
		this.codigoEdificio = codigoEdificio;
		this.piso = piso;
		this.ubicacion = ubicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagenes = imagenes;
		this.estado = estado;
		this.idUnidad = idUnidad;
	}
	
	public ReclamoView(int id, String documentoPersona, int codigoEdificio, String piso, String ubicacion, String titulo, String descripcion, int idUnidad, String estado, List<Imagen> imagenes, String fecha) {
		this.id = id;
		this.documentoPersona = documentoPersona;
		this.codigoEdificio = codigoEdificio;
		this.piso = piso;
		this.ubicacion = ubicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagenes = imagenes;
		this.estado = estado;
		this.idUnidad = idUnidad;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}
	
	public String getDocumentoPersona() {
		return documentoPersona;
	}

	public int getCodigoEdificio() {
		return codigoEdificio;
	}
	
	public String getPiso() {
		return piso;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public String getEstado() {
		return estado;
	}

	public int getIdUnidad() {
		return idUnidad;
	}

	public String getFecha() {
		return fecha;
	}
	
	public String getTitulo() {
		return titulo;
	}
}