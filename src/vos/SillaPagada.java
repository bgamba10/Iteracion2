package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import oracle.sql.DATE;

public class SillaPagada {

	@JsonProperty(value="idSillaPagada")
	private int idSillaPagada;

	@JsonProperty(value="fila")
	private String fila;
	
	@JsonProperty(value="columna")
	private int columna;
	
	@JsonProperty(value="correoElectronico")
	private String correoElectronico;
	
	@JsonProperty(value="contrasena")
	private String contrasena;
	
	@JsonProperty(value="fecha")
	private DATE fecha;
	
	@JsonProperty(value="localidad")
	private String localidad;
	
	@JsonProperty(value="numeroFuncion")
	private int numFuncion;
	
	@JsonProperty(value="espectaculo")
	private String espectaculo;
	

	
	public SillaPagada( @JsonProperty(value="idSillaPagada") int idSillaPagada, @JsonProperty(value="fila") String fila, @JsonProperty(value="columna") int columna, @JsonProperty(value="correoElectronico") String correoElectronico, @JsonProperty(value="contrasena") String contrasena,
			@JsonProperty(value="fecha") DATE fecha, @JsonProperty(value="localidad") String localidad, @JsonProperty(value="NumeroFuncion") int numFuncion, @JsonProperty(value="espectaculo") String espectaculo) {
		super();
		this.idSillaPagada = idSillaPagada;
		this.fila = fila;
		this.columna = columna;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.fecha = fecha;
		this.localidad = localidad;
		this.numFuncion = numFuncion;
		this.espectaculo = espectaculo;
	}

	
	public int getNumFuncion() {
		return numFuncion;
	}

	public void setNumFuncion(int numFuncion) {
		this.numFuncion = numFuncion;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}

	public int getIdSillaPagada() {
		return idSillaPagada;
	}

	public void setIdSillaPagada(int idSillaPagada) {
		this.idSillaPagada = idSillaPagada;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
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

	public DATE getFecha() {
		return fecha;
	}

	public void setFecha(DATE fecha) {
		this.fecha = fecha;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}	
	
}
