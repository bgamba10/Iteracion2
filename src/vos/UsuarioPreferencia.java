package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioPreferencia 
{
	@JsonProperty(value="correoElectronico")
	private String correoElectronico; 
	
	@JsonProperty(value="preferencia")
	private String preferencia; 
	
	@JsonProperty(value="contrasena")
	private String contrasena; 
	
	@JsonProperty(value="preferenciaAnterior")
	private String preferenciaAnterior; 
	
	
	public UsuarioPreferencia (@JsonProperty(value="correoElectronico") String correoElectronico, @JsonProperty(value="preferencia") String preferencia, @JsonProperty(value="contrasena") String contrasena, @JsonProperty(value="preferenciaAnterior") String preferenciaAnterior)
	{
		super(); 
		this.correoElectronico = correoElectronico; 
		this.preferencia = preferencia; 
		this.contrasena = contrasena; 
		this.preferenciaAnterior = preferenciaAnterior;
		
	}

	
	public String getPreferenciaAnterior() {
		return preferenciaAnterior;
	}


	public void setPreferenciaAnterior(String preferenciaAnterior) {
		this.preferenciaAnterior = preferenciaAnterior;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}