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
	
		
                   
					
             <div class="box4">
                         <h2>ランキング</h2>
                            
                                <p><c:forEach var="rank" items="${ranking}" varStatus="status">
                                
                    ${status.count}位&ensp;<a href="/SBoard/addres?thID=${rank.id}">${rank.name}(${rank.num})&ensp;更新日時：${rank.date}</a>
                                        <br>
                                    
                                </c:forEach></p>
                            </div>
                        
                    
            
            </div>
            <div class=box3>
            <h2>　スレッド一覧</h2>
            <br>
	        
	        <c:forEach var="thread" items="${threads}">
                <div class=kara><a href="/SBoard/addres?thID=${thread.id}">${thread.name}&ensp;${thread.date}</a></div><br>
            </c:forEach>

           <hr size="10">
            
                   
            
            <h1>スレッドを作成する</h1>
            <form method='post' action='addth'>
           		<div class="migi1"> <input type='text' name='resName' size='16'maxlength='16' placeholder='名前(省略可)'></div><br>
           		<div class="migi2"><input type='text' name='thName' size='32'maxlength='32' placeholder='スレッドタイトル'></div><br>
            	<textarea type='text' name='resText' placeholder='コメント内容' required></textarea><br>
            	<div class="migi"><input type='submit' value='　作成　'><div>
            
            </form>
            
            
            <p id="page-top"><a href="#wrap">↑</a></p>
            <p id="shita"></p>
			</div>
		</div>
            
	</div>
	</body>
</html>