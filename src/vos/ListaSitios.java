package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaSitios {
	
	@JsonProperty(value="nombreSitio")
	private String nombreSitio; 
	

	@JsonProperty(value="lista")
	private List<Sitio> lista;

	public ListaSitios(@JsonProperty(value="nombreSitio")String nombreSitio,@JsonProperty(value="lista")List<Sitio> lista) {
		super();
		this.lista = lista;
		this.nombreSitio = nombreSitio; 
	}

	public List<Sitio> getLista() {
		return lista;
	}

	public void setLista(List<Sitio> lista) {
		this.lista = lista;
	}

	public String getNombreSitio() {
		return nombreSitio;
	}

	public void setNombreSitio(String nombreSitio) {
		this.nombreSitio = nombreSitio;
	} 
	
	
}