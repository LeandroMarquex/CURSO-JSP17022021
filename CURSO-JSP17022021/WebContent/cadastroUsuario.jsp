<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuario</title>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>


</head>

<body>
	<a href="acessoliberado.jsp">Voltar para o Inicio</a>
	<br>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de Usuário</h1>
	<h3 align="center" style="color: red;">${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data">


		<table>
			<tr>
				<td>Código:</td>
				<td><input type="text" readonly="readonly" id="id" name="id"
					value="${user.idUsuario}"></td>

				<td>CEP:</td>
				<td><input type="text" id="cep" name="cep" placeholder="Informe o cep do usuario"
					value="${user.cepUsuario}" onblur="consultaCep()"></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" id="login" name="login"
					value="${user.login}"></td>

				<td>Rua:</td>
				<td><input type="text" id="rua" name="rua"
					value="${user.ruaUsuario}"></td>


			</tr>
			<tr>
				<td>Senha:</td>
				<td><input type="password" id="senha" name="senha"
					value="${user.senha}"></td>

				<td>Bairro:</td>
				<td><input type="text" id="bairro" name="bairro" placeholder="Informe o  bairro"
					value="${user.bairroUsuario}"></td>
			</tr>
			<tr>
				<td>Nome:</td>
				<td><input type="text" id="nome" name="nome" placeholder="Informe o nome do usuario"
					value="${user.nomeUsuario}"></td>

				<td>Cidade:</td>
				<td><input type="text" id="cidade" name="cidade" placeholder="Informe a cidade"
					value="${user.cidadeUsuario}"></td>
			</tr>
			<tr>
				<td>Telefone:</td>
				<td><input type="text" id="telefone" name="telefone"
					value="${user.telefoneUsuario}"></td>

				<td>Estado:</td>
				<td><input type="text" id="estado" name="estado"
					value="${user.estadoUsuario}"></td>
			</tr>


			<tr>
				<td>IBGE::</td>
				<td><input type="text" id="ibge" name="ibge"
					value="${user.ibgeUsuario}"></td>
			</tr>
			<tr>
				<td>FOTO:</td>
				<td><input type="file" name="foto" value="Foto"></td>
			</tr>
			
		</table>
		<input type="submit" value="Salvar"><input type="submit"
			value="Cancelar"
			onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">

	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>LOGIN</th>
			<th>NOME</th>
			<th>TELEFONE</th>
			<th>CEP</th>
			<th>RUA</th>
			<th>BAIRRO</th>
			<th>CIDADE</th>
			<th>ESTADO</th>
			<th>IBGE</th>
			<th>DELETE</th>
			<th>EDITAR</th>
			<th>FONES</th>

		</tr>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td style="width: 150px"><c:out value="${user.idUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.nomeUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.telefoneUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.cepUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.ruaUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.bairroUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.cidadeUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.estadoUsuario}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.ibgeUsuario}"></c:out></td>


				<td><a href="salvarUsuario?acao=delete&user=${user.idUsuario}"><img
						alt="Excluir" title="Excluir" src="resources/img/excluir.png"
						width="20px" height="20px"></a></td>


				<td><a href="salvarUsuario?acao=editar&user=${user.idUsuario}"><img
						alt="Telefones" title="Telefones" src="resources/img/editar.png"
						width="20px" height="20px"></a></td>


				<td><a href="salvarTelefones?acao=addTelefone&user=${user.idUsuario}"><img
						alt="Alterar" title="Alterar" src="resources/img/phone2.ico"
						width="20px" height="20px"></a></td>
			</tr>

		</c:forEach>
	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;

			} else if (document.getElementById("senha").value == '') {
				alert('Informe o Senha');
				return false;

			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;

			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o Telefone');
				return false;

			}
			return true;
		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {
						//		alert(dados.logradouro);

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);

						} //end if.
						else {
							//CEP pesquisado não foi encontrado.
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');

							alert("CEP não encontrado.");
						}
					});

		}
	</script>
</body>
</html>