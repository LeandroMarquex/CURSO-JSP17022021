<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produto</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>

</head>
<body>
	<a href="acessoliberado.jsp">Voltar para o Inicio</a>
	<br>
	<a href="index.jsp">Sair</a>
	<center>
		<h1>Cadastro de Produto</h1>

		<h3 style="color: orange;">${msg}</h3>
	</center>

	<form action="salvarProduto" method="post" id="formProduto"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="idProduto"
							name="idProduto" style="width: 70px" value="${produto.idProduto}"
							class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nomeProduto" name="nomeProduto"
							maxlength="50" style="width: 170px"
							value="${produto.nomeProduto}"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="number" id="quantidadeProduto"
							name="quantidadeProduto" maxlength="10" style="width: 50px"
							value="${produto.quantidadeProduto}"></td>
					</tr>
					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valorProduto" name="valorProduto"
							maxlength="15" style="width: 100px" data-thousands="."
							data-decimal="," data-precision="2"
							value="${produto.valorEmTexto}"></td>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Produtos cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor R$</th>
				<th>Delete</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td style="width: 150px"><c:out value="${produto.idProduto}">
						</c:out></td>
					<td style="width: 150px"><c:out value="${produto.nomeProduto}">
						</c:out></td>
					<td><c:out value="${produto.quantidadeProduto}"></c:out></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${produto.valorProduto}"/></td> 
			
			

					<td><a
						href="salvarProduto?acao=delete&produto=${produto.idProduto}"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>
					<td><a
						href="salvarProduto?acao=editar&produto=${produto.idProduto}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nomeProduto").value == '') {
				alert('Informe o Nome');
			} else if (document.getElementById("quantidadeProduto").value == '') {
				alert('Informe o Quantidade do Produto');
			} else if (document.getElementById("valorProduto").value == '') {
				alert('Informe o Valor do Produto');
			}
			return true;
		}
	</script>

	<script type="text/javascript">
		$(function() {
			$('#valorProduto').maskMoney();
		})
	</script>
</body>
</html>
