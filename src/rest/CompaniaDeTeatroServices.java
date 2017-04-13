package rest;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.ListaCompania;


@Path("compania")
public class CompaniaDeTeatroServices {

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
	@Path("/{numeroCompania}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response generarReporteFuncion(@PathParam("numeroCompania")Integer numeroCompania) 
	{
		ListaCompania lista = null; 
		FestivAndesMaster master = new FestivAndesMaster(getPath()); 

		lista = master.consultarCompania(numeroCompania);


		return Response.status(200).entity(lista).build();
	}
}
