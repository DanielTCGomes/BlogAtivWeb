<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
</head>
<body>


	<h1>LISTA USUARIOS</h1>
	<a href="/blog/autenticado/usuario/novo">Novo Usuario</a>
	<table>
		<tr>
			<td>Login</td>
			<td>Senha</td>
			<td>Perfil</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach var="usuario" items="${usuarios}">
			<tr>
				<td>${usuario.login}</td>
				<td>${usuario.password}</td>
				<td>${usuario.perfil}</td>
				
				<td><a
					href="/blog/autenticado/usuario/alterar?id=${usuario.login}">ALTERAR</a></td>
				<td><a
					href="/blog/autenticado/usuario/excluir?id=${usuario.login}">EXCLUIR</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
