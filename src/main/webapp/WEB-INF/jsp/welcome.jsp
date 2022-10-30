<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>

<sieteislas:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <h2>Title: ${title}</h2>
        <h2>Group: ${group}</h2>
        <ul>
        	<c:forEach items="${people}" var="person">
        		<li>${person.firstName} ${person.lastName}</li>
        	</c:forEach>
        </ul>
        <img src="/resources/images/logoPNG_3.png"/>
    </div>
</sieteislas:layout>
