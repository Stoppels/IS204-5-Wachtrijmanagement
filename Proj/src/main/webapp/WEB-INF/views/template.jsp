<%-- 
    Document   : template
    Created on : Nov 10, 2015, 7:01:39 PM
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
        <div id="container">

            <div id="map">
                
                <%--canvas has border for temporary testing--%>
<<<<<<< HEAD
                <canvas id="myCanvas"
                                style="border:1px solid #000"
                                >
                    </canvas>
=======
                <center><canvas id="myCanvas">
                    </canvas></center>
>>>>>>> cfe8fc85ae2c7ad41c9a835605c61eee974985ee
            </div>

            <div id="sidebar">
                <ul id="nav">
                    <li><a href="${pageContext.request.contextPath}/">Home</a></li><br>
                    <li><a href="${pageContext.request.contextPath}/snapshot">Snapshot</a></li>
                    <li><a href="${pageContext.request.contextPath}/heatmap">Heatmap</a></li>
                    <li><a href="${pageContext.request.contextPath}/graph">Graph</a></li>
                </ul>

                <table id="form">
                </table>

                <ul id="stats">
                </ul>
                <div id="footer">
                    <p>Footer (needs to be at bottom of sidebar)</p>
                </div>
            </div>
        </div>
    </body>
</html>
