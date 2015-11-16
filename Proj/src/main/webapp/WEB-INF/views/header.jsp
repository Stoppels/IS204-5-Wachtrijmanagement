<%-- 
    Document   : header
    Created on : Nov 16, 2015, 4:34:28 PM
    Author     : chrisverra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header id="header">
    <ul id="nav">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li> 
        <li><a href="${pageContext.request.contextPath}/snapshot">Snapshot</a></li>
        <li><a href="${pageContext.request.contextPath}/heatmap">Heatmap</a></li>
        <li><a href="${pageContext.request.contextPath}/graph">Graph</a></li>
    </ul>
</header>