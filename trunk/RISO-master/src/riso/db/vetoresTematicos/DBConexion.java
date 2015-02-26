package riso.db.vetoresTematicos;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConexion {

	private final static DBConexion instance = new DBConexion();
	private ComboPooledDataSource cpds;

	private DBConexion() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("org.postgresql.Driver");
			//cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/jwnl");
			cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
			cpds.setUser("postgres");
			cpds.setPassword("Patchanka777");
			cpds.setCheckoutTimeout(2000);
			cpds.setIdleConnectionTestPeriod(3000);
			cpds.setMaxPoolSize(5);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection con) {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResult(ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement stm) {
		if(stm != null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() throws SQLException{
		return cpds.getConnection();
	}

	public static DBConexion getInstance() {
		return instance;
	}

}
