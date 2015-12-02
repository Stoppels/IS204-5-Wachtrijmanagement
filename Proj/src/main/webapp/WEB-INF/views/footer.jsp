<%-- 
    Document   : footer
    Created on : Nov 30, 2015
    Author     : IS204-5
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<footer>
    <c:set var="x" value="${requestScope['javax.servlet.forward.servlet_path']}"/> 
    <c:if test="${x == '/graph'}">
        <script>loadStats();</script>
    </c:if>
</footer>
