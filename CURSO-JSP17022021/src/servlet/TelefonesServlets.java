package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.TelefoneBean;
import dao.DaoTelefone;
import dao.DaoUsuario;

/**
 * Servlet implementation class TelefonesServlets
 */
@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefonesServlets() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			BeanCursoJsp beanCursoJsp = daoUsuario.consultarUsuario(user);

			if (acao.equalsIgnoreCase("addTelefone")) {

				request.getSession().setAttribute("userEscolhido", beanCursoJsp);
				request.setAttribute("userEscolhido", beanCursoJsp);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listarTelefone(beanCursoJsp.getIdUsuario()));
//				request.setAttribute("msg", "Telefone Salvo com sucesso");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("deleteTelefone")) {
				String idFone = request.getParameter("idFone");
				daoTelefone.deleteTelefone(idFone);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listarTelefone(Long.parseLong(user)));
				request.setAttribute("msg", "Excluído Com Sucesso!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			String acao = request.getParameter("acao");

			if (acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {

				if (numero == null || (numero != null && numero.isEmpty())) {

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefone", daoTelefone.listarTelefone(beanCursoJsp.getIdUsuario()));
					request.setAttribute("msg", "Informe o numero do telefone!");
					view.forward(request, response);

				} else {

					TelefoneBean telefoneBean = new TelefoneBean();
					telefoneBean.setNumeroTelefone(numero);

					telefoneBean.setTipoTelefone(tipo);
					telefoneBean.setUsuario(beanCursoJsp.getIdUsuario());

					daoTelefone.salvarTelefone(telefoneBean);

					request.getSession().setAttribute("userEscolhido", beanCursoJsp);
					request.setAttribute("userEscolhido", beanCursoJsp);

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

					request.setAttribute("telefones", daoTelefone.listarTelefone(beanCursoJsp.getIdUsuario()));
					request.setAttribute("msg", "TELEFONE salvo com sucesso");
					view.forward(request, response);
				}

			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuario());
				view.forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
