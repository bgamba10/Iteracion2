package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Sitio;


public class SitioDAO {

	/**
	 * Arraylits de recursos que se usan para la ejecuci�n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexi�n a la base de datos
	 */
	private Connection conn;

	/**
	 * M�todo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public SitioDAO() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * M�todo que cierra todos los recursos que estan enel arreglo de recursos
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
	 * M�todo que inicializa la connection del DAO a la base de datos con la conexi�n que entra como par�metro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	public ArrayList<Sitio> consultarSitio(String nombre, String criterio, String orden) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Sitio> lista = new ArrayList<Sitio>(); 

		String sql = "SELECT ci.ciu_nombre as nombreCiudad, si.sit_nombre as nombresitio, si.sit_abierto as abierto, si.sit_capacidad as capacidad, s.sil_nombre as tipoSilla, fe.fun_fecha as fechaFuncion, fe.fun_id as numeroFuncion, esp.esp_nombre as nombreEspectaculo, loc.loc_nombre as nombreLocalidad,loc.loc_id as idLocalidad"
				+ " FROM ISIS2304A131720.SITIO si,"
				+ " ISIS2304A131720.CIUDAD ci,"
				+ " ISIS2304A131720.TIPO_SILLA s,"
				+ " ISIS2304A131720.SITIO_NECESIDAD sn,"
				+ " ISIS2304A131720.NECESIDAD ne,"
				+ " ISIS2304A131720.FUNCION fe,"
				+ " ISIS2304A131720.ESPECTACULO esp,"
				+ " ISIS2304A131720.SITIO_LOCALIDAD sl,"
				+ " ISIS2304A131720.LOCALIDAD loc"
				+ " WHERE si.sil_id = s.sil_id"
				+ " AND si.sit_id   =sn.sit_id"
				+ " AND sn.nec_id   =ne.nec_id"
				+ " AND fe.ESP_ID    = esp.ESP_ID"
				+ " AND fe.SIT_ID    =si.SIT_ID"
				+ " AND si.SIT_ID   =sl.SIT_ID"

				+ " AND ci.CIU_ID   =si.CIU_ID AND si.sit_nombre='"+nombre+"'"
				+ " order by "+criterio+","+orden;

		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while (rs.next()) {

			String nombreCiudad = rs.getString("nombreCiudad");
			// String nombreSitio = rs.getString("nombreSitio");
			Integer abiertoNum = rs.getInt("abierto"); 
			Integer capacidad = rs.getInt("capacidad");
			String tipoSilla = rs.getString("tipoSilla"); 
			String fechaFuncion = rs.getString("fechaFuncion");
			Integer numeroFuncion = rs.getInt("numeroFuncion"); 
			String nombreEspectaculo = rs.getString("nombreEspectaculo"); 
			String nombreLocalidad = rs.getString("nombreLocalidad"); 
			Integer idLocalidad = rs.getInt("idLocalidad"); 

			String sq = "SELECT COUNT (*) as cuenta FROM ISIS2304A131720.SILLA_PAGADA WHERE LOC_ID ="+idLocalidad + "AND FUN_ID = "+ numeroFuncion;
			System.out.println(sq);
			PreparedStatement prepStmtcap123 = conn.prepareStatement(sq);
			recursos.add(prepStmtcap123);
			ResultSet rscap123 = prepStmtcap123.executeQuery();

			Integer vendidas=0;
			while(rscap123.next())
			{
				vendidas = rscap123.getInt("cuenta");
			}

			Integer cuposDisponibles = capacidad - vendidas;
			System.out.println("cupos..." +cuposDisponibles);

			Boolean abierto = false; 
			if (abiertoNum == 1) abierto = true; 

			lista.add(new Sitio(nombreCiudad, abierto, capacidad, tipoSilla, fechaFuncion, numeroFuncion, nombreEspectaculo, nombreLocalidad, cuposDisponibles));
		}

		return lista;
	}

}