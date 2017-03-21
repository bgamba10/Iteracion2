package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Funcion;

public class FuncionDAO {

	
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
	public FuncionDAO() {
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
	
	public void updateFuncion(Funcion funcion) throws SQLException, Exception 
	{
		int numFuncion = funcion.getNumFuncion();
		String correo = funcion.getCorreo();
		String contrasena = funcion.getContrasena();

		String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID = 3";

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();
		
		String usuID = null;

		while(rs1.next())
		{
			usuID = rs1.getString("USU_ID");
		}
		
		if(usuID != null )
		{
			int realizada = 1;
			String sql = "UPDATE ISIS2304A131720.FUNCION SET ";
			sql += "FUN_REALIZADA=" + realizada ;
			sql += " WHERE FUN_ID = " + numFuncion;

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
	}
	

}
