package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.UsuarioPreferencia;


public class UsuarioPreferenciaDAO {

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
	public UsuarioPreferenciaDAO() {
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

	public void addUsuarioPreferencia(UsuarioPreferencia usuarioP) throws SQLException, Exception {

		String correo = usuarioP.getCorreoElectronico();
		String preferencia = usuarioP.getPreferencia();
		String contrasena = usuarioP.getContrasena();

		String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID = 2";
		String sql2 = "SELECT PRE_ID FROM ISIS2304A131720.PREFERENCIA WHERE PRE_NOMBRE = '" + preferencia + "'";

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();

		String usuID = null;

		while(rs1.next()){
			usuID = rs1.getString("USU_ID");
		}
		
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		ResultSet rs2 = prepStmt2.executeQuery();
		
		String preID = null;
		
		while(rs2.next())
		{
			preID = rs2.getString("PRE_ID");
		}
		
		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos \n o no es cliente ");
		}
		else if(preID == null)
		{
			System.out.println("No existe esa preferencia");
		}
		else
		{
			String sql = "INSERT INTO ISIS2304A131720.USUARIO_PREFERENCIA (USU_ID, PRE_ID) VALUES ( ";
			sql += usuID + ",";
			sql += preID + ") ";

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
	}

	public void updateUsuarioPreferencia(UsuarioPreferencia usuarioP) throws SQLException, Exception {

		String correo = usuarioP.getCorreoElectronico();
		String preferencia = usuarioP.getPreferencia();
		String contrasena = usuarioP.getContrasena();
		String prefAnterior = usuarioP.getPreferenciaAnterior();

		String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID = 2";
		String sql2 = "SELECT PRE_ID FROM ISIS2304A131720.PREFERENCIA WHERE PRE_NOMBRE = '" + preferencia + "'";
		String sql4 = "SELECT PRE_ID FROM ISIS2304A131720.PREFERENCIA WHERE PRE_NOMBRE = '" + prefAnterior + "'";

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();
		
		String usuID = null;

		while(rs1.next())
		{
			usuID = rs1.getString("USU_ID");
		}

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		ResultSet rs2 = prepStmt2.executeQuery();

		String preID = null;
		
		while(rs2.next())
		{
			preID = rs2.getString("PRE_ID");
		}

		PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
		recursos.add(prepStmt4);
		ResultSet rs4 = prepStmt4.executeQuery();

		String preAnteriorID = null;
		
		while(rs4.next())
		{
			preAnteriorID = rs4.getString("PRE_ID");
		}
		
		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos \n o no es cliente");
		}
		else if(preID == null)
		{
			System.out.println("No existe esa preferencia");
		}
		else if(preAnteriorID == null)
		{
			System.out.println("No tenia esa preferencia");
		}
		else
		{

			String sql = "UPDATE ISIS2304A131720.USUARIO_PREFERENCIA SET ";
			sql += "USU_ID=" + usuID + ",";
			sql += "PRE_ID=" + preID;
			sql += " WHERE PRE_ID = " + preAnteriorID + " AND USU_ID = " + usuID;

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
	}

	public void deleteUsuarioPreferencia(UsuarioPreferencia usuarioP) throws SQLException, Exception {

		String correo = usuarioP.getCorreoElectronico();

		String contrasena = usuarioP.getContrasena();
		String prefAnterior = usuarioP.getPreferenciaAnterior();

		String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID = 2";

		String sql4 = "SELECT PRE_ID FROM ISIS2304A131720.PREFERENCIA WHERE PRE_NOMBRE = '" + prefAnterior + "'";

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();

		String usuID = null;

		while(rs1.next())
		{
			usuID = rs1.getString("USU_ID");
		}
		
		PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
		recursos.add(prepStmt4);
		ResultSet rs4 = prepStmt4.executeQuery();

		String preAnteriorID = null;
		
		while(rs4.next())
		{
			preAnteriorID = rs4.getString("PRE_ID");
		}

		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos \n o no es cliente");
		}
		else if(preAnteriorID == null)
		{
			System.out.println("No tenia esa preferencia");
		}
		else{
			String sql = "DELETE FROM ISIS2304A131720.USUARIO_PREFERENCIA";
			sql += " WHERE PRE_ID = " + preAnteriorID + " AND USU_ID = " + usuID;

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
	}
}
