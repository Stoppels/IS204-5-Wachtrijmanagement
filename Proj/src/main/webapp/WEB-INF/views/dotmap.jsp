<%-- 
    Document   : snapshot
    Created on : Nov 2, 2015
    Author     : IS204-5
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
            <div id="sidebar">
                <form name="form">
                    <p>
                        <label>Time:</label>
                        <br>
                        <input class="start" type="time" step="1" id="time1" value="${starttime}" />
                        <input class="end" type="time" step="1" id="time2" value="${endtime}" />
                        <input class="enter" id="Start" type="button" value="Start" onclick="play(
                                        document.getElementById('time1').value,
                                        document.getElementById('time2').value);" />
                        <input class="reset" type="button" value="Reset" onclick="stop();" />
                    </p>
                    <p>
                        <label>Play speed: <label id="speed">1000</label> ms</label>
                        <br>
                        <input id="slowSpeed" type="button" value="Slow down" onclick="slowDown();" />
                        <input id="highSpeed" type="button" value="Speed up" onclick="speedUp();" />
                    </p>
                    <p>
                        <label>Line alert:</label>
                        <br>
                        <select name="dropDown" id="dropDown" onChange="highlight();" />
                        <br>
                        <input class="enter" type="button" value="Clear lines" onclick="clearSessions();" />
                    </p>
                </form>
                <p>
                    <label>Scenery: </label><tab><input type="checkbox" id="scenery" checked></tab>
                    <br>
                    <label>Tracks: </label><tab><input type="checkbox" id="tracks"></tab>
                </p>

            </div>
            <div id="map">                
                <canvas id="myCanvas">
                    <script src="<c:url value="${Controller}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Canvas}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Interface}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Player}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Dotmap}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Draw}" />" type="text/javascript"></script>
                    <script src="<c:url value="${LinesIntersect}" />" type="text/javascript"></script>
                    <img src="<c:url value="${counter}" />" type="image">
                </canvas>

                <c:forEach var="person" items="${list}">
                    <script>
                            createPerson(${person.getPersonId()},
                        ${person.timestampArray()},
                        ${person.xArray()},
                        ${person.yArray()});
                    </script>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
