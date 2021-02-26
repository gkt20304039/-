<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>レス投稿</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
        <form method='post' action='rsarch'>
	<p><input type='rsarch' name='rsarch'><input type="submit" value="検索"></p><br>
	</form>
            
            <h1>レスを投稿する</h1>
            <form method='post' action='addres'>
            <input type='text' name='resName' size='16'maxlength='16' placeholder='名前(省略可)'><br>
            <textarea type='text' name='resText' placeholder='コメント内容' required></textarea><br>
            <input type='submit' value='書き込む'>
            </form>
            
             <c:forEach var="user" items="${users}">
                <table>
                    <tr class="attr"><td>${user.num}&ensp;<span>${user.name}<span></td><td>&ensp;${user.date}</td></tr>
                    <tr><td colspan="2">${user.text}</td></tr>
                </table>
	        </c:forEach>
        </body>
    </html>