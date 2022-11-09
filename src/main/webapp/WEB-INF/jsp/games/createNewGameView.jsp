<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>
        <c:if test="${game['new']}">New </c:if> Game
    </h2>
    <div>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error} "/>
        </c:forEach>
    </div>
    <form:form modelAttribute="game" class="form-horizontal" id="add-owner-form">
        
        <input type="hidden" name="id" value="${game.id}"/>    
        <input type="hidden" name="active" value="${game.active}"/>    
        <input type="hidden" name="diceRoll" value="${game.diceRoll}"/>    
        <input type="hidden" name="turnNum" value="${game.turnNum}"/>    
        <input type="hidden" name="duration" value="${game.duration}"/>    
        <input type="hidden" name="islands" value="${game.islands}"/>    
        <input type="hidden" name="creatorUsername" value="${game.creatorUsername}"/>    
        <input type="hidden" name="statistics" value="${game.statistics}"/>    
        <input type="hidden" name="chat" value="${game.chat}"/>    
        <input type="hidden" name="deck" value="${game.deck}"/>    
        <input type="hidden" name="players" value="${game.players}"/>

        <div class="form-group has-feedback">
            <petclinic:inputField label="Game name" name="gameName"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${game['new']}">
                        <button class="btn btn-default" type="submit">Add Game</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Game</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>