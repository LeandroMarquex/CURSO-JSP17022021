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
	private String fotoBase64;
	private String fotoBase64Miniatura;
	private String contentType;
	private String tempFotoUser;
	private String contentTypeCurriculo;
	private String curriculoBase64;
	
	
	
	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}



	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}



	public void setTempFotoUser(String tempFotoUser) {
		this.tempFotoUser = tempFotoUser;
	}



	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}



	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}



	public String getCurriculoBase64() {
		return curriculoBase64;
	}



	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}



	public String getTempFotoUser() {
		
		tempFotoUser= "data:" + contentType + ";base64," + fotoBase64;
		return tempFotoUser;
	}



	public boolean validarLoginSenha(String login, String senha) {
		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
			return true;
			
		} else {
			return false;

		}
	}
	
	

	public String getFotoBase64() {
		return fotoBase64;
	}



	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}



	public String getContentType() {
		return contentType;
	}



	public void setContentType(String contentType) {
		this.contentType = contentType;
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
