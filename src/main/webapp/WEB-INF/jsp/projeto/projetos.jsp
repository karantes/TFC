<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Projetos</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a
								href='<spring:url value="/projeto-register.html"></spring:url>'>
								<button class="btn btn-success">Novo Projeto</button>
							</a>
						</div>

						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Projeto</th>
									<th>Descrição</th>
									<th>Ativo</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${projetos}" var="projeto">
									<tr>
										<td>${projeto.nome}</td>
										<td>${projeto.descricao }</td>
										<td>${projeto.ativo }</td>
										<td><a
											href='<spring:url value="/projeto-detail/${projeto.id}.html"></spring:url>'
											data-toggle="tooltip"
											title="Gerenciar Projeto, visualizar detalhes.."><span
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