package riso.db.vetoresTematicos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;


public class ConnectionManager {
	
	private String id;

	public ConnectionManager(String id, String driverClass, String url, String username, String password, 
			String validationQuery, int maxActive, byte whenExaustedAction) throws Exception {
		try {
			this.id = id;
			Class.forName(driverClass);
			
			ObjectPool connectionPool = new GenericObjectPool(
					null, maxActive, whenExaustedAction, GenericObjectPool.DEFAULT_MAX_WAIT, true, true);
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, username, password);
			PoolableConnectionFactory poolableConnectionFactory = 
				new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);
			poolableConnectionFactory.setValidationQuery(validationQuery);
			
			
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:"); 
			driver.registerPool(id, connectionPool);
		} catch (ClassNotFoundException e) {
			throw new Exception("Unable to connect to the database.", e);
		} catch (SQLException e) {
			throw new Exception("Unable to connect to the database.", e);
		}
	}
	
	public Connection getConnection() throws Exception {
		try {
			return DriverManager.getConnection("jdbc:apache:commons:dbcp:" + id);
		} catch (SQLException e) {
			throw new Exception("Unable to connect to the database.", e);
		}
	}
}