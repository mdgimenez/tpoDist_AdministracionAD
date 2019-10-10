package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reclamos")
public class ReclamoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReclamo;
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity persona;
	
	@ManyToOne
	@JoinColumn(name="codigo")
	private EdificioEntity edificio;
	
	private String piso;
	private String ubicacion;
	private String descripcion;
	private String estado;
	private String fecha;
	
	@OneToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="idReclamo")
	private List<ImagenEntity> imagenes;
	
	public ReclamoEntity() { }
	
	public ReclamoEntity(PersonaEntity persona, EdificioEntity edificio, String piso, String ubicacion, String descripcion, List<ImagenEntity> imagenes, String estado, UnidadEntity unidad) {
		this.persona = persona;
		this.edificio = edificio;
		this.piso = piso;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.imagenes = imagenes;
		this.estado = estado;
		this.unidad = unidad;
	}
	
	public ReclamoEntity(PersonaEntity persona, EdificioEntity edificio, String piso, String ubicacion, String descripcion, List<ImagenEntity> imagenes, String estado, UnidadEntity unidad, String fecha) {
		this.persona = persona;
		this.edificio = edificio;
		this.piso = piso;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.imagenes = imagenes;
		this.estado = estado;
		this.unidad = unidad;
		this.fecha = fecha;
	}
	
	public int getId() {
		return idReclamo;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public EdificioEntity getEdificio() {
		return edificio;
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

	public List<ImagenEntity> getImagenes() {
		return imagenes;
	}

	public String getEstado() {
		return estado;
	}

	public UnidadEntity getUnidad() {
		return unidad;
	}
	
	public String getFecha() {
		return fecha;
	}
}
