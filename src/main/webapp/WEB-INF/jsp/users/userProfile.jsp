<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="user profile">

    <h2>${user.firstName} Profile</h2>

    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.firstName} ${user.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><c:out value="${user.username}"/></td>
        </tr>
        <tr>
            <th>Image</th>
            <td><img src="${user.profileImage}" style="width: 80px; height:auto"/></td>
        </tr>
         <tr>
            <th>Achievements</th>
            <td><c:out value="${user.player.achievements}"/></td>
        </tr>
    </table>

    
    <c:if test="${user.username.equals(principalName) || isAdmin}">
        <spring:url value="/users/edit/{username}" var="editUrl">
        	<spring:param name="username" value="${user.username}"/>
    	</spring:url>
        <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit User</a>
    </c:if>
    
    <c:if test="${isAdmin}">
	    <spring:url value="/users/delete/{username}" var="deleteUrl">
	        <spring:param name="username" value="${user.username}"/>
	    </spring:url>
	        <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete User</a>
    </c:if>
    

</petclinic:layout>