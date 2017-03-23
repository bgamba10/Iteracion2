package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaSitioFuncion {


	/**
	 * List con los videos
	 */
	@JsonProperty(value="sitioFuncion")
	private List<SitioFuncion> sitioFuncion;

	/**
	 * Constructor de la clase ListaVideos
	 * @param videos - videos para agregar al arreglo de la clase
	 */
	public ListaSitioFuncion( @JsonProperty(value="sitioFuncion")List<SitioFuncion> sitioFuncion){
		this.sitioFuncion = sitioFuncion;
	}

	public List<SitioFuncion> getSitioFuncion() {
		return sitioFuncion;
	}

	public void setSitioFuncion(List<SitioFuncion> sitioFuncion) {
		this.sitioFuncion = sitioFuncion;
	}

	
}


