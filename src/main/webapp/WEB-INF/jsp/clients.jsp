<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Clients</title>
</head>
<body>
<h1>Clients(JSP)</h1>
<p />
<hr width="100%" />

<table border="1" >
    <tr><th>Nom, prénom</th>
        <th>Prénom</th>
        <th>Ville</th><th>Pays</th></tr>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td>
                <a href="/detail.html?prenom=${client.pnom}" >
                    ${client.nom}, ${client.pnom}
                </a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>

