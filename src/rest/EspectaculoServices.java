package rest;


import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Funcion;
import vos.FuncionEspectaculoR;
import vos.ListaSitioFuncion;
import vos.FuncionEspectaculo;

@Path("espectaculos")
public class EspectaculoServices {

	// Servicios REST tipo GET:


	/**
	 * Atributo que usa la anotaci�n @Context para tener el ServletContext de la conexi�n actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * M�todo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	@GET
	@Path("sayHello")
	public String sayHello(@QueryParam("name")String name) {
		System.out.println("aasaaa");
		return "asa: "+name;

	}

	/**
	 * M�todo que expone servicio REST usando PUT que agrega los videos que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
	 * @param videos - videos a agregar. 
	 * @return Json con el video que agrego o Json con el error que se produjo
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void actualizarFuncion(Funcion funcion) {
		FestivAndesMaster fm = new FestivAndesMaster(getPath());
		fm.registrarRealizacion(funcion);
	}

	/**
	 * M�todo que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos O json con 
	 * el error que se produjo
	 */
	@POST
	@Path("/consulta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVideos(FuncionEspectaculoR fe) 
	{
		FestivAndesMaster fm = new FestivAndesMaster(getPath());

		FuncionEspectaculo lista = null;

		try {
			lista = fm.funcionesEspectaculo(fe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(lista).build();
	}
	
	@GET
	@Path("/reporte/{nombreEspectaculo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reporte(@PathParam("nombreEspectaculo")String nombreEspectaculo) {
		ListaSitioFuncion lista= null; 
		FestivAndesMaster fm = new FestivAndesMaster(getPath()); 
		
		lista = fm.reporteEspectaculo(nombreEspectaculo); 
		
		return Response.status(200).entity(lista).build();
	}
}

