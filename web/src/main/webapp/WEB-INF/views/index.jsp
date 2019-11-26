
<%@page isELIgnored = "false" %>
<html>
<body>
<h2>Hello World!</h2>
<h1>${message}</h1>

<form action="${pageContext.servletContext.contextPath}/hallgatok">
    <input type="submit" value="Hallgatok">
</form>
<form action="${pageContext.servletContext.contextPath}/addHallgato">
    <input type="submit" value="Add hallgato">
</form>

</body>
</html>
