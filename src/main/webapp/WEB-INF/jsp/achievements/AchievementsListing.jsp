<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="achievements">
    <h2>Achievements</h2>

    <table id="achievementsTable" class="table table-striped">
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
            <c:forEach items="${achievements}" var="achievement">
                <tr>
                    <td>
                        <c:out value="${achievement.name}" />
                    </td>
                    <td>
                        <c:out value="${achievement.description}" />
                    </td>
                    <td>
                        <img src="${achievement.badgeImage}"
                            style="width: 80px; height:auto" />
                    </td>
                    <td>
                        <c:out value="${achievement.threshold}" />
                    </td>
                    <td>
                        <spring:url value="/statistics/achievements/edit/{achievementId}" var="editUrl">
                            <spring:param name="achievementId" value="${achievement.id}" />
                        </spring:url>
                        <a href="${fn:escapeXml(editUrl)}" class="glyphicon glyphicon-pencil"></a>
                    </td>
                    <td>
                        <spring:url value="/statistics/achievements/delete/{achievementId}" var="deleteUrl">
                            <spring:param name="achievementId" value="${achievement.id}" />
                        </spring:url>
                        <a href="${fn:escapeXml(deleteUrl)}" class="glyphicon glyphicon-trash"></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</petclinic:layout>