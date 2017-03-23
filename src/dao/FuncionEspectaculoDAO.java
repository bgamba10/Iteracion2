package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.FuncionDetail;
import vos.FuncionEspectaculoR;
import vos.SitioFuncion;


public class FuncionEspectaculoDAO {

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
	public FuncionEspectaculoDAO() {
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

	public ArrayList<FuncionEspectaculoR> getFuncionEspectaculo(FuncionEspectaculoR fe) throws SQLException 
	{
		ArrayList<FuncionEspectaculoR> lista = new ArrayList<FuncionEspectaculoR>();

		Integer disponibilidad = fe.getDisponibilidad();
		Integer numeroFuncion = fe.getNumeroFuncion();
		Date fechaInicial = fe.getFechaInicial();
		Date fechaFinal = fe.getFechaFinal();
		String compania = fe.getCompania();
		String categoria = fe.getCategoria();
		String idioma = fe.getIdioma();
		String restriccion = fe.getRestriccion();
		String espectaculo = fe.getEspectaculo();
		Date fechaFuncion = fe.getFechaFuncion();

		String sql = "SELECT fun.FUN_ID AS numerofuncion, "
				+ "comp.COM_NOMBRE AS compania,"
				+ "fun.fun_fecha   AS fechafuncion,"
				+ "nec.nec_nombre  AS restriccion,"
				+ "esp.esp_nombre  AS nombreEspectaculo,"
				+ "idi.IDI_NOMBRE  AS idioma,"
				+ "LISTAGG(cat.CON_NOMBRE, ', ') within group (order by cat.CON_NOMBRE) OVER (PARTITION BY fun.fun_id) AS categoria,"
				+ "loc.loc_disponibles AS disponibles "
				+ "FROM ISIS2304A131720.COMPANIA comp,"
				+ "ISIS2304A131720.ESPECTACULO_COMPANIA escomp,"
				+ "ISIS2304A131720.CATEGORIA cat,"
				+ "ISIS2304A131720.LOCALIDAD loc,"
				+ "ISIS2304A131720.ESPECTACULO_CATEGORIA esc,"
				+ "ISIS2304A131720.ESPECTACULO esp,"
				+ "ISIS2304A131720.IDIOMA idi,"
				+ "ISIS2304A131720.NECESIDAD nec,"
				+ "ISIS2304A131720.SITIO sit,"
				+ "ISIS2304A131720.sitio_necesidad sn,"
				+ "ISIS2304A131720.sitio_localidad sl,"
				+ "ISIS2304A131720.funcion fun "
				+ "WHERE comp.COM_ID = escomp.COM_ID "
				+ "AND escomp.ESP_ID = esp.ESP_ID "
				+ "AND cat.CAT_ID    = esc.CAT_ID "
				+ "AND esc.ESP_ID    = esp.ESP_ID "
				+ "AND esp.esp_id    = fun.esp_id "
				+ "AND esp.IDI_ID    = idi.IDI_ID "
				+ "AND nec.nec_id    = sn.nec_id "
				+ "AND fun.sit_id    = sit.SIT_ID "
				+ "AND sn.SIT_ID     = sit.SIT_ID "
				+ "AND loc.LOC_ID    = sl.LOC_ID "
				+ "AND sl.SIT_ID     = sit.SIT_ID ";

		if(disponibilidad != null && disponibilidad != 0)
		{
			sql += "AND loc.LOC_DISPONIBLES > " +  disponibilidad;
		}
		if(numeroFuncion != null  && numeroFuncion != 0)
		{
			sql += "AND fun.FUN_ID = " + numeroFuncion;
		}
		if(fechaInicial != null && fechaFinal != null)
		{
			sql += "AND fun.FUN_FECHA BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "'";
		}
		if(categoria != null)
		{
			sql += "AND comp.COM_NOMBRE = '" + compania + "'";
		}
		if(idioma != null)
		{
			sql += "AND idi.IDI_NOMBRE = '" + idioma + "'";
		}
		if(restriccion != null)	
		{
			sql += "AND nec.nec_nombre = '" + restriccion + "'";
		}
		if(espectaculo != null)
		{
			sql += "AND esp.esp_nombre = '" + espectaculo + "'";
		}
		if(fechaFuncion != null)
		{
			sql += "AND fun.fun_fecha = '" + fechaFuncion + "'";
		}


		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while (rs.next()) 
		{

			int numeroFuncion1 = rs.getInt("NUMEROFUNCION");
			String compania1 = rs.getString("COMPANIA");
			Date fechaFuncion1 = rs.getDate("FECHAFUNCION");
			String restriccion1 = rs.getString("RESTRICCION");
			String nombreEspectaculo1 = rs.getString("NOMBREESPECTACULO");
			String idioma1 = rs.getString("IDIOMA");
			String categoria1 = rs.getString("CATEGORIA");
			int disponibles1 = rs.getInt("DISPONIBLES");

			lista.add(new FuncionEspectaculoR(fechaInicial, fechaFinal, compania1, categoria1, idioma1, disponibles1, restriccion1, numeroFuncion1, nombreEspectaculo1, fechaFuncion1));


		}

		return lista;
	}
	
	public ArrayList<SitioFuncion> reporte(String nombreEspectaculo) throws SQLException
	{
		ArrayList<SitioFuncion> lista = new ArrayList<>(); 
		String sql ="select sit.SIT_NOMBRE as nombreSitio, sit.sit_id as numSitio, sit.sit_capacidad numCapacidad"
		+ " from ISIS2304A131720.SITIO sit, ISIS2304A131720.funcion fun, ISIS2304A131720.espectaculo esp"
		+ " where sit.sit_id = fun.SIT_ID AND fun.ESP_ID = esp.esp_id AND esp.esp_nombre = '"+nombreEspectaculo+"'";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next())
		{
			String nombreSitio = rs.getString("nombreSitio"); 
			Integer numSitio = rs.getInt("numSitio"); 
			Integer capacidad = rs.getInt("numCapacidad"); 
			
			List<FuncionDetail> funciones = new ArrayList<FuncionDetail>(); 
			
			String sql1 ="select fun.FUN_ID as numFuncion"
					+ " from ISIS2304A131720.ESPECTACULO esp, ISIS2304A131720.FUNCION fun"
					+ " where esp.esp_id = fun.esp_id AND esp.ESP_NOMBRE = '"+nombreEspectaculo+"' AND fun.SIT_ID ="+numSitio;
			System.out.println("SQL stmt:" + sql1);
			
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			
			while (rs1.next())
			{
				Integer numFuncion = rs1.getInt("numFuncion");
				String sql2 = "SELECT COUNT (*) as cuenta FROM ISIS2304A131720.SILLA_PAGADA WHERE FUN_ID = "+ numFuncion;
				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				Integer cuenta = 0; 
				while (rs2.next())
				{
				cuenta = rs2.getInt("cuenta");
				}
			
				Double porcentajeOcupacion = (double) ((cuenta/capacidad)*100);
				funciones.add(new FuncionDetail(numFuncion, cuenta, porcentajeOcupacion));
			}
			lista.add(new SitioFuncion(nombreSitio, funciones));
		}
		
		
		return lista; 
	}

}
