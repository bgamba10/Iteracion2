package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vos.Funcion;
import vos.SillaPagada;

public class FuncionDAO {

	
	private SillaPagadaDAO sillaPagada;
	
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

	public void cancelarFuncion(Funcion funcion) throws Exception 
	{
		sillaPagada = new SillaPagadaDAO();
		sillaPagada.setConn(conn);
		
		String correo = funcion.getCorreo(); 
		String contrasena = funcion.getContrasena(); 

		String sql = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID ="+ 5;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		String usuID = null;

		while(rs.next()){
			usuID = rs.getString("USU_ID");
		}

		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos, o no es administrador...");
			throw new Exception("Usuario y/o contrasena invalidos, o no es administrador...");
		}
		
		Date fechaActual = new Date(System.currentTimeMillis());

		LocalDate fa = fechaActual.toLocalDate();
		LocalDate ff = darFechaFuncion(funcion.getNumFuncion()).toLocalDate();

		
		if(ff.isAfter(fa) || ff.isEqual(fa))
		{
			String sql2 = "UPDATE ISIS2304A131720.FUNCION SET FUN_ESTADO = 'CANCELADA' WHERE FUN_ID = " + funcion.getNumFuncion();

			System.out.println("SQL stmt:" + sql2);
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			prepStmt2.executeQuery();
			
			
			for(SillaPagada sp : darSillasPagadas(funcion.getNumFuncion()))
			{
				
				sillaPagada.eliminarSilla(sp);
			}
			
		}
		else
		{
			System.out.println("No se puede cancelar la función "+ funcion.getNumFuncion() );
		}
		
	}
	
	public Date darFechaFuncion(Integer numFuncion) throws SQLException
	{

		String sql = "SELECT FUN_FECHA FROM ISIS2304A131720.FUNCION WHERE FUN_ID = '" + numFuncion + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		Date fecha = null;

		while(rs.next())
		{
			fecha = rs.getDate("FUN_FECHA");
		}

		return fecha;
	}
	
	public List<SillaPagada> darSillasPagadas(Integer numFuncion) throws SQLException
	{
		List<SillaPagada> sillas = new ArrayList<>();
		
		String sql = "SELECT PAG_ID, "
				+ "USU_CORREO, "
				+ "USU_CONTRASENA "
				+ "FROM ISIS2304A131720.SILLA_PAGADA, "
				+ "ISIS2304A131720.USUARIO "
				+ "WHERE FUN_ID                            = " + numFuncion
				+ "AND ISIS2304A131720.SILLA_PAGADA.USU_ID = ISIS2304A131720.USUARIO.USU_ID "
				+ "AND ROL_ID                              = 2";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next())
		{
			SillaPagada sp = new SillaPagada(0, null, 0, rs.getString("USU_CORREO"), rs.getString("USU_CONTRASENA"), null, null, numFuncion, null, 0, rs.getInt("PAG_ID"));
			
			sillas.add(sp);
		}

		return sillas;
	}
	

}
