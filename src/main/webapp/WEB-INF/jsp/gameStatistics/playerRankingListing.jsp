<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<sieteislas:layout pageName="previousGames">
    <h1 style="text-align: center;">Global Player Ranking</h1>
    <table id="previousGamesGlobalTable" class="table table-striped">
        <thead>
            <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Games Won</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${playerRanking}" var="player_ranking" varStatus="loop">
                <tr>
                    <td>
                        <c:out value="${player_ranking.rank}" />
                    </td>
                    <td>
                        <c:out value="${player_ranking.player}" />
                    </td>
                    <td>
                        <c:out value="${player_ranking.wins}" />
                    </td>
                </tr>
                </c:forEach>
        </tbody>
    </table>
</sieteislas:layout>
