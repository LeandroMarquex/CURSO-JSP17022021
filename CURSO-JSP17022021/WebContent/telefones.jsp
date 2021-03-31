<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de TELEFONES</title>


</head>

<body>
	<a href="acessoliberado.jsp">Voltar para o Inicio</a>
	<br>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de Usuário</h1>
	<h3 align="center" style="color: red;">${msg}</h3>
	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">


		<table>

			<tr>
				<td>USER:</td>
				<td><input type="text" id="id" name="id" readonly="readonly"
					class="field-long" value="${userEscolhido.idUsuario}"></td>

				<td>NOME:</td>
				<td><input type="text" id="nome" name="nome"
					readonly="readonly" class="field-long" value="${userEscolhido.nomeUsuario}"></td>
					<td>
					<select id="tipo" name="tipo">
						<option>Casa</option>
						<option>Contato</option>
						<option>Celular</option>
					</select>
					
					</td>


			</tr>
			<tr>
				<td>NÚMERO</td>
				<td><input type="text" id="numero" name="numero"></td>
		</table>
		<input type="submit" value="Salvar"><input type="submit"
			value="Cancelar"
			onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">

	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>NUMERO</th>
			<th>TIPO</th>
			<th>EXCLUIR</th>


		</tr>
		<c:forEach items="${telefones}" var="telefone">
			<tr>
				<td style="width: 150px"><c:out value="${telefone.idTelefone}"></c:out></td>
				<td style="width: 150px"><c:out value="${telefone.numeroTelefone}"></c:out></td>
				<td style="width: 150px"><c:out value="${telefone.tipoTelefone}"></c:out></td>



				<td><a
					href="salvarTelefones?acao=deleteTelefone&foneId=${telefone.idTelefone}"><img
						alt="Excluir" title="Excluir" src="resources/img/excluir.png"
						width="20px" height="20px"></a></td>


			</tr>

		</c:forEach>
	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o NUMERO DOTELEFONE');
				return false;

			}
			return true;
		}
	</script>
</body>
</html>