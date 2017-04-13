package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Abonamiento;






@Path("abonamiento")
public class AbonamientoServices {

	@Context
	private ServletContext context;

	private String getPath() 
	{
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e)
	{
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarAbonamiento(Abonamiento abo) {

		FestivAndesMaster fm = new FestivAndesMaster(getPath()); 

		if (abo.getEliminar() != null )
		{
			fm.eliminarAbonamiento(abo); 
			return Response.status(200).entity(abo).build();
		}
		else 
		{
			fm.agregarAbonamiento(abo); 
			return Response.status(200).entity(abo).build();
		}


	}





}
