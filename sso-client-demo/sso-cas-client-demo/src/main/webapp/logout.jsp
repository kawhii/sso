<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%
    session.invalidate();
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CAS Example Java Web App</title>
</head>
<body>
<h1>CAS Example Java Web App</h1>
<p>Application session is now invalidated. You may also issue a request to "/cas/logout" to destroy the CAS SSO Session as well.</p>
<hr>

<a href="index.jsp">Back to Home</a>
</body>
</html>
