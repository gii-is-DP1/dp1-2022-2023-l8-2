<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
<div style="text-align:center"> 
    <a href="/games/new" class="btn btn-default">CREATE NEW GAME!</a>   
</div> 
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
                     <c:choose>
                        <c:when test="${not actualPlayer.game.id.equals(game.id)}">
                        
                    <c:choose>
                        <c:when test="${4 > fn:length(game.players)}">
                            <spring:url value="/games/join/{gameId}" var="joinGameUrl">
                                <spring:param name="gameId" value="${game.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(joinGameUrl)}">
                                <p class="btn btn-success">JOIN GAME</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="#" class="btn btn-danger">FULL!</a>
                        </c:otherwise>
                    </c:choose>
                 
                  </c:when>
                        <c:otherwise>
                            <spring:url value="/games/join/{gameId}" var="joinGameUrl">
                                <spring:param name="gameId" value="${game.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(joinGameUrl)}">
                                <p class="btn btn-primary">RETURN TO LOBBY</p>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </td>            
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>