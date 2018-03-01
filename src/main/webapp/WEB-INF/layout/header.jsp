<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <c:url value="resources/" /> -->
<header class="main-header">

	<!-- Logo -->
	<a href="/home.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini">TFC</span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg">TFC</span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- Messages: style can be found in dropdown.less-->
				<li class="dropdown messages-menu">
					<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="fa fa-envelope-o"></i> <c:if
							test="${messages.size() > 0 }">
							<span class="label label-info">${messages.size() }</span>
						</c:if>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Você tem ${messages.size() } mensagens</li>
						<li>
							<!-- inner menu: contains the messages -->
							<ul class="menu">
								<li><c:forEach items="${messages }" var="message">
										<a href="/mensagem-detail/${message.id }.html">
											<h4>${message.assunto }</h4> <!-- The message -->
											<p>de: ${message.remetente.nome }</p>
										</a>
									</c:forEach></li>
								<!-- end message -->
							</ul> <!-- /.menu -->
						</li>
						<li class="footer"><a href="/mensagens-recebidas.html">Ver
								Todas As Mensagens</a></li>
					</ul>
				</li>
				<!-- /.messages-menu -->

				<!-- Notifications Menu -->
				<li class="dropdown tasks-menu">
					<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span
						class="label label-danger">9</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">You have 9 tasks</li>
						<li>
							<!-- Inner menu: contains the tasks -->
							<ul class="menu">
								<li>
									<!-- Task item --> <a href="#"> <!-- Task title and progress text -->
										<h3>
											Design some buttons <small class="pull-right">20%</small>
										</h3> <!-- The progress bar -->
										<div class="progress xs">
											<!-- Change the css width attribute to simulate progress -->
											<div class="progress-bar progress-bar-aqua"
												style="width: 20%" role="progressbar" aria-valuenow="20"
												aria-valuemin="0" aria-valuemax="100">
												<span class="sr-only">20% Complete</span>
											</div>
										</div>
								</a>
								</li>
								<!-- end task item -->
							</ul>
						</li>
						<li class="footer"><a href="#">View all tasks</a></li>
					</ul>
				</li>
				<!-- User Account Menu -->
				<li class="dropdown user user-menu">
					<!-- Menu Toggle Button --> <a
					href="/usuario-detail/${user.id }.html" class="dropdown-toggle">
						<span class="hidden-xs">${pageContext.request.userPrincipal.name }</span>
				</a>
				</li>

				<li class="dropdown user user-menu"
					style="background-color: rgb(0, 153, 51);"><a
					href="/logout.html"> <span class="hidden-xs">Sair</span>
				</a></li>
			</ul>
		</div>
	</nav>
</header>