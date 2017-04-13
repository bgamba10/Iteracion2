package rest;


import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import tm.Master;
import vos.Funcion;
import vos.FuncionEspectaculoR;
import vos.Video;
import vos.FuncionEspectaculo;

@Path("funciones")
public class FuncionServices {


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

	@POST
	@Path("/cancelar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFuncion(Funcion funcion) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.cancelarFuncion(funcion);
		} 
		catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcion).build();
	}


}