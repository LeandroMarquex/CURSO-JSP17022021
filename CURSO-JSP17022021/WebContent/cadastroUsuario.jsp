<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuario</title>
</head>
<body>
	<h1>Cadastro de Usuário</h1>
	<form action="salvarUsuario" method="post" id="formUser">
		<table>
			<tr>
				<td>Código:</td>
				<td><input type="text" readonly="readonly" id="id" name="id"
					value="${user.idUsuario}"></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" id="login" name="login"
					value="${user.login}"></td>
			</tr>
			<tr>
				<td>Senha:</td>
				<td><input type="password" id="senha" name="senha"
					value="${user.senha}"></td>
			</tr><tr>
				<td>Nome:</td>
				<td><input type="text" id="nome" name="nome"
					value="${user.nomeUsuario}"></td>
			</tr>
		</table>
		<input type="submit" value="Salvar"><input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">

	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>LOGIN</th>
			<th>NOME</th>
			<th>DELETE</th>
			<th>EDITAR</th>
		</tr>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td style="width: 150px"><c:out value="${user.idUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.nomeUsuario}"></c:out></td>

				<td><a href="salvarUsuario?acao=delete&user=${user.idUsuario}"><img
						alt="Excluir" title="Excluir" src="resources/img/excluir.png"
						width="20px" height="20px"></a></td>
				<td><a href="salvarUsuario?acao=editar&user=${user.idUsuario}"><img
						alt="Alterar" title="Alterar" src="resources/img/editar.png"
						width="20px" height="20px"></a></td>

			</tr>

		</c:forEach>
	</table>
</body>
</html>