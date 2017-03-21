package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Funcion {

	
	@JsonProperty(value="numFuncion")
	private int numFuncion;
	
	@JsonProperty(value="correo")
	private String correo; 
	
	@JsonProperty(value="contrasena")
	private String contrasena;

	public Funcion(@JsonProperty(value="numFuncion") int numFuncion, @JsonProperty(value="correo") String correo, 	@JsonProperty(value="contrasena") String contrasena) {
		super();
		this.numFuncion = numFuncion;
		this.correo = correo;
		this.contrasena = contrasena;
	}

	public int getNumFuncion() {
		return numFuncion;
	}

	public void setNumFuncion(int numFuncion) {
		this.numFuncion = numFuncion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	} 
	
}