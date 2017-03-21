package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	////Atributos

	@JsonProperty(value="idCliente")
	private int idCliente;

	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="apellido")
	private String apellido;
	
	@JsonProperty(value="correoElectronico")
	private String correoElectronico;
	
	@JsonProperty(value="contrasena")
	private String contrasena;
	
	@JsonProperty(value="rol")
	private String rol;

	@JsonProperty(value="preferencias")
	private List<String> preferencias;
	
	

	public Usuario(@JsonProperty(value="idCliente")	int idCliente,	@JsonProperty(value="nombre")	String nombre, 	@JsonProperty(value="apellido")	String apellido, @JsonProperty(value="correoElectronico") String correoElectronico, @JsonProperty(value="contrasena")	String contrasena, 	@JsonProperty(value="rol")	String rol,	@JsonProperty(value="preferencias")	List<String> preferencias) 
	{
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.rol = rol;
		this.preferencias = preferencias;
				
		
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<String> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(List<String> preferencias) {
		this.preferencias = preferencias;
	}
	
	
	
}
