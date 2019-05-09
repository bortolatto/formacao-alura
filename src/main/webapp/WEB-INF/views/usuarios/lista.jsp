<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Lista de Usuários">

	<div class="container">
		<a href="${s:mvcUrl('UC#form').build() }" rel="nofollow">Novo Usuário</a>
		<h1>Lista de Usuários</h1>
		<h1>${message }</h1>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
			</tr>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
					<td>${usuario.roles }</td>
					<td><a href="${s:mvcUrl('UC#detalhe').arg(0, usuario.email).build() }">
					<img alt="" src="${contextPath }resources/imagens/adicionar.png"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</tags:pageTemplate>