/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			// insert.setLong(1, salvarProduto.getIdProduto());
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

	public List<ProdutoBean> listarProduto() throws Exception {
		List<ProdutoBean> listarProduto = new ArrayList<ProdutoBean>();

		String sql = "select * from produto";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			ProdutoBean produtoLista = new ProdutoBean();
			produtoLista.setIdProduto(resultSet.getLong("idProduto"));
			produtoLista.setNomeProduto(resultSet.getString("nomeProduto"));
			produtoLista.setQuantidadeProduto(resultSet.getDouble("quantidadeProduto"));
			produtoLista.setValorProduto(resultSet.getDouble("valorProduto"));
			listarProduto.add(produtoLista);
		}

		return listarProduto;
	}

	public void deleteProduto(String idProduto) {

		try {
			String sql = "delete from produto where idProduto = '" + idProduto + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

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

	public ProdutoBean consultarProduto(String idProduto) throws Exception {
		String sql = "select * from produto where idProduto='" + idProduto + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			ProdutoBean produto = new ProdutoBean();
			produto.setIdProduto(resultSet.getLong("idProduto"));
			produto.setNomeProduto(resultSet.getString("nomeProduto"));
			produto.setQuantidadeProduto(resultSet.getDouble("quantidadeProduto"));
			produto.setValorProduto(resultSet.getDouble("valorProduto"));
			return produto;
		}

		return null;
	}

	public boolean validarNome(String nomeProduto) throws Exception {
		String sql = "select count(1) as qtd from produto where nomeProduto='" + nomeProduto + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Return true */
		}

		return false;
	}

	public void atualizarProduto(ProdutoBean produtoBean) {
		try {
			String sql = "update produto set nomeProduto = ?, quantidadeProduto = ?, valorProduto = ?  where idProduto = "
					+ produtoBean.getIdProduto();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produtoBean.getNomeProduto());
			preparedStatement.setDouble(2, produtoBean.getQuantidadeProduto());
			preparedStatement.setDouble(3, produtoBean.getValorProduto());
			preparedStatement.executeUpdate();
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

}
