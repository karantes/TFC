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
		<a href='<spring:url value="/documentos.html"></spring:url>'>
			<button class="btn btn-success">Voltar</button>
		</a>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>
					<div class="box-header">
						<h3 class="box-title">Cadastro de Documentos</h3>
					</div>

					<div class="box-body">
						<form:form action="/documento-register.html" method="POST"
							enctype="multipart/form-data">
							<div class="col-xs-12">

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Descrição do Documento:</label><br>
									<textarea path="descricao" type="text" id="descricao" rows="5"
										cols="50" name="descricao"></textarea>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Arquivo:</label><br> <input type="file" name="file">
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Tipo:</label> <br> <select class="form-control"
										id="tipo" name="tipo" required="required">
										<option value="Administrativo">Administrativo</option>
										<option value="Informativo">Informativo</option>
										<option value="Revisão">Revisão</option>
									</select>
								</div>
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<select class="col-xs-12 form-control select2"
										id="destinatariosId" name="destinatariosId"
										multiple="multiple" required>
										<c:forEach items="${destinatarios }" var="destinatario">
											<option value="${destinatario.id }">${destinatario.nome }</option>
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