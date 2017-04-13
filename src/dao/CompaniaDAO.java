package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Compania;
import vos.Sitio;

public class CompaniaDAO {

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
	public CompaniaDAO() {
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

	public ArrayList<Compania> consultarCompania(Integer numCompania) throws SQLException {
		
		ArrayList<Compania> lista = new ArrayList<>();
		
		String sql = "SELECT DISTINCT loc.loc_id AS idLocalidad,"
				+ " si.sit_capacidad         AS capacidad,"
				+ " fe.fun_id                AS numeroFuncion,"
				+ " esp.esp_nombre           AS nombreEspectaculo,"
				+ " loc.loc_precio           AS precio,"
				+ " comp.com_nombre          AS nombrecompania "
				+ "FROM ISIS2304A131720.SITIO si,"
				+ " ISIS2304A131720.FUNCION fe,"
				+ " ISIS2304A131720.ESPECTACULO esp,"
				+ " ISIS2304A131720.SITIO_LOCALIDAD sl,"
				+ " ISIS2304A131720.LOCALIDAD loc,"
				+ " ISIS2304A131720.COMPANIA comp,"
				+ " ISIS2304A131720.ESPECTACULO_COMPANIA esc "
				+ "WHERE fe.ESP_ID = esp.ESP_ID "
				+ "AND fe.SIT_ID   = si.SIT_ID "
				+ "AND si.SIT_ID   = sl.SIT_ID "
				+ "AND esc.COM_ID  = comp.COM_ID "
				+ "AND esc.ESP_ID  = esp.ESP_ID "
				+ "AND comp.COM_ID = " + numCompania;

		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		Integer vendidasTotal = null;
		
		while (rs.next()) {
 
			Integer capacidad = rs.getInt("capacidad");
			Integer numeroFuncion = rs.getInt("numeroFuncion"); 
			String nombreEspectaculo = rs.getString("nombreEspectaculo"); 
			String nombreCompania = rs.getString("nombrecompania"); 
			Integer idLocalidad = rs.getInt("idLocalidad"); 
			Integer precio = rs.getInt("precio"); 
			

			String sq = "SELECT COUNT (*) as cuenta FROM ISIS2304A131720.SILLA_PAGADA WHERE USU_ID IS NOT NULL AND LOC_ID ="+idLocalidad + " AND FUN_ID = "+ numeroFuncion;
			System.out.println(sq);
			PreparedStatement prepStmtcap123 = conn.prepareStatement(sq);
			recursos.add(prepStmtcap123);
			ResultSet rscap123 = prepStmtcap123.executeQuery();

			Integer vendidasClientes = 0;
			
			while(rscap123.next())
			{
				vendidasClientes = rscap123.getInt("cuenta");
			}
			
			Double ganancia = (double) (precio*vendidasClientes);

			Double ocupacion = (double) ((vendidasClientes*100)/capacidad);
			

			lista.add(new Compania(nombreCompania, vendidasClientes, nombreEspectaculo, numeroFuncion, ocupacion, ganancia));
		}

		return lista;
		
	}

}
