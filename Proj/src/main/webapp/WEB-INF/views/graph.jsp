<%-- 
    Document   : graph
    Created on : Nov 2, 2015, 7:01:39 PM
    Author     : Stefan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="template.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project</title>
    </head>
    <body>
        <div id="container">
            <div id="map">                
                <center><canvas id="myCanvas">
                        <script src="<c:url value="${Controller}" />" type="text/javascript"></script>
                        <script src="<c:url value="${ChartBar}" />" type="text/javascript"></script>
                        <script src="<c:url value="${ChartCore}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Chart}" />" type="text/javascript"></script>
                        <script src="<c:url value="${Graph}" />" type="text/javascript"></script>
                </center></canvas>
            </div>

            <c:forEach var="stat" items="${stats}">
                <script>
                    createStats(${stat.getStatId()},
                    ${stat.labelArray()},
                    ${stat.dataArray()});
                </script>
            </c:forEach>

            <div id="sidebar">
                <ul id="nav">
                    
                </ul>
                <table id="form">
                    <form name="form">
                        Time: 
                        <input type="time" step="1" id="time1" value="${starttime}">
                        <input type="time" step="1" id="time2" value="${endtime}">
                        <input type="button" value="Enter" onclick="play(
                                        document.getElementById('time1').value,
                                        document.getElementById('time2').value);
                                return false;">
                    </form>
                </table>
                        
                <ul id="stats">
                    <li>${filename}</li>
                </ul>
            </div>
        </div>
    </body>
</html>
