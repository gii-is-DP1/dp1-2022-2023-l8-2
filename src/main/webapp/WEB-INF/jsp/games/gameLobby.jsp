<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<sieteislas:layout pageName="Game lobby">
    <div style="display:inline-flex; width: 100%; height: 20%;">
        <h1 style="position: absolute; left:40%"> WELCOME TO <i><c:out value="${game.gameName}" /></i> LOBBY </h2>
        <p style="margin-left: auto;">
            <spring:url value="/games/lobby/{id}/exit" var="lobbyExitUrl">
                <spring:param name="id" value="${game.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(lobbyExitUrl)}" class="glyphicon glyphicon-log-out" style="font-size: 200%; color: darkgoldenrod"></a>
        </p>    
    </div>
    <div style="position:absolute; left:45%; bottom: 5%;">
        <c:choose>
            <c:when test="${fn:length(game.players) > 1}">
                <spring:url value="/games/start/{gameId}" var="startGameUrl">
                    <spring:param name="gameId" value="${game.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(startGameUrl)}" class="btn btn-success">Start Game!</a>
            </c:when>
            <c:otherwise>
                <a href="" class="btn btn-danger">There must be at least 2 players to start the game!</a>
            </c:otherwise>
        </c:choose>
    </div>
    <br>
    <h2>PLAYERS (<c:out value="${fn:length(game.players)}"/>/4)</h2>
    <div style="display:inline-flex; position:absolute; left: 5%; width: 100%;">
        <c:forEach items="${game.players}" var="player">
            <div style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); margin: 10px; text-align: center; width: 20%; height: 20%;">
                <div style="width: 100%; height: 100%;">
                    <img src="${player.user.profileImage}" alt="image" style="width: 100%; height: 100%; object-fit: cover;">
                </div>
                <h4 style="color: black;"><c:out value="${player.user.username}" /></h4>
                
                    <c:if test="${!player.user.username.equals(game.creatorUsername)}">
                        <spring:url value="/games/lobby/{id}/kick/{playerId}" var="lobbyKickUrl">
                            <spring:param name="id" value="${game.id}"/>
                            <spring:param name="playerId" value="${player.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(lobbyKickUrl)}" class="glyphicon glyphicon-remove">
                            <p style="color: darkgoldenrod;">KICK</p>
                        </a>
                    </c:if>
                    <c:if test="${player.user.username.equals(game.creatorUsername)}">
                        <p class="glyphicon glyphicon-king" style="color: rgb(112, 107, 107); font-size: 150%;"></p>
                    </c:if>
               
            </div>
        </c:forEach>
    </div>
        
</sieteislas:layout>


