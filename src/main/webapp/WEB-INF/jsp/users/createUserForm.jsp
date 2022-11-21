<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>

<sieteislas:layout pageName="users">

    <h2>
         New User
    </h2>
    
    <form:form modelAttribute="users">
       
        <div class="form-group has-feedback">
        	
        	<p>First Name</p>
            <form:input path="firstName"></form:input>
            <p>Last Name</p>
            <form:input path="lastName"></form:input>
            <p>Username</p>
            <form:input path="username"></form:input>
            <p>Password</p>
            <form:input path="password"></form:input>
            <p>Profile Image</p>
            <form:input path="profileImage"></form:input>
            
        </div>
        
        <div class="form-group">
        
            <div class="col-sm-offset-2 col-sm-10">
            	
            	<button class="btn btn-default" type="submit">Sign Up</button>
            	
            </div>
            
        </div>
        
    </form:form>
    
</sieteislas:layout>