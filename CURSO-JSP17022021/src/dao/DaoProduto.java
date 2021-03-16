/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
			
			String sql = "insert into produto(idProduto, nomeProduto, quantidadeProduto, valorProduto)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, salvarProduto.getIdProduto());
			insert.setString(2, salvarProduto.getNomeProduto());
			insert.setDouble(3, salvarProduto.getQuantidadeProduto());
			insert.setDouble(4, salvarProduto.getValorProduto());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}

}
