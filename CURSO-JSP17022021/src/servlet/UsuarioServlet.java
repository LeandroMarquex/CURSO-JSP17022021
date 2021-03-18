package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/salvarUsuario")
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
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultarUsuario(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				view.forward(request, response);

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

			BeanCursoJsp salvarUsuario = new BeanCursoJsp();

			salvarUsuario.setIdUsuario(!id.isEmpty() ? Long.parseLong(id) : null);
			salvarUsuario.setLogin(login);
			salvarUsuario.setSenha(senha);
			salvarUsuario.setNomeUsuario(nome);
			salvarUsuario.setTelefoneUsuario(telefone);

			try {

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
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) { // QUANDO FOR USUARIO NOVO
					// request.setAttribute("msg", "Usuario já existe com o mesmo login!");
					msg = "Usuário já existe com o mesmo login";
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "\n A senha já existe para outro usuário!";
					podeInserir = false;

				}
				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if  (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvarUsuario(salvarUsuario);
				} else if (id != null && !id.isEmpty() && podeInserir) {
					if (!daoUsuario.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Este Login já existe e pertence a um outro usuario");

					}
					if (!daoUsuario.validarSenhaUpdate(senha, id)) {
						request.setAttribute("msg", "Este SENHA já  pertence a um outro usuario");
					} else {
						daoUsuario.atualizarUsuario(salvarUsuario);
					}

				}
				if (!podeInserir) {
					request.setAttribute("user", salvarUsuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
