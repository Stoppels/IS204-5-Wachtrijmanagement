<%-- 
    Document   : header
    Created on : Nov 16, 2015
    Author     : IS204-5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header id="header">
    <ul id="nav">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li> 
        <li><a href="${pageContext.request.contextPath}/dotmap">Dotmap</a></li>
        <li><a href="${pageContext.request.contextPath}/heatmap">Heatmap</a></li>
        <li><a href="${pageContext.request.contextPath}/graph">Graph</a></li>
    </ul>
</header>