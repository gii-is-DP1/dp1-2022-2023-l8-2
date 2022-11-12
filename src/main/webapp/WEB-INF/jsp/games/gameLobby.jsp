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
        <h1> Game name: <c:out value="${game.gameName}" /> </h2>
        <p style="margin-left: auto;">
            <a href="#" class="glyphicon glyphicon-log-out" style="font-size: 200%;"></a>
        </p>    
    </div>

    
    <h2>PLAYERS (<c:out value="${fn:length(game.players)}"/>/4)</h2>
    <div style="display:inline-flex;">
        <c:forEach items="${game.players}" var="player">
            <div style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); max-width: 300px; margin: auto; text-align: center; ">
                <img src="${player.user.profileImage}" alt="image" style="width: 100%;">
                <h4><c:out value="${player.user.username}" /></h4>

                <c:if test="${principalName.equals(game.creatorUsername)}">
                    <c:if test="${!player.user.username.equals(game.creatorUsername)}">
                        <spring:url value="/games/lobby/{id}/kick/{username}" var="lobbyKickUrl">
                            <spring:param name="username" value="${player.user.username}"/>
                            <spring:param name="id" value="${game.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(lobbyKickUrl)}" class="glyphicon glyphicon-remove"></a>
                    </c:if>
                </c:if>
            </div>
        </c:forEach>
    </div>
</sieteislas:layout>


