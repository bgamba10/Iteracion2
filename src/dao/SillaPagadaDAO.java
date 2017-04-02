package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

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

			numEspectaculo = (Integer) numEsp;

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
				throw new Exception("no existe esa localidad"); 
				
			}
			else 
			{
				numEspectaculo = (Integer) numEsp;

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
						throw new Exception("No existe esa función o espectaculo");
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

							String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ null + "', "+ null + ", " + fechaA + ", null, "+ LOCID+ ", " + funID + ")";
							
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
								
								
								String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ fila + "', "+ columna + ", " + fechaA + ", null, "+ LOCID+ ", " + funID + ")"; 

								System.out.println("SQL stmt:" + sql);
								PreparedStatement prepStmt = conn.prepareStatement(sql);
								recursos.add(prepStmt);
								prepStmt.executeQuery();

								//PAG ID ? 


							}
							else 
							{
								System.out.println("Sorry that seat has been taken, better luck next time");
								throw new Exception("Sorry that seat has been taken, better luck next time"); 
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
						throw new Exception("Usuario y/o contrasena invalidos, o no es cliente...");
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
					}
					
					
					else 
					{
						//REVISAR AMBOS CASOS TIENE UNA SILLA O SOLO UNA LOCALIDAD

						//caso solo hay localidad 
						if (columna == null || columna == 0)
						{
							System.out.println("no se ha pagado la sila!!! congrats");

							java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
							String fechaA = "to_date('"+ fechaActual +"','YYYY-MM-DD')";

							String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ null + "', "+ null + ", " + fechaA + ", " + usuID + ", "+ LOCID+ ", " + funID + ")";
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
								
								
								String sql = "INSERT INTO ISIS2304A131720.SILLA_PAGADA (PAG_ID, PAG_FILA, PAG_COLUMNA, PAG_FECHA_PAGO, USU_ID, LOC_ID, FUN_ID) VALUES ( SQ_SILLA_PAGADA.NEXTVAL, '"+ fila + "', "+ columna + ", " + fechaA + ","+ usuID + ", "+ LOCID+ ", " + funID + ")";
								
								System.out.println("SQL stmt:" + sql);
								PreparedStatement prepStmt = conn.prepareStatement(sql);
								recursos.add(prepStmt);
								prepStmt.executeQuery();

								//PAG ID ? 

							}
							else 
							{
								System.out.println("Sorry that seat has been taken, better luck next time");
								throw new Exception("Sorry that seat has been taken, better luck next time");
							}
							
							
							
						}
					}
				}
			}
		}
		else 
		{
			System.out.println("No hay cupo disponible");
			throw new Exception("No hay cupo disponible"); 
		}
		
	}
}