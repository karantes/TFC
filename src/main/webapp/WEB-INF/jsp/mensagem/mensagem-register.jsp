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
		$('.select2').select2({
			placeholder : "Para:"
		})
	});

	$(function() {
		//Add text editor
		$("#mensagem").wysihtml5();
	});
</script>

<div class="content-wrapper">
	<section class="content-header">
	<div class="box-header">
		<a href='<spring:url value="/mensagens-enviadas.html"></spring:url>'>
			<button class="btn btn-success">Voltar</button>
		</a>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Nova Mensagem</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form:form action="/mensagem-register.html" method="POST">
						<div class="form-group">
							<select class="col-xs-12 form-control select2"
								id="destinatariosId" name="destinatariosId" multiple="multiple"
								required>
								<c:forEach items="${destinatarios }" var="destinatario">
									<option value="${destinatario.id }">${destinatario.nome }&nbsp;&nbsp;&laquo;${destinatario.email }&raquo;</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="Assunto:" id="assunto"
								name="assunto" required>
						</div>
						<div class="form-group">
							<textarea id="mensagem" name="mensagem" class="form-control"
								style="height: 300px" required></textarea>
						</div>
						<div class="box-footer ">
							<button type="submit" class="btn btn-success">Enviar</button>
						</div>
					</form:form>
				</div>
				<!-- /.box-body -->
			</div>
		</div>
	</div>
	</section>
</div>
<!-- /.content-wrapper -->



