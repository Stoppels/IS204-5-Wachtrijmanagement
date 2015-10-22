<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <%@page contentType="text/html" pageEncoding="windows-1252"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <html>
        <head>
            <meta charset="utf-8">
            <!--  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
            <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
            <title>EWA-5 | Wachtrijmanagement</title>
            <meta name="description" content="">
            <meta name="author" content="Chris Verra">
            <meta name="viewport" content="width=device-width, initial-scale=1">

            <link href="<c:url value="/resources/css/base.css" />" rel="stylesheet" >
            <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" >
            <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" >
            <link href="<c:url value="/resources/css/normalize.css" />" rel="stylesheet" >
            <link href="<c:url value="/resources/css/skeleton.css" />" rel="stylesheet" >

        </head>
        <body>
        <div id="map-container">
            <div id="map-primary">
                
                <c:forEach var="PersonObject" items="${personObjects}">
                        
                        
                </c:forEach>
                
                <canvas id="myCanvas">
                    <script src="<c:url value="${javascript}" />" type="text/javascript"></script>
                </canvas>
                
            </div>
            <div id="map-secondary">
                    <div id="map-form">
                        <form action="Untitled.html" method="" accept-charset="utf-8">
                            <textarea></textarea>
                        </form>
                    </div>
            </div>
        </div>
</body>
</html>

