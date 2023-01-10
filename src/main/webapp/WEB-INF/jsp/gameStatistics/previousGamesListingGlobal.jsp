<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sieteislas:layout pageName="previousGamesGlobal">
    <h1 style="text-align: center;">PREVIOUS GAMES (GLOBALLY)</h1>
    <table id="previousGamesGlobalTable" class="table table-striped" style="justify-content: space-around;">
        <thead>
            <tr>
                <th>Game Id</th>
                <th>Game Creator</th>
                <th>Players</th>
                <th>Duration (in sec)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${gameStatistics}" var="game_statistic" varStatus="loop">
                <tr>
                    <td>
                        <c:out value="${game_statistic.game.id}" />
                    </td>
                    <td>
                        <c:out value="${game_statistic.gameCreatorName}" />
                    </td>
                    <td>
                    <c:forEach items="${playerPointsMaps[loop.index]}" var="item" varStatus="status">
                        ${item}<c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                    </td>
                    <td>
                        <c:out value="${game_statistic.duration}" />
                    </td>
                </tr>
                </c:forEach>
        </tbody>
    </table>
</sieteislas:layout>
