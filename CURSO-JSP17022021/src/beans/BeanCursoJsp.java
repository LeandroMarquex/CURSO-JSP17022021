/**
 * 
 */
package beans;

/**
 * @author - Leandro MARQUES
 *
 * 
 */
public class BeanCursoJsp {
	
	private Long idUsuario;
	private String login;
	private String senha;
	private String nomeUsuario;
	private String telefoneUsuario;
	
	public boolean validarLoginSenha(String login, String senha) {
		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
			return true;
			
		} else {
			return false;

		}
	}
	
	

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}



	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}



	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	

}
