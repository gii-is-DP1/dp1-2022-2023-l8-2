<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sieteislas:layout pageName="previousGamesUser">
    <sec:authorize access="isAuthenticated()">
    <h1 style="text-align: center;">YOUR PREVIOUS GAMES</h1>
        <table id="previousGamesUserTable" class="table table-striped">
            <thead>
            <tr>
                <th>Game Id</th>
                <th>Game Creator</th>
                <th>Players</th>
                <th>Duration (in sec)</th>
                <th>Your Gained Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${previousGamesUser['gameId']}" varStatus="loop">
                <tr>
                    <td>
                        <c:out value="${previousGamesUser['gameId'][loop.index]}" />
                    </td>
                    <td>
                        <c:out value="${previousGamesUser['gameCreator'][loop.index]}" />
                    </td>
                    <td>
                    <c:forEach items="${playerPointsMaps[loop.index]}" var="item" varStatus="status">
                        ${item}<c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                    </td>
                    <td>
                        <c:out value="${previousGamesUser['gameDuration'][loop.index]}" />
                    </td>
                    <td>
                        <c:out value="${previousGamesUser['gamePoints'][loop.index]}" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </sec:authorize>
</sieteislas:layout>
