package modelo;

public class Imagen {

	private int numero;
	private String binary;
	private String tipo;
	private int idReclamo;
	
	public Imagen(int numero, String binary, String tipo, int idReclamo) {
		this.numero = numero;
		this.binary = binary;
		this.tipo = tipo;
		this.idReclamo = idReclamo;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getBinary() {
		return binary;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getIdReclamo() {
		return idReclamo;
	}
}
