<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: tdann
  Date: 2019. 11. 26.
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Hallgatok</title>
</head>
<body>

<c:if test="${!empty hallgatok}">

<h2>Hallgatok az adatbazisban!</h2>

<table frame="border" width="250" rules="all">
    <c:forEach items="${hallgatok}" var="hallgato">
        <tr>
            <td> <a href="${pageContext.servletContext.contextPath}/hallgato/${hallgato.neptunKod}">
                    ${hallgato.neptunKod}</a>
            </td>
            <td>${hallgato.nev}</td>
            <td>${hallgato.tipus}</td>
            <td>${hallgato.szak}</td>
        </tr>

    </c:forEach>
</table>
</c:if>
<c:if test="${empty hallgatok}">
Nincs hallgato az adatbázisban :C
</c:if>

<form action="${pageContext.servletContext.contextPath}/addHallgato">
    <input type="submit" value="Add hallgato">
</form>
<form action="${pageContext.servletContext.contextPath}/">
    <input type="submit" value="Home">
</form>
