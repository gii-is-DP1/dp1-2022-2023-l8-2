<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <jsp:body>
        <h2>
            <c:if test="${achievement['new']}">New </c:if> Achievement
        </h2>
        <form:form modelAttribute="achievement">
            <input type="hidden" name="id" value="${achievement.id}"/>
            <div class="form-group has-feedback">
                <p>Name</p>
                <form:input path="name"></form:input>
                <p>Description</p>
                <form:input path="description"></form:input>
                <p>ImageUrl</p>
                <form:input path="badgeImage"></form:input>
                <p>Threshold</p>
                <form:input path="threshold"></form:input>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${achievement['new']}">
                            <button class="btn btn-default" type="submit">Add Achievement</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Achievement</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!pet['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>