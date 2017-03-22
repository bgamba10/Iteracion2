package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sitio {
	
	@JsonProperty(value="nombreCiudad")
	private String nombreCiudad; 
	
	
	
	@JsonProperty(value="abierto")
	private Boolean abierto; 
	
	@JsonProperty(value="capacidad")
	private Integer capacidad; 
	
	@JsonProperty(value="tipoSilla")
	private String tipoSilla; 
	
	@JsonProperty(value="fechaFuncion")
	private String fechaFuncion; 
	
	@JsonProperty(value="numeroFuncion")
	private Integer numeroFuncion; 
	
	@JsonProperty(value="nombreEspectaculo")
	private String nombreEspectaculo;
	
	@JsonProperty(value="nombreLocalidad")
	private String nombreLocalidad;
	
	@JsonProperty(value="cuposDisponibles")
	private Integer cuposDisponibles;

	public Sitio(@JsonProperty(value="nombreCiudad") String nombreCiudad, @JsonProperty(value="abierto") Boolean abierto, @JsonProperty(value="capacidad")Integer capacidad, @JsonProperty(value="tipoSilla")String tipoSilla,
			@JsonProperty(value="fechaFuncion") String fechaFuncion, @JsonProperty(value="numeroFuncion")Integer numeroFuncion, @JsonProperty(value="nombreEspectaculo")String nombreEspectaculo, @JsonProperty(value="nombreLocalidad") String nombreLocalidad,
			@JsonProperty(value="cuposDisponibles") Integer cuposDisponibles) {
		super();
		this.nombreCiudad = nombreCiudad;
		
		this.abierto = abierto;
		this.capacidad = capacidad;
		this.tipoSilla = tipoSilla;
		this.fechaFuncion = fechaFuncion;
		this.numeroFuncion = numeroFuncion;
		this.nombreEspectaculo = nombreEspectaculo;
		this.nombreLocalidad = nombreLocalidad;
		this.cuposDisponibles = cuposDisponibles;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}


	public Boolean getAbierto() {
		return abierto;
	}

	public void setAbierto(Boolean abierto) {
		this.abierto = abierto;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getTipoSilla() {
		return tipoSilla;
	}

	public void setTipoSilla(String tipoSilla) {
		this.tipoSilla = tipoSilla;
	}

	public String getFechaFuncion() {
		return fechaFuncion;
	}

	public void setFechaFuncion(String fechaFuncion) {
		this.fechaFuncion = fechaFuncion;
	}

	public Integer getNumeroFuncion() {
		return numeroFuncion;
	}

	public void setNumeroFuncion(Integer numeroFuncion) {
		this.numeroFuncion = numeroFuncion;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public Integer getCuposDisponibles() {
		return cuposDisponibles;
	}

	public void setCuposDisponibles(Integer cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
	}

	
	

}