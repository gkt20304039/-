<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>検索結果</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
            <div class=sarch>
                <form method='post' action='sarch'>
                    <p><input type='sarch' name='thSarch'><input type="submit" value="検索"></p><br>
                </form>
            </div>

            <h1>検索結果</h1>
	        <c:if test="${not empty threads}">
	            <c:forEach var="thread" items="${threads}">
                    <a href="/SBoard/addres?thID=${thread.id}">${thread.name}&ensp;${thread.date}</a><br>
                </c:forEach>
            </c:if>

            <c:if test="${not empty responses}">
	            <c:forEach var="res" items="${responses}">
                    <table>
                        <tr class="attr"><td>${res.num}&ensp;<span>${res.name}<span></td><td>&ensp;${res.date}</td></tr>
                        <tr><td colspan="2">${res.text}</td></tr>
                    </table>
	            </c:forEach>
            </c:if>
        </body>
    </html>