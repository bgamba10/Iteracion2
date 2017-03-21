package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class FuncionEspectaculo {
	
	@JsonProperty(value="fechaInicial")
	private java.sql.Date fechaInicial; 
	
	@JsonProperty(value="fechaFinal")
	private java.sql.Date fechaFinal; 
	
	@JsonProperty(value="compania")
	private String compania; 
	
	@JsonProperty(value="categoria")
	private String categoria; 
	
	@JsonProperty(value="idioma")
	private String idioma; 
	
	@JsonProperty(value="disponibilidad")
	private int disponibilidad;
	
	@JsonProperty(value="numeroFuncion")
	private int numeroFuncion;
	
	@JsonProperty(value="restriccion")
	private String restriccion;
	
	@JsonProperty(value="espectaculo")
	private String espectaculo;
	
	@JsonProperty(value="fechaFuncion")
	private java.sql.Date fechaFuncion;
	
	
	

	public FuncionEspectaculo(@JsonProperty(value="fechaInicial")java.sql.Date fechaInicial, @JsonProperty(value="fechaFinal")java.sql.Date fechaFinal, @JsonProperty(value="compania")String compania, @JsonProperty(value="categoria")String categoria, @JsonProperty(value="idioma")String idioma,
			@JsonProperty(value="disponibilidad")int disponibilidad,@JsonProperty(value="restriccion") String restriccion, @JsonProperty(value="numeroFuncion") int numeroFuncion, @JsonProperty(value="espectaculo") String espectaculo, @JsonProperty(value="fechaFuncion") java.sql.Date fechaFuncion) {
		super();
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.compania = compania;
		this.categoria = categoria;
		this.idioma = idioma;
		this.disponibilidad = disponibilidad;
		this.restriccion = restriccion;
		this.fechaFuncion = fechaFuncion;
		this.espectaculo = espectaculo;
		this.numeroFuncion = numeroFuncion;
	}

	
	public String getCompania() {
		return compania;
	}


	public void setCompania(String compania) {
		this.compania = compania;
	}


	public int getNumeroFuncion() {
		return numeroFuncion;
	}


	public void setNumeroFuncion(int numeroFuncion) {
		this.numeroFuncion = numeroFuncion;
	}


	public String getEspectaculo() {
		return espectaculo;
	}


	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}


	public java.sql.Date getFechaFuncion() {
		return fechaFuncion;
	}


	public void setFechaFuncion(java.sql.Date fechaFuncion) {
		this.fechaFuncion = fechaFuncion;
	}


	public java.sql.Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(java.sql.Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public java.sql.Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(java.sql.Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	} 
	
	
}