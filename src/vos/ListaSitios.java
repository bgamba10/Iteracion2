package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaSitios {

	@JsonProperty(value="lista")
	private List<Sitio> lista;

	public ListaSitios(@JsonProperty(value="lista")List<Sitio> lista) {
		super();
		this.lista = lista;
	}

	public List<Sitio> getLista() {
		return lista;
	}

	public void setLista(List<Sitio> lista) {
		this.lista = lista;
	} 
	
	
}