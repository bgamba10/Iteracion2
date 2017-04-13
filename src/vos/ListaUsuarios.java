package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaUsuarios {
	

	

	@JsonProperty(value="lista")
	private List<Usuario> lista;

	public ListaUsuarios( @JsonProperty(value="lista")List<Usuario> lista) 
	{
		super();
		this.lista = lista;
		 
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	
	
	
}