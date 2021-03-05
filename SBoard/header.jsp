<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="icon" type="image/png" href="./pic/kanban2.png">
            <title>レス投稿</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
	        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	    <script>
	        $(window).scroll(function () {
                sessionStorage.scrollTop = $(this).scrollTop();
            });
            $(document).ready(function () {
                if (sessionStorage.scrollTop != "undefined") {
                    $(window).scrollTop(sessionStorage.scrollTop);
                }
            });
        
            $(function() {
                var topBtn = $('#page-top');    
                topBtn.hide();
                //スクロールが100に達したらボタン表示
                $(window).scroll(function () {
                    if ($(this).scrollTop() > 50) {
                        topBtn.fadeIn();
                    } else {
                        topBtn.fadeOut();
                    }
                });
                //スクロールしてトップ
                topBtn.click(function () {
                    $('body,html').animate({
                        scrollTop: 0
                    }, 500);
                    return false;
                });
            });
            $(function(){
                $("a[href^=#shita]").click(function(){
                    $('html, body').animate({
                      scrollTop: $(document).height()
                    },500);
                    return false;
                });
            });
        </script>
    </head>
    <body>
        <header class="sample">
            <a href= "/SBoard/toppage" class="example1"><img src="./pic/ita2.png" alt="4ちゃんねる" title="4ちゃんねる"></a>
            <div class="waku">
                    <a href= "/SBoard/toppage" class="sticky">トップページ</a>
                    <a href= "/SBoard/addth?bdID=newspl" class="sticky">ニュース速報+</a>
                    <a href= "/SBoard/addth?bdID=prog" class="sticky">プログラミング</a>
                    <a href= "/SBoard/addth?bdID=nanj" class="sticky">なんでも実況J(ジュピター)</a>	
            </div>
                <%-- 閲覧しているページによってスレッド検索欄とレス検索欄の表示を分ける --%>
                <c:if test="${thisPage == 'response' or not empty responses}">
                    <div class=sarch>
                        <form method="post" action="sarch" class="search_container">
                            <input type="sarch" name="resSarch" placeholder="キーワード検索"><input type="submit" value="検索">
                        </form>
                    </div>
                </c:if>

                <c:if test="${thisPage == 'thread' or not empty threads}">
                    <div class="sarch">
                        <form method="post" action="sarch" class="search_container">
                            <input type="sarch" name="thSarch" placeholder="キーワード検索"><input type="submit" value="検索">
                        </form>
                    </div>
                </c:if>
                <c:if test="${thisPage != 'top'}">
                    <p id="page-top"><a href="#wrap">↑TOP</a></p>
		            <p class="down"><a href="#shita">↓投稿</a></p>
                </c:if>
            <!-- <hr sizer=”5″> -->    
        </header>
    </body>
</html>