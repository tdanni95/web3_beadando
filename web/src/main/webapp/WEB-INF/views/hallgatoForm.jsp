<%--
  Created by IntelliJ IDEA.
  User: tdann
  Date: 2019. 11. 26.
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form"
          uri ="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hallgato hozzaadasa</title>
</head>
<body>

${message}
<h2>Hallgato adatai:</h2>
<form:form method="post" action="addHallgato">
    <form:label path="neptunKod">Neptun Kod</form:label>
    <form:input path="neptunKod"/>
    <br>
    <form:label path="Nev">Nev</form:label>
    <form:input path="nev"/>
    <br>
    <form:label path="szulDatum">Szuletesi Datum</form:label>
    <form:input type="date" path="szulDatum"/>
    <br>
    <br>
    <br>
    <form:label path="tipus">Tipus</form:label>
    <form:select path="tipus">
        <form:options items="${tipusok}"/>
    </form:select>
    <br>
    <form:label path="allapot">Allapot</form:label>
    <form:select path="allapot">
        <form:options items="${allapotok}"/>
    </form:select>
    <br>
    <form:label path="szak">Szak</form:label>
    <form:select path="szak">
        <form:options items="${szak}"/>
    </form:select>
    <br>
    <form:label path="orak">Orak</form:label>
    <form:select path="orak" multiple="true">
        <form:options items="${orakTipusa}"/>
    </form:select>
    <input type="submit" value="Küldés">
</form:form>
<form action="${pageContext.servletContext.contextPath}/">
    <input type="submit" value="Mégse">
</form>

</body>
</html>
