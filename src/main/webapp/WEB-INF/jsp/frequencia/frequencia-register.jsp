<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	$(function() {
		//Initialize Select2 Elements
		$('.select2').select2()
	})
</script>

<div class="content-wrapper">
	<section class="content-header">
	<div class="box-header">
		<a href='<spring:url value="/frequencias.html"></spring:url>'>
			<button class="btn btn-success">Voltar</button>
		</a>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Cadastro de Frequencias</h3>
					</div>

					<div class="box-body">
						<form:form action="/frequencia-register.html" method="POST">
							<div class="col-xs-12">
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Primeira Frequencia:</label><br> <input
										class="form-control" type="date" id="dtProposta"
										name="dtProposta" required>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Quantidade de Frequencias:</label><br> <input
										class="form-control" type="number" id="nrFrequencias"
										name="nrFrequencias" required>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Projeto:</label><br> <select
										class="form-control select2" id="projetoId" name="projetoId"
										required>
										<option value=""></option>
										<c:forEach items="${projetos }" var="projeto">
											<option value="${projeto.id }">${projeto.nome }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="box-footer ">
								<button type="submit" class="btn btn-success">Cadastrar</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>
<!-- /.content-wrapper -->