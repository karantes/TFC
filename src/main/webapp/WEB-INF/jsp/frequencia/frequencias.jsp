<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	function setIdUpdate(id) {
		$("#idFrequencia").val(id);
	}
	
	$(document).ready(function() {
		$('#example').DataTable({
			"order" : [ [ 3, "asc" ] ],
			"iDisplayLength" : 50
		});
	});
</script>

<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Pediodo de resumo</h3>
				</div>

				<div class="box-body">
					<form:form action="/frequencias.html">
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Data Inicial:</label><br> <input type="date"
								name="dtInicial" id="dtInicial" value="${dtInicial }"
								class="form-control" required><br> <label>Data
								Final:</label><br> <input type="date" name="dtFinal" id="dtFinal"
								value="${dtFinal }" class="form-control" required>

						</div>
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Projeto:</label><br> <select
								class="col-xs-12 form-control select2" id="projetoId"
								name="projetoId" required>
								<option value=""></option>
								<c:forEach items="${projetos }" var="projeto">
									<option value="${projeto.id }"
										${projetoId == projeto.id ? 'selected' : ''}>${projeto.nome }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-xs-12">
							<button type="submit" class="btn btn-success">Buscar</button>
							<button type="submit" class="btn btn-success"
								formaction='<spring:url value="/download-frequencias.html"></spring:url>'>
								Download</button>
						</div>
					</form:form>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->


	<div class="row">
		<div class="col-xs-12">
			<div class="box">

				<div class="box-header">
					<h3 class="box-title">Frequencias</h3>
				</div>

				<div class="box-body">
					<security:authorize access="hasAuthority('2')">
						<div class="box-footer">
							<a
								href='<spring:url value="/frequencia-register.html"></spring:url>'>
								<button class="btn btn-success">Nova Frequencia</button>
							</a>
						</div>
					</security:authorize>

					<table id="example" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Atividades</th>
								<th>Aluno</th>
								<th>Orientador</th>
								<th>Data Frequencia</th>
								<th>Compareceu</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${frequencias}" var="frequencia">
								<tr>
									<td>${frequencia.atividades }</td>
									<td>${frequencia.aluno.nome }</td>
									<td>${frequencia.orientador.nome }</td>
									<td><fmt:formatDate value="${frequencia.dtFrequencia }"
											pattern="dd/MM/yyyy" /></td>
									<td>${frequencia.compareceu == true ? 'sim' : (frequencia.compareceu == false) ? 'nao' : '' }</td>
									<td><security:authorize access="hasAuthority('2')">
											<a href='<spring:url value="#modal-update"></spring:url>'
												data-toggle="modal" title="Opções"
												onclick="setIdUpdate(${frequencia.id})"><span
												class="fa fa-navicon"></span></a>
										</security:authorize> <security:authorize access="!hasAuthority('2')">
											<c:if test="${frequencia.compareceu != null }">
												<span class="fa fa-check"></span>
											</c:if>
										</security:authorize></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="modal fade" id="modal-update">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Frequencia</h4>
								</div>
								<div class="modal-body">
									<form
										action='<spring:url value="/update-frequencia.html"></spring:url>'
										method="post">

										<input type="hidden" id="idFrequencia" name="idFrequencia">

										<div class="form-group">
											<label>Descrição das atividades:</label> <input type="text"
												class="form-control" id="atividades" name="atividades"
												required="required">
										</div>
										<div class="form-group">
											<label>Compareceu:</label> <br> <label><input
												value="true" type="radio" id="compareceu" name="compareceu"
												checked="checked">Sim</label><br> <label><input
												value="false" type="radio" id="compareceu" name="compareceu">Não</label>
										</div>

										<input type="hidden" name="dtInicial" id="dtInicial"
											value="${dtInicial }"><input type="hidden"
											name="dtFinal" id="dtFinal" value="${dtFinal }"> <input
											type="hidden" name="projetoId" id="projetoId"
											value="${projetoId }">

										<button type="submit" class="btn btn-success">Confirmar</button>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default pull-left"
										data-dismiss="modal">Fechar</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->


				</div>
			</div>
		</div>
	</div>
	</section>
</div>