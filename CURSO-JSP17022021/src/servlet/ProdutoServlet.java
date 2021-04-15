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
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.deleteProduto(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				ProdutoBean produtoBean = daoProduto.consultarProduto(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produtoBean);
				request.setAttribute("produtos", daoProduto.listarProduto());

				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
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
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String idProduto = request.getParameter("idProduto");
			String nomeProduto = request.getParameter("nomeProduto");
			String quantidadeProduto = request.getParameter("quantidadeProduto");
			String valorProduto = request.getParameter("valorProduto");

			try {

				String msg = null;
				boolean podeInserir = true;

				if (valorProduto == null || valorProduto.isEmpty()) {
					msg = "Valor R$ deve ser informado";
					podeInserir = false;

				} else if (quantidadeProduto == null || quantidadeProduto.isEmpty()) {
					msg = "Quantidade deve ser informado";
					podeInserir = false;

				} else if (nomeProduto == null || nomeProduto.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (idProduto == null || idProduto.isEmpty()
						&& !daoProduto.validarNome(nomeProduto)) {// QUANDO
															// FDOR
															// PRODUTO
															// NOVO
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;

				}

				ProdutoBean produto = new ProdutoBean();
				produto.setNomeProduto(nomeProduto);
				produto.setIdProduto(!idProduto.isEmpty() ? Long.parseLong(idProduto) : null);

				if (quantidadeProduto != null && !quantidadeProduto.isEmpty()) {
					produto.setQuantidadeProduto(Double.parseDouble(quantidadeProduto));
				}

				if (valorProduto != null && !valorProduto.isEmpty()) {
					
					String valorParce = valorProduto.replaceAll("\\." , "");
					valorParce = valorParce.replaceAll("\\," , ".");
					produto.setValorProduto(Double.parseDouble(valorParce));
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (idProduto == null || idProduto.isEmpty()
						&& daoProduto.validarNome(nomeProduto) && podeInserir) {

					daoProduto.salvarProoduto(produto);

				} else if (idProduto != null && !idProduto.isEmpty() && podeInserir) {
					daoProduto.atualizarProduto(produto);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}

/*
 * String acao = request.getParameter("acao");
 * 
 * if (acao != null && acao.equalsIgnoreCase("reset")) { try { RequestDispatcher
 * view = request.getRequestDispatcher("/cadastroProduto.jsp");
 * request.setAttribute("produtos", daoProduto.listarProduto());
 * view.forward(request, response);
 * 
 * } catch (Exception e) { e.printStackTrace(); } } else {
 * 
 * String idProduto = request.getParameter("idProduto"); String nomeProduto =
 * request.getParameter("nomeProduto"); String quantidadeProduto =
 * request.getParameter("quantidadeProduto"); String valorProduto =
 * request.getParameter("valorProduto");
 * 
 * try {
 * 
 * String msg = null; boolean podeInserir = true;
 * 
 * if (nomeProduto == null || nomeProduto.isEmpty()) { msg =
 * "Nome deve ser informado"; podeInserir = false;
 * 
 * 
 * } else if (idProduto == null || idProduto.isEmpty() &&
 * !daoProduto.validarNome(nomeProduto)) {// QUANDO // FDOR // PRODUTO // NOVO
 * msg = "Produto já existe com o mesmo nome!"; podeInserir = false; } else if
 * (quantidadeProduto == null || quantidadeProduto.isEmpty()) { msg =
 * "Quantidade deve ser informado"; podeInserir = false; } else if (valorProduto
 * == null || valorProduto.isEmpty()) { msg = "Valor R$ deve ser informado"; }
 * ProdutoBean salvarProduto = new ProdutoBean();
 * 
 * 
 * salvarProduto.setNomeProduto(nomeProduto);
 * salvarProduto.setIdProduto(!idProduto.isEmpty() ? Long.parseLong(idProduto) :
 * null); //
 * salvarProduto.setQuantidadeProduto(Double.parseDouble(quantidadeProduto)); //
 * salvarProduto.setValorProduto(Double.parseDouble(valorProduto));
 * 
 * if (quantidadeProduto != null && !quantidadeProduto.isEmpty()) {
 * salvarProduto.setQuantidadeProduto(Double.parseDouble(quantidadeProduto)); }
 * if (valorProduto != null && !valorProduto.isEmpty())
 * salvarProduto.setValorProduto(Double.parseDouble(valorProduto)); if (msg !=
 * null) { request.setAttribute("msg", msg); } else if (idProduto == null ||
 * idProduto.isEmpty() && daoProduto.validarNome(nomeProduto) && podeInserir) {
 * daoProduto.salvarProoduto(salvarProduto); } else if (idProduto != null &&
 * idProduto.isEmpty() && podeInserir) {
 * 
 * daoProduto.atualizarProduto(salvarProduto);
 * 
 * }
 * 
 * if (!podeInserir) { request.setAttribute("produto", salvarProduto); }
 * 
 * RequestDispatcher view =
 * request.getRequestDispatcher("/cadastroProduto.jsp");
 * request.setAttribute("produtos", daoProduto.listarProduto());
 * view.forward(request, response);
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * }
 * 
 * }
 * 
 * }
 */
