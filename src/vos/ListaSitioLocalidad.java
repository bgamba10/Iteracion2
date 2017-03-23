package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaSitioLocalidad {

	/**
	 * List con los videos
	 */
	@JsonProperty(value="reporte")
	private List<SitioLocalidad> reporte;
	
	/**
	 * Constructor de la clase ListaVideos
	 * @param videos - videos para agregar al arreglo de la clase
	 */
	public ListaSitioLocalidad( @JsonProperty(value="reporte")List<SitioLocalidad> reporte){
		this.reporte = reporte;
	}

	/**
	 * Método que retorna la lista de videos
	 * @return  List - List con los videos
	 */
	public List<SitioLocalidad> getReporte() {
		return reporte;
	}

	/**
	 * Método que asigna la lista de videos que entra como parametro
	 * @param  videos - List con los videos ha agregar
	 */
	public void setReporte(List<SitioLocalidad> reporte) {
		this.reporte = reporte;
	}
}
