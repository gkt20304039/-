<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>�f����index</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
            <h1>�悤�����T�����˂�i���j��</h1>
	        <h1>�ꗗ</h1>
	        <a href="/SBoard/addth?bdID=newspl">�j���[�X����{</a>
            <a href="/SBoard/addth?bdID=prog">�v���O���~���O</a>
            <a href="/SBoard/addth?bdID=nanj">�Ȃ�ł�����J�i�W���s�^�[�j</a>
            
            <!--�����L���O��\��-->
            <table border = "1">
            <h2>�����L���O</h2>
            <c:forEach var="rank" items="${ranking}" varStatus="status">
                <tr><td>${status.count}��</td><td>${rank.name}</td><td>�����F${rank.date}</td></tr>
            </c:forEach>
            </table>
        </body>
    </html>
