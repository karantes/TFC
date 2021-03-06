<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="content-wrapper">
	<section class="content-header"> <c:if
		test="${erroCadastro eq true}">
		<div class="alert alert-warning alert-dismissible">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			<h4>
				<i class="icon fa fa-warning"></i> Atenção!
			</h4>
			Erro ao cadastrar!
		</div>
	</c:if> <c:if test="${erroAtualizar eq true}">
		<div class="alert alert-warning alert-dismissible">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			<h4>
				<i class="icon fa fa-warning"></i> Atenção!
			</h4>
			Erro ao atualizar os dados!
		</div>
	</c:if>

	<div class="box-header">
		<a href='<spring:url value="/usuario-register.html"></spring:url>'>
			<button class="btn btn-success">Novo Usuario</button>
		</a>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Usuarios</h3>
					</div>

					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Email</th>
									<th>Ativo</th>
									<th>Data de Cadastro</th>
									<th>Data de Alteração</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${usuarios}" var="usuario">
									<tr>
										<td>${usuario.nome }</td>
										<td>${usuario.email }</td>
										<td>${usuario.ativo == true ? '<span class="label label-success">Usuario Ativo</span>' : '<span class="label label-danger">Usuario Inativo</span>' }</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${usuario.dtCadastro}" /></td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${usuario.dtAlteracao}" /></td>
										<td><a
											href='<spring:url value="/usuario-detail/${usuario.id}.html"></spring:url>'
											data-toggle="tooltip"
											title="Gerenciar Usuario, visualizar detalhes.."><span
												class="glyphicon glyphicon-list"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>