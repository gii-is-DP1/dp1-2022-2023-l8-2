<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>

<sieteislas:layout pageName="achievements">

    <jsp:body>
    
        <h2>
            <c:if test="${achievement['new']}">New </c:if> Achievement
        </h2>
        
        <form:form modelAttribute="achievement">
        
            <input type="hidden" name="id" value="${achievement.id}"/>
            
            <div class="form-group has-feedback">
            
                <p>Name</p>
                <sieteislas:inputField name="name"
                label=""></sieteislas:inputField>
                
                <p>Description</p>
                <sieteislas:inputField name="description"
                label=""></sieteislas:inputField>
                
                <p>ImageUrl</p>
                <sieteislas:inputField name="badgeImage"
                label=""></sieteislas:inputField>
                
                <p>Threshold</p>
                <sieteislas:inputField name="threshold"
                label=""></sieteislas:inputField>
                
            </div>
            
            <div class="form-group">
            
                <div class="col-sm-offset-2 col-sm-10">
                
                    <c:choose>
                    
                        <c:when test="${achievement['new']}">
                            <button class="btn btn-default" type="submit">
                            Add Achievement</button>
                        </c:when>
                        
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">
                            Update Achievement</button>
                        </c:otherwise>
                        
                    </c:choose>
                    
                </div>
                
            </div>
            
        </form:form>
        
    </jsp:body>
    
</sieteislas:layout>
