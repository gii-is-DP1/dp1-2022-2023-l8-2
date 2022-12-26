<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="user profile">
    <div style="text-align: center;">
        <h2>INVITE YOUR FRIENDS!</h2>
        <c:choose>
            <c:when test="${fn:length(friends) >= 1}">
                <c:forEach items="${friends}" var="friend">
                    <div style="text-align: center;">
                        <img src="${friend.profileImage}" style="height: 50px; width: 50px;">
                        <c:out value="${friend.username}"/>
                        <spring:url value="/games/lobby/invitation/{gameId}" var="inviteUrl">
                            <spring:param name="gameId" value="${gameId}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(inviteUrl)}" style="color: rgb(29, 202, 29); width: 30px; height: auto;" class="glyphicon glyphicon-plus"></a>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h3>SEEMS LIKE YOU DON'T HAVE FRIENDS... OR ALL YOUR FRIENDS ARE BUSY PLAYING RIGHT NOW!</h3>
            </c:otherwise>
        </c:choose>
        
        <br>
        <spring:url value="/games/lobby/{gameId}" var="backToLobbyUrl">
            <spring:param name="gameId" value="${gameId}"/>
        </spring:url>
        <a href="${fn:escapeXml(backToLobbyUrl)}" class="btn btn-default"> BACK TO LOBBY </a>
    </div>    
          

</petclinic:layout>