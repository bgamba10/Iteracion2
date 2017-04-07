package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import tm.FestivAndesMaster;
import vos.SillaPagada;
import vos.UsuarioPreferencia;


@Path("sillasPagadas")
public class SillaPagadaServices {

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
	public void comparBoleta(SillaPagada up) {
		FestivAndesMaster fm = new FestivAndesMaster(getPath()); 
		if (up.getSillaEliminar() != null)
		{
			fm.eliminarSilla(up); 
		}
		else 
		{
			fm.agregarSillaPagada(up); 
		}
		
	}
	
	
	
	
}
