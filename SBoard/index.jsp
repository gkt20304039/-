<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <link rel="icon" type="image/png" href="pic/fav.png">
            <title>�S�����˂�</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="./CSS/stylesheet.css">
        </head>
        <body>
            <jsp:include page="header.jsp" flush="true" />
            <div class="haku"></div>
            <div class="top-h1">
                <img src="pic/ita3.png" alt="4�����˂�" title="4�����˂�">
                <h1 class="moji2">���悤�����S�����˂��</h1>
            </div>
	        <!-- <h1>�ꗗ</h1>
	        <a href="/SBoard/addth?bdID=newspl">�j���[�X����{</a>
            <a href="/SBoard/addth?bdID=prog">�v���O���~���O</a>
            <a href="/SBoard/addth?bdID=nanj">�Ȃ�ł�����J�i�W���s�^�[�j</a> -->
            
            <!--�����L���O��\��-->
            <div class="box4">
                <h2>�����L���O</h2>
                <c:forEach var="rank" items="${ranking}" varStatus="status">
                    ${status.count}��&ensp;<a href="/SBoard/addres?thID=${rank.id}&bdID=${rank.bdID}">${rank.name}(${rank.num})&ensp;�X�V�����F${rank.date}</a>
                    <br>
                </c:forEach>
            </div>
        </body>
    </html>