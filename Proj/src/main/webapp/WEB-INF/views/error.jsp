<%-- 
    Document   : error
    Created on : Nov 18, 2015
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
            <div id="map">
                <canvas id="myCanvas">
                    <script src="<c:url value="${Controller}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Canvas}" />" type="text/javascript"></script>
                    <script src="<c:url value="${Error}" />" type="text/javascript"></script>
                </canvas>
            </div>

        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
