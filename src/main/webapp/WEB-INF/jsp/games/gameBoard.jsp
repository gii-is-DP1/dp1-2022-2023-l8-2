<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	html,body,.container {
    height:100%;
	}
	.container-fluid {
		display:table;
		width: 100%;
		margin-top: -50px;
		padding: 50px 0 0 0; /*set left/right padding according to needs*/
		box-sizing: border-box;
		background-image: url('/resources/images/board/welcome3.png');
		background-repeat: no-repeat;
		background-size: 100% 100%;
	}

	.row {
		height: 100%;
		display: table-row;
	}

	.row .no-float {
		display: table-cell;
		float: none;
	}
	.board {
		background-image: url('/resources/images/board/board8.png');
  		background-repeat: no-repeat;
		background-size: 100% 100%;
		height: 80%;
	}
	.board-element{
		background-color: rgba(241, 202, 139, 0.811); 
		height: 20%;
	
	}
	.board-border{
		border-style: solid;
		border-width: 5px;
		border-color: wheat;
		border-radius: 10px;
		margin: 2px
	}
	.board-border-white{
		border-style: solid;
		border-width: 5px;
		border-color: white;
		border-radius: 10px;
		margin: 2px
	}
	.current-player-border{
		border-style: solid;
		border-width: 5px;
		border-color: rgb(11, 44, 207);
		border-radius: 10px;
		margin: 2px
	}
	.message-input{
		width: 75%; 
		border-style: solid; 
		border-width: 1px; 
		border-color: wheat; 
		border-radius: 10px; 
		margin-left: 3px;
	}
	.profileImage{
		width: 50px;
		height: 50px;
	}

	.island{
		background-color: rgba(245, 222, 179, 0.6);
	}

</style>

<!DOCTYPE html>
<html>
	<sieteislas:htmlHeader/>
	<body class="container-fluid">
		<div class="row">
			<div class="col-md-9 ">
				<div class="board board-border">.
					<c:forEach items="${game.deck}" var="card" varStatus="loop" end="5">
						<div class="board-border island" style="width: 15%; display: inline-block;">
							<h2 color="black">ISLAND ${loop.index + 1}</h2>
							<img src="/resources/images/cards/${card.cardType.name}.png" alt="island">
						</div>
					</c:forEach>
					<br>
					<div class="board-border island" style="width: 15%; display: inline-block;">
						<h2 color="black">DECK
							(<c:out value="${fn:length(game.deck)}"></c:out>)
						</h2>
						<img src="/resources/images/cards/upsideDown.png" alt="island">
					</div>
				</div>
				<div class="board-element board-border" >
					<div style="display: inline-block; width: 79%; height: 100%; text-align: center;">
						<c:choose>
							<c:when test="${isPlayer}">
								<c:forEach items="${principalPlayer.cards}" var="card">
									<div class="board-border-white" style="display: inline-block;">
										<img src="/resources/images/cards/${card.cardType.name}.png" alt="island" style="height: 100px; width: auto">
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<h1>YOU'RE ON SPECTATOR MODE</h1>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="board-border" style="display: inline-block; float: right; width: 20%; height: 100%;">DADO
						<c:if test = "${game.players.get(playerTurn).equals(username)}">
							<spring:url value="/gameBoard/{id}/rollDice" var="rollDice">TIRAR DADO
                			<spring:param name="id" value="${game.id}"/>
            				</spring:url>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="board-element board-border">
					<div></div>
					<div>
						
					</div>
					<div>Aqui van los mensajes</div>
					<div>Aqui van los mensajes</div>
					<div>Aqui van los mensajes</div>
					<div>
						<form>
							<input type="text" class="message-input">
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
					
				</div>
				<h2 color="black" class="island board-border">PLAYERS</h2>
				<c:forEach items="${game.players}" var="player">
					<c:choose>
						<c:when test="${isCurrentPlayer}">
							<div class="board-element current-player-border">
								<img src="${player.user.profileImage}" alt="foto_perfil" class="profileImage">
								<c:out value="${player.user.username}"></c:out>
								<c:out value="${fn:length(player.cards)}"></c:out>
								<c:out value="current"></c:out>
							</div>
						</c:when>
						<c:otherwise>
							<div class="board-element board-border">
								<img src="${player.user.profileImage}" alt="foto_perfil" class="profileImage">
								<c:out value="${player.user.username}"></c:out>
								<c:out value="${fn:length(player.cards)}"></c:out>
							</div>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</div>
		</div>
		<sieteislas:footer/>
	</body>
</html>