package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="personas")
public class PersonaEntity {

	@Id
	private String documento;
	private String nombre;
	
	@OneToMany
	@JoinColumn(name = "documento")
	private List<ReclamoEntity> reclamo;
	
	public PersonaEntity() { }
	
	public PersonaEntity(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

}
