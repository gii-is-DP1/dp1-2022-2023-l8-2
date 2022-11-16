<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sieteislas:layout pageName="My achievements">
    <h2>My achievements</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Image</th>
                <th>Threshold</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${player.achievements}" var="player">
                <tr>
                    <td>
                        <c:out value="${player.achievement.name}" />
                    </td>
                    <td>
                        <c:out value="${player.achievement.description}" />
                    </td>
                    <td>
                        <img src="${player.achievement.badgeImage}"
                            style="width: 80px; height:auto" />
                    </td>
                    <td>
                        <c:out value="${player.achievement.threshold}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
</sieteislas:layout>
