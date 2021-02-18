<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>掲示板index</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
            <h1>ようこそ５ちゃんねる（仮）へ</h1>
	        <h1>板一覧</h1>
	        <a href="/SBoard/addth?bdID=news+">ニュース速報＋</a>
            <a href="/SBoard/addth?bdID=prog">プログラミング</a>
            <a href="/SBoard/addth?bdID=nanj">なんでも実況J（ジュピター）</a>
            
        </body>
    </html>