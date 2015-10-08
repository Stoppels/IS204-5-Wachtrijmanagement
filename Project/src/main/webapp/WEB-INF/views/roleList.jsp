<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Rollen</title>
        <link href="<c:url value="/resources/css/base.css" />" rel="stylesheet" >
        <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" >
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" >
        <link href="<c:url value="/resources/css/normalize.css" />" rel="stylesheet" >
        <link href="<c:url value="/resources/css/skeleton.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>Rollen</h2>
        <c:choose>
              <c:when test="${roleList.size() != 0}">
                <!-- Wanneer er rollen opgeslagen zijn, worden ze hier getoond -->
                <table class="roleList">
                    <tr>
                        <td>
                            <strong>Naam</strong>
                        </td>
                        <td>
                            <strong>Adres</strong>
                        </td>
                        <td>
                            <strong>Huisnummer</strong>
                        </td>
                        <td>
                            <strong>Plaats</strong>
                        </td>
                         <td>
                            <strong>Rol</strong>
                        </td>
                         <td>
                            <strong>Id</strong>
                        </td>
                    </tr>
                    <c:forEach var="user" items="${userList}">
                        <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.streetAddress}</td>
                            <td>${user.houseNumber}</td>
                            <td>${user.city}</td>
                            <td>${user.role.name}</td>
                            <td>${user.id}</td>
                            <td><a href="${pageContext.request.contextPath}/user/edit/${user.id}">Wijzig</a> </td>
                            <td><a href="${pageContext.request.contextPath}/user/remove/${user.id}">Verwijder</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <!-- Als er geen gebruikers zijn, wordt deze melding getoond -->
                Er zijn geen rollen gevonden.
            </c:otherwise>
        </c:choose>
        <p>
            <a href="${pageContext.request.contextPath}/user/add">Maak nieuwe gebruiker aan</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/index">Terug naar de index</a>
        </p>
    </body>
</html>
