<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<sieteislas:layout pageName="playerRanking">
    <h1 style="text-align: center;">GLOBAL PLAYER RANKING</h1>
    <h2 style="text-align: center;"> MOST POINTS GAINED</h2>
    <table id="previousGamesGlobalTable" class="table table-striped">
        <thead>
            <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Total Points</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${playerRankingPoints['pointsUsernames']}" varStatus="loop">
                <tr>
                    <td>
                        <c:out value="${loop.count}" />
                    </td>
                    <td>
                        <c:out value="${playerRankingPoints['pointsUsernames'][loop.index]}" />
                    </td>
                    <td>
                        <c:out value="${playerRankingPoints['points'][loop.index]}" />
                    </td>
                </tr>
                </c:forEach>
        </tbody>
    </table>

    <h2 style="text-align: center;"> MOST GAMES WON</h2>
    <table id="previousGamesGlobalTable" class="table table-striped">
        <thead>
        <tr>
            <th>Rank</th>
            <th>Player</th>
            <th>Games Won</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${playerRankingWins}" varStatus="loop">
            <tr>
                <td>
                    <c:out value="${loop.count}" />
                </td>
                <td>
                    <c:out value="${playerRankingWins.get(loop.index).getKey()}" />
                </td>
                <td>
                    <c:out value="${playerRankingWins.get(loop.index).getValue()}" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</sieteislas:layout>
