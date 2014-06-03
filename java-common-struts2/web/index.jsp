<%@ page import="common.basic.logs.Logger" %>
<%@ page import="common.struts2.logs.LoggerStruts2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>
    java-common-struts2
    <%
        Logger.setLogger(new LoggerStruts2());
        Logger.setDebug(true);
        Logger.e();
    %>
</h1>
</body>
</html>
