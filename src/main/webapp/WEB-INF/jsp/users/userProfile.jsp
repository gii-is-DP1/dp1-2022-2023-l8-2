<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>



<petclinic:layout pageName="user profile">

    <h2>${user.firstName} Profile</h2>
    <div>
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <td><b><c:out value="${user.firstName} ${user.lastName}"/></b></td>
            </tr>
            <tr>
                <th>Username</th>
                <td><c:out value="${user.username}"/></td>
            </tr>
            <tr>
                <th>Image</th>
                <td><img src="${user.profileImage}" style="width: 80px; height:auto"/></td>
            </tr>
            <tr>
                <th>Achievements</th>
                    <td>
                        <c:choose>
                            <c:when test="${fn:length(user.player.achievements) < 1}">
                                <p>NO ACHIEVEMENTS!</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${user.player.achievements}" var="achievement">
                                    <div class="achievement-profile">
                                        <div>
                                            <img src="${achievement.badgeImage}" style="height: 50px; width: 50px;">
                                            <c:out value="${achievement.name}"/>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>   
            </tr>
        </table>
    </div>
    <div>
        <p>FRIENDS</p>
        <c:forEach items="${user.friends}" var="friend">
                <div style="text-align: center;">
                    <img src="${friend.profileImage}" style="height: 50px; width: 50px;">
                    <c:out value="${friend.username}"/>
                    <c:if test="${user.username.equals(principalName) || isAdmin}">  
                        <spring:url value="/users/friends/remove/{friendUsername}" var="removeFriendUrl">
                            <spring:param name="friendUsername" value="${friend.username}"/>
                        </spring:url>
                        <td width="5%">&nbsp;</td>
                        <a href="${fn:escapeXml(removeFriendUrl)}" class="btn btn-danger">Remove Friend</a>
                        
                        <c:if test="${!friend.player.game.equals(null) && friend.player.game.active.equals(false)}">
                             <spring:url value="/games/gameBoard/{gameId}" var="watchGame">
                                <spring:param name="gameId" value="${friend.player.game.id}"/>
                            </spring:url>
                            
                           <c:choose>
                               <c:when test="${not actual.game.id.equals(friend.player.game.id)}">
                                   <a href="${fn:escapeXml(watchGame)}" class="btn btn-success">Watch Game</a>
                               </c:when>
                               <c:otherwise>
                                   <a href="${fn:escapeXml(watchGame)}" class="btn btn-primary">Return To Game</a>
                           </c:otherwise>
                         </c:choose>
                            
                        </c:if>
                        
                    </c:if>
                </div>
        </c:forEach>
    </div>
    <c:if test="${user.username.equals(principalName) || isAdmin}">    
        <div>
           
            <p>FRIEND REQUESTS</p>
            <c:forEach items="${friendRequests}" var="request">
                    <div style="text-align: center;">
                        <img src="${request.sender.profileImage}" style="height: 50px; width: 50px;">
                        <c:out value="${request.sender.username}"/>

                        <spring:url value="/users/friends/acceptRequest/{requestId}" var="acceptRequestUrl">
                            <spring:param name="requestId" value="${request.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(acceptRequestUrl)}" class="btn btn-success">Accept request</a>
                        
                        <spring:url value="/users/friends/denyRequest/{requestId}" var="denyRequestUrl">
                            <spring:param name="requestId" value="${request.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(denyRequestUrl)}" class="btn btn-danger">Deny request</a>
                    </div>
            </c:forEach>
        </div>
    </c:if>

    
    <c:if test="${user.username.equals(principalName) || isAdmin}">
        <spring:url value="/users/edit/{username}" var="editUrl">
        	<spring:param name="username" value="${user.username}"/>
    	</spring:url>
        <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit User</a>
    </c:if>
    
    <c:if test="${isAdmin}">
	    <spring:url value="/users/delete/{username}" var="deleteUrl">
	        <spring:param name="username" value="${user.username}"/>
	    </spring:url>
	        <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete User</a>
    </c:if>

    

</petclinic:layout>