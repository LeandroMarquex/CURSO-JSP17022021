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

import beans.TelefoneBean;
import connection.SingleConnection;

/**
 * @author - Leandro MARQUES
 *
 * 
 */
public class DaoTelefone {

	private Connection connection;
	
	

	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void salvarTelefone(TelefoneBean salvarTelefone) {
		try {

			String sql = "insert into telefone(numerotelefone, tipotelefone, usuario) values (?, ?, ?)";

			PreparedStatement insert = connection.prepareStatement(sql);
			// insert.setLong(1, salvarTelefone.getIdtelefone());
			insert.setString(1, salvarTelefone.getNumeroTelefone());
			insert.setString(2, salvarTelefone.getTipoTelefone());
			insert.setLong(3, salvarTelefone.getUsuario());

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

	public List<TelefoneBean> listarTelefone(Long user) throws Exception {
		List<TelefoneBean> listarTelefone = new ArrayList<TelefoneBean>();

		String sql = "select * from telefone where usuario = " + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			TelefoneBean telefoneLista = new TelefoneBean();
			telefoneLista.setIdTelefone(resultSet.getLong("idtelefone"));
			telefoneLista.setNumeroTelefone(resultSet.getString("numerotelefone"));
			telefoneLista.setTipoTelefone(resultSet.getString("tipotelefone"));
			telefoneLista.setUsuario(resultSet.getLong("usuario"));
			listarTelefone.add(telefoneLista);
		}

		return listarTelefone;
	}

	public void deleteTelefone(String idTelefone) {

		try {
			String sql = "delete  from telefone where idtelefone = '" + idTelefone + "'";
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

}
