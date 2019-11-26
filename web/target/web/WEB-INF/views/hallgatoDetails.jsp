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
    <title>${hallgato.neptunKod} adatai</title>
</head>
<body>

<h2>${hallgato.nev} adatai!</h2>

<p>Neptunkod: ${hallgato.neptunKod}</p>
<p>Szuletett: ${hallgato.szulDatum}</p>
<p>Jelenlegi felev allapota: ${hallgato.allapot}</p>
<p>Tanulmanyok tipusa: ${hallgato.tipus}</p>
<p>Szak: ${hallgato.szak}</p>
<p>Orai:</p>
<ul>
    <c:forEach items="${hallgato.orak}" var="ora">
        <li>${ora}</li>
    </c:forEach>
</ul>

<form action="${pageContext.servletContext.contextPath}/addHallgato">
    <input type="submit" value="Add hallgato">
</form>
<form action="${pageContext.servletContext.contextPath}/">
    <input type="submit" value="Home">
</form>
<form action="${pageContext.servletContext.contextPath}/hallgatok">
    <input type="submit" value="Hallgatok">
</form>
