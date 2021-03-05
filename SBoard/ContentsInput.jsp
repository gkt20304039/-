<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
    <head>		        
    <title>4ちゃんねる-レス一覧</title>
    <link rel="icon" type="image/png" href="pic/fav.png">
    <!--CSS-->
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
    <link rel="stylesheet" href="./CSS/stylesheet.css">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="haku"></div>
        <div class=box3>
            <jsp:include page="ContentsList.jsp" flush="true" />
            <hr size="10">
            <h1>レスを投稿する</h1>
            
            <form method="post" action="addres">
                <div class="migi1">
                    <input type="text" name="resName" size="16" maxlength="16" placeholder="名前(省略可)">
                </div><br>
                <textarea type="text"  name="resText" placeholder="コメント内容" required wrap="hard"></textarea><br>
                <div class="migi">
                    <input type="submit" value="投稿">
                </div>
            </form>
        </div>
        <p id="shita"></p>
        <footer></fotter>
    </body>
</html>
