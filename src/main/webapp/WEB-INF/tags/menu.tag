<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, games, achievements or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />">
				<img src= "/resources/images/favicon.png" width="100" height="100" align="right">
			</a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<sieteislas:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</sieteislas:menuItem>

				<sieteislas:menuItem active="${name eq 'games'}" url="#"
					title="find games">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Find games</span>
				</sieteislas:menuItem>

				<sieteislas:menuItem active="${name eq 'Players'}" url="/players/"
					title="Players" dropdown="true" >
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Players</span>
					<ul class="dropdown-menu">
						<li>
							<div class="navbar-login">
								<p class="text-left">
									<a href="<c:url value="/players/" />">Players Listing</a>
								</p>
							</div>
						</li>
					</ul>
				</sieteislas:menuItem>

				<sec:authorize access="isAuthenticated()">
					<sieteislas:menuItem active="${name eq 'users'}" url="/users/home/profile" title="profile">
							<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
							<span>Profile</span>
					</sieteislas:menuItem>
				</sec:authorize>

				<sieteislas:menuItem active="${name eq 'achievements'}" url="/statistics/achievements/"
					title="achievements" dropdown="true" >
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Achievements</span>
					<ul class="dropdown-menu">
						<li>
							<div class="navbar-login">
								<p class="text-left">
									<a href="<c:url value="/statistics/achievements/" />">Achievement Listing</a>
								</p>
							</div>
						</li>
						<li class="divider"></li>
						<li>
							<div class="navbar-login">
								<p class="text-left">
									<a href="<c:url value="/statistics/achievements/" />">My Achievements</a>
								</p>
							</div>
						</li>
					</ul>
				</sieteislas:menuItem>

				<sieteislas:menuItem active="${name eq 'gameStatistics'}" url="/statistics/gameStatistics/"
					title="Statistics" dropdown="true" >
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Statistics</span>
					<ul class="dropdown-menu">
						<li>
							<div class="navbar-login">
								<p class="text-left">
									<a href="<c:url value="/statistics/gameStatistics/" />">Game Stats</a>
								</p>
							</div>
						</li>
					</ul>
				</sieteislas:menuItem>


			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>�
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
