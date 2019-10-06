package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	@OneToOne
	@JoinColumn(name="documento")
	private PersonaEntity persona;
	
	private String contrasena;
	
	public UsuarioEntity() { }
	
	public UsuarioEntity(PersonaEntity persona, String contrasena) {
		this.persona = persona;
		this.contrasena = contrasena;
	}

	public int getIdUser() {
		return idUser;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public String getContrasena() {
		return contrasena;
	}
}
