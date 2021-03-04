<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
    
        <head>
        	<link rel="icon" type="image/png" href="pic/fav.png">
            <title>スレッド一覧</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
	        	         <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

    <script>
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
    $("a[href^=#page-bottom]").click(function(){
        $('html, body').animate({
          scrollTop: $(document).height()
        },1500);
        return false;
    });
});
</script>
</head>
<body>
	<header class="sample">
	<img src="pic/ita2.png" alt="4ちゃんねる" title="4ちゃんねる" class="example1">
		<p class="down"><a href="#shita">↓</a></p>
			<div class="waku">
				<a href= "http://localhost:8080/SBoard/toppage"class="sticky">トップページ</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=newspl"class="sticky">ニュース速報+</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=prog"class="sticky">プログラミング</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=nanj" class="sticky">なんでも実況J(ジュピター)</a>
			</div>
			 <div class=sarch>
				<form method='post' action='sarch' class="search_container">
                    <p><input type='text' name='thSarch'size="25" placeholder="キーワード検索"><input type="submit"></p><br>
                </form>
            </div>
           
	
	
	</header>
	<div class="haku"></div>
            
      <div class="box3">
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
                        <tr class="attr2"><td colspan="2">${res.text}</td></tr>
                    </table>
	            </c:forEach>
            </c:if>
            <div class=ma><a href="javascript:history.back()">[前のページに戻る]</a></div>
            </div>
        </body>
    </html>