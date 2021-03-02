<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
    
        <head>
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
	
	<center><h1>5ちゃんねる</h1>
	
	<a href= "http://localhost:8080/SBoard/toppage" >トップページ</a>
	<a href= "http://localhost:8080/SBoard/addth?bdID=newspl" >ニュース速報+</a>
	<a href= "http://localhost:8080/SBoard/addth?bdID=prog" >プログラミング</a>
	<a href= "http://localhost:8080/SBoard/addth?bdID=nanj" >なんでも実況J(ジュピター)</a>
	
	
	<br>
        
            <div class=sarch>
                <form method='post' action='sarch'>
                    <p><input type='sarch' name='thSarch'><input type="submit" value="スレッド検索"></p><br>
                </form>
            </div>
            <hr sizer=”5″>
      </header>
      <div class="haku">
            <h1>スレッド一覧</h1>
	        
	        <c:forEach var="thread" items="${threads}">
                <a href="/SBoard/addres?thID=${thread.id}">${thread.name}&ensp;${thread.date}</a><br>
            </c:forEach>

            <!--ランキングを表示-->
            <div class="ranking">
                <h2>ランキング</h2>
                <c:forEach var="rank" items="${ranking}" varStatus="status">
                    ${status.count}位&ensp;<a href="/SBoard/addres?thID=${rank.id}">${rank.name}(${rank.num})&ensp;更新日時：${rank.date}</a>
                <br>
                </c:forEach>
            </div>
            
            <h1>スレッドを作成する</h1>
            <form method='post' action='addth'>
            <input type='text' name='thName' size='32'maxlength='32' placeholder='スレッドタイトル'><br>
            <input type='text' name='resName' size='16'maxlength='16' placeholder='名前(省略可)'><br>
            <textarea type='text' name='resText' placeholder='コメント内容' required></textarea><br>
            <input type='submit' value='作成'>
            </form>
            <p id="page-top"><a href="#wrap">PAGE TOP</a></p>
        </body>
    </html>