<%-- 
    Document   : graph
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
            <div id="sidebar" class="graph">
                <table id="form">
                    <input type="time" step="1" id="time1" value="${starttime}" />
                    <input type="time" step="1" id="time2" value="${endtime}" />
                    <input class="enter" type="button" value="Update graph" onclick="play(
                                    document.getElementById('time1').value,
                                    document.getElementById('time2').value);"  />
                    <p>
                        <label>Statistic:</label> 
                        <select name="dropDown" id="dropDown" onChange="play();" />
                        <input class="enter" type="button" value="Clear lines" onclick="clearSessions();" />
                    </p>
                </table>
            </div>
        </div>
        <div id="map" class="graphmap">                
            <canvas id="myCanvas">
                <script src="<c:url value="${Controller}" />" type="text/javascript"></script>
                <script src="<c:url value="${ChartBar}" />" type="text/javascript"></script>
                <script src="<c:url value="${ChartCore}" />" type="text/javascript"></script>
                <script src="<c:url value="${Chart}" />" type="text/javascript"></script>
                <script src="<c:url value="${Graph}" />" type="text/javascript"></script>
            </canvas>
        </div>
        <c:forEach var="stat" items="${stats}">
            <script>
                            importStats(${stat.getStatId()},
                ${stat.getStatName()},
                ${stat.labelArray()},
                ${stat.dataArray()});
            </script>
        </c:forEach>
        <jsp:include page="footer.jsp" />
    </body>
</html>
