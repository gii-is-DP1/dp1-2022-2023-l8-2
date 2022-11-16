<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2> Current Games </h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Game name</th>
            <th style="width: 100px;">Creator</th>
            <th>Players</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                    <c:out value="${game.gameName}"/>
                </td>
                <td>
                    <c:out value="${game.creatorUsername}"/>
                </td>
                <td>
                    <c:forEach var="player" items="${game.players}">
                        <c:out value="${player.user.username}  "/>
                    </c:forEach>
                </td>  
                <td>
                    <spring:url value="/games/join/{gameId}" var="joinGameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(joinGameUrl)}">
                        <p>JOIN GAME</p>
                    </a>
                </td>            
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>