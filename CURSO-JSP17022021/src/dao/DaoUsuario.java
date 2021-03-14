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

import beans.BeanCursoJsp;
import connection.SingleConnection;

/**
 * @author - Leandro MARQUES
 *
 * 
 */
public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvarUsuario(BeanCursoJsp salvarUsuario) {
		try {
			String sql = "insert into usuario(login, senha, nome, telefone) values (?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, salvarUsuario.getLogin());
			insert.setString(2, salvarUsuario.getSenha());
			insert.setString(3, salvarUsuario.getNomeUsuario());
			insert.setString(4, salvarUsuario.getTelefoneUsuario());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanCursoJsp> listarUsuario() throws Exception {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		
		String sql = "select * from usuario";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setIdUsuario(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNomeUsuario(resultSet.getString("nome"));
			beanCursoJsp.setTelefoneUsuario(resultSet.getString("telefone"));
			
			listar.add(beanCursoJsp);
			
		}
		return listar;
	}
	public void deleteUsuario (String idUsuario) {
		try {
			String sql = "delete from usuario where id = '" + idUsuario + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultarUsuario(String idUsuario) throws Exception{
		String sql = "select * from usuario where id = '" + idUsuario + "'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setIdUsuario(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNomeUsuario(resultSet.getString("nome"));
			beanCursoJsp.setTelefoneUsuario(resultSet.getString("telefone"));
			
			return beanCursoJsp;
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizarUsuario(BeanCursoJsp atualizaUsuario) {
		// TODO Auto-generated method stub
		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ? where id = " + atualizaUsuario.getIdUsuario();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, atualizaUsuario.getLogin());
			preparedStatement.setString(2, atualizaUsuario.getSenha());
			preparedStatement.setString(3, atualizaUsuario.getNomeUsuario());
			preparedStatement.setString(4, atualizaUsuario.getTelefoneUsuario());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
				
			}
		}
		
		
	}
	public boolean validarLoginUpdate(String login, String id) throws Exception{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			
			
			return resultSet.getInt("qtd") <= 0;
			
		}
		// TODO Auto-generated method stub
		return false;
	}
	public boolean validarSenhaUpdate(String senha, String id) throws Exception{
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <> " + id;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			
			
			return resultSet.getInt("qtd") <= 0;
			
		}
		// TODO Auto-generated method stub
		return false;
	}
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			
			
			return resultSet.getInt("qtd") <= 0;
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validarSenha(String senha) throws Exception {
		
		String sql = "select count(1) as qtd from usuario where senha= '" + senha + "'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
