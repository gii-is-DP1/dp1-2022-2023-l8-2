<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sieteislas:layout pageName="players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
            <tr>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td>
                        <img src="${player.user.profileImage}" alt="profileImage" style="height: 50px; width: 50px;" />
                    </td>
                    <td>
                        <c:out value="${player.user.username}" />
                    </td>
                    <td>
                        <c:if test="${!friendOrSentRequest.contains(player.user.username) && !principalName.equals(player.user.username)}">
                            <spring:url value="/users/friends/sendRequest/{username}" var="friendRequestUrl">
                                <spring:param name="username" value="${player.user.username}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(friendRequestUrl)}" class="btn btn-success">Send Friend Request</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</sieteislas:layout>
