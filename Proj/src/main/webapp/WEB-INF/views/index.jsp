<%-- 
    Document   : index
    Created on : Nov 2, 2015, 7:01:39 PM
    Author     : Stefan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="template.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
        <div id="container">
            <div id="map">
                <center><canvas id="myCanvas">
                        <script src="<c:url value="${Canvas}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Home}" />" type="text/javascript"></script>
                </center></canvas>
            </div>
            <div id="sidebar">
                <ul id="nav">

                </ul>
                <table id="form">
                    <form name="form">
                        Time: 
                        <input type="time" step="1" id="time1" value="${starttime}">
                        <input type="time" step="1" id="time2" value="${endtime}">
                        <input type="button" value="Enter" onclick="play();" />
                    </form>
                </table>

                <ul id="stats">
                    <li>${filename}</li>
                </ul>
            </div>
        </div>
    </body>
</html>
