<%-- 
    Document   : index
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
                <div id="stats">
                    <p>${filename}</p>
                </div>
            </div>
            <div id="map">
                <canvas id="myCanvas">
                        <script src="<c:url value="${Canvas}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Home}" />" type="text/javascript"></script>
               </canvas>
            </div>
            
        </div>
    </body>
</html>
