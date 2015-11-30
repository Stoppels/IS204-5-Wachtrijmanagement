<%-- 
    Document   : footer
    Created on : Nov 30, 2015, 5:30:18 PM
    Author     : chrisverra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<footer>
    <c:set var="x" value="${requestScope['javax.servlet.forward.servlet_path']}"/> 


    <c:if test="${x == '/graph'}">
        <script>loadStats();</script>
    </c:endif>    

</footer>
