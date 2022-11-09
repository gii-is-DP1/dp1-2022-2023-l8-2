<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="user edit">
    <h2>
        User
    </h2>
    <div>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error} "/>
        </c:forEach>
    </div>
    <form:form modelAttribute="user" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <input type="hidden" name="password" value="ps"/>    
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <petclinic:inputField label="profileImage" name="profileImage"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Update Owner</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>