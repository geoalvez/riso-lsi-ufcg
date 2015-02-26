package riso.db.vetoresTematicos;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jena.VetorTematico;

public class VetorTematicoDAO {


	public List<VetorTematico> obtemVetoresTematicos(String termo){
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<VetorTematico> vetores = new ArrayList<VetorTematico>();  
		try {
			con = PostgresConnectionManager.getInstance().getConnection();
			stm = con.prepareStatement("SELECT vetoresTematicos FROM termoEnriquecido WHERE termo = '"+termo+"'");
			rs = stm.executeQuery();  
			while(rs.next()){
				String vet = URLDecoder.decode(rs.getString("vetoresTematicos"),"UTF-8");
				VetorTematico vetor = new VetorTematico(vet,termo);  
				vetores.add(vetor);  
			}  
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {

			DBConexion.closeResult(rs);  
			DBConexion.closeStatement(stm);  
			DBConexion.closeConnection(con);
		}
		
		return vetores;  
	}  

	

	public void salvarVetores(List<VetorTematico> vetoresTematicos){  
		Statement stm = null;
		try{
			stm = PostgresConnectionManager.getInstance().getConnection().createStatement();
			Iterator<VetorTematico> it = vetoresTematicos.iterator();
			
			while(it.hasNext()){  
				VetorTematico vetorDaVez = it.next();
				String conceito = vetorDaVez.getConceito();
				String vetor = vetorDaVez.toString();
				vetor = URLEncoder.encode(vetor,"UTF-8");
				String query = "INSERT INTO termoEnriquecido(termo,vetorestematicos) VALUES ('"+conceito+"','"+vetor+"')"; 
				stm.addBatch(query); 
			}  
			stm.executeBatch(); 

		}catch(Exception e){
		  e.printStackTrace();
		  System.exit(1);
		}finally{
			DBConexion.closeStatement(stm);  
		}
	}   

}
