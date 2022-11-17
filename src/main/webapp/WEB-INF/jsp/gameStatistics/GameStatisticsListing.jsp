<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sieteislas:layout pageName="gameStatistics">
    <h2>Games</h2>

    <table id="gamesStatisticsTable" class="table table-striped">
        <thead>
            <tr>
                <th>Game Id</th>
                <th>Game Creator Name</th>
                <th>Game Players Names</th>
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
                        <c:out value="${playerPointsMaps[loop.index]} "/>
                    </td>
                    <td>
                        <c:out value="${game_statistic.duration}" />
                    </td>
                </tr>
                </c:forEach>
        </tbody>
    </table>
</sieteislas:layout>
