<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head><title>���X���e</title></head>
        <body>
            <h1>���X�ꗗ</h1>
	        <table border="1">
	        	<c:forEach var="user" items="${users}">
                    <tr><td>${user.num}�F</td><td>${user.name}</td><td>�����F${user.date}</td></tr>
                    <tr><td>${user.text}</td></tr>
	        	</c:forEach>
	        </table>
        </body>
    </html>