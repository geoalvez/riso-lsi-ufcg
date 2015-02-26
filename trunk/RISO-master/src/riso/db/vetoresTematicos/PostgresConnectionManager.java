package riso.db.vetoresTematicos;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.pool.impl.GenericObjectPool;


public class PostgresConnectionManager {
	
	private static PostgresConnectionManager INSTANCE;
	
	//private static final String URL_DEFAULT = "jdbc:postgresql://localhost:5432/jwnl";
	private static final String URL_DEFAULT = "jdbc:postgresql://localhost:5432/postgres";
	
	private static final String USERNAME = "postgres";
	
	private static final String PASSWORD = "Patchanka777";

	private ConnectionManager connectionManager;	

	private PostgresConnectionManager() throws Exception {
		connectionManager = new ConnectionManager("riso", "org.postgresql.Driver", URL_DEFAULT, USERNAME, PASSWORD,
				"SELECT 1+1;", 8, GenericObjectPool.WHEN_EXHAUSTED_GROW);
	}
	
	/**
	 * Get an instance of the MySQLDBConnection class.
	 * @return An instance of the MySQLDBConnection class.
	 * @throws SQLException An error in the connection
	 * @throws DBConnectionException Not established connection.
	 */
	public static PostgresConnectionManager getInstance() throws Exception {
		if (INSTANCE == null) {
			INSTANCE = new PostgresConnectionManager();
		}
		
		return INSTANCE;
	}
	
	/**
	 * Gets the connection with the database
	 * @return The connection with the database
	 * @throws DBConnectionException  Not established connection.
	 */
	public Connection getConnection() throws Exception {
		return connectionManager.getConnection();
	}
}