<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
    <head>
        <link rel="icon" type="image/png" href="pic/fav.png">
        <title>４ちゃんねる-スレッド一覧</title>
        <!--CSS-->
	    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	    <link rel="stylesheet" href="./CSS/stylesheet.css">
	    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="haku"></div>
        
        <!--ランキングを表示-->
        <div class="box4">
            <h2>ランキング</h2>
            <p><c:forEach var="rank" items="${ranking}" varStatus="status">
                ${status.count}位&ensp;<a href="/SBoard/addres?thID=${rank.id}&bdID=${rank.bdID}">${rank.name}(${rank.num})&ensp;更新日時：${rank.date}</a>
            <br>
            </c:forEach></p>
        </div>
        
        <div class=box3>
            <h2>スレッド一覧</h2>
            <br>
            
            
	        <c:forEach var="thread" items="${threads}">
                <div class=kara>
                    <a href="/SBoard/addres?thID=${thread.id}&bdID=${thread.bdID}">${thread.name}&ensp;${thread.date}</a>
                </div><br>
            </c:forEach>
            <hr size="10">
        
            <h1>スレッドを作成する</h1>
            <form method="post" action="addth">
                <div class="migi1">
                    <input type="text" name="thName" size="32" maxlength="32" placeholder="スレッドタイトル">
                </div><br>
                <div class="migi2">
                    <input type="text" name="resName" size="16" maxlength="16" placeholder="名前(省略可)">
                </div><br>
                <textarea type="text" name="resText" placeholder="コメント内容" required></textarea><br>
                <div class="migi">
                    <input type="submit" value="作成">
                </div>
            </form>
            <p id="page-top"></p>
            <p id="shita"></p>
        </div>
    </body>
</html>