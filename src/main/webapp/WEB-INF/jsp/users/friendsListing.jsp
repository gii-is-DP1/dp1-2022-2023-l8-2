<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sieteislas:layout pageName="friends">
    <h2>Friends</h2>

    <table id="friendsTable" class="table table-striped">
        <thead>
        
            <tr>
                <th>Image</th>
                <th>Username</th>
                <th></th>
            </tr>
            
        </thead>
        
        <tbody>
        
            <c:forEach items="${user}" var="user">
            
                <tr>
                
                    <td>
                        <img src="${user.profileImage}"
                            style="width: 80px; height:auto" />
                    </td>
                    
                    <td>
                        <c:out value="${user.username}" />
                    </td>
                    
                    <c:if test="${isAdmin}"> //cambiar el test
                    
                    	<c:if test="${isAdmin}"> //cambiar el test
                    
		                    <td>
		                        <spring:url value="/friends/{username}/accept/{username}" var="okUrl">
		                            <spring:param name="userId" value="${user.username}" />
		                        </spring:url>
		                        <a href="${fn:escapeXml(okUrl)}" class="glyphicon glyphicon-check"></a>
		                    </td>
		                    
	                    </c:if>
                    
	                    <td>
	                        <spring:url value="/friends/{username}/delete/{username}" var="deleteUrl">
	                            <spring:param name="userId" value="${user.username}" />
	                        </spring:url>
	                        <a href="${fn:escapeXml(deleteUrl)}" class="glyphicon glyphicon-trash"></a>
	                    </td>
	                    
                    </c:if>
                    
                </tr>
                
            </c:forEach>
            
        </tbody>
        
    </table>
    
</sieteislas:layout>
