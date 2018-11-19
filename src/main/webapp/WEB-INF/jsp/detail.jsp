<%--
  Created by IntelliJ IDEA.
  User: CDI
  Date: 16/11/2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détails client</title>
</head>
<body>
<h1>Détails client</h1>

    <ul>
        <li>${client.nom}</li>
        <li> ${client.pnom} </li>
        <li> ${client.loc} </li>
        <li> ${client.pays} </li>
    </ul>

</body>
</html>
