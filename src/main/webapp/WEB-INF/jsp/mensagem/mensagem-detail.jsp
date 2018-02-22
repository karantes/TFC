<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<
<style>
@media print {
	body, html, #wrapper {
		width: 100%;
	}
}
</style>

<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-body no-padding">
					<div class="mailbox-read-info">
						<h3>
							<b>${mensagem.assunto }</b>
						</h3>
						<h5>
							de: ${mensagem.remetente.email } <span
								class="mailbox-read-time pull-right"><fmt:formatDate
									value="${mensagem.dtEnvio }" pattern="dd-MM-yyyy HH:mm" /> </span>
						</h5>
						<h5>para: ${mensagem.destinatario.email }</h5>
					</div>
					<!-- /.mailbox-read-info -->

					<div class="mailbox-read-message">
						<p>${mensagem.mensagem }</p>
					</div>
				</div>

			</div>
		</div>
	</div>
	</section>
</div>