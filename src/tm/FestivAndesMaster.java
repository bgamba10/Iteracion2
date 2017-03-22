package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import dao.FuncionDAO;
import dao.FuncionEspectaculoDAO;
import dao.SillaPagadaDAO;
import dao.SitioDAO;
import dao.UsuarioPreferenciaDAO;
import vos.Funcion;
import vos.FuncionEspectaculo;
import vos.ListaFuncionEspectaculo;
import vos.ListaSitios;
import vos.ListaVideos;
import vos.SillaPagada;
import vos.Sitio;
import vos.UsuarioPreferencia;

public class FestivAndesMaster {

	/**
	 * Atributo est�tico que contiene el path relativo del archivo que tiene los datos de la conexi�n
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo est�tico que contiene el path absoluto del archivo que tiene los datos de la conexi�n
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Conexi�n a la base de datos
	 */
	private Connection conn;


	/**
	 * M�todo constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logia de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesMaster, se inicializa el path absoluto de el archivo de conexi�n y se
	 * inicializa los atributos que se usan par la conexi�n a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public FestivAndesMaster(String contextPathP) {
		// TODO Auto-generated constructor stub
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/*
	 * M�todo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexi�n a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que  retorna la conexi�n a la base de datos
	 * @return Connection - la conexi�n a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexi�n a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////



	public void cambiarPreferencia(UsuarioPreferencia up) {
		// TODO Auto-generated method stub
		// crear nuevo y despues hacerle el update 
		UsuarioPreferenciaDAO dao = new UsuarioPreferenciaDAO();
		try {

			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConn(conn);
			dao.updateUsuarioPreferencia(up);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void borrarPreferencia(UsuarioPreferencia up) {
		// TODO Auto-generated method stub
		UsuarioPreferenciaDAO dao = new UsuarioPreferenciaDAO();
		try {
			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConn(conn);
			dao.deleteUsuarioPreferencia(up);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearPreferencia(UsuarioPreferencia up) {
		
		UsuarioPreferenciaDAO dao = new UsuarioPreferenciaDAO();
		try {

			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConn(conn);
			dao.addUsuarioPreferencia(up);
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarSillaPagada(SillaPagada up) 
	{
		SillaPagadaDAO dao = new SillaPagadaDAO();

		try 
		{
			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConn(conn);
			dao.agregarSillaPagada(up);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void registrarRealizacion(Funcion funcion) 
	{
		FuncionDAO dao= new FuncionDAO();
		try 
		{
			this.conn = darConexion();
			dao.setConn(conn);
			dao.updateFuncion(funcion);
			conn.commit(); 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
	}
	
	public ListaFuncionEspectaculo funcionesEspectaculo(FuncionEspectaculo fe) throws SQLException {
		
		ArrayList<FuncionEspectaculo> lista; 
		
		FuncionEspectaculoDAO dao = new FuncionEspectaculoDAO(); 
		
		this.conn = darConexion();
		dao.setConn(conn);
		lista = dao.getFuncionEspectaculo(fe); 
		conn.commit(); 
		
		ListaFuncionEspectaculo listaReturn = new ListaFuncionEspectaculo(lista);
		
		return listaReturn;
}
	
	public ListaSitios consultarSitios(String nombre, String criterio, String orden) throws SQLException 
	{
		// TODO Auto-generated method stub
		ArrayList<Sitio> lista = null; 
		SitioDAO dao = new SitioDAO(); 
		
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			lista = dao.consultarSitio(nombre, criterio, orden);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaSitios(lista, nombre);
	}

}