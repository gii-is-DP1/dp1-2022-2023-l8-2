<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sieteislas:layout pageName="dashboard">
    <h1>N&#250mero de Partidas</h1>
    <h2>Global</h2>
    <table id="globalNumberGamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>M&#237nimo por Mes</th>
            <th>M&#225ximo por Mes</th>
            <th>Promedio por Mes</th>
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
    <h2>Partidas de <sec:authentication property="name"/></h2>
    <table id="userNumberGamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>M&#237nimo por Mes</th>
            <th>M&#225ximo por Mes</th>
            <th>Promedio por Mes</th>
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

    <h1>Duraci&#243n de las Partidas (en Segundos)</h1>
    <h2>Global</h2>
    <table id="globalNumberGamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>M&#237nimo</th>
            <th>M&#225ximo</th>
            <th>Promedio</th>
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
    <h2>Partidas de <sec:authentication property="name"/></h2>
    <table id="userNumberGamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Total</th>
            <th>M&#237nimo</th>
            <th>M&#225ximo</th>
            <th>Promedio</th>
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

    <h1>Puntos ganados</h1>
    <h2>Global</h2>
    <table id="globalPointsTable" class="table table-striped">
    <thead>
    <tr>
        <th>Total</th>
        <th>M&#237nimo</th>
        <th>M&#225ximo</th>
        <th>Promedio</th>
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
</sieteislas:layout>
