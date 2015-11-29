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
            <div id="sidebar">
                <table id="form">
                    <form name="form">
                        <!--<label class="timeLabel">Time:</label>--> 
                        <input class="start" type="time" step="1" id="time1" value="${starttime}">
                        <input class="end" type="time" step="1" id="time2" value="${endtime}">
                        <input class="enter" type="button" value="Enter" onclick="play(
                                        document.getElementById('time1').value,
                                        document.getElementById('time2').value);
                                return false;">
                        <input class="reset" type="button" value="Reset" onclick="stop();">
                        <br><br>
                        Line:
                        <br>
                        <select name="dropDown" id="dropDown" onChange="highlight();" />
                        <br>
                        <input class="enter" type="button" value="Clear" onclick="clearLines();" />
                    </form>
                </table>
                <br>
                Scenery: <tab><input type="checkbox" id="scenery" checked></tab>
                <br>
                Tracks: <tab><input type="checkbox" id="tracks" checked></tab>

                <div id="stats">
                    <p>${filename}</p>
                </div>
            </div>
            <div id="map">                
                <center><canvas id="myCanvas">
                        <script src="<c:url value="${Controller}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Canvas}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Snapshot}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Heatmap}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Draw}" />" type="text/javascript"></script>
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


            <br>
        </div>
    </body>
</html>
