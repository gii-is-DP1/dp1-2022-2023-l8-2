<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>

<sieteislas:layout pageName="error 404">

    <spring:url value="/resources/images/pets.png" var="pirateImage"/>
    <img src="${petsImage}"/>

    <h2>ERROR 404</h2>
    <h2>The thin' ye be lookin' fer does not exist, click 'ere to go to safer waters</h2>
    <a href="/" class="btn btn-default">HOME</a>

</sieteislas:layout>