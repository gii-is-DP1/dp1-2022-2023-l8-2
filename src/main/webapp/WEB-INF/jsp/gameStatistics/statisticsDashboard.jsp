<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sieteislas:layout pageName="dashboard">
    <h1>N&#250mero de Partidas</h1>
    <h2>Global</h2>
    <h2>Tus Partidas</h2>

    <h1>Duraci&#243n de las Partidas</h1>
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
    <h2>Tus Partidas</h2>
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
</sieteislas:layout>
