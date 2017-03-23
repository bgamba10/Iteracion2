package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class FuncionEspectaculo {


	@JsonProperty(value="lista")
	private List<FuncionEspectaculoR> lista;

	public FuncionEspectaculo(@JsonProperty(value="lista") List<FuncionEspectaculoR> lista) {
		super();
		this.lista = lista;
	}
 
	public List<FuncionEspectaculoR> getLista() {
		return lista;
	}

	public void setLista(List<FuncionEspectaculoR> lista) {
		this.lista = lista;
	} 

}