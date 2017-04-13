package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	////Atributos

	@JsonProperty(value="idCliente")
	private Integer idCliente;
	
	@JsonProperty(value="correoElectronico")
	private String correoElectronico;
	
	@JsonProperty(value="contrasena")
	private String contrasena;
	
	@JsonProperty(value="discriminacion")
	private String discriminacion;
	
	@JsonProperty(value="numFuncion")
	private Integer numFuncion;

	public Usuario(@JsonProperty(value="idCliente") Integer idCliente, @JsonProperty(value="correoElectronico") String correoElectronico,@JsonProperty(value="contrasena") String contrasena,@JsonProperty(value="discriminacion") String discriminacion,
			@JsonProperty(value="numFuncion") Integer numFuncion) {
		super();
		this.idCliente = idCliente;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.discriminacion = discriminacion;
		this.numFuncion = numFuncion;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

	public String getDiscriminacion() {
		return discriminacion;
	}

	public void setDiscriminacion(String discriminacion) {
		this.discriminacion = discriminacion;
	}

	public Integer getNumFuncion() {
		return numFuncion;
	}

	public void setNumFuncion(Integer numFuncion) {
		this.numFuncion = numFuncion;
	}
	
	
}
