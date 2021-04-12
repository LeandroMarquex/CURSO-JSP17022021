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
	<h1>Cadastro de TELEFONES</h1>
	<h3 align="center" style="color: red;">${msg}</h3>
	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">


		<table>

			<tr>
				<td>USER:</td>
				<td><input type="text" id="user" name="user"
					readonly="readonly" class="field-long"
					value="${userEscolhido.idUsuario}"></td>

				<td>NOME:</td>
				<td><input type="text" id="nome" name="nome"
					readonly="readonly" class="field-long"
					value="${userEscolhido.nomeUsuario}"></td>
				<td><select id="tipo" name="tipo">
						<option>Celular</option>
						<option>Casa</option>
						<option>Trabalho</option>
						<option>Recado</option>
						<option>Outros</option>
				</select></td>


			</tr>
			<tr>
				<td>NÚMERO</td>
				<td><input type="text" id="numero" name="numero" style="width: 173px;"></td>
			</tr>
			<tr>
						<td></td>
						<td>
							<input type="submit" value="Salvar" style="width: 173px;"/>
						
						</td>
						<td>
								<input type="submit" value="Voltar" style="width: 173px;" onclick="document.getElementById('formUser').action = 'salvarTelefones?acao=voltar'">
						</td>
					</tr>
		</table>


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
				<td style="width: 150px"><c:out
						value="${telefone.numeroTelefone}"></c:out></td>
				<td style="width: 150px"><c:out
						value="${telefone.tipoTelefone}"></c:out></td>



				<td><a
					href="salvarTelefones?user=${telefone.usuario}&acao=deleteTelefone&idFone=${telefone.idTelefone}"><img
						alt="Excluir" title="Excluir" src="resources/img/excluir.png"
						width="20px" height="20px"></a></td>


			</tr>

		</c:forEach>
	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o NUMERO DO TELEFONE');
				return false;

			} else if (document.getElementById("tipo").value == '') {
				alert("Informe o Tipo!");
			}
			return true;
		}
	</script>
</body>
</html>