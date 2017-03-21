package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaFuncionEspectaculo {


	@JsonProperty(value="lista")
	private List<FuncionEspectaculo> lista;

	public ListaFuncionEspectaculo(@JsonProperty(value="lista") List<FuncionEspectaculo> lista) {
		super();
		this.lista = lista;
	}
 
	public List<FuncionEspectaculo> getLista() {
		return lista;
	}

	public void setLista(List<FuncionEspectaculo> lista) {
		this.lista = lista;
	} 

}