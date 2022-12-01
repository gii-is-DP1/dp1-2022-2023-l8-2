<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sieteislas:layout pageName="dashboard">
    <h1 style="text-align: center;">DASHBOARD</h1>
    <h1>Number of Games</h1>
    <h2>Global</h2>
    <table id="globalNumberGamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>Minimum per Month</th>
            <th>Maximum per Month</th>
            <th>Average per Month</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <c:out value="${globalNumberGames.totalNumberGames}"/>
            </td>
            <td>
                <c:out value="${globalNumberGames.minNumberGames}"/>
            </td>
            <td>
                <c:out value="${globalNumberGames.maxNumberGames}"/>
            </td>
            <td>
                <c:out value="${globalNumberGames.avgNumberGames}"/>
            </td>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="isAuthenticated()">
        <h2>Games of <sec:authentication property="name"/></h2>
        <table id="userNumberGamesTable" class="table table-striped">
            <thead>
            <tr>
                <th>Total</th>
                <th>Minimum per Month</th>
                <th>Maximum per Month</th>
                <th>Average per Month</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <c:out value="${userNumberGames.totalNumberGamesUser}"/>
                </td>
                <td>
                    <c:out value="${userNumberGames.maxNumberGamesUser}"/>
                </td>
                <td>
                    <c:out value="${userNumberGames.minNumberGamesUser}"/>
                </td>
                <td>
                    <c:out value="${userNumberGames.avgNumberGamesUser}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </sec:authorize>

    <h1>Duration of the Games (in sec)</h1>
    <h2>Global</h2>
    <table id="globalDurationGamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>Minimum</th>
            <th>Maximum</th>
            <th>Average</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <c:out value="${globalTimePlayed.totalTimePlayed}"/>
            </td>
            <td>
                <c:out value="${globalTimePlayed.minTimePlayed}"/>
            </td>
            <td>
                <c:out value="${globalTimePlayed.maxTimePlayed}"/>
            </td>
            <td>
                <c:out value="${globalTimePlayed.avgTimePlayed}"/>
            </td>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="isAuthenticated()">
        <h2>Games of <sec:authentication property="name"/></h2>
        <table id="userDurationGamesTable" class="table table-striped">
            <thead>
            <tr>
                <th>Total</th>
                <th>Minimum</th>
                <th>Maximum</th>
                <th>Average</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <c:out value="${userTimePlayed.totalTimePlayed}"/>
                </td>
                <td>
                    <c:out value="${userTimePlayed.minTimePlayed}"/>
                </td>
                <td>
                    <c:out value="${userTimePlayed.maxTimePlayed}"/>
                </td>
                <td>
                    <c:out value="${userTimePlayed.avgTimePlayed}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </sec:authorize>

    <h1>Gained Points</h1>
    <h2>Global</h2>
    <table id="globalPointsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>Minimum</th>
            <th>Maximum</th>
            <th>Average</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <c:out value="${globalPoints.totalPoints}"/>
            </td>
            <td>
                <c:out value="${globalPoints.minPoints}"/>
            </td>
            <td>
                <c:out value="${globalPoints.maxPoints}"/>
            </td>
            <td>
                <c:out value="${globalPoints.avgPoints}"/>
            </td>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="isAuthenticated()">
        <h2>Games of <sec:authentication property="name"/></h2>
        <table id="userPointsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>Minimum</th>
            <th>Maximum</th>
            <th>Average</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <c:out value="${userPoints.totalPoints}"/>
            </td>
            <td>
                <c:out value="${userPoints.minPoints}"/>
            </td>
            <td>
                <c:out value="${userPoints.maxPoints}"/>
            </td>
            <td>
                <c:out value="${userPoints.avgPoints}"/>
            </td>
        </tr>
        </tbody>
    </sec:authorize>
</sieteislas:layout>
