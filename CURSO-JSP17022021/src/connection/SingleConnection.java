/**
 * 
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author - Leandro MARQUES
 *
 * 
 */
public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "123";
	private static String user = "postgres";
	private static Connection connection = null;
	
	
	static {
		conectar();
	}
	public SingleConnection() {
		// TODO Auto-generated constructor stub
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao conectar com o BANCO DE DADOS");
		}
	}
	public static Connection getConnection() {
		return connection;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
