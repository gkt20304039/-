<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>レス投稿</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="./CSS/stylesheet.css">
        </head>
        <body>
            <h1>${thName}</h1>
	        
	        <c:forEach var="res" items="${responses}">
                <table>
                    <tr><td><p class="attr" id=">>${res.num}">${res.num}&ensp;<span>${res.name}</span>&ensp;${res.date}<p></td></tr>
                    <tr><td>${res.text}</td></tr>
                </table>
	        </c:forEach>
        </body>
    </html>