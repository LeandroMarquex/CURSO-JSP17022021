/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.ProdutoBean;
import connection.SingleConnection;

/**
 * @author - Leandro MARQUES
 *
 * 
 */
public class DaoProduto {
	
	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvarProoduto(ProdutoBean salvarProduto) {
		try {
			
			String sql = "insert into produto(nomeProduto, quantidadeProduto, valorProduto) values (?, ?, ?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
	//		insert.setLong(1, salvarProduto.getIdProduto());
			insert.setString(1, salvarProduto.getNomeProduto());
			insert.setDouble(2, salvarProduto.getQuantidadeProduto());
			insert.setDouble(3, salvarProduto.getValorProduto());
			
			insert.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void deleteProduto(String user) {
		// TODO Auto-generated method stub
		
	}

	public Object listarProduto() {
		// TODO Auto-generated method stub
		return null;
	}

}
