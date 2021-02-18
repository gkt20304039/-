<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>���X���e</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
            <h1>�X���b�h��</h1>
	        
	        <c:forEach var="user" items="${users}">
                <table>
                    <tr class="attr"><td>${user.num}&ensp;<span>${user.name}<span></td><td>&ensp;${user.date}</td></tr>
                    <tr><td colspan="2">${user.text}</td></tr>
                </table>
	        </c:forEach>
        </body>
    </html>