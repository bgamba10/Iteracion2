package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Abonamiento {



	@JsonProperty(value="sillas")
	private List<SillaPagada> sillas = new ArrayList<>();

	@JsonProperty(value="correoElectronico")
	private String correoElectronico;

	@JsonProperty(value="contrasena")
	private String contrasena;
	
	@JsonProperty(value="eliminar")
	private Integer eliminar;
	
	



	public Abonamiento(@JsonProperty(value="sillas") List<SillaPagada> sillas, @JsonProperty(value="correoElectronico") String correo, @JsonProperty(value="contrasena") String contrasena,@JsonProperty(value="eliminar") Integer eliminar) {
		super();
		this.sillas = sillas;
		this.correoElectronico = correo; 
		this.contrasena = contrasena; 
		this.eliminar = eliminar;
	}
	

	public Integer getEliminar() {
		return eliminar;
	}


	public void setEliminar(Integer eliminar) {
		this.eliminar = eliminar;
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


	public List<SillaPagada> getSillas() {
		return sillas;
	}

	public void setSillas(List<SillaPagada> sillas) {
		this.sillas = sillas;
	} 



}


