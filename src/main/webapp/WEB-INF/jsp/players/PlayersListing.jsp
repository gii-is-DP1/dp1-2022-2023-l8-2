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
                <th>Username</th>
                <th>Cards</th>
                <th>Achievements</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td>
                        <c:out value="${player.user.username}" />
                    </td>
                    <td>
                        <c:out value="${player.cards}" />
                    </td>
                    <td>
                        <c:out value="${player.achievements}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</sieteislas:layout>
