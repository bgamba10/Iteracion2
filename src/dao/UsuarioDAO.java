package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import vos.Usuario;


public class UsuarioDAO {

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public UsuarioDAO() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	public ArrayList<Usuario> consultarAsistencia(Usuario usuario) throws SQLException 
	{
		ArrayList<Usuario> lista = new ArrayList<>();

		String correo = usuario.getCorreoElectronico(); 
		String contrasena = usuario.getContrasena(); 

		String sql = "SELECT USU_ID, ROL_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena +"'" ;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		String usuID = null;
		Integer rol = null;


		while(rs.next())
		{
			usuID = rs.getString("USU_ID");
			rol = rs.getInt("ROL_ID");

		}

		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos...");
			throw new WebApplicationException();
		}
		else if(rol == 2)
		{
			String sql2 = "SELECT FUN_FECHA, ISIS2304A131720.FUNCION.FUN_ID AS FUNID, PAG_ESTADO, USU_ID FROM ISIS2304A131720.SILLA_PAGADA, ISIS2304A131720.FUNCION WHERE ISIS2304A131720.SILLA_PAGADA.FUN_ID = ISIS2304A131720.FUNCION.FUN_ID AND USU_ID = "+ usuID;

			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();

			String estado = null;
			Integer funId = null;
			Date fechaFuncion = null;
			Integer id = null;
			String correoo = null;

			
			while(rs2.next())
			{
				funId = rs2.getInt("FUNID");
				estado = rs2.getString("PAG_ESTADO");
				fechaFuncion = rs2.getDate("FUN_FECHA");
				id = rs2.getInt("USU_ID");
				
				Date fechaActual = new Date(System.currentTimeMillis());

				LocalDate fa = fechaActual.toLocalDate();
				LocalDate ff = fechaFuncion.toLocalDate();
				
				

				if(fa.isBefore(ff))
				{
					//previstas
					
					Usuario n = new Usuario(id, correoo, null, "Función prevista", funId);
					lista.add(n);
				}
				if(fa.isAfter(ff))
				{
					//realizadas
					
					Usuario n = new Usuario(id,  correoo, null, "Función realizada o en curso", funId);
					lista.add(n);
				}
				if(estado != null && estado.equals("DEVUELTO"))
				{
					//Boletas devueltas
					Usuario n = new Usuario(id, correoo, null, "Boleta devuelta", funId);
					lista.add(n);
				}
			}

			return lista;

		}
		else if(rol == 5)
		{
			String sql2 = "SELECT FUN_FECHA, ISIS2304A131720.FUNCION.FUN_ID AS FUNID, PAG_ESTADO, USU_ID FROM ISIS2304A131720.SILLA_PAGADA, ISIS2304A131720.FUNCION WHERE ISIS2304A131720.SILLA_PAGADA.FUN_ID = ISIS2304A131720.FUNCION.FUN_ID AND USU_ID IS NOT NULL";

			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();

			String estado = null;
			Integer funId = null;
			Date fechaFuncion = null;
			Integer id = null;
			String correoo = null;

			while(rs2.next())
			{
				funId = rs2.getInt("FUNID");
				estado = rs2.getString("PAG_ESTADO");
				fechaFuncion = rs2.getDate("FUN_FECHA");
				id = rs2.getInt("USU_ID");
				

				Date fechaActual = new Date(System.currentTimeMillis());

				LocalDate fa = fechaActual.toLocalDate();
				LocalDate ff = fechaFuncion.toLocalDate();

				if(fa.isBefore(ff))
				{
					//previstas
					Usuario n = new Usuario(id,  correoo, null, "Función prevista", funId);
					lista.add(n);
				}
				if(fa.isAfter(ff))
				{
					//realizadas
					Usuario n = new Usuario(id,  correoo, null, "Función realizada o en curso", funId);
					lista.add(n);
				}
				if(estado != null && estado.equals("DEVUELTO"))
				{
					//Boletas devueltas
					Usuario n = new Usuario(id,  correoo, null, "Boleta devuelta", funId);
					lista.add(n);
				}
			}
			return lista;
		}
		else
		{
			System.out.println("Su tipo de usuario no puede acceder a la información que está solicitando");
			throw new WebApplicationException();
		}

	}


}
