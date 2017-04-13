package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaCompania {

	@JsonProperty(value="lista")
	private List<Compania> lista;

	public ListaCompania( @JsonProperty(value="lista")List<Compania> lista) 
	{
		super();
		this.lista = lista;
		 
	}

	public List<Compania> getLista() {
		return lista;
	}

	public void setLista(List<Compania> lista) {
		this.lista = lista;
	}
	
	
}
