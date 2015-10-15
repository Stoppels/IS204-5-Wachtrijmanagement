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
            <!--[if lt IE 7]>
                <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
            <![endif]-->
<!--            <div id="header" class="parentContainer container">
                <div class="header-container">
                    <div class="content-logo">

                        <a href="${pageContext.request.contextPath}/"><div class="company-name">EWA-5 | Wachtrijmanagement</div></a>
                    </div>
                    <div class="block-menu">
                        <select class="navegation_resp" onchange="location.hash = this.options[this.selectedIndex].value;">
                            <a href="${pageContext.request.contextPath}/">home</a>
                            <option value="#over" class="second_item">Over</option>
                            <option value="#grafiek" class="second_item">Grafieken</option>

                        </select>
                    </div>
                    <div class="menu-select">
                        <nav>
                            <ol class="contentMenu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/"><div class="menubtn homebtn">
                                            <div class="contentbtn">home</div>
                                            <div class="contentbtn2">home</div>
                                        </div></a>
                                </li>
                                <li>
                                    <a href="#over"><div class="menubtn">
                                            <div class="contentbtn">over</div>
                                            <div class="contentbtn2">over</div>
                                        </div></a>
                                </li>
                                <li>
                                    <a href="#grafiek"><div class="menubtn">
                                            <div class="contentbtn">grafiek</div>
                                            <div class="contentbtn2">grafiek</div>
                                        </div></a>
                                </li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>  end header-->
            
        <canvas id="myCanvas">
            <script src="<c:url value="/resources/js/canvas.js" />" type="text/javascript"></script>
        </canvas>

    </div>
</body>
</html>

