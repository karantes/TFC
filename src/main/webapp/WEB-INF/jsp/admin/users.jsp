<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <c:url value="/resources/" /> -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Usuários <small>Cadastro de Usuários</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Administração</a></li>
			<li class="active">Usuários</li>
		</ol>
	</section>
	<!-- Main content -->
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Usuários</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- .box-body -->
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Email</th>
								<th>Centro de Custo</th>
								<th>Inclusão</th>
								<th>Profissão</th>
								<th>Role(s)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td><a href='<spring:url value="/users/${user.id}.html"></spring:url>'>${user.name}</a></td>
									<td>${user.email}</td>
									<td></td>
									<td><fmt:formatDate type="date" dateStyle="short"
											timeStyle="short" value="${user.anoInclusao}" /></td>
									<td>${user.profissao }
									<td><c:forEach items="${user.roles}" var="role"
											varStatus="i">
                      					${role.name } 
                      					<c:if test="${i.index >= 0}">
												<c:if test="${i.last == false}">
                      					 			,&nbsp;
                      							</c:if>
											</c:if>
										</c:forEach></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<a href='<spring:url value="/user-register.html"></spring:url>'>
						<button class="btn btn-primary">Novo Usuário</button>
					</a>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

