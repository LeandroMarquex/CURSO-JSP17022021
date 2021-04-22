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
			String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype, contentTypeCurriculo, curriculoBase64, fotoBase64Miniatura ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, salvarUsuario.getLogin());
			insert.setString(2, salvarUsuario.getSenha());
			insert.setString(3, salvarUsuario.getNomeUsuario());
			insert.setString(4, salvarUsuario.getTelefoneUsuario());
			insert.setString(5, salvarUsuario.getCepUsuario());
			insert.setString(6, salvarUsuario.getRuaUsuario());
			insert.setString(7, salvarUsuario.getBairroUsuario());
			insert.setString(8, salvarUsuario.getCidadeUsuario());
			insert.setString(9, salvarUsuario.getEstadoUsuario());
			insert.setString(10, salvarUsuario.getIbgeUsuario());
			insert.setString(11, salvarUsuario.getFotoBase64());
			insert.setString(12, salvarUsuario.getContentType());
			insert.setString(13, salvarUsuario.getContentTypeCurriculo());
			insert.setString(14, salvarUsuario.getCurriculoBase64());
			insert.setString(15, salvarUsuario.getFotoBase64Miniatura());
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
			
			beanCursoJsp.setCepUsuario(resultSet.getString("cep"));
			beanCursoJsp.setRuaUsuario(resultSet.getString("rua"));
			beanCursoJsp.setBairroUsuario(resultSet.getString("bairro"));
			beanCursoJsp.setCidadeUsuario(resultSet.getString("cidade"));
			beanCursoJsp.setEstadoUsuario(resultSet.getString("estado"));
			beanCursoJsp.setIbgeUsuario(resultSet.getString("ibge"));
			
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
		//	beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contentTypeCurriculo"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			
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
			
			beanCursoJsp.setCepUsuario(resultSet.getString("cep"));
			beanCursoJsp.setRuaUsuario(resultSet.getString("rua"));
			beanCursoJsp.setBairroUsuario(resultSet.getString("bairro"));
			beanCursoJsp.setCidadeUsuario(resultSet.getString("cidade"));
			beanCursoJsp.setEstadoUsuario(resultSet.getString("estado"));
			beanCursoJsp.setIbgeUsuario(resultSet.getString("ibge"));
			

			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
		//	beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));
		
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contentTypeCurriculo"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			
			return beanCursoJsp;
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizarUsuario(BeanCursoJsp atualizaUsuario) {
		// TODO Auto-generated method stub
		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?,"
					+ "cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, "
					+ " fotobase64 = ?, contenttype  = ?, contentTypeCurriculo  = ?, curriculoBase64  = ?, fotoBase64Miniatura = ? "
					+ " where id = " + atualizaUsuario.getIdUsuario();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, atualizaUsuario.getLogin());
			preparedStatement.setString(2, atualizaUsuario.getSenha());
			preparedStatement.setString(3, atualizaUsuario.getNomeUsuario());
			preparedStatement.setString(4, atualizaUsuario.getTelefoneUsuario());
			
			preparedStatement.setString(5, atualizaUsuario.getCepUsuario());
			preparedStatement.setString(6, atualizaUsuario.getRuaUsuario());
			preparedStatement.setString(7, atualizaUsuario.getBairroUsuario());
			preparedStatement.setString(8, atualizaUsuario.getCidadeUsuario());
			preparedStatement.setString(9, atualizaUsuario.getEstadoUsuario());
			preparedStatement.setString(10, atualizaUsuario.getIbgeUsuario());
			preparedStatement.setString(11, atualizaUsuario.getFotoBase64());
			preparedStatement.setString(12, atualizaUsuario.getContentType());
			preparedStatement.setString(13, atualizaUsuario.getCurriculoBase64());
			preparedStatement.setString(14, atualizaUsuario.getContentTypeCurriculo());
			preparedStatement.setString(15, atualizaUsuario.getFotoBase64Miniatura());
		
		
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
