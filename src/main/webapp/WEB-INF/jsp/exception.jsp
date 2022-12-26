<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>

<sieteislas:layout pageName="error">

    <spring:url value="/resources/images/error404.png" var="pirateError"/>
    <div style="text-align: center;">
        <img src="${pirateError}" style="width: 300px; height: auto;"/>
        <h2>Looks like an error ocurred! Maybe the thin' ye be lookin' fer does not exist, click 'ere to go to safer waters</h2>
        <a href="/" class="btn btn-default">HOME</a>
    </div>
</sieteislas:layout>
