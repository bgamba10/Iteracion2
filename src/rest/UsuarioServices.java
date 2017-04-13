package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Funcion;
import vos.ListaSitioFuncion;
import vos.ListaUsuarios;
import vos.Usuario;

@Path("usuarios")
public class UsuarioServices {
	
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
	@Path("/asistencia")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarAsistencia(Usuario usuario) 
	{
		ListaUsuarios lista = null;
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			lista = tm.consultarAsistencia(usuario);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(lista).build();
	}
	

}
