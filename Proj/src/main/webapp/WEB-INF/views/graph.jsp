<%-- 
    Document   : graph
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

            <div id="sidebar">

                <!--BUGGY AS SHIT ###########################################--><!-- remove -->
                <table id="form">                                               <!-- remove -->
                        <span id="timeElements">                                <!-- remove -->
                        </span>                                                 <!-- remove -->
                    <form name="form">                                          <!-- remove -->
                    </form>                                                     <!-- remove -->
                <!--REMOVE THIS CRAP ########################################--><!-- remove -->    
                    
                    
                    
                    <input type="time" step="1" id="time1" value="${starttime}">
                    <input type="time" step="1" id="time2" value="${endtime}">
                    <input type="button" value="Enter">
                    <body onload="loadStats();">
                        <br><br>
                        Statistic: 
                        <br>
                        <select name="dropDown" id="dropDown" onChange="play();" />
                        <br>
                        <input class="enter" type="button" value="Clear" onclick="clearLines();" />
                </table>

                <div id="stats">
                    <p>${filename}</p>
                </div>
            </div>
        </div>
    </body>
</html>
