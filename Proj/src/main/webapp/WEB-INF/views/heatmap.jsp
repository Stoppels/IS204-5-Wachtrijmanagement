<%-- 
    Document   : heatmap
    Created on : Nov 2, 2015, 7:01:39 PM
    Author     : Stefan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="${stylesheet}" />" rel="stylesheet" >
        <title>Project</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div id="container">
            <div id="map">                
                <center><canvas id="myCanvas">
                        <script src="<c:url value="${Controller}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Canvas}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Heatmap}" />" type="text/javascript"></script>
                </center></canvas>
            </div>

            <c:forEach var="person" items="${list}">
                <script>
                    createPerson(${person.getPersonId()},
                    ${person.timestampArray()},
                    ${person.xArray()},
                    ${person.yArray()});
                </script>
            </c:forEach>

            <div id="sidebar">
                <table id="form">
                    <form name="form">
                        Time: 
                        <input type="time" step="1" id="time1" value="${starttime}">
                        <input type="time" step="1" id="time2" value="${endtime}">
                        <input type="button" value="Enter" onclick="play(
                                        document.getElementById('time1').value,
                                        document.getElementById('time2').value);
                                return false;">
                        <br>
                        Show scenery: <input type="checkbox" id="scenery">
                    </form>
                </table>
                        
                <ul id="stats">
                    <li>${filename}</li>
                </ul>
            </div>
            <br>
        </div>
    </body>
</html>
