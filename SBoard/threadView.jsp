<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
    <head>
        <link rel="icon" type="image/png" href="pic/fav.png">
        <title>�S�����˂�-�X���b�h�ꗗ</title>
        <!--CSS-->
	    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	    <link rel="stylesheet" href="./CSS/stylesheet.css">
	    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="haku"></div>
        
        <!--�����L���O��\��-->
        <div class="box4">
            <h2>�����L���O</h2>
            <p><c:forEach var="rank" items="${ranking}" varStatus="status">
                ${status.count}��&ensp;<a href="/SBoard/addres?thID=${rank.id}&bdID=${rank.bdID}">${rank.name}(${rank.num})&ensp;�X�V�����F${rank.date}</a>
            <br>
            </c:forEach></p>
        </div>
        
        <div class=box3>
            <h2>�X���b�h�ꗗ</h2>
            <br>
            
            
	        <c:forEach var="thread" items="${threads}">
                <div class=kara>
                    <a href="/SBoard/addres?thID=${thread.id}&bdID=${thread.bdID}">${thread.name}&ensp;${thread.date}</a>
                </div><br>
            </c:forEach>
            <hr size="10">
        
            <h1>�X���b�h���쐬����</h1>
            <form method="post" action="addth">
                <div class="migi1">
                    <input type="text" name="thName" size="32" maxlength="32" placeholder="�X���b�h�^�C�g��">
                </div><br>
                <div class="migi2">
                    <input type="text" name="resName" size="16" maxlength="16" placeholder="���O(�ȗ���)">
                </div><br>
                <textarea type="text" name="resText" placeholder="�R�����g���e" required></textarea><br>
                <div class="migi">
                    <input type="submit" value="�쐬">
                </div>
            </form>
            <p id="page-top"></p>
            <p id="shita"></p>
        </div>
    </body>
</html>