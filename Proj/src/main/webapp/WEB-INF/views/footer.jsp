<%-- 
    Document   : footer
    Created on : Nov 30, 2015
    Author     : IS204-5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<footer>
    <c:set var="x" value="${requestScope['javax.servlet.forward.servlet_path']}"/> 
    <c:if test="${x == '/graph'}">
        <script>loadStats();</script>
        </c:endif>    
</footer>
