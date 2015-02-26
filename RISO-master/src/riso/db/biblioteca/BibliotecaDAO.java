package riso.db.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import riso.builder.documents.Documento;
import riso.db.vetoresTematicos.DBConexion;

public class BibliotecaDAO {


	private List<Documento> listaDeDocumentos;

	public List<Documento> getListaDeDocumentos() {
		return listaDeDocumentos;
	}

	public void setListaDeDocumentos(List<Documento> listaDeDocumentos) {
		this.listaDeDocumentos = listaDeDocumentos;
	}

	public BibliotecaDAO(List<Documento> listaDeDocumentos){

		setListaDeDocumentos(listaDeDocumentos);
	}

	public Documento obtemDocumentos(String nomeDocumento){  
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Documento doc = null;
		try {
			con = DBConexion.getInstance().getConnection();
			stm = con.prepareStatement("SELECT palavrasMarcadas, vetorParagrafo FROM documentos where nome = "+nomeDocumento);
			rs = stm.executeQuery();  
			doc = new Documento(rs.getString("palavrasMarcadas"),rs.getString("vetorParagrafo"),nomeDocumento);  
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConexion.closeResult(rs);  
			DBConexion.closeStatement(stm);  
			DBConexion.closeConnection(con);
		}

		return doc;  
	}  

	public boolean verificaSeDocumentoExiste(Documento doc){  
		
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			con = DBConexion.getInstance().getConnection();
			stm = con.prepareStatement("SELECT 1 FROM documentos WHERE nomearquivo = '"+doc.getNomeArquivo()+"'");
			rs = stm.executeQuery(); 
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void salvaDocumento(Documento doc){  

		Statement stm = null;
		String palavrasMarcadas = doc.getPalavrasMarcadas();
		String vetorParagrafo = doc.getVetorParagrafo();
		String nomeArquivo = doc.getNomeArquivo();
		try{
			stm = DBConexion.getInstance().getConnection().createStatement();
			String sql = "INSERT INTO documentos VALUES ('"+palavrasMarcadas+"','"+vetorParagrafo+"','"+nomeArquivo+"')";  
			stm.execute(sql);

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBConexion.closeStatement(stm);  
		}

	} 

}
