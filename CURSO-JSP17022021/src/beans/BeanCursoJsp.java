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
	private String cepUsuario;
	private String ruaUsuario;
	private String bairroUsuario;
	private String cidadeUsuario;
	private String estadoUsuario;
	private String ibgeUsuario;
	
	public boolean validarLoginSenha(String login, String senha) {
		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
			return true;
			
		} else {
			return false;

		}
	}
	
	

	public String getCepUsuario() {
		return cepUsuario;
	}



	public void setCepUsuario(String cepUsuario) {
		this.cepUsuario = cepUsuario;
	}



	public String getRuaUsuario() {
		return ruaUsuario;
	}



	public void setRuaUsuario(String ruaUsuario) {
		this.ruaUsuario = ruaUsuario;
	}



	public String getBairroUsuario() {
		return bairroUsuario;
	}



	public void setBairroUsuario(String bairroUsuario) {
		this.bairroUsuario = bairroUsuario;
	}



	public String getCidadeUsuario() {
		return cidadeUsuario;
	}



	public void setCidadeUsuario(String cidadeUsuario) {
		this.cidadeUsuario = cidadeUsuario;
	}



	public String getEstadoUsuario() {
		return estadoUsuario;
	}



	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}



	public String getIbgeUsuario() {
		return ibgeUsuario;
	}



	public void setIbgeUsuario(String ibgeUsuario) {
		this.ibgeUsuario = ibgeUsuario;
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
