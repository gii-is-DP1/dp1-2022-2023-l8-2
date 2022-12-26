<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	html,body,.container{
    height:100%;
	}
	.container-fluid{
		display:table;
		width: 100%;
		margin-top: -50px;
		padding: 50px 0 0 0; /*set left/right padding according to needs*/
		box-sizing: border-box;
		background-image: url('/resources/images/board/welcome3.png');
		background-size: 100% 100%;
	}
	.row{
		height: 100%;
		display: table-row;
	}
	.row .no-float{
		display: table-cell;
		float: none;
	}
	.board{
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
	.current-player{
		background-color: rgba(210, 105, 30, 0.811); 
		height: 20%;
		border-style: solid;
		border-width: 5px;
		border-color: rgb(247, 173, 0);
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
		width: 80px;
		height: 80px;
		margin-right: 5%;
		border-radius: 5%;
		border: 5px solid #555;
	}
	.island{
		background-color: rgba(245, 222, 179, 0.6);
	}
	.possible-island{
		background-color: rgba(65, 136, 3, 0.81);
		border-style: solid;
		border-width: 5px;
		border-color: rgb(7, 215, 0);
		border-radius: 10px;
		margin: 2px
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
						<c:choose>
							<c:when test="${isCurrentPlayer && game.hasRolledDice && possibleChoices.contains(card)}">
								<div class="possible-island" style="width: 15%; display: inline-block;">
									<h2 color="black" style="text-align: center;">ISLAND ${loop.index + 1}</h2>
									<img src="/resources/images/cards/${card.cardType.name}.png" alt="island" style="width: 150px;">
									
									<spring:url value="/games/gameBoard/{gameId}/chooseCard/{cardId}" var="chooseIsland">
                						<spring:param name="gameId" value="${game.id}"/>
										<spring:param name="cardId" value="${card.id}"/>
            						</spring:url>
									<a href="${fn:escapeXml(chooseIsland)}" class="btn btn-default">CHOOSE</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="board-border island" style="width: 15%; display: inline-block;">
									<h2 color="black" style="text-align: center;">ISLAND ${loop.index + 1}</h2>
									<img src="/resources/images/cards/${card.cardType.name}.png" alt="island" style="width: 150px;">
								</div>
							</c:otherwise>					
						</c:choose>           

					</c:forEach>
					<br>
					<div class="board-border island" style="width: 15%; display: inline-block;">
						<h2 color="black">
							DECK (<c:out value="${fn:length(game.deck)-6}"></c:out>)
						</h2>
						<img src="/resources/images/cards/upsideDown.png" alt="island">
					</div>
				</div>
				<div class="board-element board-border" >
					<div style="display: inline-block; width: 79%; height: 100%; text-align: center;">
						<c:choose>
							<c:when test="${isPlayer}">
								<c:if test="${game.numCardsToPay >= 1 && isCurrentPlayer}">
									<b>YOU HAVE TO DISCARD ${game.numCardsToPay} CARDS:</b>
								</c:if>
								<br>
								<c:forEach items="${principalPlayer.cards}" var="card">
									<div class="board-border-white" style="display: inline-block;">
										<img src="/resources/images/cards/${card.cardType.name}.png" alt="island" style="height: 100px; width: auto">
										<c:if test="${game.numCardsToPay >= 1 && isCurrentPlayer}">
											<spring:url value="/games/gameBoard/{gameId}/discard/{cardId}" var="discardUrl">
												<spring:param name="gameId" value="${game.id}"/>
												<spring:param name="cardId" value="${card.id}"/>
											</spring:url>
											<a href="${fn:escapeXml(discardUrl)}" class="btn btn-default">Discard</a>
										</c:if>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<h1>YOU'RE ON SPECTATOR MODE</h1>
							</c:otherwise>
						</c:choose>
					</div>
					<div style="display: inline-block; float: right;">
						<c:if test="${isCurrentPlayer && !game.hasRolledDice}">
							<spring:url value="/games/gameBoard/{id}/rollDice" var="rollDice">
								<spring:param name="id" value="${game.id}"/>
							</spring:url>
							<a href="${fn:escapeXml(rollDice)}" class="btn btn-default">ROLL DICE</a>
						</c:if>
						<img src="/resources/images/dado/dado${game.diceRoll + 1}.png" alt="dice" style="width: 80px; height: 80px">
				</div>
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="board-element board-border">
					<div></div>
					<div>
						
					</div>
				<c:forEach items="${game.chat}" var="message">
				    <c:out value="${message.body}"></c:out>
				</c:forEach>
					<div>
						<form:form modelAttribute="game">
							<form:input path="chat" type="text" class="message-input"></form:input>
							<button type="submit" class="btn btn-default">Submit</button>
						</form:form>
					</div>
					
				</div>
				<h2 color="black" class="island board-border" style="text-align: center;">PLAYERS</h2>
				<c:forEach items="${game.players}" var="player">
					<c:choose>
						<c:when test="${ player.user.username.equals(currentPlayerName) }">
							<div class="current-player">
								<div>
									<img src="${player.user.profileImage}" alt="foto_perfil" class="profileImage">
									<img src="/resources/images/cards/upsideDown.png" alt="island" style="height: 80px; width: auto">
									<c:out value="${fn:length(player.cards)} cards"></c:out>
									<img src="/resources/images/ancla.png" alt="current player" style="width: auto; height: 100%; max-height: 40px; float: right; margin-top: 7%;">
								</div>
								<c:out value="${player.user.username}" ></c:out>
							</div>
						</c:when>
						<c:otherwise>
							<div class="board-element board-border">
								<div>
									<img src="${player.user.profileImage}" alt="foto_perfil" class="profileImage">
									<img src="/resources/images/cards/upsideDown.png" alt="island" style="height: 80px; width: auto">
									<c:out value="${fn:length(player.cards)} cards"></c:out>
								</div>
								<c:out value="${player.user.username}"></c:out>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<sieteislas:footer/>
	</body>
</html>