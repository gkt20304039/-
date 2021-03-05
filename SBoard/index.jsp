<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <link rel="icon" type="image/png" href="pic/fav.png">
            <title>４ちゃんねる</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="./CSS/stylesheet.css">
        </head>
        <body>
            <jsp:include page="header.jsp" flush="true" />
            <div class="haku"></div>
            <div class="top-h1">
                <img src="pic/ita3.png" alt="4ちゃんねる" title="4ちゃんねる">
                <h1 class="moji2">★ようこそ４ちゃんねるへ</h1>
            </div>
	        <!-- <h1>板一覧</h1>
	        <a href="/SBoard/addth?bdID=newspl">ニュース速報＋</a>
            <a href="/SBoard/addth?bdID=prog">プログラミング</a>
            <a href="/SBoard/addth?bdID=nanj">なんでも実況J（ジュピター）</a> -->
            
            <!--ランキングを表示-->
            <div class="box4">
                <h2>ランキング</h2>
                <c:forEach var="rank" items="${ranking}" varStatus="status">
                    ${status.count}位&ensp;<a href="/SBoard/addres?thID=${rank.id}&bdID=${rank.bdID}">${rank.name}(${rank.num})&ensp;更新日時：${rank.date}</a>
                    <br>
                </c:forEach>
            </div>
        </body>
    </html>