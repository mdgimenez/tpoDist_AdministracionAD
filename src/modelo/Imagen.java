package modelo;

public class Imagen {

	private int numero;
	private byte[] binary;
	private String tipo;
	private int idReclamo;
	
	public Imagen(int numero, byte[] binary, String tipo, int idReclamo) {
		this.numero = numero;
		this.binary = binary;
		this.tipo = tipo;
		this.idReclamo = idReclamo;
	}
	
	public Imagen(int numero, String binary, String tipo, int idReclamo) {
		this.numero = numero;
		this.binary = binary.getBytes();
		this.tipo = tipo;
		this.idReclamo = idReclamo;
	}
	
	public Imagen() {}

	public int getNumero() {
		return numero;
	}
	
	public byte[] getBinary() {
		return binary;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getIdReclamo() {
		return idReclamo;
	}
}
