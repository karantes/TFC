<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Frequencias</h3>
					</div>

					<div class="box-body">
						<security:authorize access="hasAuthority('1')">
							<div class="box-footer">
								<a
									href='<spring:url value="/frequencia-register.html"></spring:url>'>
									<button class="btn btn-success">Nova Frequencia</button>
								</a>
							</div>
						</security:authorize>

						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Projeto</th>
									<th>Aluno</th>
									<th>Orientador</th>
									<th>Data Proposta</th>
									<th>Data Real</th>
									<th>Compareceu</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${frequencias}" var="frequencia">
									<tr>
										<td>${frequencia.projeto.id }</td>
										<td>${frequencia.aluno.nome }</td>
										<td>${frequencia.orientador.nome }</td>
										<td>${frequencia.dtProposta }</td>
										<td>${frequencia.dtReal }</td>
										<td>${frequencia.compareceu }</td>
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