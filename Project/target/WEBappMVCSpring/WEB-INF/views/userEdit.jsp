<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${paginaTitel}</title>
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet" >
    </head>
    <body>
        <h2>${paginaTitel}</h2>


        <form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/user/edit">
            <form:errors path="*" class="errorblock" element="div" />

            <table border="0">
                <tbody>
                    <tr>
                        <td>Naam</td>
                        <td><form:input path="name" /></td>
                        <td><form:errors path="name" class="error" /></td>
                    </tr>
                    <tr>
                        <td>Straatnaam</td>
                        <td><form:input path="streetAddress" /></td>
                        <td><form:errors path="streetAddress" class="error" /></td>
                    </tr>
                    <tr>
                        <td>Huisnummer</td>
                        <td><form:input path="houseNumber" /></td>
                        <td><form:errors path="houseNumber" class="error" /></td>
                    </tr>
                    <tr>
                        <td>Plaats</td>
                        <td><form:input path="city" /></td>
                        <td><form:errors path="city" class="error" /></td>
                    </tr>
                    <tr>

                        <td>Rol:</td>
                        <td>
                            <form:select path="role">
                                <form:option value="${user.role.id}">${user.role.name}</form:option>
                                <form:options items="${roleList}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </td>
                        <td><form:hidden path="id"/>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Edit" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>


        </form:form>
    </body>
</html>