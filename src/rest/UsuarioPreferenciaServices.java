package rest;


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
import vos.UsuarioPreferencia;

@Path("preferencias")
public class UsuarioPreferenciaServices {
	

	

		/**
		 * Atributo que usa la anotación @Context para tener el ServletContext de la conexión actual.
		 */
		@Context
		private ServletContext context;

		/**
		 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
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
	     * Método que expone servicio REST usando PUT que agrega el video que recibe en Json
	     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
	     * @param video - video a agregar
	     * @return Json con el video que agrego o Json con el error que se produjo
	     */
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public void cambiarPreferencia(UsuarioPreferencia up) {
			FestivAndesMaster fm = new FestivAndesMaster(getPath()); 
			fm.cambiarPreferencia(up); 
		}
		
		
	    /**
	     * Método que expone servicio REST usando POST que actualiza el video que recibe en Json
	     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
	     * @param video - video a actualizar. 
	     * @return Json con el video que actualizo o Json con el error que se produjo
	     */
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public void crearPreferencia(UsuarioPreferencia up) {
			FestivAndesMaster fm = new FestivAndesMaster(getPath()); 
			fm.crearPreferencia(up); 
		}
		
	    /**
	     * Método que expone servicio REST usando DELETE que actualiza el video que recibe en Json
	     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
	     * @param video - video a aliminar. 
	     * @return Json con el video que elimino o Json con el error que se produjo
	     */
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public void borrarPreferencia(UsuarioPreferencia up) {
			FestivAndesMaster fm = new FestivAndesMaster(getPath()); 
			fm.borrarPreferencia(up); 
		}


	}