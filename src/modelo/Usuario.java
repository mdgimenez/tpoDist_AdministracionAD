package modelo;

import views.UsuarioView;

public class Usuario {
	
	private int id;
	private Persona persona;
	private String contrasena;
	
	public Usuario (int id, Persona persona, String contrasena) {
		this.id = id;
		this.persona = persona;
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getContrasena() {
		return contrasena;
	}
	
	public UsuarioView toView() {
		return new UsuarioView (id, persona.getDocumento(), contrasena);
	}
}
