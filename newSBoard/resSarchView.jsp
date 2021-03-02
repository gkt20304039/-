<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>検索結果</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
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
                    <p><input type='sarch' name='resSarch'><input type="submit" value="レス検索"></p><br>
                </form>
            </div>
	
	<hr sizer=”5″>
	</center>
</header>
       	
       	<div class="haku"></div>
            
      
            <h1>検索結果</h1>
	        

            <c:if test="${not empty responses}">
	            <c:forEach var="res" items="${responses}">
                    <table>
                        <tr class="attr"><td>${res.num}&ensp;<span>${res.name}<span></td><td>&ensp;${res.date}</td></tr>
                        <tr><td colspan="2">${res.text}</td></tr>
                    </table>
	            </c:forEach>
            </c:if>
            <a href="javascript:history.back()">[前のページに戻る]</a>
        </body>
    </html>