package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/salvarUsuario")
@MultipartConfig
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.deleteUsuario(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				request.setAttribute("msg", "Deletado com sucesso");
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultarUsuario(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				request.setAttribute("user", beanCursoJsp);
				// request.setAttribute("msg", "Editado com sucesso");
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("download")) {
				BeanCursoJsp usuario = daoUsuario.consultarUsuario(user);
				if (usuario != null) {
					String contentType = "";
					byte[] fileBytes = null;

					String tipo = request.getParameter("tipo");

					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						new Base64();
						fileBytes = Base64.decodeBase64(usuario.getFotoBase64());
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						new Base64();
						fileBytes = Base64.decodeBase64(usuario.getCurriculoBase64());
					}

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					/* Coloca os bytes em um objeto de entrada para processar */
					InputStream is = new ByteArrayInputStream(fileBytes);

					/* inicio da resposta para o navegador */
					int read = 0;
					byte[] bytes = new byte[2048];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");

			BeanCursoJsp salvarUsuario = new BeanCursoJsp();

			salvarUsuario.setIdUsuario((id != null && !id.isEmpty()) ? Long.parseLong(id) : null);
			salvarUsuario.setLogin(login);
			salvarUsuario.setSenha(senha);
			salvarUsuario.setNomeUsuario(nome);
			salvarUsuario.setTelefoneUsuario(telefone);
			salvarUsuario.setCepUsuario(cep);
			salvarUsuario.setRuaUsuario(rua);
			salvarUsuario.setBairroUsuario(bairro);
			salvarUsuario.setCidadeUsuario(cidade);
			salvarUsuario.setEstadoUsuario(estado);
			salvarUsuario.setIbgeUsuario(ibge);

			try {

				/*
				 * Inicio File upload de imagens e pdf
				 * 
				 * if (ServletFileUpload.isMultipartContent(request)) {
				 * 
				 * List<FileItem> fileItens = new ServletFileUpload(new
				 * DiskFileItemFactory()).parseRequest(request);
				 * 
				 * for (FileItem fileItem : fileItens) { if
				 * (fileItem.getFieldName().equals("foto")) { String fotoBase64 = new
				 * Base64().encodeBase64String(fileItem.get()); String contenttype =
				 * fileItem.getContentType(); salvarUsuario.setFotoBase64(fotoBase64);
				 * salvarUsuario.setContentType(contenttype); }
				 * 
				 * }
				 * 
				 * } FIM File upload de imagens e pdf
				 */

				/* Inicio File upload de imagems e pdf */

				if (ServletFileUpload.isMultipartContent(request)) {

					Part imagemFoto = request.getPart("foto");

					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						new Base64();
						/*
						 * 
						 * String fotoBase64 = new Base64()
						 * .encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));
						 * 
						 * salvarUsuario.setFotoBase64(fotoBase64);
						 * salvarUsuario.setContentType(imagemFoto.getContentType());
						 */
						String fotoBase64 = Base64
								.encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));

						salvarUsuario.setFotoBase64(fotoBase64);
						salvarUsuario.setContentType(imagemFoto.getContentType());

						/* Inicio miniatura imagem */

						new Base64();
						/* Transforma emum bufferedImage */
						byte[] imageByteDecode = Base64.decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));

						/* Pega o tipo da imagem */
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

						/* Cria imagem em miniatura */
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();

						/* Escrever imagem novamente */
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);

						String miniaturaBase64 = "data:image/png;base64,"
								+ DatatypeConverter.printBase64Binary(baos.toByteArray());

						System.out.println(miniaturaBase64);
						salvarUsuario.setFotoBase64Miniatura(miniaturaBase64);
						/* Fim miniatura imagem */

					} else {
						salvarUsuario.setFotoBase64(request.getParameter("fotoTemp"));
						salvarUsuario.setContentType(request.getParameter("contentTypeTemp"));

					}

					/* Processa pdf */
					Part curriculoPdf = request.getPart("curriculo");
					if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
						new Base64();
						String curriculoBase64 = Base64
								.encodeBase64String(converteStremParabyte(curriculoPdf.getInputStream()));

						salvarUsuario.setCurriculoBase64(curriculoBase64);
						salvarUsuario.setContentTypeCurriculo(curriculoPdf.getContentType());
					} else {
						salvarUsuario.setCurriculoBase64(request.getParameter("curriculoTemp"));
						salvarUsuario.setContentTypeCurriculo(request.getParameter("contentTypeCurriculoTemp"));
					}

					/* FIM File upload de imagems e pdf */

					String msg = null;
					boolean podeInserir = true;

					if (login == null || login.isEmpty()) {
						msg = "Login deve ser informado";
						podeInserir = false;
					} else if (senha == null || senha.isEmpty()) {
						msg = "Senha deve ser informada.";
						podeInserir = false;
					} else if (nome == null || nome.isEmpty()) {
						msg = "Nome deve ser informado";
						podeInserir = false;
					} else if (telefone == null || telefone.isEmpty()) {
						msg = "Telefone deve ser informado";
						podeInserir = false;
					} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) { // QUANDO FOR USUARIO
																								// NOVO
						// request.setAttribute("msg", "Usuario já existe com o mesmo login!");
						msg = "Usuário já existe com o mesmo login";
						podeInserir = false;
					} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
						msg = "\n A senha já existe para outro usuário!";
						podeInserir = false;

					}
					if (msg != null) {
						request.setAttribute("msg", msg);
					} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
						daoUsuario.salvarUsuario(salvarUsuario);
						request.setAttribute("msg", "Salvo com sucesso");
					} else if (id != null && !id.isEmpty() && podeInserir) {
						if (!daoUsuario.validarLoginUpdate(login, id)) {
							request.setAttribute("msg", "Este Login já existe e pertence a um outro usuario");

						}
						if (!daoUsuario.validarSenhaUpdate(senha, id)) {
							request.setAttribute("msg", "Este SENHA já  pertence a um outro usuario");
						} else {
							daoUsuario.atualizarUsuario(salvarUsuario);
							request.setAttribute("msg", "Atualizado com sucesso");
						}

					}
					if (!podeInserir) {
						request.setAttribute("user", salvarUsuario);
					}

					RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
					request.setAttribute("usuarios", daoUsuario.listarUsuario());
					view.forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private byte[] converteStremParabyte(InputStream imagem) throws Exception {
		// TODO Auto-generated method stub

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();

		}
		return baos.toByteArray();
	}
}
