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

import vos.Abonamiento;
import vos.SillaPagada;

public class SillaPagadaDAO {

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
	public SillaPagadaDAO(){
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

	public Date darFechasFuncion(Integer numFuncion) throws SQLException
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

	public void agregarSillaPagada (SillaPagada silla) throws Exception{
		Integer cuantas = silla.getCuantas(); 
		if (silla.getCorreoElectronico() != null)
		{
			if (cuantas == null)
			{
				agregarSillaPagada1(silla);
			}

			else 
			{
				Integer colAntes = silla.getColumna(); 
				silla.setColumna(colAntes-1);

				for (int i=0; i<cuantas; i++)
				{
					SillaPagada s = silla; 
					Integer col = s.getColumna(); 
					s.setColumna(col +1);
					agregarSillaPagada1(s);
				}
			}
		}
	}

	public void agregarSillaPagada1(SillaPagada silla ) throws Exception
	{
		String fila = silla.getFila(); 
		Integer columna = silla.getColumna(); 
		String correo = silla.getCorreoElectronico(); 
		String contrasena = silla.getContrasena(); 
		Integer numFuncion = silla.getNumFuncion(); 
		String localidad = silla.getLocalidad(); 
		String espectaculo = silla.getEspectaculo(); 

		//capacidad

		String sqlcap = "SELECT LOC_ID FROM ISIS2304A131720.LOCALIDAD WHERE LOC_NOMBRE = '" + localidad + "'";

		PreparedStatement prepStmtcap = conn.prepareStatement(sqlcap);
		recursos.add(prepStmtcap);
		ResultSet rscap = prepStmtcap.executeQuery();

		int disponibles= 0;

		String LOCID = null;
		while(rscap.next()){
			LOCID = rscap.getString("LOC_ID");
		}

		String sq = "SELECT COUNT (*) as cuenta FROM ISIS2304A131720.SILLA_PAGADA WHERE LOC_ID ="+LOCID + "AND FUN_ID = "+ numFuncion;

		PreparedStatement prepStmtcap123 = conn.prepareStatement(sq);
		recursos.add(prepStmtcap123);
		ResultSet rscap123 = prepStmtcap123.executeQuery();

		Integer vendidas=0;
		while(rscap123.next())
		{
			vendidas = rscap123.getInt("cuenta");
		}

		String s1 = "SELECT loc_capacidad as cuenta FROM ISIS2304A131720.LOCALIDAD WHERE LOC_ID ="+ LOCID;

		PreparedStatement prepStmtcap1234 = conn.prepareStatement(s1);
		recursos.add(prepStmtcap1234);
		ResultSet rscap1234 = prepStmtcap1234.executeQuery();

		Integer capacidad = 0; 
		while(rscap1234.next())
		{
			capacidad = rscap1234.getInt("cuenta");
		}
		disponibles = capacidad - vendidas; 

		System.out.println(disponibles);

		if(disponibles > 0)
		{
			String sqle = "SELECT ESP_ID FROM ISIS2304A131720.ESPECTACULO WHERE ESP_NOMBRE = '" + espectaculo + "'";

			PreparedStatement prepStmtE = conn.prepareStatement(sqle);
			recursos.add(prepStmtE);
			ResultSet rsE = prepStmtE.executeQuery();

			Integer numEspectaculo = null;
			int numEsp = 0 ;

			while(rsE.next()){
				numEsp = (rsE.getInt("ESP_ID"));
			}

			numEspectaculo = numEsp;

			String sqle1 = "SELECT LOC_ID FROM ISIS2304A131720.LOCALIDAD WHERE LOC_NOMBRE = '" + localidad + "'";

			PreparedStatement prepStmtE1 = conn.prepareStatement(sqle1);
			recursos.add(prepStmtE1);
			ResultSet rsE1 = prepStmtE1.executeQuery();

			LOCID= null;


			while(rsE1.next()){
				LOCID = rsE1.getString("LOC_ID");
			}

			if (LOCID == null)
			{
				System.out.println("No existe esa localidad");
				throw new WebApplicationException();

			}
			else 
			{
				numEspectaculo = numEsp;

				if (correo == "" || correo == null)
				{
					System.out.println("Se hara el registro como público");

					String sql2 = "SELECT FUN_ID FROM ISIS2304A131720.FUNCION WHERE FUN_ID = "+ numFuncion + "AND ESP_ID = " +numEspectaculo ;

					PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
					recursos.add(prepStmt2);
					ResultSet rs2 = prepStmt2.executeQuery();

					String funID = null; 
					while (rs2.next())
					{
						funID = rs2.getString("FUN_ID"); 
					}
					if (funID == null)
					{
						System.out.println("No existe esa función o espectáculo"); 
						throw new WebApplicationException();
					}


					else 
					{
						//REVISAR AMBOS CASOS TIENE UNA SILLA O SOLO UNA LOCALIDAD

						//caso solo hay localidad 
						if (columna == null || columna == 0)
						{
							System.out.println("No se ha pagado la sila aún!!! Congrats!!!");

							java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
							String fechaA = "to_date('"+ fechaActual +"','YYYY-MM-DD')";

							String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID, PAG_ESTADO) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ null + "', "+ null + ", " + fechaA + ", null, "+ LOCID+ ", " + funID + ", 'VENDIDO')";

							System.out.println("SQL stmt:" + sql);
							PreparedStatement prepStmt = conn.prepareStatement(sql);
							recursos.add(prepStmt);
							prepStmt.executeQuery();

							//PAG ID ? 

						}
						// caso hay una silla
						else 
						{
							// valida que este la silla no este pagada.
							String sql3 = "SELECT PAG_ID FROM ISIS2304A131720.SILLA_PAGADA WHERE PAG_FILA = '" + fila + "' AND PAG_COLUMNA = " + columna + " AND FUN_ID = " + numFuncion;

							PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
							recursos.add(prepStmt3);
							ResultSet rs3 = prepStmt3.executeQuery();
							Integer pagID = null; 

							while(rs3.next()){
								pagID = rs3.getInt("PAG_ID");
							}

							if (pagID == null)
							{
								System.out.println("No se ha pagado la sila aún!!! Congrats!!!");


								java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
								String fechaA = "to_date('"+ fechaActual +"','YYYY-MM-DD')"; 


								String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID, PAG_ESTADO) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ fila + "', "+ columna + ", " + fechaA + ", null, "+ LOCID+ ", " + funID + ", 'VENDIDO')"; 

								System.out.println("SQL stmt:" + sql);
								PreparedStatement prepStmt = conn.prepareStatement(sql);
								recursos.add(prepStmt);
								prepStmt.executeQuery();

								//PAG ID ? 


							}
							else 
							{
								System.out.println("Sorry that seat has been taken, better luck next time");
								throw new WebApplicationException();
							}



						}
					}


				}
				else 
				{
					System.out.println("Se hara el registro como usuario con el correo: "+ correo );

					String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID ="+ 2;

					PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
					recursos.add(prepStmt1);
					ResultSet rs1 = prepStmt1.executeQuery();

					String usuID = null;

					while(rs1.next()){
						usuID = rs1.getString("USU_ID");
					}

					if(usuID == null)
					{
						System.out.println("Usuario y/o contrasena invalidos, o no es cliente...");
						throw new WebApplicationException();
					}

					String sql2 = "SELECT FUN_ID FROM ISIS2304A131720.FUNCION WHERE FUN_ID = "+ numFuncion + "AND ESP_ID = " +numEspectaculo ;

					PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
					recursos.add(prepStmt2);
					ResultSet rs2 = prepStmt2.executeQuery();

					String funID = null; 
					while (rs2.next())
					{
						funID = rs2.getString("FUN_ID"); 
					}
					if (funID == null)
					{
						System.out.println("No existe esa función o espectáculo"); 
						throw new WebApplicationException();
					}


					else 
					{
						//REVISAR AMBOS CASOS TIENE UNA SILLA O SOLO UNA LOCALIDAD

						//caso solo hay localidad 
						if (columna == null || columna == 0)
						{
							System.out.println("No se ha pagado la sila!!! congrats");

							java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
							String fechaA = "to_date('"+ fechaActual +"','YYYY-MM-DD')";

							String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID, PAG_ESTADO) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ null + "', "+ null + ", " + fechaA + ", " + usuID + ", "+ LOCID+ ", " + funID + ", 'VENDIDO')";
							System.out.println("SQL stmt:" + sql);
							PreparedStatement prepStmt = conn.prepareStatement(sql);
							recursos.add(prepStmt);
							prepStmt.executeQuery();

							//PAG ID ? 

						}
						// caso hay una silla
						else 
						{
							// valida que este la silla no este pagada.
							String sql3 = "SELECT PAG_ID FROM ISIS2304A131720.SILLA_PAGADA WHERE PAG_FILA = '" + fila + "' AND PAG_COLUMNA = " + columna + "";

							PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
							recursos.add(prepStmt3);
							ResultSet rs3 = prepStmt3.executeQuery();
							Integer pagID = null; 

							while(rs3.next()){
								pagID = rs3.getInt("PAG_ID");
							}

							if (pagID == null)
							{
								System.out.println("No se ha pagado la sila aún!!! Congrats!!!");


								java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
								String fechaA = "to_date('"+ fechaActual +"','YYYY-MM-DD')";


								String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID, PAG_ESTADO) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ fila + "', "+ columna + ", " + fechaA + ","+ usuID + ", "+ LOCID+ ", " + funID + ", 'VENDIDO')";

								System.out.println("SQL stmt:" + sql);
								PreparedStatement prepStmt = conn.prepareStatement(sql);
								recursos.add(prepStmt);
								prepStmt.executeQuery();

								//PAG ID ? 

							}
							else 
							{
								System.out.println("Sorry that seat has been taken, better luck next time");
								throw new WebApplicationException();
							}



						}
					}
				}
			}
		}
		else 
		{
			System.out.println("No hay cupo disponible");
			throw new WebApplicationException(); 
		}

	}

	public void eliminarSilla(SillaPagada up) throws Exception {
		// TODO Auto-generated method stub
		String correo = up.getCorreoElectronico(); 
		String contrasena = up.getContrasena(); 

		String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID ="+ 2;
		
		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();

		String usuID = null;

		while(rs1.next()){
			usuID = rs1.getString("USU_ID");
		}

		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos, o no es cliente...");

		}
		else
		{
			//Se valida que el usuario que quiere eliminar la silla sea el usuario de la silla 
			String sql = "select pag_id, fun_id from ISIS2304A131720.SILLA_PAGADA where usu_id = "+usuID+"and pag_id = "+ up.getSillaEliminar();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			Integer pagId = null; 
			Integer funId = null;
			
			while(rs.next())
			{
				pagId = rs.getInt("PAG_ID");
				funId = rs.getInt("FUN_ID");
			}

			if (pagId == null)
			{
				System.out.println("El usuario que quiere eliminar esa silla no la compró");
			}
			else
			{
				Date fechaActual = new Date(System.currentTimeMillis());

				LocalDate fa = fechaActual.toLocalDate();
				LocalDate ff = darFechasFuncion(funId).toLocalDate();

				LocalDate ffAntes = ff.minusDays(5);

				if(ffAntes.isBefore(fa))
				{
					System.out.println("Faltan menos de 5 días para la función "+ funId +", no es posible devolver la boleta.");
				}
				else
				{
					String sql2 = "UPDATE ISIS2304A131720.SILLA_PAGADA SET PAG_ESTADO = 'DEVUELTO' WHERE PAG_ID = "+up.getSillaEliminar();

					System.out.println("SQL stmt:" + sql2);
					PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
					recursos.add(prepStmt2);
					prepStmt2.executeQuery();
				}
			}
		}
		
		
	}

	public void comprarAbonamiento(Abonamiento abo) throws Exception 
	{

		List<SillaPagada> lista = abo.getSillas(); 
		
		for (SillaPagada sp : lista)
		{
			sp.setCorreoElectronico(abo.getCorreoElectronico());
			sp.setContrasena(abo.getContrasena());
			System.out.println(sp.getColumna());
			System.out.println(sp.getFila());


			Date fechaActual = new Date(System.currentTimeMillis());

			LocalDate fa = fechaActual.toLocalDate();
			LocalDate ff = darFechasFuncion(sp.getNumFuncion()).toLocalDate();


			LocalDate ffAntes = ff.minusWeeks(3);

			if(ffAntes.isBefore(fa))
			{
				System.out.println("No se puede realizar el abonamiento para la función "+ sp.getNumFuncion() +", esta acción sólo se puede hacer tres semanas antes de la fecha de la función" + ffAntes);
			}
			else
			{
				agregarSillaPagada(sp);

				String sql = "INSERT INTO ISIS2304A131720.ABONAMIENTO (ABO_ID, ABO_FECHA, ABO_ESTADO) VALUES ( SQ_ABONAMIENTO.NEXTVAL, to_date('"+ fa +"','YYYY-MM-DD'), 'VENDIDO')";

				System.out.println("SQL stmt:" + sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

				String sqlSQ = "SELECT LAST_NUMBER FROM SYS.ALL_SEQUENCES WHERE SEQUENCE_NAME = 'SQ_ABONAMIENTO'";

				System.out.println("SQL stmt:" + sqlSQ);
				PreparedStatement prepStmtSQ = conn.prepareStatement(sqlSQ);
				recursos.add(prepStmtSQ);
				ResultSet rs = prepStmtSQ.executeQuery();

				Integer aboId = null; 

				while(rs.next())
				{
					aboId = rs.getInt("LAST_NUMBER");
				}

				aboId = aboId - 1;

				String sqlSQ1 = "SELECT LAST_NUMBER FROM SYS.ALL_SEQUENCES WHERE SEQUENCE_NAME = 'SQ_SILLA_PAGADA'";

				System.out.println("SQL stmt:" + sqlSQ1);
				PreparedStatement prepStmtSQ1 = conn.prepareStatement(sqlSQ1);
				recursos.add(prepStmtSQ1);
				ResultSet rs1 = prepStmtSQ1.executeQuery();

				Integer pagId = null; 

				while(rs1.next())
				{
					pagId = rs1.getInt("LAST_NUMBER");
				}

				pagId = pagId - 1;

				System.out.println(pagId);

				String sql1 = "UPDATE ISIS2304A131720.SILLA_PAGADA SET ";
				sql1 += "ABO_ID=" + aboId;
				sql1 += " WHERE PAG_ID = " + pagId;

				System.out.println("SQL stmt:" + sql1);

				PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
				recursos.add(prepStmt1);
				prepStmt1.executeQuery();
			}
		}
	}

	public void eliminarAbonamiento(Abonamiento abo) throws Exception {
		// TODO Auto-generated method stub
		String correo = abo.getCorreoElectronico(); 
		String contrasena = abo.getContrasena(); 

		String sql1 = "SELECT USU_ID FROM ISIS2304A131720.USUARIO WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENA = '" + contrasena + "' AND ROL_ID ="+ 2;

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();

		String usuID = null;

		while(rs1.next()){
			usuID = rs1.getString("USU_ID");
		}

		if(usuID == null)
		{
			System.out.println("Usuario y/o contrasena invalidos, o no es cliente...");
			throw new Exception("Usuario y/o contrasena invalidos, o no es cliente...");
		}

		// Revisa cuales boletas tienen abo_id con el que se quiere borrar 

		String sql = "SELECT PAG_ID, FUN_ID FROM ISIS2304A131720.SILLA_PAGADA WHERE ABO_ID = " + abo.getEliminar();

		System.out.println("SQL stmt:" + sql);
		PreparedStatement prepStmt= conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while(rs.next())
		{


			SillaPagada sp = new SillaPagada(0, null, 0, correo, contrasena, null, null, rs.getInt("FUN_ID"), null, 0, rs.getInt("PAG_ID"));

			Date fechaActual = new Date(System.currentTimeMillis());

			LocalDate fa = fechaActual.toLocalDate();
			LocalDate ff = darFechasFuncion(sp.getNumFuncion()).toLocalDate();

			LocalDate ffAntes = ff.minusWeeks(3);

			if(ffAntes.isBefore(fa))
			{
				System.out.println("No se puede devolver el abonamiento para la función "+ sp.getNumFuncion() +", esta acción sólo se puede hacer tres semanas antes de la fecha de la función");
			}
			else
			{
				eliminarSilla(sp);

				String sql2 = "UPDATE ISIS2304A131720.ABONAMIENTO SET ABO_ESTADO = 'DEVUELTO' WHERE ABO_ID = " + abo.getEliminar();

				System.out.println("SQL stmt:" + sql2);
				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				prepStmt2.executeQuery();
			}

		}

	}

}