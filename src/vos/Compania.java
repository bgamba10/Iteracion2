package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Compania {

	
	@JsonProperty(value="nombreCompania")
	private String nombreCompania;
	
	@JsonProperty(value="asistencia")
	private Integer asistencia;  
	
	@JsonProperty(value="nombreEspectaculo")
	private String nombreEspectaculo;
	
	@JsonProperty(value="numeroFuncion")
	private Integer numeroFuncion;
	
	@JsonProperty(value="porcentajeOcupacion")
	private Double porcentajeOcupacion;

	@JsonProperty(value="dinero")
	private Double precio;
	
	

	public Compania(@JsonProperty(value="nombreCompania") String nombreCompania, @JsonProperty(value="asistencia") Integer asistencia,@JsonProperty(value="nombreEspectaculo") String nombreEspectaculo, @JsonProperty(value="numeroFuncion") Integer numeroFuncion,@JsonProperty(value="porcentajeOcupacion") Double porcentajeOcupacion,
			@JsonProperty(value="dinero") Double precio) {
		super();
		this.nombreCompania = nombreCompania;
		this.asistencia = asistencia;
		this.nombreEspectaculo = nombreEspectaculo;
		this.numeroFuncion = numeroFuncion;
		this.porcentajeOcupacion = porcentajeOcupacion;
		this.precio = precio;
	}

	public Integer getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Integer asistencia) {
		this.asistencia = asistencia;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}

	public Integer getNumeroFuncion() {
		return numeroFuncion;
	}

	public void setNumeroFuncion(Integer numeroFuncion) {
		this.numeroFuncion = numeroFuncion;
	}

	public Double getPorcentajeOcupacion() {
		return porcentajeOcupacion;
	}

	public void setPorcentajeOcupacion(Double porcentajeOcupacion) {
		this.porcentajeOcupacion = porcentajeOcupacion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}
	
	
	

}