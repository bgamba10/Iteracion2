package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.SitioLocalidad;

public class SitioLocalidadDAO {

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
	public SitioLocalidadDAO(){
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

	public ArrayList<SitioLocalidad> generarReporteFuncion(int numeroFuncion) throws SQLException {
		
		ArrayList<SitioLocalidad> lista = new ArrayList<>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT fun.FUN_ID numerofuncion,"
				+ " sit.SIT_NOMBRE AS sitio,"
				+ " loc.LOC_NOMBRE AS localidad,"
				+ " loc.loc_precio precio,"
				+ " COUNT (*) AS vendidasCliente"
				+ " FROM ESPECTACULO esp,"
				+ " FUNCION fun,"
				+ " SITIO sit,"
				+ " SITIO_LOCALIDAD stl,"
				+ " LOCALIDAD loc,"
				+ " SILLA_PAGADA sip"
				+ " WHERE fun.esp_id = esp.esp_id"
				+ " AND fun.SIT_ID   = sit.SIT_ID"
				+ " AND sit.sit_id   = stl.sit_id"
				+ " AND stl.loc_id   = loc.loc_id"
				+ " AND loc.loc_id   = sip.loc_id"
				+ " AND fun.fun_id   = sip.FUN_ID"
				+ " AND sip.usu_id  IS NOT NULL"
				+ " GROUP BY fun.FUN_ID,"
				+ " sit.SIT_NOMBRE,"
				+ " loc.LOC_NOMBRE,"
				+ " loc.loc_precio ),"
				+ " (SELECT sit.SIT_NOMBRE AS sitio1,"
				+ " loc.LOC_NOMBRE       AS localidad1,"
				+ " COUNT (*)            AS vendidasPublico"
				+ " FROM ESPECTACULO esp,"
				+ " FUNCION fun,"
				+ " SITIO sit,"
				+ " SITIO_LOCALIDAD stl,"
				+ " LOCALIDAD loc,"
				+ " SILLA_PAGADA sip"
				+ " WHERE fun.esp_id = esp.esp_id"
				+ " AND fun.SIT_ID   = sit.SIT_ID"
				+ " AND sit.sit_id   = stl.sit_id"
				+ " AND stl.loc_id   = loc.loc_id"
				+ " AND loc.loc_id   = sip.loc_id"
				+ " AND fun.fun_id   = sip.FUN_ID"
				+ " AND sip.usu_id  IS NULL"
				+ " GROUP BY sit.SIT_NOMBRE,"
				+ " loc.LOC_NOMBRE )"
				+ " WHERE sitio   = sitio1"
				+ " AND localidad = localidad1"
				+ " AND numerofuncion =" + numeroFuncion;
				
				
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		
		while (rs.next()) 
		{
			int numFuncion = rs.getInt("NUMEROFUNCION");
			String sitio = rs.getString("SITIO");
			String localidad = rs.getString("LOCALIDAD");
			int vendidasPublico = rs.getInt("VENDIDASPUBLICO");
			int vendidasCliente = rs.getInt("VENDIDASCLIENTE");
			int precio = rs.getInt("PRECIO");
			
			double producidoClientes = precio*vendidasCliente;
			double producidoPublico = precio*vendidasPublico;
			
			lista.add(new SitioLocalidad(numFuncion, sitio, localidad, vendidasPublico, vendidasCliente, producidoPublico, producidoClientes));
		}
		return lista;
	}
}
