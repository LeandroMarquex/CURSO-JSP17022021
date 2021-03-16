package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.ProdutoBean;
import dao.DaoProduto;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.deleteProduto(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("usuarios", daoProduto.listarProduto());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				// BeanCursoJsp beanCursoJsp = daoUsuario.consultarUsuario(user);
				ProdutoBean produtoBean = new ProdutoBean();

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("usuarios", daoProduto.listarProduto());
				request.setAttribute("user", produtoBean);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("usuarios", daoProduto.listarProduto());
				view.forward(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idProduto = request.getParameter("idProduto");
		String nomeProduto = request.getParameter("nomeProduto");
		String quantidadeProduto = request.getParameter("quantidadeProduto");
		String valorProduto = request.getParameter("valorProduto");

		ProdutoBean salvarProduto = new ProdutoBean();

		salvarProduto.setIdProduto(!idProduto.isEmpty() ? Long.parseLong(idProduto) : 0);
		salvarProduto.setNomeProduto(nomeProduto);
		salvarProduto.setQuantidadeProduto(Double.parseDouble(quantidadeProduto));
		salvarProduto.setValorProduto(Double.parseDouble(valorProduto));
		
		daoProduto.salvarProoduto(salvarProduto);

		try {

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listarProduto());
			view.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*
		 * String acao = request.getParameter("acao");
		 * 
		 * if (acao != null && acao.equalsIgnoreCase("reset")) { try { RequestDispatcher
		 * view = request.getRequestDispatcher("/cadastroproduto.jsp");
		 * request.setAttribute("usuarios", daoProduto.listarProduto());
		 * view.forward(request, response);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } else {
		 * 
		 * String id = request.getParameter("id"); String login =
		 * request.getParameter("login"); String senha = request.getParameter("senha");
		 * String nome = request.getParameter("nome"); String telefone =
		 * request.getParameter("telefone");
		 * 
		 * BeanCursoJsp salvarUsuario = new BeanCursoJsp();
		 * 
		 * salvarUsuario.setIdUsuario(!id.isEmpty() ? Long.parseLong(id) : null);
		 * salvarUsuario.setLogin(login); salvarUsuario.setSenha(senha);
		 * salvarUsuario.setNomeUsuario(nome);
		 * salvarUsuario.setTelefoneUsuario(telefone);
		 * 
		 * try { /*
		 * 
		 * String msg = null; boolean podeInserir = true;
		 * 
		 * if (id == null || id.isEmpty() && !daoProduto.validarLogin(login)) { //
		 * QUANDO FOR USUARIO NOVO // request.setAttribute("msg",
		 * "Usuario já existe com o mesmo login!"); msg =
		 * "Usuário já existe com o mesmo login"; podeInserir = false; } else if(id ==
		 * null || id.isEmpty() && !daoProduto.validarSenha(senha)) { msg =
		 * "\n A senha já existe para outro usuário!"; podeInserir = false;
		 * 
		 * 
		 * } if (msg != null) { request.setAttribute("msg", msg); }
		 * 
		 * if (id == null || id.isEmpty() && daoProduto.validarLogin(login) &&
		 * podeInserir) { daoProduto.salvarUsuario(salvarUsuario); } else if (id != null
		 * && !id.isEmpty() && podeInserir) { if (!daoProduto.validarLoginUpdate(login,
		 * id)) { request.setAttribute("msg",
		 * "Este Login já existe e pertence a um outro usuario");
		 * 
		 * } if ( !daoProduto.validarSenhaUpdate(senha, id)){
		 * request.setAttribute("msg", "Este SENHA já  pertence a um outro usuario");
		 * }else { daoProduto.atualizarUsuario(salvarUsuario); }
		 * 
		 * } if (!podeInserir) { request.setAttribute("user", salvarUsuario); }
		 * 
		 * RequestDispatcher view =
		 * request.getRequestDispatcher("/cadastroUsuario.jsp");
		 * request.setAttribute("usuarios", daoUsuario.listarUsuario());
		 * view.forward(request, response);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * }
		 */
	}

}
