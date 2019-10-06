package exceptions;

public class UsuarioException extends Exception {

	private static final long serialVersionUID = -1690698840733203643L;
	
	public UsuarioException(String mensaje) {
		super(mensaje);
	}
}
