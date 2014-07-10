<%@ page import="common.basic.logs.Logger" %>
<%@ page import="common.struts2.logs.LoggerStruts2" %>
<%@ page import="common.CommonInit" %>
<%@ page import="common.basic.facades.jsons.gson.JsonEngineGson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>
    java-common-struts2
    <%
        CommonInit.init(new LoggerStruts2(), true, new JsonEngineGson());
        Logger.e();
    %>
</h1>
</body>
</html>
