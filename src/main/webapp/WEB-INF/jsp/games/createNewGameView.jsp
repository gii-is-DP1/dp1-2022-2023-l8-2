<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>

<sieteislas:layout pageName="games">
    <h2>
        <c:if test="${game['new']}">New </c:if> Game
    </h2>
    <div>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error} "/>
        </c:forEach>
    </div>

    <c:choose>
        <c:when test="${empty actualPlayer.game}">
            <form:form modelAttribute="game" class="form-horizontal" id="add-game-form">
                <div class="form-group">
                    <sieteislas:inputField label="Game name" name="gameName"/>
                </div>
            
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Add Game</button>
                    </div>
                </div>
            </form:form>
            </c:when>
	        <c:otherwise>
	            <div style="text-align:center"> 
	                <spring:url value="/games/active" var="returnListUrl">
                    </spring:url>
                        <a href="${fn:escapeXml(returnListUrl)}">
                            <p class="btn btn-danger">You can not create a new game if you are already playing or in lobby!</p>
                        </a>
			    </div>
        </c:otherwise>
    </c:choose> 
    
</sieteislas:layout>