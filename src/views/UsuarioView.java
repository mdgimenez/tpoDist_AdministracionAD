package views;

public class UsuarioView {
	
	private int id;
	private String documento;
	private String contrasena;
	
	public UsuarioView() { }

	public UsuarioView(int id, String documento, String contrasena) {
		this.id = id;
		this.documento = documento;
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getContrasena() {
		return contrasena;
	}
	
}
