package rest;



import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import tm.FestivAndesMaster;
import vos.ListaSitios;

@Path("sitios")
public class SitioServices {
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
	
	@GET
	@Path("/{nombre}/{criterio}/{orden}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarSitio(@PathParam("nombre")String nombre, @PathParam("criterio")String criterio, @PathParam("orden")String orden) 
	{
		ListaSitios lista = null; 
		FestivAndesMaster master = new FestivAndesMaster(getPath()); 
		try {
			lista = master.consultarSitios(nombre, criterio, orden);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Response.status(200).entity(lista).build();
	}
}